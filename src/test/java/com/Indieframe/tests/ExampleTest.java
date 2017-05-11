package com.Indieframe.tests;

import AutomationFramework.DataItems;
import com.Indieframe.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ExampleTest extends DriverBase {

    @Test
            (groups = "Test")
    public void Example_01() throws Exception {
        WebDriver driver = getDriver();

        Indieframe_Base base = new Indieframe_Base(driver);
        base.setUp("Test short description", "TestID (Example_01");

    }
}