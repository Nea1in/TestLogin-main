import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;


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
            logger.error("Element not found: " + locator + " - " + e.getMessage());
        } catch (ElementNotInteractableException e) {
            logger.error("Element not interactable: " + locator + " - " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during filling input: " + e.getMessage());
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
            logger.error("Element not found: " + locator + " - " + e.getMessage());
        } catch (ElementNotInteractableException e) {
            logger.error("Element not interactable: " + locator + " - " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during filling input: " + e.getMessage());
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
            logger.error("Element not found: " + locator + " - " + e.getMessage());
        } catch (ElementNotInteractableException e) {
            logger.error("Element not interactable: " + locator + " - " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during filling input: " + e.getMessage());
        }
    }

    public static String validateMessageByLocator(WebDriver driver,String locator, String message ){
        String errorMessageText = null;
        try {
        WebElement elementMessage = driver.findElement(By.xpath(locator));
        errorMessageText = elementMessage.getText();
        } catch (NoSuchElementException e) {
            logger.error("Element not found: " + locator + " - " + e.getMessage());
        } catch (ElementNotInteractableException e) {
            logger.error("Element not interactable: " + locator + " - " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during filling input: " + e.getMessage());
        }
        if(message != null ) {
            logger.info("Error message text: " + errorMessageText);
        }
        return errorMessageText;
    }
}
