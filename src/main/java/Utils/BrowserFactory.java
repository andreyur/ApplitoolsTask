package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver getDriver (String name){
        switch (name){
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
                return new FirefoxDriver();
            case "CHROME":
                System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
                return new ChromeDriver();
            case "EDGE":
                System.setProperty("webdriver.edge.driver","src/main/resources/msedgedriver.exe");
                return new EdgeDriver();
            default:
                System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
                return new FirefoxDriver();
        }
    }
}
