package playwright_java.Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;;

public class CreateAccountPage {
    
    private final Page createAccountPage;

    public CreateAccountPage(Page page){
        this.createAccountPage=page;
    }

    public void createAccount(){

        createAccountPage.locator("input#firstName").fill("FirstName");
        createAccountPage.fill("input#firstName", "FirstName");
        
        createAccountPage.locator("input#lastName").fill("LastName");
        createAccountPage.locator("input#email").fill("jhun@example.com");
        createAccountPage.locator("input#fld-p1").fill("1qazXSW@");
        createAccountPage.locator("input#reenterPassword").fill("1qazXSW@");

        assertThat(createAccountPage.locator("span.c-input-error-message"))
        .containsText("Your passwords match!",new LocatorAssertions.ContainsTextOptions().setTimeout(30000));

        assertThat(createAccountPage.locator("span.c-input-error-message")).hasText("Your passwords match!");

        createAccountPage.locator("input#phone").fill("7232432432");
        createAccountPage.locator("input#is-recovery-phone").check();

        assertThat(createAccountPage.locator("button.cia-form__controls__submit")).
        isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(10000));
   
   
    }
}
