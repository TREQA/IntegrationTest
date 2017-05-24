package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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

    @FindBy(xpath = "/html/body/nav/div/div[5]/ul/li[2]/a")
    public WebElement loginButton;

    @FindBy (xpath = "/html/body/nav/div/div[5]/ul/li[1]/ul/li[4]/a")
    public WebElement logOutButton;

    @FindBy (xpath = "/html/body/nav/div/div[5]/ul/li[1]/a")
    public WebElement createFreeAccButtonMainPage;

    @FindBy (xpath = "//*[@id=\"PageContent\"]/div/div[1]/div/button/p")
    public WebElement facebookLoginButton;

    @FindBy (id = "userlabel")
    public WebElement userLabel;

    @FindBy (css = "body > div.notification-wrapper")
    public WebElement errorNotification;

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

    public void signUpEmail () throws InterruptedException {
        CreateAccountPage ca = new CreateAccountPage(driver);
        LoginPage lp = new LoginPage(driver);
        createFreeAccButtonMainPage.click();
        ca.emailSignUpButton.click();
        setInputField(driver, lp.emailField, "automateduser" + generateUserNumber(emailFilePath) + "@test.com");
        setInputField(driver, ca.usernameField, "Automated User" + generateUserNumber(usernameFilePath));
        setInputField(driver, lp.passwordField, "password");
        setInputField(driver, ca.confirmPassField, "password");
        ca.countryDropDownField.click();
        ca.countryDropDownField.sendKeys("Romania");
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




}
