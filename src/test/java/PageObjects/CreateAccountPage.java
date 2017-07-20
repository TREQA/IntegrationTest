package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sraschitor on 24.05.2017.
 */

public class CreateAccountPage {

    //Declare WebDriver
    protected static WebDriver driver;

    public CreateAccountPage(WebDriver passedDriver) {
        this.driver = passedDriver;
        PageFactory.initElements(passedDriver, this);
    }

    //Set locators for elements

    @FindBy(xpath = "//*[@id=\"main_screen\"]/button[2]/p")
    public WebElement emailSignUpButton;

    @FindBy (id = "inputUsername")
    public WebElement usernameField;

    @FindBy (id = "inputConfirm")
    public WebElement confirmPassField;

    @FindBy (id = "idCountry_of_origin")
    public WebElement countryDropDownField;

    @FindBy (css = "#PageContent > div > div.if-wrap-form > div > form > div:nth-child(7) > button")
    public WebElement createMyFreeAccButton;

    @FindBy (css = "#PageContent > div > div.if-wrap-form > div > header > h1")
    public WebElement createFreeAccMessage;

    @FindBy (css = "#PageContent > div > div.if-wrap-form > div > p:nth-child(6) > a")
    public WebElement createAccLogInButton;

    @FindBy (css = "#inputTerms")
    public WebElement acceptCheckbox;


}
