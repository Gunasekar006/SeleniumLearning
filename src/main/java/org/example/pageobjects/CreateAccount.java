package org.example.pageobjects;

import org.example.utils.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccount extends Utility {

    private WebDriver driver;

    public CreateAccount(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "firstname")
    WebElement firstName;

    @FindBy(id = "lastname")
    WebElement lastName;

    @FindBy(id = "email_address")
    WebElement email;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "password-confirmation")
    WebElement confirmPassword;

    @FindBy(className = "submit")
    WebElement createAccountBtn;

    @FindBy(className = "logged-in")
    WebElement welcome;

    @FindBy(className = "messages")
    WebElement message;

    String fname = "Guna";
    String lname = "Sekar";

//    String emailAddress = "gunasekar006" + rand_int1 + "@gmail.com";

    String pwd = "Welcome@1234";

    public WebElement CreateAnAccount() {
//        String emailAddress = "gunasekar006" + rand_int1 + "@gmail.com";
        System.out.println(getUserNmae());
        System.out.println(getPassword());
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        email.sendKeys(getUserNmae());
        password.sendKeys(getPassword());
        confirmPassword.sendKeys(getPassword());
        createAccountBtn.click();

        verifyLogin();
        return email;
    }

    public String verifyLogin() {
        String user = welcome.getText();
        System.out.println("Welcome:"+user);
        if(user.contains("Welcome, "+fname+" "+lname+"!")){
            System.out.println("Account has been created!. use below details to login");
            System.out.println("UserName: "+getUserNmae());
            System.out.println("Password: "+pwd);
        }
        return message.getText();
    }

}
