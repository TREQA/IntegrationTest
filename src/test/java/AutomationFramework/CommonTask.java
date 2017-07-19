package AutomationFramework;

import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonTask {

    public static void setDropDownField(WebDriver driver,By fieldLocator,String item)  {
        //Set value of drop down field (referenced by fieldLocator) to "item"

        //Wait for field to be available
        WebElement wait = Wait.clickable(driver,fieldLocator);

        //"Select" object provides useful methods for drop-down fields
        Select select = new Select(driver.findElement(fieldLocator));
        driver.findElement(fieldLocator).click();

        //Wait for option to be visible
        Boolean waitForOption = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.optionPresent(item,select));

        //Select the option
        select.selectByVisibleText(item);

        //Wait for selection to be item
        boolean waitForSelection = Wait.selectionToBe(driver,fieldLocator,item);
    }

    public static void setDropDownField(WebDriver driver,WebElement fieldLocator,String item)  {
        //Set value of drop down field (referenced by fieldLocator) to "item"

        //Wait for field to be available
        WebElement wait = Wait.clickable(driver,fieldLocator);

        //"Select" object provides useful methods for drop-down fields
        Select select = new Select(fieldLocator);
        fieldLocator.click();

        //Wait for option to be visible
        Boolean waitForOption = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.optionPresent(item,select));

        //Select the option
        select.selectByVisibleText(item);

        //Wait for selection to be item
        boolean waitForSelection = Wait.selectionToBe(driver,fieldLocator,item);
    }

    public static boolean setDropDownFieldAndCheckThatElementIsNotPresent(WebDriver driver,By fieldLocator,String item)  {
        //Set value of drop down field (referenced by fieldLocator) to "item"

        //Wait for field to be available
        WebElement wait = Wait.clickable(driver,fieldLocator);

        //"Select" object provides useful methods for drop-down fields
        Select select = new Select(driver.findElement(fieldLocator));
        driver.findElement(fieldLocator).click();

        //Wait for option to be visible
        Boolean waitForOption = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.not(CommonTask.optionPresent(item,select)));

        return waitForOption;
    }


    public static void clearDropDownField(WebDriver driver, By fieldLocator) {
        //Clear selection from drop down field

        //Wait for field to be available
        WebElement wait = Wait.clickable(driver,fieldLocator);

        //Select object used to interact with field
        Select select = new Select(driver.findElement(fieldLocator));

        //Click field to reveal options
        driver.findElement(fieldLocator).click();

        //Select option 0, also displayed as "Select"
        select.selectByIndex(0);

    }


    public static void setTextField(WebDriver driver,By fieldLocator, String item) {
        //Enter "item" into a text field

        //Wait for the element to be available
        WebElement element = Wait.clickable(driver,fieldLocator);

        //Click the field to focus the cursor here
        element.click();

        //Enter "item"
        element.sendKeys(item);

        //Wait for the value of "item" to be found in the field
        boolean waitForText = Wait.textPresent(driver,fieldLocator, item);
    }

    public static void setInputField(WebDriver driver, By fieldLocator, String item) {
        //Enter "item" into a text field. This is an alternative method to setTextField, as the input field requires a different wait condition

        //Wait for the field to be available
        WebElement element = Wait.clickable(driver,fieldLocator);

        //Click the field
        element.click();

        //Clear field
        element.clear();

        //Enter "item" into the field
        element.sendKeys(item);
    }

    public static void setInputField(WebDriver driver, WebElement element, String item) {
        //Enter "item" into a text field. This is an alternative method to setTextField, as the input field requires a different wait condition

        //Wait for the field to be available
        Wait.clickable(driver,element);

        //Click the field
        element.click();

        //Clear field
        element.clear();

        //Enter "item" into the field
        element.sendKeys(item);

    }

    public static void setInputFieldAlt(WebDriver driver, String id, String item) {
        //Enter "item" into an input field. This is an alternative method to the ones above

        //Wait for the field to be available
        WebElement waitForField = Wait.clickable(driver,By.id(id));

        //Use Javascript to set the element's text
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('"+id+"').setAttribute('attr', '"+item+"')");

        //Wait for the text to be present in the field
        boolean waitForText = Wait.textPresentInput(driver,By.id(id),item);
    }

    public static void setCheckBox(WebDriver driver, By fieldLocator) {
        //Check a checkbox, ensuring its value is "checked"

        //Only perform the condition if it is not already selected to avoid a timeout exception


        if (!driver.findElement(fieldLocator).isSelected()) {
            //Wait for element to be available
            WebElement element = Wait.clickable(driver,fieldLocator);


            //Check box
            element.click();

            //Wait for box to be checked
            Boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsChecked(driver.findElement(fieldLocator)));
        }

    }

    public static void setCheckBox(WebDriver driver, WebElement element) {
        //Check a checkbox, ensuring its value is "checked"

        //Only perform the condition if it is not already selected to avoid a timeout exception


        if (!element.isSelected()){
            element.click();
            //Check box
            element.click();

            //Wait for box to be checked
            Boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsChecked(element));
        }
    }


    public static void unSetCheckBox(WebDriver driver, By fieldLocator) {
        //Check a checkbox, ensuring its value is "unchecked"

        //Only perform the condition if it is not already selected to avoid a timeout exception
        if (driver.findElement(fieldLocator).isSelected()) {
            //Wait for element to be available
            WebElement element = Wait.clickable(driver,fieldLocator);

            //Check box
            element.click();

            //Wait for box to be unchecked
            Boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsNotChecked(driver.findElement(fieldLocator)));
        }

    }


    public static void clickInputCheckBox(WebDriver driver, By fieldLocator) {
        //Click an "input" checkbox, turning its value from checked to unchecked or visa-versa. These checkboxes are different to the ones handled above

        //Wait for element to be available
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fieldLocator));

        //Click element
        btn.click();
    }


    public static void setDateField(WebDriver driver, WebElement webElement) {
        //Set the value of a date field. The algorithm will selet a date 3 days in advance,
        //unless the date is beyond the 25th, when the 3rd of next month will be selected

        //Wait for element to be available
        Wait.clickable(driver, webElement);

        //Click element to reveal date picker
        webElement.click();

        //Get the current day of the month
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        //New action for clicking
        Actions clickDatePicked = new Actions(driver);

        if (dayOfMonth < 25) {
            //Calculate day of month three days in future
            String date = String.valueOf((int) dayOfMonth + 3);

            //Find label with corect date and click it
            By dateLocator = By.xpath("//a[contains(text(),'" + date + "')]");
            clickDatePicked.click(driver.findElement(dateLocator)).build().perform();
        } else {
            //Find label for next month and click
            By nextMonthLocator = By.cssSelector("th.next");
            clickDatePicked.click(driver.findElement(nextMonthLocator)).build().perform();

            //Find label for 3rd of the month and click
            By thirdOfMonthLocator = By.xpath("//*[@id=\"drawers\"]/div/div/div[1]/div/div[2]/div[1]/table/tbody/tr[1]/td[7]");
            driver.findElement(thirdOfMonthLocator).click();
        }
    }

    public static void setSearchFieldAlt(WebDriver driver, WebElement searchLocator, String item) {
        //Set the value of search field (located by webelement) to "item"

        //Locator for input field used to enter search term
        searchLocator.click();

        //Freaking workaround for //WebElement resultLocator = driver.findElement(By.xpath("//input[contains(@class,'select2-input')]")); //("//input[starts-with(@id,'s2id_autogen')]")); //
        String text = searchLocator.toString();                 //Convert webelement to String to use for the second locator
        String[] parts = text.split("select2-chosen-");         //Split to second part of string
        String part2 = parts[1];
        String ab = part2.substring(0, part2.length()-1);       //Substract only the number


        //Send search item to second locator
        WebElement resultLocator = driver.findElement(By.id("s2id_autogen" + ab + "_search"));
        resultLocator.sendKeys(item);
        resultLocator.click();

        //Wait for search to be finished
        WebElement abc = driver.findElement(By.cssSelector("li.select2-searching"));
        Wait.textNotPresent(driver, abc, "Loading");

        //enter results in searchbox
        Actions clickDatePicked = new Actions(driver);
        clickDatePicked.sendKeys(resultLocator, Keys.ENTER).build().perform();

        //Wait for search result appear
        Wait.textPresent(driver, searchLocator, item);
    }

    public static void setDropDownFieldAlt(WebDriver driver, WebElement webElement,String item) {

        if (webElement.getText().equals(item)) {
            System.out.println("DropDown option already selected");
        }
        else {
            webElement.click();
            WebElement dropDownOption = webElement.findElement(By.xpath("//*[.=\"" + item + "\"]"));
            dropDownOption.click();

            //Wait for DropDown to have selected item
            Wait.textPresent(driver, webElement, item);
        }
    }

    public static void waitForOverlay(WebDriver driver){
        //Wait for an overlay frame to be available and switch control to it. Generally reliable. Designed for RCTests

        //Wait for frame
        WebDriver wait = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.tagName("iframe"))));
    }

    public static void setSearchFieldCreateContract(WebDriver driver,WebElement fieldLocator, String item) {
        //Set the value of search field (located by fieldLocator) to "item"

        Wait.clickable(driver,fieldLocator);

        //Click on the search field
        fieldLocator.click();

        fieldLocator.clear();

        //Enter the searched term
        fieldLocator.sendKeys(item);

        //Wait.textPresent(driver,firstResultCreateContract,item);

        fieldLocator.sendKeys(Keys.ENTER);

        Wait.textPresentInput(driver,fieldLocator, item);

    }

    public static boolean checkAlertText(WebDriver driver,String expected){
        Alert alert = Wait.alert(driver);
        String text = alert.getText().trim();
        System.out.println("Alert found: " + text);
        if(text.equals(expected)){
            alert.accept();
            System.out.println("Alert accepted!");
            return true;
        }
        System.out.println("Alert text does not match");
        return false;
    }

    public static boolean checkAlertTextDeny(WebDriver driver,String expected){
        Alert alert = Wait.alert(driver);
        String text = alert.getText().trim();
        System.out.println("Alert found: " + text);
        if(text.equals(expected)){
            alert.dismiss();
            System.out.println("Alert rejected!");
            return true;
        }
        System.out.println("Alert text does not match");
        return false;
    }

    public static void waitGeneralMethod(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, DataItems.shortWait);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String generateUserNumber (String filePath)  {
        //Generate a number used to increment username/email for new users at signup

        //Initialise a Number with value null to begin
        String usrNumber = "null";
        try {

            //Access file to read
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            //Get current ID
            String idString = br.readLine();
            int id = Integer.valueOf(idString);

            //Increment ID to be written back to file
            id++;

            //Safely close file
            br.close();

            //Access file to write
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

            //Write incremented id to file
            bw.write(String.valueOf(id));

            //Safely close file
            bw.close();

            usrNumber = String.valueOf(id);

        } catch (IOException e) {
            System.out.println(" IOException handling the ID file");
        }

            //Return generated number
          return usrNumber;
    }

    public static String generateRandomUserNumber (String filePath) throws IOException {

        String number = "null";

        try {
        Random generator = new Random();
        int i = generator.nextInt(1999999999);

        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        //Write incremented id to file
        bw.write(String.valueOf(i));

        //Safely close file
        bw.close();

        number = String.valueOf(i);

    } catch (IOException e) {
        System.out.println(" IOException handling the ID file");
    }

        return number;
    }




    public static String getUserFileNumber () {
        String usrNumber = "null";

        try {

            //Access file to read
            BufferedReader br = new BufferedReader(new FileReader(DataItems.usernameFilePath));

            //Get current ID
            String idString = br.readLine();

            br.close();

            usrNumber = idString;

        } catch (IOException e) {
            System.out.println(" IOException handling the ID file");
        }

        //Return generated number
        return usrNumber;
    }





    public static ExpectedCondition<Boolean> textContains(final String text, final String elementText) {
        //A wait condition which will ensure the elementText variable contains the text variable before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (elementText.contains(text)) {
                    return true;
                } else {
                    return false;
                }
            }

        };
    }

    public static ExpectedCondition<Boolean> boxIsChecked(final WebElement element) {
        //A wait condition which will ensure a check box is checked before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (element.getAttribute("checked").equals("true")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> boxIsNotChecked(final WebElement element) {
        //A wait condition which will ensure a checkbox is unchecked before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (element.getAttribute("checked").equals("false")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> boxIsUnchecked(final WebElement element) {
        //A wait condition which will ensure a checkbox is not checked before continuing (alternative method)
        return new ExpectedCondition<Boolean>() {
            public Boolean apply (WebDriver f) {
                if (element.isSelected()) {
                    return false;
                } else {
                    return true;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInput(final By locator, final String text) {
        //A wait condition which will ensure text is present in the field (for input fields) before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                WebElement inputField = f.findElement(locator);
                if (inputField.getAttribute("value").equals(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInput(final WebElement locator, final String text) {
        //A wait condition which will ensure text is present in the field (for input fields) before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                WebElement inputField = locator;
                if (inputField.getAttribute("value").equals(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInput2(final String element, final String text) {
        //A wait condition which will ensure text is present in the field (for input fields) before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {

                WebElement inputField = f.findElement(By.id("BrandName"));
                if (inputField.getAttribute("value").equals(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInput3(final String element, final String text) {
        //A wait condition which will ensure text is present in the field (for input fields) before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {

                WebElement inputField = f.findElement(By.id("CountryCountryCode"));
                if (inputField.getAttribute("value").equals(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> selectionToBePresent(final By locator) {
        //A wait condition which will ensure a drop-down menu is not blank before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                Select select = new Select(f.findElement(locator));
                if (select.getFirstSelectedOption().equals(null)) {
                    return false;
                } else {
                    return true;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> selectionToBe(final By locator, final String item) {
        //A wait condition which will ensure the "item" is selected before contiuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                Select select = new Select(f.findElement(locator));
                if (select.getFirstSelectedOption().getText().equals(item)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> selectionToBe(final WebElement locator, final String item) {
        //A wait condition which will ensure the "item" is selected before contiuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                Select select = new Select(locator);
                if (select.getFirstSelectedOption().getText().equals(item)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> optionPresent(final String item, final Select select) {
        //A wait condition which will ensure an option is present in the drop-down menu before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                List<WebElement> options = select.getOptions();
                boolean returnMe = false;
                for (WebElement element : options) {
                    if (element.getText().equals(item)) {
                        returnMe = true;
                        break;
                    }
                }
                return returnMe;
            }
        };
    }





}
