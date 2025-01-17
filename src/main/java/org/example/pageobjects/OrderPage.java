package org.example.pageobjects;

import org.example.utils.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage extends Utility {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "product-addtocart-button")
    WebElement addToCart;

    @FindBy(xpath = "//*[@class='messages']/div/div")
    WebElement message;

    @FindBy(className = "counter-number")
    WebElement productCount;

    @FindBy(id = "top-cart-btn-checkout")
    WebElement proceedToCheckoutBtn;

    @FindBy(name = "street[0]")
    WebElement streetAddress1;

    @FindBy(name = "city")
    WebElement city;

    @FindBy(name = "region_id")
    WebElement state;

    @FindBy(name = "postcode")
    WebElement postcode;

    @FindBy(name = "country_id")
    WebElement country;

    @FindBy(name = "telephone")
    WebElement telephone;

    @FindBy(className = "continue")
    WebElement Next;
    @FindBy(id = "customer-email")
    WebElement email;
    @FindBy(name = "firstname")
    WebElement firstname;

    @FindBy(name = "lastname")
    WebElement lastname;

    @FindBy(xpath = "//*[text()='Place Order']")
    WebElement placeOrder;

    @FindBy(className = "page-title")
    WebElement placeOrderMessage;

    @FindBy(className = "checkout-success")
    WebElement orderID;

@FindBy(xpath = "//*[text()='Place Order']")
WebElement placeOrderButton;

    public void SelectProductandItem(String category, String product) {
        selectProduct(category, product);
    }

    public void addToCart(String productName) {
        driver.findElement(By.partialLinkText(productName)).click();

        if (driver.getTitle().contains(productName)) {
            System.out.println(productName + " product is selected");
        }
        addToCart.click();

        waitForElementToAppear(message);
        System.out.println(message.getText());
        if (message.getText().contains("You added Overnight Duffle to your")) {
            System.out.println(productName + " has been added to the cart");
        }

    }

    public void proceedToCheckoutShipping() throws InterruptedException {
        Thread.sleep(5000);
        waitForElementToAppear(productCount);
        String count = productCount.getText();
        System.out.println("Total: " + count);
        int productCt = Integer.parseInt(count);
        if (productCt >= 1) {
            productCount.click();
            proceedToCheckoutBtn.click();
            if (driver.getTitle().contains("Checkout")) {

                firstname.sendKeys("Guna");
                lastname.sendKeys("sekar");
                email.sendKeys(getUserNmae());
                System.out.println("Checkout page has loaded..");
                streetAddress1.sendKeys("street1");
                city.sendKeys("Chennai");

                postcode.sendKeys("641654");

                selectValueFromDropDown(country,"IN");
                selectValueFromDropDown(state,"563");
                telephone.sendKeys("2525252524");



                Thread.sleep(5000);
                Next.click();
            }

        }

    }

    public String proceedToCheckoutPayments   () throws InterruptedException {
        Thread.sleep(5000);
                waitForElementToAppear(placeOrder);

                new WebDriverWait(driver, Duration.ofSeconds(25))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Place Order']"))).click();


        waitForElementToAppear(placeOrderMessage);
        System.out.println(placeOrderMessage.getText());
        waitForElementToAppear(orderID);
        System.out.println("****************");
        System.out.println(orderID.getText());
        System.out.println("****************");
        return orderID.getText().replaceAll("[a-zA-Z#:'.]","").trim();


    }


}
