package PageObjects;

import AutomationFramework.CommonTask;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    //Declare WebDriver
    protected static WebDriver driver;

    public MainPage(WebDriver passedDriver) {
        this.driver = passedDriver;
        PageFactory.initElements(passedDriver, this);
    }

    //Set locators for bellow elements

    @FindBy(id = "")
    public WebElement usernameField;

    @FindBy(id = "")
    public WebElement passwordField;

    @FindBy(id = "")
    public WebElement loginButton;

    public void loginWithUsernameAndPassword(String username, String password){
        CommonTask.setInputField(driver, usernameField, username);
        CommonTask.setInputField(driver, passwordField, password);
        loginButton.click();
    }



}
