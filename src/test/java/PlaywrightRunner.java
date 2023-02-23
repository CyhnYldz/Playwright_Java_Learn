import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.*;

import com.microsoft.playwright.*;

import Pages.AccountNavigationPage;
import Pages.CreateAccountPage;
import Pages.HomePage;
import Pages.SingInPage;
import annotations.PlaywrightPage;
import org.junit.jupiter.api.extension.ExtendWith;
import services.EnvironmentReaderService;

@ExtendWith(TestWatcherExtension.class)//pass olan testleri silmek için
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//paralel test için
public class PlaywrightRunner {

    protected Page page;
    protected BrowserContext browserContext;
    protected Browser browser;
    protected  Playwright playwright;

    @PlaywrightPage
    protected CreateAccountPage createAccountPage;

    @PlaywrightPage
    protected AccountNavigationPage accountNavigationPage;

    @PlaywrightPage
    protected HomePage homePage;

    @PlaywrightPage
    protected SingInPage singInPage;

    @BeforeAll
    public  void init(){
        playwright=Playwright.create();
    }

    @BeforeEach
    public void setUp(){
        browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
      //  browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext(new Browser.NewContextOptions().setPermissions(Arrays.asList("geolocation")));
//        browserContext=browser.newContext(new Browser.NewContextOptions()
//                .setPermissions(Arrays.asList("geolocation"))
//                .setRecordVideoDir(Paths.get("videos/")) //video kaydı için açılır
//                .setRecordVideoSize(1920,1080));
        browserContext.setDefaultTimeout(40000); //bu time out hem navigation hem tüm sayfalar için çalışır
        //browserContext.setDefaultNavigationTimeout(40000); //sadece navigation için belirlenebilir
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(false));//paralel test için false yapılır
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
    public void tearDown(TestInfo testInfo){
        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("traces/"+testInfo.getDisplayName().replace("()","")+".zip")));
        browserContext.close();
        browser.close();


    }

    protected String getProperty(String key){
        return EnvironmentReaderService.getProperty(key);
    }
}
