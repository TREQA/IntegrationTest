package PageObjects;


import AutomationFramework.CommonTask;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by sraschitor on 24.05.2017.
 */

public class LoginPage {

    //Declare WebDriver
    protected static WebDriver driver;

    public LoginPage(WebDriver passedDriver) {
        this.driver = passedDriver;
        PageFactory.initElements(passedDriver, this);
    }

    //Set locators for elements

    @FindBy(id = "inputEmail")
    public WebElement emailField;

    @FindBy(id = "inputPassword")
    public WebElement passwordField;

    @FindBy (xpath = "//*[@id=\"PageContent\"]/div/div[1]/div/form/div[4]/button")
    public WebElement emailLoginButton;

    @FindBy(id = "email")
    public WebElement fbUsername;

    @FindBy(id = "pass")
    public WebElement fbPassword;

    @FindBy (id = "u_0_2")
    public WebElement fbLogin;

    @FindBy (xpath = "//*[@id=\"PageContent\"]/div/div[1]/div/p[1]/a")
    public WebElement recoverPasswordButton;

    //General methods used in Login Page

    public void recoverPassword () {
        RecoverAccountPage re = new RecoverAccountPage(driver);
        recoverPasswordButton.click();
        CommonTask.setInputField(driver, emailField, "invalidEmail");
        re.sendRecoverButton.click();
    }



}
