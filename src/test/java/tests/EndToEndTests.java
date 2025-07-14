package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EndToEndTests extends BaseTest {

    @Test
    public void testUserFlowRegisterAndCreateWishListSuccessfully(){
        SoftAssert softAssert = new SoftAssert();

        openWelcomePageAndClickLogin();
        softAssert.assertTrue(isHeaderPresent("h1", "Ласкаво просимо до Wantora!"),
            "Login page header is not displayed as expected");

        redirectToRegisterPage();
        softAssert.assertTrue(isHeaderPresent("h1","Реєстрація в Wantora"),
            "Register page header is not displayed as expected");

        fillRegistrationFormAndSubmit();
        softAssert.assertTrue(isHeaderPresent("h1","Мої вішлісти"),
            "Main page header is not displayed as expected");

        openCreateWishlistModal();
        softAssert.assertTrue(isHeaderPresent("h2","Створення вішліста"),
            "Create wishlist form did not open properly");

        String wishlistName ="Test wishlist";
        fillCreateWishlistForm(wishlistName);
        softAssert.assertTrue(isWishlistWithTitlePresent(wishlistName),
            "Wishlist with title '" + wishlistName + "' should be visible");

        openWishlistByTitle(wishlistName);
        assertWishlistTitleEquals(wishlistName, softAssert);

        openCreateWishModal();
        softAssert.assertTrue(isHeaderPresent("h2","Нове бажання"),
            "Create wish form did not open properly");

        String wishName = "Мій перший подарунок";
        createWish(wishName);

        goToProfile();
        softAssert.assertTrue(isHeaderPresent("h2","Особисті дані"),
            "Profile page header is not displayed as expected");

        deleteUserAccount();

        softAssert.assertAll();
    }
}
