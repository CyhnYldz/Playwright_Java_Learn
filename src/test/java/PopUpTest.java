import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PopUpTest {

    @Test
    public void popUpTest(){
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page= browser.newPage();

        page.navigate("http://watir.com/examples/forms_with_input_elements.html");
        Page popup=page.waitForPopup(()->{
            page.locator("#new_popup_window").click();
        });


        assertThat(popup.locator("#axis_example")).containsText("Hugh Laurie");
        page.bringToFront();
        page.locator("#new_user_first_name").fill("Playwright");


    }
}
