import org.junit.jupiter.api.Test;

import Pages.AccountEntries;


public class SignInTest extends PlaywrightRunner {
    
    @Test
    public void signInExistingUser(){
        homePage.navigate();
      //  page.navigate("https://www.bestbuy.com/?intl=nonsplash&intl=nosplash");
        accountNavigationPage.navigateTo(AccountEntries.SIGN_IN);
        singInPage.signIn(getProperty("email"),getProperty("password"));
        //singInPage.signIn("nateda8333@laserlip.com","1qazXSW@");
        homePage.checkWeAreOnTheHomePage();
    }
    @Test
    public void signInExistingUser2(){
        homePage.navigate();
        //  page.navigate("https://www.bestbuy.com/?intl=nonsplash&intl=nosplash");
        accountNavigationPage.navigateTo(AccountEntries.SIGN_IN);
        singInPage.signIn(getProperty("email"),getProperty("password"));
        //singInPage.signIn("nateda8333@laserlip.com","1qazXSW@");
        homePage.checkWeAreOnTheHomePage();
    }
}
