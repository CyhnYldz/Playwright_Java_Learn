package Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class CreateAccountPage {
    
    private final Page createAccountPage;
    private static String FIRST_NAME="input#firstName";
    private static String LAST_NAME="input#lastName";
    private static String EMAIL="input#email";
    private static String PASSWORD="input#fld-p1";
    private static String RE_PASS="input#reenterPassword";
    private static String VALIDATION_MESSAGE="span.c-input-error-message";
    private static String PHONE="input#phone";
    private static String RECOVERY_PHONE_CHECKBOX="input#is-recovery-phone";
    private static String SUBMIT_BUTTON="button.cia-form__controls__submit";

    public CreateAccountPage(Page page){
        this.createAccountPage=page;
    }

    public void createAccount(){

        createAccountPage.locator(FIRST_NAME).fill("FirstName");
       // createAccountPage.fill(FIRST_NAME, "FirstName");
        
        createAccountPage.locator(LAST_NAME).fill("LastName");
        createAccountPage.locator(EMAIL).fill("jhun@example.com");
        createAccountPage.locator(PASSWORD).fill("1qazXSW@");
        createAccountPage.locator(RE_PASS).fill("1qazXSW@");

        assertThat(createAccountPage.locator(VALIDATION_MESSAGE))
        .containsText("Your passwords match!",new LocatorAssertions.ContainsTextOptions().setTimeout(30000));

        // assertThat(createAccountPage.locator(VALIDATION_MESSAGE)).hasText("Your passwords match!");

        createAccountPage.locator(PHONE).fill("7232432432");
        createAccountPage.locator(RECOVERY_PHONE_CHECKBOX).check();

        assertThat(createAccountPage.locator(SUBMIT_BUTTON)).
        isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(10000));
   
   
    }
}
