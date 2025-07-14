package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RegisterPage extends BasePage {

    @FindBy(id = "fname")
    private WebElement nameField;

    @FindBy(id = "lname")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "birthDate")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//button[@class='btn' and @type='submit']")
    private WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillName(String name) {
        sendKeys(nameField, name);
    }

    public void fillLastName(String lastName) {
        sendKeys(lastNameField, lastName);
    }

    public void fillEmail(String email) {
        sendKeys(emailField, email);
    }

    public void fillPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void fillConfirmPassword(String confirmPassword) {
        sendKeys(confirmPasswordField, confirmPassword);
    }

    public void fillBirthDate(String birthDate) {
        sendKeys(dateOfBirthField, birthDate);
    }

    public void clickRegisterButton() {
        click(registerButton);
    }
}
