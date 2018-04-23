package web;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.Page;

/**
 * Created by josmer on 18/04/2018.
 */
public class Web {
//    public static FirefoxDriver driver = new FirefoxDriver();
    public static WebDriverWait wait;
    public static Page despegarPage;

    public static WebDriver remoteWeb(){
        String pwd = System.getProperty("user.dir");
        System.out.println("Print current work directory: "+pwd);

        System.setProperty("webdriver.chrome.driver", pwd+"/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");


        return new ChromeDriver(options);
    }


}
