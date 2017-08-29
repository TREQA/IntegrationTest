package com.Indieframe.tests;

/**
 * Created by sraschitor on 29.08.2017.
 */

import AutomationFramework.DataItems;
import PageObjects.CreateAccountPage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import com.Indieframe.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static AutomationFramework.CommonTask.*;
import static AutomationFramework.DataItems.*;





public class Signup_Test extends DriverBase {


    @Test (groups = {"Indie"}, enabled = false)

    public void signUpWithEmail() throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Sign up in Test environment with email and password", "TestID SIEM");
        System.out.println("You have reached Test environment, proceed with email sign up...");

        //Sign up with a new email account
        MainPage mp = new MainPage(driver);
        String compare = mp.getRandomString();
        mp.signUpEmail(compare);
        System.out.println("You have signed up with a new account from email, proceed with verification... ");

        //Assert user log in name
        AssertJUnit.assertEquals("Username logged in is not correct", "Automated User" + compare, mp.userLabel.getText());
        System.out.println("You have successfully created a new username account with an email address.");

    }

    @Test (groups = {"Indie"})

    public void signUpWithEmailNeg () throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Sign up in Test environment negative scenarios", "TestID SIEMN");
        System.out.println("You have reached Test environment, proceed with email negative sign up...");

        //Attempt to sign up with a new email account using incorrect details (3 scenarios: empty fields, incorrect email format, not matching password)
        MainPage mp = new MainPage(driver);
        CreateAccountPage ca = new CreateAccountPage(driver);
        mp.signUpEmailIncorrect();
        Assert.assertTrue(mp.getFieldsMarkedError(ca.confirmPassField, "border-color"), "Mandatory fields are not highlighted red");
        System.out.println("Creating a new user with incomplete details was not possible, error notifications are properly displayed.");

    }

}
