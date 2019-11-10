package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
        super(driver);
    }
    private static final String LOGO_LINK = "div.logo-w a";
    private static final String AUTH_HEADER = "div.auth-box-w h4";
    private static final String LOGIN_FORM = "div.auth-box-w form";
    private static final String USERNAME_LABEL = "//form/div[1]/label";
    private static final String USERNAME_INPUT = "input#username";
    private static final String PASSWORD_LABEL = "//form/div[2]/label";
    private static final String PASSWORD_INPUT = "input#password";
    private static final String LOGIN_BUTTON = "button#log-in";
    private static final String REMEMBER_LABEL = "label.form-check-label";
    private static final String REMEMBER_CHECKBOX = "label.form-check-label input";
    private static final String TWITTER_LINK = "//div[@style='text-align:center']/a[1]";
    private static final String FB_LINK = "//div[@style='text-align:center']/a[2]";
    private static final String LN_LINK = "//div[@style='text-align:center']/a[3]";
    private static final String ERROR_MESSAGE = "div.alert-warning";
    private static final String ERROR_MESSAGE_FOR_EMPTY_BOTH_FIELDS = "Both Username and Password must be present";
    private static final String ERROR_MESSAGE_FOR_EMPTY_USERNAME = "Username must be present";
    private static final String ERROR_MESSAGE_FOR_EMPTY_PASSWORD = "Password must be present";

    public String getErrorMessageForEmptyBothFields() {
        return ERROR_MESSAGE_FOR_EMPTY_BOTH_FIELDS;
    }

    public String getErrorMessageForEmptyUsername() {
        return ERROR_MESSAGE_FOR_EMPTY_USERNAME;
    }

    public String getErrorMessageForEmptyPassword() {
        return ERROR_MESSAGE_FOR_EMPTY_PASSWORD;
    }

    @FindBy(css=LOGO_LINK)
    private WebElement logoLink;
    public WebElement getLogoLink() {
        return logoLink;
    }

    @FindBy(css=AUTH_HEADER)
    private WebElement authHeader;
    public WebElement getAuthHeader() { return authHeader; }

    @FindBy(css=LOGIN_FORM)
    private WebElement loginForm;
    public WebElement getLoginForm() {
        return loginForm;
    }

    @FindBy (xpath=USERNAME_LABEL)
    private WebElement usernameLabel;
    public WebElement getUsernameLabel() {
        return usernameLabel;
    }

    @FindBy (css=USERNAME_INPUT)
    private WebElement usernameInput;
    public WebElement getUsernameInput() {
        return usernameInput;
    }

    @FindBy (xpath=PASSWORD_LABEL)
    private WebElement passwordLabel;
    public WebElement getPasswordLabel() {
        return passwordLabel;
    }

    @FindBy (css=PASSWORD_INPUT)
    private WebElement passwordInput;
    public WebElement getPasswordInput() {
        return passwordInput;
    }

    @FindBy (css=LOGIN_BUTTON)
    private WebElement loginButton;
    public WebElement getLoginButton() {
        return loginButton;
    }

    @FindBy (css=REMEMBER_LABEL)
    private WebElement rememberLabel;
    public WebElement getRememberLabel() {
        return rememberLabel;
    }

    @FindBy (css=REMEMBER_CHECKBOX)
    private WebElement rememberCheckbox;
    public WebElement getRememberCheckbox() { return rememberCheckbox; }

    @FindBy(xpath=TWITTER_LINK)
    private WebElement twitterLink;
    public WebElement getTwitterLink() { return twitterLink; }

    @FindBy(xpath=FB_LINK)
    private WebElement fbLink;
    public WebElement getFbLink() { return fbLink; }

    @FindBy(xpath=LN_LINK)
    private WebElement lnLink;
    public WebElement getLnLink() { return lnLink; }

    @FindBy (css=ERROR_MESSAGE)
    private WebElement errorMessage;
    public WebElement getErrorMessage() { return errorMessage; }

    public LoginPage open(String url){
        driver.get(url);
        driver.manage().window().maximize();
        return this;
    }
    public HomePage login(String userName, String password){
        getUsernameInput().sendKeys(userName);
        getPasswordInput().sendKeys(password);
        getLoginButton().click();
        return new HomePage(driver);
    }
    public void shouldHaveLogoLink(){
        assertThat("Logo link should be displayed",getLogoLink().isDisplayed());
    }
    public void shouldHaveAuthHeader(){
        assertThat("Auth header should be displayed",getAuthHeader().isDisplayed());
    }
    public void shouldHaveLoginForm(){
        assertThat("Login Form should be displayed",getLoginForm().isDisplayed());
    }
    public void shouldHaveUsernameLabelAndInput(){
        assertThat("Username label should be displayed",getUsernameLabel().isDisplayed());
        assertThat("Username Input should be displayed",getUsernameInput().isDisplayed());
    }
    public void shouldHavePasswordLabelAndInput(){
        assertThat("Password label should be displayed",getPasswordLabel().isDisplayed());
        assertThat("Password Input should be displayed",getPasswordInput().isDisplayed());
    }
    public void shouldHaveLoginButton(){
        assertThat("Login Button should be displayed",getLoginButton().isDisplayed());
    }
    public void shouldHaveRememberLabelAndCheckbox(){
        assertThat("Remember label should be displayed",getRememberLabel().isDisplayed());
        assertThat("Remember Checkbox should be displayed",getRememberCheckbox().isDisplayed());
    }
    public void shouldHaveTwitterLink(){
        assertThat("Twitter link should be displayed",getTwitterLink().isDisplayed());
    }
    public void shouldHaveFbLink(){
        assertThat("FB link should be displayed",getFbLink().isDisplayed());
    }
    public void shouldHaveLnLink(){
        assertThat("Ln link should be displayed",getLnLink().isDisplayed());
    }
    public String getErrorText(){
        return getErrorMessage().getText();
    }

    }
