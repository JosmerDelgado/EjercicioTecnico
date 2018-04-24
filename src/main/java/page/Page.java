package page;

import org.openqa.selenium.WebDriver;
import page.buyingPage.BuyingPage;
import page.homePage.HomePage;
import page.hotelRoomTab.HotelRoomTab;
import page.resultHotelPage.ResultHotelPage;
import page.resultPage.ResultPage;

/**
 * Created by josmer on 18/04/2018.
 */
public class Page {

    public BuyingPage buyingPage;
    public HomePage homePage;
    public ResultPage resultPage;
    public ResultHotelPage resultHotelPage;
    public HotelRoomTab hotelRoomTab;

    public Page(WebDriver driver) {
        this.homePage = new HomePage(driver);
        this.resultPage = new ResultPage(driver);
        this.buyingPage = new BuyingPage(driver);
        this.resultHotelPage = new ResultHotelPage(driver);
        this.hotelRoomTab = new HotelRoomTab(driver);

    }

    public String startURL(){
        return "http://www.despegar.com.ar/";
    }

    public void searchFlight(String fromDestination, String toDestination,
                             String monthFrom, String dayFrom, String monthTo, String dayTo){

        homePage.waitAndClickFlightTab().waitAndTypeFromLocation(fromDestination)
                .waitAndTypeToLocation(toDestination).waitAndClickCheckInDate().selectDate(monthFrom,dayFrom)
                .selectDate(monthTo,dayTo).waitAndClickSearchButton();

    }

}
