package com.Indieframe.tests;

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

/**
 * Created by sraschitor on 19.05.2017.
 */

public class Login_and_Signup_Test extends DriverBase {

    @Test (groups = {"Indie"})

    public void loginWithFacebook() throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Log in to Test environment with Facebook account", "TestID LGFB");
        System.out.println("You have reached Test environment, proceed with Facebook login...");

        MainPage mp = new MainPage(driver);
        mp.logInFacebook();
        System.out.println("You have logged in Test environment with Facebook account, proceed with verification...");

        //Assert user log in name
        AssertJUnit.assertEquals("Username logged in is not correct", DataItems.displayNameFB, mp.userLabel.getText());
        System.out.println("You have successfully logged in Test environment with a particular username created via Facebook.");

    }

    @Test (groups = {"Indie"})

    public void loginWithEmail() throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Log in to Test environment with email and password", "TestID LGEM", validUsername, validPassword);
        System.out.println("You have reached Test environment and logged in with email and password, proceed with verification...");

        //Assert user log in name
        MainPage mp = new MainPage(driver);
        AssertJUnit.assertEquals("Username logged in is not correct", displayNameEmail, mp.userLabel.getText());
        System.out.println("You have successfully logged in Test environment with a a particular username and password.");

        //Log out of test environment and verify
        System.out.println("Now log out and verify if you will continue as guest...");
        mp.logOut();
        AssertJUnit.assertEquals("You are still logged in Test environment with an username", "Create free account", mp.createFreeAccButtonMainPage.getText());
        System.out.println("You have successfully logged out of the Test environment and are currently navigating as guest.");

    }

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

    public void logInNeg () throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Log in Test environment negative scenarios", "TestID LGN");
        System.out.println("You have reached Test environment, proceed with negative log in...");

        //Attempt to log in with incorrect details (3 scenarios: incorrect email & password, incorrect email, incorrect password)
        MainPage mp = new MainPage(driver);
        LoginPage lp = new LoginPage(driver);
        mp.logInEmailIncorrect("incorrectUsername", "incorrectPassword", DataItems.validUsername, validPassword);
        Assert.assertTrue(mp.errorNotification.isDisplayed(), "Wrong email/password notification is not displayed");
        Assert.assertEquals(mp.errorNotification.getText(), "Wrong email/password!", "Wrong email/password notification doesn't have the correct message");

        //Attempt to recover password with incorrect email address
        lp.recoverPassword();
        Assert.assertTrue(mp.errorNotification.isDisplayed(), "Email not found notification is not displayed");
        Assert.assertEquals(mp.errorNotification.getText(), "Email not found", "Message from email not found notification is not correct");

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

    @Test (groups ={"Indie"})

    public void checkSignUp_LogIn_labels () throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Check major labels from Sign up/ log in menus", "TestID SULIL");
        System.out.println("You have reached Test environment, proceed with checking sign up and log in labels...");

        //Cycle through log in, sign up, recover password menus and use SoftAssert
        MainPage mp = new MainPage(driver);
        mp.cycleThroughAuthScenarios();

    }




}

