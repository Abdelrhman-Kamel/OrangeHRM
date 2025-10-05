package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Dashboard {

    private final By PIM = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']//span[text()='PIM']");
    private final By profileDropdown = By.cssSelector("span.oxd-userdropdown-tab");
    private final By logoutButton = By.xpath("//a[text()='Logout']");
    private final WebDriver driver;

    public Dashboard(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPIMCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pressPIM = wait.until(ExpectedConditions.elementToBeClickable(PIM));
        pressPIM.click();
    }

    public void logout() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(profileDropdown));
        dropdown.click();

        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logout.click();
    }


}
