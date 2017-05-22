package com.Indieframe.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.MainPage;
import com.Indieframe.DriverBase;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.*;

/**
 * Created by sraschitor on 19.05.2017.
 */
public class Login_and_Signup extends DriverBase {

    @Test (groups = {"Indie"})

    public void loginWithFacebook() throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Log in to Test environment with Facebook account", "TestID LGFB");
        System.out.println("You have reached Test environment, proceed with Facebook login...");

        MainPage mp = new MainPage(driver);
        mp.loginWithFacebook();
        System.out.println("You have logged in Test environment with Facebook account, proceed with verification...");

        //Assert user log in name
        AssertJUnit.assertEquals("Username logged in is not correct", DataItems.displayNameFB, mp.userLabel.getText());
        System.out.println("You have successfully logged in Test environment with a particular username created with Facebook.");

    }

    @Test (groups = {"Indie"})

    public void loginWithEmail() throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Log in to Test environment with email and password", "TestID LGEM", DataItems.validUsername, DataItems.validPassword);
        System.out.println("You have reached Test environment and logged in with email and password, proceed with verification...");

        //Assert user log in name
        MainPage mp = new MainPage(driver);
        AssertJUnit.assertEquals("Username logged in is not correct", DataItems.displayNameEmail, mp.userLabel.getText());
        System.out.println("You have successfully logged in Test environment with a a particular username and password.");

        //Log out of test environment and verify
        System.out.println("Now log out and verify if you will continue as guest...");
        mp.logOut();
        AssertJUnit.assertEquals("You are still logged in Test environment with an username", "Create free account", mp.createFreeAccButtonMainPage.getText());
        System.out.println("You have successfully logged out of the Test environment and are currently navigating as guest.");


    }

    @Test (groups = {"Indie"})

    public void signUpWithEmail() throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Sign up in Test environment with email and password", "TestID SIEM");
        System.out.println("You have reached Test environment, proceed with email sign up...");

        //Sign up with a new email account
        MainPage mp = new MainPage(driver);
        mp.signUpWithEmail();
        System.out.println("You have signed up with a new account from email, proceed with verification... ");

        //Assert user log in name
        AssertJUnit.assertEquals("Username logged in is not correct", "Automated User" + CommonTask.getUserFileNumber(), mp.userLabel.getText());
        System.out.println("You have successfully created a new username account with an email address.");

    }

    @Test (groups = {"Indie"})

    public void signUpWithEmailNeg () throws Exception {
        WebDriver driver = getDriver();

        //Log in test environment
        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Sign up in Test environment with email and password", "TestID SIEMN");
        System.out.println("You have reached Test environment, proceed with email sign up...");

        //Attempt to sign up with a new email account using incorrect details (3 scenarios: empty fields, incorrect email format, not matching password)
        MainPage mp = new MainPage(driver);
        mp.signUpWithEmailIncorrect();
        Assert.assertTrue(mp.getFieldsMarkedError(mp.confirmPassField, "border-color"), "Mandatory fields are not highlighted red");
        System.out.println("Creating a new user with incomplete details was not possible, error notifications are properly displayed.");

    }




}

