package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

public class HomePage extends BasePage  {
    public HomePage(WebDriver driver){
        super(driver);
    }
    public static final String TRANSACTIONS_TABLE = "#transactionsTable";
    public static final String LOGO = "a.logo[href='/']";
    public static final String LOGIN_LINK = "a.item[href*='login.html']";
    public static final String RADIO_BUTTON_TYPE = "[for='buRadioType']";
    public static final String SELECT_CATEGORIES = "#categories";
    public static final String BRAND_INPUT = "#brandTooltipBrandAutocompleteInput-brand";
    public static final String MODEL_INPUT = "#brandTooltipBrandAutocompleteInput-model";
    public static final String SELECT_YEAR = "#yearFrom";
    public static final String SEARCH_BUTTON = "[type='submit']";






    @FindBy (css = TRANSACTIONS_TABLE)
    private WebElement transactionsTable;
    public WebElement getTransactionsTable() {
        return transactionsTable;
    }

    @FindBy (css = LOGO)
    private WebElement logo;
    public WebElement getLogo() {
        return logo;
    }

    @FindBy (css = LOGIN_LINK)
    private WebElement loginLink;
    public WebElement getLoginLink() {
        return loginLink;
    }

    @FindBy (css = RADIO_BUTTON_TYPE)
    private WebElement radioButtonType;
    public WebElement getRadioButtonType() {
        return radioButtonType;
    }

    @FindBy (css = SELECT_CATEGORIES)
    private WebElement selectCategories;
    public WebElement getSelectCategories() {
        return selectCategories;
    }

    @FindBy (css = BRAND_INPUT)
    private WebElement brandInput;
    public WebElement getBrandInput() {
        return brandInput;
    }

    @FindBy (css = MODEL_INPUT)
    private WebElement modelInput;
    public WebElement getModelInput() {
        return modelInput;
    }

    @FindBy (css = SELECT_YEAR)
    private WebElement selectYear;
    public WebElement getSelectYear() {
        return selectYear;
    }

    @FindBy (css = SEARCH_BUTTON)
    private WebElement searchButton;
    public WebElement getSearchButton() {
        return searchButton;
    }

    public HomePage open(String url){
        driver.get(url);
        driver.manage().window().maximize();
        return this;
    }

    public LoginPage clickLoginLink(){
        loginLink.click();
        return new LoginPage(driver);
    }




    public void shouldHaveHost(String host) throws URISyntaxException {
        assertThat(new URI(driver.getCurrentUrl()).getHost(), equalTo("auto.ria.com"));
    }
    public void shouldHaveTitle(String title){
        assertThat(driver.getTitle(), equalTo(title));
    }

   // public void shouldHaveSearchForm(){
       // assertThat("Search Form should be displayed",searchForm.isDisplayed());
   // }

    public void shouldHaveLogo(){
        assertThat("Logo should be displayed",logo.isDisplayed());
    }
    public void shouldHaveLoginLink(){
        assertThat("Login link should be displayed",loginLink.isDisplayed());
    }
}
