import Pages.HomePage;
import Pages.LoginPage;
import Utils.BrowserFactory;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static Pages.BasePage.BASE_URL;
import static org.hamcrest.MatcherAssert.assertThat;

public class VisualAITests {
    private static BatchInfo batch;
    private EyesRunner runner;
    private Eyes eyes;
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeClass
    public void setBatch(){
        // Initialization WebDriver
        driver= BrowserFactory.getDriver("CHROME");
        batch = new BatchInfo("Hackathon");
    }

    @BeforeMethod
    public void beforeMethod(){
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setBatch(batch);
        eyes.setApiKey("rLmpQ1m3TEIn1enMfoV5F6op9QDAbJj4MIYU2P2cRuo110");
        loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL);

    }

    @Test
    public void testUIElements() throws URISyntaxException {
        eyes.open(driver, "Hackathon","UI Elements Test");
        eyes.checkWindow("Login Window");
        eyes.closeAsync();
    }

    @Test (dataProvider = "Login")
    public void testDataDriven(String username, String password, String expected, String testName) throws URISyntaxException {
        homePage=loginPage.login(username,password);
        eyes.open(driver, "Hackathon",testName);
        eyes.check(testName, Target.window().ignoreDisplacements());
        eyes.closeAsync();
    }

    @Test
    public void testSortTable() throws URISyntaxException {
        homePage=loginPage.login("username","password");
        driver.findElement(By.cssSelector("th#amount")).click();
        eyes.open(driver, "Hackathon","Sorting Table Test");
        eyes.checkWindow("Window After Sorting The Table");
        eyes.closeAsync();
    }

    @Test
    public void testCanvas() throws URISyntaxException {
        homePage=loginPage.login("username","password");
        homePage.getCompareLink().click();
        eyes.open(driver, "Hackathon","Canvas Test");
        eyes.checkWindow("Window Before Adding The DataSet");
        homePage.getAddDatasetButton().click();
        eyes.checkWindow("Window After Adding The DataSet");
        eyes.closeAsync();
    }

    @Test
    public void testDynamicContent() throws URISyntaxException {
        driver.get(BASE_URL+"?showAd=true");
        homePage=loginPage.login("username","password");
        eyes.open(driver, "Hackathon","Dynamic Content Test");
        eyes.checkWindow("Window with Dynamic Content");
        eyes.closeAsync();
    }

    @DataProvider(name = "Login")
    public static Object[][] search() {
        return new Object[][]{
                {"", "", "Both Username and Password must be present", "Login test - Both Username and Password fields empty"},
                {"", "Password", "Username must be present", "Login test - Username field is empty"},
                {"Username","", "Password must be present", "Login test - Password field is empty"},
                {"Username","Password", "Jack Gomez", "Login test - Both Username and Password fields are entered"}
        };
    }

    @AfterSuite
    public void afterSuit(){
        //Close the browser
        driver.quit();
        eyes.abort();
    }
}

