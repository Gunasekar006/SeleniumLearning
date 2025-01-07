package tests;

import org.example.pageobjects.OrderPage;
import org.example.pageobjects.LoginApplication;
import org.testng.Assert;
import org.testng.annotations.Test;
import resource.TestComponents.BaseClassTest;
import resource.TestComponents.FailureRetry;

public class OrderProduct extends BaseClassTest {

/* Failing this test to run Retry listner */

//    @Test//(retryAnalyzer= FailureRetry.class)
    public void submitOrderTest() throws InterruptedException {
      LoginApplication login =homePage.signIn();
      OrderPage page = login.goToAccountMenu();
      page.SelectProductandItem("Gear", "Bags");
      page.addToCart("Overnight Duffle");
      page.proceedToCheckoutShipping();
      String orderId= page.proceedToCheckoutPayments();
        System.out.println("Order ID:"+orderId);


    }



}




