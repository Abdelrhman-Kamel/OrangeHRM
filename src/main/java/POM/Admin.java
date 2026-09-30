package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Admin {

    public final By successToast = By.cssSelector("div.oxd-toast.oxd-toast--success");
    private final By userManagement = By.xpath("//span[normalize-space()='User Management']");
    private final By users = By.xpath("//a[normalize-space()='Users']");
    private final By usernameField = By.xpath("//label[text()='Username']/following::input[1]");
    private final By employeeNameField = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");
    private final By firstRowEditButton = By.xpath("(//i[@class='oxd-icon bi-pencil-fill'])[1]");
    private final By userRoleDropdown = By.xpath("//label[text()='User Role']/following::div[@class='oxd-select-wrapper'][1]");
    private final By userRoleAdminOption = By.xpath("//div[@role='listbox']//span[text()='Admin']");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Admin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickUserManagement() {
        WebElement pressUserManagement = wait.until(ExpectedConditions.elementToBeClickable(userManagement));
        pressUserManagement.click();
    }

    public void clickUsers() {
        WebElement pressUsers = wait.until(ExpectedConditions.elementToBeClickable(users));
        pressUsers.click();
    }

    public void searchByUsername(String username) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userField.clear();
        userField.sendKeys(username);

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchBtn.click();
    }

    public void searchByEmployeeName(String employeeName) {
        WebElement empField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameField));
        empField.clear();
        empField.sendKeys(employeeName);

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchBtn.click();
    }

    public void clickEdit() {
        WebElement editBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowEditButton));
        editBtn.click();
    }

    public void editUserRoleToAdmin() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown));
        dropdown.click();

        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(userRoleAdminOption));
        adminOption.click();

        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
    }
}
