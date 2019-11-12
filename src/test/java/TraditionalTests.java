import Pages.*;
import Utils.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static Pages.BasePage.BASE_URL;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
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

    @Test
    public void testSortTable() throws URISyntaxException {
        homePage=loginPage.login("username","password");
        assertThat("We should see Transactions Table on the Home Page",homePage.getTransactionsTable().isDisplayed());
        int numberRows = (driver.findElements(By.xpath("//tbody/tr"))).size();
        int numberColumns = (driver.findElements(By.xpath("//tbody/tr[1]/td"))).size();
        List<List> listRows = new ArrayList<>();
        for(int i=0;i<numberRows;i++) {
            int m = i+1;
            List<String> row = new ArrayList<>();
            for (WebElement element : driver.findElements(By.xpath("//tbody/tr["+m+"]/td"))) {
                row.add(element.getText());
            }
            listRows.add(row);
        }
        driver.findElement(By.cssSelector("th#amount")).click();
        List<List> listUpdatedRows = new ArrayList<>();
        for(int i=0;i<numberRows;i++) {
            int m = i+1;
            if(i<numberRows-1){
                int next = m+1;
                String topRow = driver.findElement(By.xpath("//tbody/tr["+m+"]/td[5]")).getText().split("U")[0].replace(",","");
                String nextRow = driver.findElement(By.xpath("//tbody/tr["+next+"]/td[5]")).getText().split("U")[0].replace(",","");
                double topAmount = Double.parseDouble(topRow.substring(1));
                if(topRow.contains("-")) topAmount = 0-topAmount;
                double nextAmount = Double.parseDouble(nextRow.substring(1));
                if(nextRow.contains("-")) nextAmount = 0-nextAmount;
                assertThat("Top Amount "+topAmount+" is more then Next Amount "+nextAmount,topAmount<nextAmount);
            }
            List<String> row = new ArrayList<>();
            for (WebElement element : driver.findElements(By.xpath("//tbody/tr["+m+"]/td"))) {
                row.add(element.getText());
            }
            String time = driver.findElement(By.xpath("//tbody/tr[" + m + "]/td[2]")).getText();
            for(int j=0;j<numberRows;j++) {
                if(time.equals(listRows.get(j).get(1))){
                    Assert.assertTrue(listRows.get(j).equals(row));
                }
            }
        }

    }

    @Test
    public void testCanvas() throws URISyntaxException {
        //Sorry, I am unable to automate canvas
    }

    @Test
    public void testDynamicContent() throws URISyntaxException {
        driver.get(BASE_URL+"?showAd=true");
        homePage=loginPage.login("username","password");
        assertThat("First Ad should be displayed", driver.findElement(By.cssSelector("#flashSale img")).isDisplayed());
        assertThat("Second Ad should be displayed", driver.findElement(By.cssSelector("#flashSale2 img")).isDisplayed());
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
