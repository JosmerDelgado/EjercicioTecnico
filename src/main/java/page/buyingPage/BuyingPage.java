package page.buyingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyingPage {

    private WebDriverWait wait;
    private WebDriver driver;
    By passengersSection = By.id("passengers");
    By paymentSection = By.id("payment");
    By invoiceSection = By.id("invoiceDefinition");
    By contactSection = By.id("contact");


    public BuyingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,10);
    }

    public Boolean verifyPassengerSection(){
        return verifyElement(passengersSection);
    }

    public Boolean verifyPaymentSection(){
        return verifyElement(paymentSection);
    }

    public Boolean verifyInvoiceSection(){
        return verifyElement(invoiceSection);
    }

    public Boolean verifyContactSection(){
        return verifyElement(contactSection);
    }

    private Boolean verifyElement(By element){
        return driver.findElements(element).size() != 0;
    }

    public void waitToAppear() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengersSection));
    }
}
