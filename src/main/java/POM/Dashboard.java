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
    private final WebDriverWait wait;

    public Dashboard(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickPIMCategory() {
        WebElement pressPIM = wait.until(ExpectedConditions.elementToBeClickable(PIM));
        pressPIM.click();
    }

    public void logout() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(profileDropdown));
        dropdown.click();

        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logout.click();
    }
}
