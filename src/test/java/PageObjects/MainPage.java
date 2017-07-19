package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;

import static org.testng.asserts.SoftAssert.*;

import static AutomationFramework.CommonTask.*;
import static AutomationFramework.DataItems.*;


public class MainPage {

    //Declare WebDriver
    protected static WebDriver driver;

    public MainPage(WebDriver passedDriver) {
        this.driver = passedDriver;
        PageFactory.initElements(passedDriver, this);
    }

    //Set locators for elements

    @FindBy(css = "body > section.navbar-fixed-top.indie-navbar > nav > div > div.hidden-xs > ul > li:nth-child(2) > a")
    public WebElement loginButton;

    @FindBy (xpath = "/html/body/nav/div/div[5]/ul/li[1]/ul/li[4]/a")
    public WebElement logOutButton;

    @FindBy (css = "body > section.navbar-fixed-top.indie-navbar > nav > div > div.hidden-xs > ul > li:nth-child(1) > a")
    public WebElement createFreeAccButtonMainPage;

    @FindBy (xpath = "//*[@id=\"PageContent\"]/div/div[1]/div/button/p")
    public WebElement facebookLoginButton;

    @FindBy (id = "userlabel")
    public WebElement userLabel;

    @FindBy (css = "body > div.notification-wrapper")
    public WebElement errorNotification;

    @FindBy (css = "#lg-navigation > ul > li:nth-child(1) > a")
    public WebElement photoVideoMenu;

    @FindBy (css = "#lg-navigation > ul > li:nth-child(2) > a")
    public WebElement reportersMenu;

    @FindBy (css = "#lg-navigation > ul > li:nth-child(3) > a")
    public WebElement aboutMenu;

    @FindBy (css = "#lg-navigation > ul > li:nth-child(4) > a" )
    public WebElement contactMenu;

    @FindBy (xpath = "//*[@id=\"Newsfeed\"]/div[1]/span")
    public WebElement typeCategory;

    @FindBy (css = "#Newsfeed > div.main-filters.clearfix > ul > li.active > a" )
    public WebElement contentCategory;

    @FindBy (css = "#Newsfeed > div.main-filters.clearfix > ul > li:nth-child(2) > a")
    public WebElement tagsCategory;

    @FindBy (css = "#Newsfeed > div.main-filters.clearfix > ul > li:nth-child(3) > a")
    public WebElement peopleCategory;

    @FindBy (css = "#Newsfeed > div.main-filters.clearfix > ul > li:nth-child(4) > a")
    public WebElement placesCategory;

    @FindBy (css = "#Newsfeed > div:nth-child(2) > ul > li.active > a" )
    public WebElement allCategory;

    @FindBy (css = "#Newsfeed > div:nth-child(2) > ul > li:nth-child(2) > a")
    public WebElement breakingCategory;

    @FindBy (css = "#Newsfeed > div:nth-child(2) > ul > li:nth-child(3) > a")
    public WebElement newsCategory;

    @FindBy (css = "#Newsfeed > div:nth-child(2) > ul > li:nth-child(4) > a")
    public  WebElement creativeCategory;

    @FindBy (css = "#Newsfeed > div:nth-child(2) > ul > li:nth-child(5) > a")
    public WebElement natureCategory;

    @FindBy (css = "#Newsfeed > div:nth-child(2) > ul > li:nth-child(6) > a")
    public WebElement sportCategory;


    //General methods used in Main Page

    public void logInUsernameAndPassword (String username, String password){
        LoginPage lp = new LoginPage(driver);
        loginButton.click();
        setInputField(driver, lp.emailField, username);
        setInputField(driver, lp.passwordField, password);
        lp.emailLoginButton.click();
        waitGeneralMethod(driver, userLabel);
    }

    public void logInEmailIncorrect (String username1, String password1, String username2, String password2) {
        LoginPage lp = new LoginPage(driver);
        loginButton.click();
        setInputField(driver, lp.emailField, username1);
        setInputField(driver, lp.passwordField, password1);
        lp.emailLoginButton.click();
        waitGeneralMethod(driver, errorNotification);
        Assert.assertTrue(errorNotification.isDisplayed(), "Wrong email/password notification is not displayed");
        setInputField(driver,  lp.emailField, username2);
        setInputField(driver,  lp.passwordField, password1);
        lp.emailLoginButton.click();
        waitGeneralMethod(driver, errorNotification);
        Assert.assertTrue(errorNotification.isDisplayed(), "Wrong email/password notification is not displayed");
        setInputField(driver,  lp.emailField, username1);
        setInputField(driver,  lp.passwordField, password2);
        lp.emailLoginButton.click();
        waitGeneralMethod(driver, errorNotification);
    }

    public void logOut () {
        userLabel.click();
        logOutButton.click();
        waitGeneralMethod(driver, createFreeAccButtonMainPage);
    }

    public void logInFacebook () {
        LoginPage lp = new LoginPage(driver);
        loginButton.click();
        facebookLoginButton.click();

        //Change view to facebook log in modal prompt
        String oldWindow = driver.getWindowHandle();
        for (String newWindow : driver.getWindowHandles()) {
            driver.switchTo().window(newWindow);
        }
        setInputField(driver, lp.fbUsername, validFbUsername);
        setInputField(driver, lp.fbPassword, validFbPassword);
        lp.fbLogin.click();

        //Return to test environment window
        driver.switchTo().window(oldWindow);
        waitGeneralMethod(driver, userLabel);
    }



