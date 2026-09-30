package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIM {
    public final By successToast = By.cssSelector("div.oxd-toast.oxd-toast--success");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By firstNameField = By.name("firstName");
    private final By middleNameField = By.name("middleName");
    private final By lastNameField = By.name("lastName");
    private final By employeeIdField = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");
    private final By createLoginToggle = By.xpath("//span[contains(@class,'oxd-switch-input')]");
    private final By usernameField = By.xpath("//label[text()='Username']/../following-sibling::div/input");
    private final By passwordField = By.xpath("//label[text()='Password']/../following-sibling::div/input");
    private final By confirmPasswordField = By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input");
    private final By statusEnabledOption = By.xpath("//label[normalize-space()='Enabled']");
    private final By statusDisabledOption = By.xpath("//label[normalize-space()='Disabled']");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");
    private final By admin = By.xpath("//span[normalize-space()='Admin']");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public PIM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addEmployee() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addBtn.click();
    }

    public void enterEmployeeFullName(String firstName, String middleName, String lastName) {
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        firstNameInput.sendKeys(firstName);
        driver.findElement(middleNameField).sendKeys(middleName);
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void toggleCreateLoginDetailsButton() {
        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(createLoginToggle));
        toggle.click();
    }

    public void enterID(String ID) {
        WebElement idField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdField));
        idField.sendKeys(ID);
    }

    public void enterEnployeeCredentials(String username, String password, String confirmPassword) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameInput.sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void enableStatus() {
        WebElement enabled = wait.until(ExpectedConditions.elementToBeClickable(statusEnabledOption));
        enabled.click();
    }

    public void disableStatus() {
        WebElement disabled = wait.until(ExpectedConditions.elementToBeClickable(statusDisabledOption));
        disabled.click();
    }

    public void pressSave() {
