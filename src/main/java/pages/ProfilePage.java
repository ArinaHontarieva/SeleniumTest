package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ProfilePage extends BasePage{

    @FindBy(css = "button.delete-account-btn")
    private WebElement deleteAccountButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void clickDeleteButton() {
        click(deleteAccountButton);
    }
}
