package page.hotelRoomTab;

import core.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelRoomTab {

    WebDriver driver;
    WebDriverWait wait;
    By roomSection = By.xpath("//a[@data-ga-ea='seeRooms']");

    public HotelRoomTab(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public Boolean waitAndVerifyRoomSection(){
        MyLogger.log.info("Waiting for the Room Section");
        wait.until(ExpectedConditions.visibilityOfElementLocated(roomSection));
        return driver.findElements(roomSection).size() > 0;
    }
}
