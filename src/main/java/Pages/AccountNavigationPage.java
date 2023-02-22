package Pages;

import com.microsoft.playwright.Page;

public class AccountNavigationPage {
    
    private final Page accountNavigationPage;

    private static String ACCOUNT_BUTTON= "button[data-lid='hdr_signin']";
    // private static String CREATE_ACCOUNT_BUTTON= "div.header-guest-user a.create-account-btn";
    private static String ACCOUNT_LINKS_TEMPLATE="//div[@class='account-menu']//a[text()='%s']";

    public AccountNavigationPage(Page page){
        this.accountNavigationPage=page;
    }

    public void navigateTo(AccountEntries accountEntries){

        accountNavigationPage.locator(ACCOUNT_BUTTON).click();
        accountNavigationPage.locator(String.format(ACCOUNT_LINKS_TEMPLATE, accountEntries.getDisplayName())).click();

    }
}
