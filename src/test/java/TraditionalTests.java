import Pages.*;
import Utils.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.net.URISyntaxException;
import static Pages.BasePage.BASE_URL;
import static org.hamcrest.MatcherAssert.assertThat;

public class TraditionalTests {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeSuite
    public void beforeSuite(){
        // Initialization WebDriver
        driver=BrowserFactory.getDriver("CHROME");
    }

    @BeforeMethod
    public void beforeMethod(){
        loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL);

    }

    @Test
    public void testUIElements() throws URISyntaxException {
        loginPage.shouldHaveLogoLink();
        loginPage.shouldHaveAuthHeader();
        loginPage.shouldHaveLoginForm();
        loginPage.shouldHaveUsernameLabelAndInput();
        loginPage.shouldHavePasswordLabelAndInput();
        loginPage.shouldHaveLoginButton();
        loginPage.shouldHaveRememberLabelAndCheckbox();
        loginPage.shouldHaveTwitterLink();
        loginPage.shouldHaveFbLink();
        loginPage.shouldHaveLnLink();
    }

    @Test (dataProvider = "Login")
    public void testDataDriven(String username, String password) throws URISyntaxException {
        homePage=loginPage.login(username,password);
        if(username.equals("")&& password.equals("")){
            assertThat("We should see correct error message",loginPage.getErrorText().equals(loginPage.getErrorMessageForEmptyBothFields()));
        }
        if(username.equals("")&& !password.equals("")){
            assertThat("We should see correct error message",loginPage.getErrorText().equals(loginPage.getErrorMessageForEmptyUsername()));
        }
        if(!username.equals("")&& password.equals("")){
            assertThat("We should see correct error message",loginPage.getErrorText().equals(loginPage.getErrorMessageForEmptyPassword()));
        }
        if(!username.equals("")&& !password.equals("")){
            assertThat("We should see Transactions Table on the Home Page",homePage.getTransactionsTable().isDisplayed());
        }
    }

    @DataProvider(name = "Login")
    public static Object[][] search() {
        return new Object[][]{
                {"", ""},
                {"", "Password"},
                {"Username",""},
                {"Username","Password"}
        };
    }

    @AfterSuite
    public void afterSuit(){
        //Close the browser
        driver.quit();;
    }
}
