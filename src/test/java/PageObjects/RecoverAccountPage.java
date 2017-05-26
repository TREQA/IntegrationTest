package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sraschitor on 24.05.2017.
 */

public class RecoverAccountPage {

    //Declare WebDriver
    protected static WebDriver driver;

    public RecoverAccountPage(WebDriver passedDriver) {
        this.driver = passedDriver;
        PageFactory.initElements(passedDriver, this);
    }

    //Set locators for elements

    @FindBy(id ="reset_data")
    public WebElement sendRecoverButton;

    @FindBy (css = "#PageContent > div > div.if-wrap-form > div > header > h1")
    public WebElement recoverMessage;

}
