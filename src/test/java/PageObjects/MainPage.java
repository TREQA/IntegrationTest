package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class MainPage {

    //Declare WebDriver
    protected static WebDriver driver;

    public MainPage(WebDriver passedDriver) {
        this.driver = passedDriver;
        PageFactory.initElements(passedDriver, this);
    }

    //Set locators for bellow elements

    @FindBy(id = "inputEmail")
    public WebElement emailField;

    @FindBy (id = "inputUsername")
    public WebElement usernameField;

    @FindBy(id = "email")
    public WebElement fbUsername;

    @FindBy(id = "inputPassword")
    public WebElement passwordField;

    @FindBy (id = "inputConfirm")
    public WebElement confirmPassField;

    @FindBy(id = "pass")
    public WebElement fbPassword;

    @FindBy(xpath = "/html/body/nav/div/div[5]/ul/li[2]/a")
    public WebElement loginButton;

    @FindBy (xpath = "/html/body/nav/div/div[5]/ul/li[1]/ul/li[4]/a")
    public WebElement logOutButton;

    @FindBy (xpath = "/html/body/nav/div/div[5]/ul/li[1]/a")
    public WebElement createFreeAccButtonMainPage;

    @FindBy (xpath = "//*[@id=\"PageContent\"]/div/div[1]/div/button/p")
    public WebElement facebookLoginButton;

    @FindBy (id = "u_0_2")
    public WebElement fbLogin;

    @FindBy (xpath = "//*[@id=\"PageContent\"]/div/div[1]/div/form/div[4]/button")
    public WebElement emailLogin;

    @FindBy (xpath = "//*[@id=\"main_screen\"]/button[2]/p")
    public WebElement emailSignUpButton;

    @FindBy (id = "userlabel")
    public WebElement userLabel;

    @FindBy (xpath = "//*[@id=\"second_screen\"]/form/div[8]/button")
    public WebElement createMyFreeAccButton;

    @FindBy (id = "idCountry_of_origin")
    public WebElement countryDropDownField;

    @FindBy (xpath = "/html/body/div[2]/div")
    public WebElement markedFieldsNotification;


    public void loginWithUsernameAndPassword (String username, String password){
        loginButton.click();
        CommonTask.setInputField(driver, emailField, username);
        CommonTask.setInputField(driver, passwordField, password);
        emailLogin.click();
        CommonTask.waitGeneralMethod(driver, userLabel);
    }

    public void logOut () {
        userLabel.click();
        logOutButton.click();
        CommonTask.waitGeneralMethod(driver, createFreeAccButtonMainPage);
    }

    public void loginWithFacebook () {
        loginButton.click();
        facebookLoginButton.click();

        //Change view to facebook log in modal prompt
        String oldWindow = driver.getWindowHandle();
        for (String newWindow : driver.getWindowHandles()) {
            driver.switchTo().window(newWindow);
        }
        CommonTask.setInputField(driver, fbUsername, "mtbogdan@yahoo.com");
        CommonTask.setInputField(driver, fbPassword, "Tester14");
        fbLogin.click();

        //Return to test environment window
        driver.switchTo().window(oldWindow);
        CommonTask.waitGeneralMethod(driver, userLabel);
    }

    public void signUpWithEmail () throws InterruptedException {
        createFreeAccButtonMainPage.click();
        emailSignUpButton.click();
        CommonTask.setInputField(driver, emailField, "automateduser" + CommonTask.generateUserNumber(DataItems.emailFilePath) + "@test.com");
        CommonTask.setInputField(driver, usernameField, "Automated User" + CommonTask.generateUserNumber(DataItems.usernameFilePath));
        CommonTask.setInputField(driver, passwordField, "password");
        CommonTask.setInputField(driver, confirmPassField, "password");
        countryDropDownField.click();
        countryDropDownField.sendKeys("Romania");
        createMyFreeAccButton.click();
        CommonTask.waitGeneralMethod(driver, userLabel);
    }

    public void signUpWithEmailIncorrect () {

        //Attempt to save without any details
        createFreeAccButtonMainPage.click();
        emailSignUpButton.click();
        createMyFreeAccButton.click();
        CommonTask.waitGeneralMethod(driver, markedFieldsNotification);
        Assert.assertTrue(markedFieldsNotification.isDisplayed(), "Marked fields notification is not displayed");
        Assert.assertTrue(getFieldsMarkedError(emailField, "border-color"), "Mandatory fields are not highlighted red");
        Assert.assertTrue(getFieldsMarkedError(usernameField, "border-color"), "Mandatory fields are not highlighted red");
        Assert.assertTrue(getFieldsMarkedError(countryDropDownField, "border-color"), "Mandatory fields are not highlighted red");
        Assert.assertTrue(getFieldsMarkedError(passwordField, "border-color"), "Mandatory fields are not highlighted red");
        Assert.assertTrue(getFieldsMarkedError(confirmPassField, "border-color"), "Mandatory fields are not highlighted red");

        //Attempt to save with incorrect email format
        CommonTask.setInputField(driver, emailField,"incorrectemail@com");
        CommonTask.setInputField(driver, usernameField, "incorrect username");
        CommonTask.setInputField(driver, passwordField, "password");
        CommonTask.setInputField(driver, confirmPassField, "password");
        countryDropDownField.click();
        countryDropDownField.sendKeys("Romania");
        createMyFreeAccButton.click();
        Assert.assertEquals(markedFieldsNotification.getText(), "Please fill in fields marked with red", "Marked fields notification message is incorrect" );

        //Attempt to save with password not matching
        CommonTask.setInputField(driver, emailField, "incorrect@email.com");
        CommonTask.setInputField(driver, confirmPassField, "pass");
        createMyFreeAccButton.click();
        Assert.assertTrue(markedFieldsNotification.isDisplayed(), "Marked fields notification is not displayed" );




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
