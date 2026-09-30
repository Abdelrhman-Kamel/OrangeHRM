import POM.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ExcelFileManager;
import utils.JsonFileManager;

import java.time.Duration;


public class LoginPageTest {
    ExcelFileManager excelFileManager = new ExcelFileManager("src/main/resources/DataDriving/Invalid_Credentials.xlsx");
    JsonFileManager jsonFileManager = new JsonFileManager("src/main/resources/DataDriving/Valid_Credentials.json");
    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(configLoader.getValue("URL"));
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void tearDownTest() {
        driver.get(configLoader.getValue("URL"));
    }

    @DataProvider(name = "Invalid Credentials")
    public Object[][] getData() {
        return excelFileManager.convertToDataProvider();
    }

    @Test(priority = 2)
    public void verifyLoginOperation() {
        loginPage.login(jsonFileManager.getValue("username"), jsonFileManager.getValue("password"));
        WebElement dashboardText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getDashboard()));
        Assert.assertEquals(dashboardText.getText(), "Dashboard");
    }

    @Test(priority = 1, dataProvider = "Invalid Credentials")
    public void loginWithWrongCredentials(String user, String pass) {
        loginPage.login(user, pass);
        WebElement errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getErrorMessage()));
        Assert.assertTrue(errorText.getText().contains("Invalid credentials"));
    }

    @Test(priority = 0)
    public void VerifyEmptyFieldsLogin() {
        loginPage.clickLoginButton();
        WebElement userRequiredText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getUsernameRequired()));
        WebElement passwordRequiredText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getPasswordRequired()));
        Assert.assertTrue(userRequiredText.getText().contains("Required"));
        Assert.assertTrue(passwordRequiredText.getText().contains("Required"));
    }
}
