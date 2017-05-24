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

    @FindBy (xpath = "//*[@id=\"second_screen\"]/form/div[8]/button")
    public WebElement createMyFreeAccButton;



}
