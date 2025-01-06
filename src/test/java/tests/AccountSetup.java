package tests;

import org.example.pageobjects.CreateAccount;
import org.example.pageobjects.LoginApplication;
import org.testng.Assert;
import org.testng.annotations.Test;
import resource.TestComponents.BaseClassTest;
import resource.TestComponents.FailureRetry;

public class AccountSetup extends BaseClassTest  {

String userName;
String Password;

    @Test(priority = 1,retryAnalyzer= FailureRetry.class)
    public void accountSignUpTest(){
        CreateAccount signUp = homePage.createAccount();
        signUp.CreateAnAccount();
        String msg = signUp.verifyLogin();
        System.out.println(msg);
        Assert.assertEquals(msg,"Thank you for registering with Main Website Store.");


    }

    @Test(retryAnalyzer= FailureRetry.class, dependsOnMethods = {"accountSignUpTest"})
    public void LoginApplicationTest(){
        LoginApplication login =homePage.signIn();
        String s =login.verifyLogin();
        System.out.println(s);
        Assert.assertTrue(s.contains("Welcome,"));

    }







}
