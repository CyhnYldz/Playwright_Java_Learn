import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.*;

import Pages.AccountNavigationPage;
import Pages.CreateAccountPage;
import Pages.HomePage;
import Pages.SingInPage;
import annotations.PlaywrightPage;
import services.EnvironmentReaderService;

public class PlaywrightRunner {

    protected Page page;
    protected BrowserContext browserContext;
    protected Browser browser;
    protected static Playwright playwright;

    @PlaywrightPage
    protected CreateAccountPage createAccountPage;

    @PlaywrightPage
    protected AccountNavigationPage accountNavigationPage;

    @PlaywrightPage
    protected HomePage homePage;

    @PlaywrightPage
    protected SingInPage singInPage;

    @BeforeAll
    public static void init(){
        playwright=Playwright.create();
    }

    @BeforeEach
    public void setUp(){
        browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
      //  browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext(new Browser.NewContextOptions().setPermissions(Arrays.asList("geolocation")));
        browserContext.setDefaultTimeout(40000); //bu time out hem navigation hem tüm sayfalar için çalışır
        //browserContext.setDefaultNavigationTimeout(40000); //sadece navigation için belirlenebilir
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page=browserContext.newPage();
        //page.setDefaultTimeout(40000); //sadece sayfa için timout vermek için kullanılır 
        //buradaki timeoutlar assertion'lar için geçerli değil

        initPage(this, page);

       
    }
    private void initPage(Object object,Page page){
        Class<?> clazz = object.getClass().getSuperclass();
        for(Field field: clazz.getDeclaredFields()){
            if(field.isAnnotationPresent((PlaywrightPage.class))){
                Class<?>[] type={Page.class};
                try {
                    field.set(this, field.getType().getConstructor(type).newInstance(page));
                } catch (IllegalAccessException |InstantiationException |InvocationTargetException |NoSuchMethodException e) {
                   System.out.println("Did not manage to call constructor for Playwright page with name " + field.getName());
                    
                } 
               
            }
        }
    }

    @AfterEach
    public void tearDown(){
        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        browserContext.close();
        browser.close();


    }

    protected String getProperty(String key){
        return EnvironmentReaderService.getProperty(key);
    }
}
