package org.example.pageobjects;

import org.example.utils.data;
import org.example.utils.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginApplication extends Utility {

    private WebDriver driver;
data p;
    public LoginApplication(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(className = "logged-in")
    WebElement welcome;

    @FindBy(className = "messages")
    WebElement message;


    public String verifyLogin() {
        String user = welcome.getText();
        if(user.contains("welcome")){
            System.out.println("Logged-in success");
        }
        return user;
    }
    public OrderPage goToAccountMenu() {
        return new OrderPage(driver);
    }


}
