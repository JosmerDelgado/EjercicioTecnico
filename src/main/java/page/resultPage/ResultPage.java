package page.resultPage;

import core.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by josmer on 18/04/2018.
 */
public class ResultPage {
    By flightsResults = By.xpath("//cluster/div[contains(@class, 'results-cluster-container')]");
    By priceElements = By.xpath("//span[@class = 'fare main-fare-big']//em/span[@class ='amount price-amount']");
    By buyButtons = By.xpath("//buy-button/a");
    By progressBar = By.xpath("//*[(@class='progress-bar-content')]");


    WebDriver driver;
    WebDriverWait wait;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);

    }

    public int getResults(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightsResults));
        return driver.findElements(flightsResults).size();
    }

    public ResultPage waitToDissapearProgressBar(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(progressBar));
        return this;
    }

    public void waitAndClickExpensiveTrip(){
        MyLogger.log.info("Selecting the Expensive Trip");
        int highest, elementNumber=0;
        List<WebElement> pricesElements = driver.findElements(priceElements);
        String text = pricesElements.get(0).getText().replace(".","");
        System.out.println("Number Text: "+text);
        highest = Integer.parseInt(text);
        for(int i = 1 ; i < pricesElements.size(); i++){
            int actualInt = Integer.parseInt(pricesElements.get(i-1).getText().replace(".",""));
            if(highest < actualInt){
                highest = actualInt;
                elementNumber=i;
            }
        }
        driver.findElements(buyButtons).get(elementNumber).click();
    }

    private void waitAndClick(By elementLocation){
        MyLogger.log.info("Waiting for the element " + elementLocation.toString() + " to be clicked.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
        driver.findElement(elementLocation).click();
    }
}
