package playwright_java;



import org.junit.jupiter.api.Test;

import playwright_java.Pages.AccountEntries;


public class SignInTest extends PlaywrightRunner{
    
    @Test
    public void signInExistingUser(){
        homePage.navigate();
      //  page.navigate("https://www.bestbuy.com/?intl=nonsplash&intl=nosplash");
        accountNavigationPage.navigateTo(AccountEntries.SIGN_IN);
        singInPage.signIn("nateda8333@laserlip.com", "1qazXSW@");
        homePage.checkWeAreOnTheHomePage();
    }
}
