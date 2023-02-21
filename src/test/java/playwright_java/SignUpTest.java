package playwright_java;

import org.junit.jupiter.api.Test;

public class SignUpTest extends PlaywrightRunner{
    

    @Test
    public void signUpTest(){

        page.navigate("https://www.bestbuy.com/?intl=nonsplash&intl=nosplash");

        page.locator("button[data-lid='hdr_signin']").click();
        page.locator("div.header-guest-user a.create-account-btn").click();

        createAccountPage.createAccount();
    }
}
