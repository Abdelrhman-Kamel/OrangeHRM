import POM.Admin;
import POM.Dashboard;
import POM.LoginPage;
import POM.PIM;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.JsonFileManager;

import java.time.Duration;
import java.util.Random;

public class AdminFirstScenario {
    JsonFileManager jsonFileManager = new JsonFileManager("src/main/resources/DataDriving/Valid_Credentials.json");
    LoginPage loginPage;
    Dashboard dashboard;
    PIM pim;
    Admin admin;
    WebDriver driver;
    ConfigLoader configLoader = new ConfigLoader("src/main/resources/DataDriving/Config.properties");


    @BeforeTest
    public void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-password-generation");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(configLoader.getValue("URL"));
        loginPage = new LoginPage(driver);
        dashboard = new Dashboard(driver);
        pim = new PIM(driver);
        admin = new Admin(driver);
        loginPage.login(jsonFileManager.getValue("username"), jsonFileManager.getValue("password"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getDashboard()));
        Assert.assertEquals(driver.findElement(loginPage.getDashboard()).getText(), "Dashboard");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testWholeScenario() {
        Random rand = new Random();
        String uniqueUsername = "testuser" + rand.nextInt(1000);
        dashboard.clickPIMCategory();
        pim.addEmployee();
        pim.enterEmployeeFullName("A", "M", "K");
        pim.enterID("702030");
        pim.toggleCreateLoginDetailsButton();
        pim.enterEnployeeCredentials(uniqueUsername, "leomessi22", "leomessi22");
        pim.enableStatus();
        pim.pressSave();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pim.successToast));
        Assert.assertTrue(driver.findElement(admin.successToast).isDisplayed());
        pim.clickAdminCategory();
        admin.clickUserManagement();
        admin.clickUsers();
        admin.searchByUsername(uniqueUsername);
        admin.clickEdit();
        admin.editUserRoleToAdmin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(admin.successToast));
        Assert.assertTrue(driver.findElement(admin.successToast).isDisplayed());
        dashboard.logout();
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginHeader));
        Assert.assertEquals(driver.findElement(loginPage.loginHeader).getText(), "Login", "Logout failed: Login page not displayed");
        loginPage.login(uniqueUsername, "leomessi22");
        WebElement dashboardText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getDashboard()));
        Assert.assertEquals(driver.findElement(loginPage.getDashboard()).getText(), "Dashboard");

    }

}
