package page.resultHotelPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultHotelPage {
    WebDriver driver;
    WebDriverWait wait;
    By priceContainers = By.xpath("//li[@class='hf-pricebox-price col -sm-12 hf-robot-price -eva-3-tc-gray-1']");
    By detailsContainers = By.xpath("//li[@class='hf-pricebox-detail-and-payment col -md-12 -eva-3-hide-small hf-robot-see-detail']");
    By element = By.xpath("//div[@class='sbox-ui-container sbox-hotels-container']");
    By fiveStarFilter = By.xpath("//ul[contains(@class,'hf-filters-recommended')]/li[@class='hf-filter-dropdown dropdown-item ga-container hf-robot-filter -active']/ul/li[3]");
    By loadingPage = By.xpath("//*[@id='fullLoader']");

    public ResultHotelPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public ResultHotelPage waitUntilIsCharged(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return this;
    }

    public ResultHotelPage waitAndClickFiveStarFilter(){
        return waitAndClick(fiveStarFilter);
    }

    public ResultHotelPage waitUntilLoaderDisappear(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingPage));
        return this;
    }

    public void findLowestPrice(){
        int lowest, elementNumber=0;
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceContainers));
        List<WebElement> pricesElements = driver.findElements(priceContainers);
        String text = pricesElements.get(0).getText().replace(".","").replace("$ ","");
        System.out.println("Number Text: "+text);
        lowest = Integer.parseInt(text);
        for(int i = 1 ; i < pricesElements.size(); i++){
            int actualInt = Integer.parseInt(pricesElements.get(i-1).getText().replace(".","")
                    .replace("$ ",""));
            if(lowest > actualInt){
                lowest = actualInt;
                elementNumber=i-1;
            }
        }
        System.out.println(elementNumber +" highest Element "+ lowest);
        driver.findElements(detailsContainers).get(elementNumber).click();
    }

    private ResultHotelPage waitAndClick(By elementLocation){
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
        driver.findElement(elementLocation).click();
        return this;
    }



}