    protected String gerRandomString() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public void signUpEmail () throws InterruptedException, IOException {
        CreateAccountPage ca = new CreateAccountPage(driver);
        LoginPage lp = new LoginPage(driver);
        createFreeAccButtonMainPage.click();
        ca.emailSignUpButton.click();
        setInputField(driver, lp.emailField, "automateduser" + gerRandomString() + "@test.com");
        setInputField(driver, ca.usernameField, "Automated User" + gerRandomString());
        setInputField(driver, lp.passwordField, "password");
        setInputField(driver, ca.confirmPassField, "password");
        ca.countryDropDownField.click();
        ca.countryDropDownField.sendKeys("Romania");
        ca.acceptCheckbox.click();
        ca.createMyFreeAccButton.click();
        waitGeneralMethod(driver, userLabel);
    }

    public void signUpEmailIncorrect () {

        //Attempt to save without any details
        CreateAccountPage ca = new CreateAccountPage(driver);
        LoginPage lp = new LoginPage(driver);
        createFreeAccButtonMainPage.click();
        ca.emailSignUpButton.click();
        ca.createMyFreeAccButton.click();
        waitGeneralMethod(driver, errorNotification);
        Assert.assertTrue(errorNotification.isDisplayed(), "Marked fields notification is not displayed");
        Assert.assertTrue(getFieldsMarkedError(lp.emailField, "border-color"), "Mandatory fields are not highlighted red");
        Assert.assertTrue(getFieldsMarkedError(ca.usernameField, "border-color"), "Mandatory fields are not highlighted red");
        Assert.assertTrue(getFieldsMarkedError(ca.countryDropDownField, "border-color"), "Mandatory fields are not highlighted red");
        Assert.assertTrue(getFieldsMarkedError(lp.passwordField, "border-color"), "Mandatory fields are not highlighted red");
        Assert.assertTrue(getFieldsMarkedError(ca.confirmPassField, "border-color"), "Mandatory fields are not highlighted red");

        //Attempt to save with incorrect email format
        setInputField(driver, lp.emailField,"incorrectemail@com");
        setInputField(driver, ca.usernameField, "incorrect username");
        setInputField(driver, lp.passwordField, "password");
        setInputField(driver, ca.confirmPassField, "password");
        ca.countryDropDownField.click();
        ca.countryDropDownField.sendKeys("Romania");
        ca.createMyFreeAccButton.click();
        Assert.assertEquals(errorNotification.getText(), "Please fill in fields marked with red", "Marked fields notification message is incorrect" );

        //Attempt to save with password not matching
        setInputField(driver, lp.emailField, "incorrect@email.com");
        setInputField(driver, ca.confirmPassField, "pass");
        ca.createMyFreeAccButton.click();
        Assert.assertTrue(errorNotification.isDisplayed(), "Marked fields notification is not displayed" );
    }

    public boolean getFieldsMarkedError (WebElement element, String value) {
        Boolean result = false;

        try {
            String css = element.getCssValue(value);
            if (css != null) {
                result = true;
            }
        } catch(Exception e) {
            System.out.println("Exception handled by catch");
        }
            return result;
    }

    public void cycleThroughAuthScenarios () {
        //Create new objects
        LoginPage lp = new LoginPage(driver);
        CreateAccountPage ca = new CreateAccountPage(driver);
        RecoverAccountPage ra = new RecoverAccountPage(driver);
        SoftAssert softAssert = new SoftAssert();

        //Assert main page labels from menus and categories
        softAssert.assertEquals(photoVideoMenu.getText(), "PHOTOS & VIDEOS", "Photo and videos menu doesn't have the correct label");
        softAssert.assertEquals(reportersMenu.getText(), "REPORTERS", "Reporters menu doesn't have the correct label");
        softAssert.assertEquals(aboutMenu.getText(), "ABOUT", "About menu doesn't have the correct label");
        softAssert.assertEquals(contactMenu.getText(), "CONTACT", "Contact menu doesn't have the correct label");
        /*softAssert.assertEquals(typeCategory.getText(), "Type");
        softAssert.assertEquals(contentCategory.getText(), "Content");
        softAssert.assertEquals(tagsCategory.getText(), "Tags");
        softAssert.assertEquals(peopleCategory.getText(), "People");
        softAssert.assertEquals(placesCategory.getText(), "Places");
        softAssert.assertEquals(allCategory.getText(), "All");
        softAssert.assertEquals(breakingCategory.getText(), "Breaking");
        softAssert.assertEquals(newsCategory.getText(), "News");
        softAssert.assertEquals(creativeCategory.getText(), "Creative");
        softAssert.assertEquals(natureCategory.getText(), "Nature");
        softAssert.assertEquals(sportCategory.getText(), "Sport");*/

        createFreeAccButtonMainPage.click();
        softAssert.assertEquals(driver.getCurrentUrl(), "http://staging.indieframe.com/create-account/", "Create account URL is not correct");
        softAssert.assertEquals(ca.createFreeAccMessage.getText(), "CREATE YOUR FREE ACCOUNT IN SECONDS", "Create free account message is not correct");
        ca.createAccLogInButton.click();
        softAssert.assertEquals(driver.getCurrentUrl(), "http://staging.indieframe.com/login/", "Log in URL is not correct");
        softAssert.assertEquals(lp.logInMessage.getText(), "LOG IN", "Log in message is not correct");
        lp.recoverPasswordButton.click();
        softAssert.assertEquals(driver.getCurrentUrl(), "http://staging.indieframe.com/recover-account/", "Recover password URL is not correct");
        softAssert.assertEquals(ra.recoverMessage.getText(), "RECOVER YOUR PASSWORD", "Recover message is not correct");
        softAssert.assertAll();

    }




}
