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
    public static final String COMPARE_LINK = "#showExpensesChart";
    public static final String ADD_DATASET_BUTTON = "#addDataset";

    @FindBy (css = TRANSACTIONS_TABLE)
    private WebElement transactionsTable;
    public WebElement getTransactionsTable() {
        return transactionsTable;
    }

    @FindBy (css = COMPARE_LINK)
    private WebElement compareLink;
    public WebElement getCompareLink() {
        return compareLink;
    }

    @FindBy (css = ADD_DATASET_BUTTON)
    private WebElement addDatasetButton;
    public WebElement getAddDatasetButton() {
        return addDatasetButton;
    }

    public HomePage open(String url){
        driver.get(url);
        driver.manage().window().maximize();
        return this;
    }
}
