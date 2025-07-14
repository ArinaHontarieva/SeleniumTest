package tests;

import helpers.PropertiesHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.time.Duration;

public abstract class BaseTest {
    private static final String baseUrl = new PropertiesHelper().getBaseUrl();
    private WebDriver driver;

    @BeforeMethod
    public void navigateToBrowserClient() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected boolean isHeaderPresent(String tag, String text) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//" + tag + "[text()='" + text + "']")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void openWelcomePageAndClickLogin() {
        WelcomePage welcomePage = new WelcomePage(getDriver());
        welcomePage.clickLoginButton();
    }

    protected void redirectToRegisterPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickRedirectToRegistrationButton();
    }

    protected void fillRegistrationFormAndSubmit() {
        RegisterPage registerPage = new RegisterPage(getDriver());

        registerPage.fillName("Test");
        registerPage.fillLastName("Testov");
        registerPage.fillEmail("test_testov@gmail.com");
        registerPage.fillPassword("Password123");
        registerPage.fillConfirmPassword("Password123");
        registerPage.fillBirthDate("01012000");

        registerPage.clickRegisterButton();
    }

    protected void goToProfile() {
        MainWishlistPage mainWishlistPage = new MainWishlistPage(getDriver());
        mainWishlistPage.clickProfileIcon();
    }

    protected void deleteUserAccount() {
        ProfilePage profilePage = new ProfilePage(getDriver());
        profilePage.clickDeleteButton();
    }

    protected void openCreateWishlistModal() {
        MainWishlistPage mainPage = new MainWishlistPage(getDriver());
        mainPage.clickCreateWishlistButton();
    }

    protected void fillCreateWishlistForm(String wishlistName) {
        CreateWishlistWindow window = new CreateWishlistWindow(getDriver());

        window.fillTitle(wishlistName);
        window.fillDate("01012055");
        window.fillDescription("This is a test wishlist");

        window.clickCreate();
    }

    protected boolean isWishlistWithTitlePresent(String title) {
        MainWishlistPage mainPage = new MainWishlistPage(getDriver());
        return mainPage.isWishlistWithTitlePresent(title);
    }

    protected void openWishlistByTitle(String title) {
        MainWishlistPage mainPage = new MainWishlistPage(getDriver());
        mainPage.clickWishlistByTitle(title);
    }

    protected void assertWishlistTitleEquals(String expectedTitle, SoftAssert softAssert) {
        WishlistDetailsPage detailsPage = new WishlistDetailsPage(getDriver());
        softAssert.assertEquals(detailsPage.getWishlistTitle(), expectedTitle,
            "Wishlist title should match the expected value");
    }

    protected void openCreateWishModal() {
        WishlistDetailsPage detailsPage = new WishlistDetailsPage(getDriver());
        detailsPage.openCreateWishModal();
    }

    protected void createWish(String title) {
        CreateWishWindow wishWindow = new CreateWishWindow(getDriver());
        wishWindow.fillTitle(title);
        wishWindow.clickSave();
    }
}