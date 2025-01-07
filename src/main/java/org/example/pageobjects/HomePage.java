package org.example.pageobjects;

import org.example.utils.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Utility {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(linkText = "Sign In")
    WebElement signInLink;

    @FindBy(linkText = "Create an Account")
    WebElement createAnAccount;

    @FindBy(id = "email")
    WebElement emailId;

    @FindBy(id = "pass")
    WebElement password;

    @FindBy(id = "send2")
    WebElement signIn;


    public void goTo(String url) {
        driver.get(url);
        System.out.println(driver.getTitle());
    }


    public CreateAccount createAccount() {
        createAnAccount.click();
        return new CreateAccount(driver);
    }

    public LoginApplication signIn(){
        signInLink.click();
        System.out.println(getUserNmae()+" -- "+getPassword());
        emailId.sendKeys(getUserNmae());
        password.sendKeys(getPassword());
        signIn.click();
        return new LoginApplication(driver);
    }



}
