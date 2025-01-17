package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.time.Duration;
import java.util.Random;

public class Utility {
    WebDriver driver;
    private String userNmae;
    private String password;
    static Random rand = new Random();
    static int rand_int1 = rand.nextInt(1001, 2000);
    final String userName = "gunasekar006" + rand_int1 + "@gmail.com";

    public Utility(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void selectProduct(String menu, String subMenu) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[text()=\'" + menu + "\']")));
        if (!subMenu.isEmpty()) {
            action.moveToElement(driver.findElement(By.xpath("//*[text()=\'" + subMenu + "\']"))).click().build().perform();
        }
    }

    public void selectValueFromDropDown(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByValue(value);
    }

    public String getUserNmae() {
        return userName;
    }

    public String getPassword() {
        return "Welcome@1234";
    }

    public void jsExecutor() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
//    executor.executeScript("document.getE")


    }


}
