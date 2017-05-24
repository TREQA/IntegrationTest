package com.Indieframe.tests;

import AutomationFramework.DataItems;
import PageObjects.MainPage;
import org.openqa.selenium.WebDriver;


public class Indieframe_Base {

    WebDriver driver;

    public Indieframe_Base(WebDriver passedDriver) {
        driver = passedDriver;
    }

    public void setUp(String testTitle, String scenarioID) {

        System.out.println("TEST: "+testTitle);
        System.out.println("Scenario ID: "+scenarioID);

        //Navigate to test site
        System.out.println("Navigating to TEST Environment...");
        driver.get(DataItems.targetURL);

    }

    public void setUp(String testTitle, String scenarioID, String username, String password) {

        System.out.println("TEST: "+testTitle);
        System.out.println("Scenario ID: "+scenarioID);

        //Navigate to test site
        System.out.println("Navigating to TEST Environment...");
        driver.get(DataItems.targetURL);

        //Login with valid credentials
        MainPage mp = new MainPage(driver);
        mp.logInUsernameAndPassword(username, password);
    }
}
