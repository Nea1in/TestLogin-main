import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Helper {
    static Logger logger = LogManager.getLogger(TestLogin.class);

    public static void clearInputByLocator(WebDriver driver, String locator, String message ){
        if(message != null ) {
            logger.info(message);
        }
        try {
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        } catch (NoSuchElementException e) {
            String errorMsg = "Element not found: " + locator + " - " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        } catch (ElementNotInteractableException e) {
            String errorMsg = "Element not interactable: " + locator + " - " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        } catch (Exception e) {
            String errorMsg = "Unexpected error during filling input: " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        }
    }

    public static void fillInputByLocator(WebDriver driver, String locator, String text, String message ){
        if(message != null ) {
            logger.info(message);
        }
        try {
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(text);
        } catch (NoSuchElementException e) {
            String errorMsg = "Element not found: " + locator + " - " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        } catch (ElementNotInteractableException e) {
            String errorMsg = "Element not interactable: " + locator + " - " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        } catch (Exception e) {
            String errorMsg = "Unexpected error during filling input: " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        }
    }
    public static void clickButtonByLocator(WebDriver driver, String locator, String message ){
        if(message != null ) {
            logger.info(message);
        }
        try {
        WebElement button = driver.findElement(By.xpath(locator));
        button.click();
        } catch (NoSuchElementException e) {
            String errorMsg = "Element not found: " + locator + " - " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        } catch (ElementNotInteractableException e) {
            String errorMsg = "Element not interactable: " + locator + " - " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        } catch (Exception e) {
            String errorMsg = "Unexpected error during filling input: " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        }
    }

    public static String validateMessageByLocator(WebDriver driver,String locator, String message ){
        String errorMessageText;
        try {
        WebElement elementMessage = driver.findElement(By.xpath(locator));
        errorMessageText = elementMessage.getText();
        } catch (NoSuchElementException e) {
            String errorMsg = "Element not found: " + locator + " - " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        } catch (ElementNotInteractableException e) {
            String errorMsg = "Element not interactable: " + locator + " - " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        } catch (Exception e) {
            String errorMsg = "Unexpected error during filling input: " + e.getMessage();
            logger.error(errorMsg);
            throw new TestExecutionException(errorMsg, e);
        }
        if(message != null ) {
            logger.info("Error message text: " + errorMessageText);
        }
        return errorMessageText;
    }


    public static boolean waitForUrlChange(WebDriverWait wait, String expectedUrl) {
        try {
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            Helper.logger.info("Redirect to inventory page, current URL: " + expectedUrl);
            return true;
        } catch (TimeoutException e) {
            Helper.logger.info("URL not change.");
            return false;
        }
    }
    public static boolean waitForTitleChange(WebDriverWait wait, String expectedTitle) {
        try {
            wait.until(ExpectedConditions.titleIs(expectedTitle));
            Helper.logger.info("Expected title Swag Labs, current title: " + expectedTitle);
            return true;
        } catch (TimeoutException e) {
            Helper.logger.info("Title not change.");
            return false;
        }
    }
}
