package playwright_java.Pages;

import com.microsoft.playwright.Page;

public class SingInPage {
    
    private final Page signInPage;

    private static String EMAIL="input#fld-e";
    private static String PASSWORD="input#fld-p1";
    private static String SIGN_IN_BUTTON="//button[@data-track='Sign In']";

    public SingInPage(Page page){
        this.signInPage=page;

    }

    public void signIn(String email,String password){
        signInPage.locator(EMAIL).fill(email);
       // signInPage.fill(EMAIL, email);
       // signInPage.fill(PASSWORD, password);
        signInPage.locator(PASSWORD).fill(password);
        signInPage.locator(SIGN_IN_BUTTON).click();
        System.out.println();

    }
}
