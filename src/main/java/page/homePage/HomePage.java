package page.homePage;

import core.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by josmer on 18/04/2018.
 */
public class HomePage {
    WebDriverWait wait;
    WebDriver driver;
    private By xButton = By.className("as-login-icon-close-circled");
    private By flightTab = By.xpath("//ul[@class = 'nevo-header-navigation-menu']/li[contains(@class,'FLIGHTS')]");
    private String formXpath = "//div[contains(@class,'sbox-bind-show-roundtrip-container')]";
    private By firstLocation = By.xpath(formXpath + "/div/div/div[1]//input");
    private By secondLocation = By.xpath(formXpath + "/div/div/div[2]//input");
    private By chekInDate = By.xpath(formXpath + "//input[contains(@class,'sbox-bind-reference-flight-start-date-input')]");
    private By chekOutDate = By.xpath(formXpath + "//input[contains(@class,'sbox-bind-reference-flight-end-date-input')]");
    private By dateSelector = By.xpath("//div[contains(@class,'_dpmg2--roundtrip _dpmg2--show')]");
    private By rightArrow = By.xpath("//div[@class='_dpmg2--controls-next']/i[contains(@class,'_dpmg2--icon-ico-arrow')]");
    private By monthActive = By.xpath("//div[contains(@class,'_dpmg2--month-active')]");
    private String dayBase = "//div[contains(@class,'_dpmg2--month-active')]//*[text()='";
    private By searchButton = By.xpath(formXpath + "/../div[4]//em");
    private By hotelTab = By.xpath("//li[@data-code='HOTELS']");
    private String formHotel = "//div[contains(@class,'sbox-hotels-container')]";
    private By hotelLocation = By.xpath(formHotel + "//input[contains(@class,'sbox-main-focus')]");
    private By checkInHotel = By.xpath(formHotel + "//input[contains(@class,'sbox-checkin-date')]");
    private By checkOutHotel = By.xpath(formHotel + "//input[contains(@class,'sbox-checkout-date')]");
    private By distributionHotel = By.xpath(formHotel + "//div[contains(@class,'sbox-distribution-picker-wrapper')]");
    private By searchHotelButton = By.xpath(formHotel + "//a[@class='sbox-3-btn -secondary -md sbox-search']");
    private String popUpDistribution = "//div[contains(@class,'distpicker-hotels')]//div[@class='_pnlpk-itemBlock']";
    private By addMinorButton = By.xpath(popUpDistribution + "//div[contains(@class,'_pnlpk-stepper-minors')]//a[contains(@class,'sbox-3-icon-plus')]");
    private By selectMinorAge = By.xpath(popUpDistribution + "//div[@class='_pnlpk-minors-age-select-wrapper']/div[1]//select");
    private By applyButton = By.xpath(popUpDistribution + "/../../../div[2]/a[text()='Aplicar']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,10);
    }

    public HomePage waitAndClickHotelTab(){
        return waitAndClick(hotelTab);
    }

    public HomePage waitAndTypeHotelLocation(String location){
        return waitAndType(location,hotelLocation);
    }
    public HomePage waitAndClickCheckInHotel(){
        return waitAndClick(checkInHotel);
    }

    public HomePage waitAndClickCheckOutHotel(){
        return waitAndClick(checkOutHotel);
    }


    public HomePage selectHotelCheckInDate(){
        return hotelDatePicker(10);
    }

    private HomePage hotelDatePicker(int i) {

        MyLogger.log.info("Selecting day " + i);
        String month,day;
        Calendar newDate = addDays(i);
        month = String.valueOf(newDate.get(Calendar.MONTH) + 1);
        day = String.valueOf(newDate.get(Calendar.DAY_OF_MONTH));

        selectDate(month,day);
        return this;
    }

    public HomePage selectHotelCheckOutDate(){
        return hotelDatePicker(13);
    }

    private Calendar addDays(int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal;
    }

    public HomePage waitAndClickDistribution(){
        return waitAndClick(distributionHotel);
    }

    public HomePage waitAndClickAddMinor(){
        return waitAndClick(addMinorButton);
    }

    public HomePage waitAndSelectAge(){
        MyLogger.log.info("Waiting for the visibility of age combo, selecting 12 años ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectMinorAge));
        WebElement selectMinorElement = driver.findElement(selectMinorAge);
        Select dropdown = new Select(selectMinorElement);
        dropdown.selectByVisibleText("12 años");
        return this;
    }

    public HomePage waitAndClickApplyButton(){
        return waitAndClick(applyButton);
    }

    public HomePage waitAndClickSearchHotelButton(){
        return waitAndClick(searchHotelButton);
    }

    public HomePage closeXButton(){
        waitAndClick(xButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xButton));
        return this;
    }

    public HomePage waitAndClickFlightTab(){
        return waitAndClick(flightTab);
    }

    public HomePage waitAndTypeFromLocation(String location){
        return waitAndType(location, firstLocation);
    }

    public HomePage waitAndTypeToLocation(String location){
        return waitAndType(location, secondLocation);
    }

    public HomePage waitAndClickCheckInDate(){
        return waitAndClick(chekInDate);
    }

    public HomePage selectDate(String month, String day){
        MyLogger.log.info("Selecting date, Month: " + month + " and day: "+ day);
        int steps;
        int currentMonthInt;
        int monthInt = Integer.parseInt(month);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateSelector));
        WebElement currentMonthElement = driver.findElements(monthActive).get(0);
        String currentMonth = currentMonthElement.getAttribute("data-month").split("-")[1];

        currentMonthInt = Integer.parseInt(currentMonth);
        monthInt = monthInt < currentMonthInt ? monthInt + 12 : monthInt;
        steps = monthInt - currentMonthInt;
        for(int i=0;i<steps;i++){
            driver.findElement(rightArrow).click();
            threadWait(300);
        }
        driver.findElements(dateXpath(day)).get(0).click();


        return this;
    }

    public void waitAndClickSearchButton(){
        waitAndClick(searchButton);
    }

    private By dateXpath(String day){
        return By.xpath(dayBase + day + "']") ;
    }

    private void threadWait(int segs){
        try {
            Thread.sleep(segs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private HomePage waitAndClick(By elementLocation){
        MyLogger.log.info("Waiting for the element" + elementLocation.toString() + " to be clicked");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
        driver.findElement(elementLocation).click();
        return this;
    }

    private HomePage waitAndType(String location, By elementLocation) {
        MyLogger.log.info("Waiting for the element" + elementLocation.toString() + " to type into");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
        WebElement element =driver.findElement(elementLocation);
        element.clear();
        element.sendKeys(location);
        threadWait(3000);
        element.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage waitAndClickCheckOutDate() {
        return waitAndClick(chekOutDate);
    }
}
