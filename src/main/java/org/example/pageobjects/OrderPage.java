package org.example.pageobjects;

import org.checkerframework.checker.units.qual.N;
import org.example.utils.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(id = "page-title-wrapper")
    WebElement placeOrderMessage;

    @FindBy(className = "checkout-success")
    WebElement orderID;


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
//                email.sendKeys(getUserNmae());
                System.out.println("Checkout page has loaded..");
                streetAddress1.sendKeys("street1");
                city.sendKeys("Chennai");

                postcode.sendKeys("641654");

                selectValueFromDropDown(country,"IN");
                selectValueFromDropDown(state,"563");
                telephone.sendKeys("2525252524");



                Thread.sleep(20000);
                Next.click();
            }

        }

    }

    public void proceedToCheckoutPayments   (){
                waitForElementToAppear(placeOrder);

        Actions action = new Actions(driver);
        action.moveToElement(placeOrder).click(placeOrder).build().perform();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.querySelector(\"button[xpath=//*[text()='Place Order']\").click()");


//        waitForElementToAppear(placeOrder);
//        placeOrder.click();
        waitForElementToAppear(placeOrderMessage);
        System.out.println(placeOrderMessage.getText());
        waitForElementToAppear(orderID);
        System.out.println("Order ID:"+orderID.getText());
    }


}
