package playwright_java;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.*;

import playwright_java.Pages.CreateAccountPage;

public class PlaywrightRunner {

    protected Page page;
    protected BrowserContext browserContext;
    protected Browser browser;
    protected static Playwright playwright;
    protected CreateAccountPage createAccountPage;

    @BeforeAll
    public static void init(){
        playwright=Playwright.create();
    }

    @BeforeEach
    public void setUp(){
        
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext(new Browser.NewContextOptions().setPermissions(Arrays.asList("geolocation")));
        page=browser.newPage();

        createAccountPage=new CreateAccountPage(page);
    }

    @AfterEach
    public void tearrDown(){
        browserContext.close();
        browser.close();


    }
}
