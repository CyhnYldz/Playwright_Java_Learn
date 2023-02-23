import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

public class ShadowDomTest {
    @Test
    public void shadowDome(){
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page= browser.newPage();

        page.navigate("http://watir.com/examples/shadow_dom.html");

        //page.locator("input[type='text']").fill("Playwright");
        page.fill("input[type='text']","Playwright");
       // page.locator("input[type='checkbox']").check();
        page.check("input[type='checkbox']");

    }
}
