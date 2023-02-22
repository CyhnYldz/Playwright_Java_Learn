package playwright_java.Pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;

public class HomePage {
    
    private final Page homePage;
    private static String TOP_MENU_LINKS="ul.bottom-nav-left li a";

    public HomePage(Page page){
        this.homePage=page;

    }

    public void navigate(){
        homePage.navigate("https://www.bestbuy.com/?intl=nonsplash&intl=nosplash");
    }

    public void checkWeAreOnTheHomePage(){
        
        assertThat(homePage.locator(TOP_MENU_LINKS,new Page.LocatorOptions().setHasText("Deal of the Day")))
        .isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(20000));
    }
}
