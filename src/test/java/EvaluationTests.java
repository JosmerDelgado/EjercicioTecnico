import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import page.Page;
import web.Web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by josmer on 18/04/2018.
 */
public class EvaluationTests {
    private static WebDriver driver = null;
    private static Page page;

    @BeforeMethod
    public void setUpDriver(){
        System.out.println("Before Test");
        this.driver = Web.remoteWeb();
        this.page= new Page(driver);
        driver.get(page.startURL());
        driver.manage().window().setSize(new Dimension(1024,1366));
        page.homePage.closeXButton();
    }

    @AfterMethod
    public void tierDown(){
        System.out.println("After Test");
//        driver.close();
//        driver.quit();
    }

    @Parameters({ "from", "to", "month-from", "day-from", "month-to", "day-to" })
    @Test
    public void SearchFlightTest(String from,String to, String monthFrom, String dayFrom, String monthTo, String dayTo){
        String title = driver.getTitle();
        String title2;

        page.searchFlight(from,to,monthFrom,dayFrom,monthTo,dayTo);

        title2 = driver.getTitle();

        Assert.assertTrue(title != title2);
        Assert.assertTrue(page.resultPage.getResults() > 0);

    }

    @Parameters({ "from", "to", "month-from", "day-from", "month-to", "day-to" })
    @Test
    public void SearchExpensiveFlightTest(String from,String to, String monthFrom, String dayFrom, String monthTo, String dayTo){
        String title1, title2;
        page.searchFlight(from,to,monthFrom,dayFrom,monthTo,dayTo);
        title1 = driver.getCurrentUrl();
        System.out.println(title1);
        page.resultPage.waitToDissapearProgressBar().waitAndClickExpensiveTrip();
        page.buyingPage.waitToAppear();
        title2 = driver.getCurrentUrl();
        System.out.println(title2);
        Assert.assertTrue(title1 != title2);
        Assert.assertTrue(page.buyingPage.verifyContactSection());
        Assert.assertTrue(page.buyingPage.verifyInvoiceSection());
        Assert.assertTrue(page.buyingPage.verifyPassengerSection());
        Assert.assertTrue(page.buyingPage.verifyPaymentSection());

    }

    @Test
    public void SearchHotelTest(){
        page.homePage.waitAndClickHotelTab().waitAndTypeHotelLocation("Montevideo").waitAndClickCheckInHotel()
                .selectHotelCheckInDate().selectHotelCheckOutDate().waitAndClickDistribution().waitAndClickAddMinor()
                .waitAndSelectAge().waitAndClickApplyButton().waitAndClickSearchHotelButton();
        page.resultHotelPage.waitUntilIsCharged().waitAndClickFiveStarFilter().waitUntilLoaderDisappear()
                .findLowestPrice();

        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());

        Assert.assertTrue(browserTabs.size() == 2);

        driver.switchTo().window(browserTabs.get(1));

        Assert.assertTrue(page.hotelRoomTab.waitAndVerifyRoomSection());
    }



}
