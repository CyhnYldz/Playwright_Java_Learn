package playwright_java;

import org.junit.jupiter.api.Test;

import playwright_java.Pages.AccountEntries;


public class SignUpTest extends PlaywrightRunner{
    

    @Test
    public void signUpTest(){

        homePage.navigate();

        //page.navigate("https://www.bestbuy.com/?intl=nonsplash&intl=nosplash");

        accountNavigationPage.navigateTo(AccountEntries.CREATE_ACCOUNT);
       // accountNavigationPage.navigateTo("Sign In");

        createAccountPage.createAccount();
    }
}
