import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class IFrameTest {

    @Test
    public void iFrameTest(){
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page= browser.newPage();
        page.navigate("https://www.rediff.com");

        //page.frameLocator("iframe[name='moneyiframe']").locator("#query").fill("Capital First Ltd."); //iframe'e bu şekilde de geçilir
        page.frameByUrl("https://money.rediff.com/widget/moneywizwidget/rhome?src=rhome1").locator("#query").fill("Capital First Ltd.");
       Page popup= page.waitForPopup(()-> {
            page.frameLocator("iframe[name='moneyiframe']").locator("input[type='submit']").click();
        });

       assertThat(popup.locator("#for_BSE")).containsText("Capital First Ltd.");

    }
}
