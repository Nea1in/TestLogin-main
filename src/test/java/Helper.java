import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class Helper {
    static Logger logger = LogManager.getLogger(TestLogin.class);

    public static void clearInputByLocator(WebDriver driver, String locator, String massage ){
        if(massage.isEmpty() ) {
            logger.info(massage);
        }
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public static void fillInputByLocator(WebDriver driver, String locator, String text, String massage ){
        if(massage != null ) {
            logger.info(massage);
        }
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(text);
    }
    public static void clickButtonByLocator(WebDriver driver, String locator, String massage ){
        if(massage != null ) {
            logger.info(massage);
        }
        WebElement button = driver.findElement(By.xpath(locator));
        button.click();
    }
    public static void validateMessageByLocator(WebDriver driver,String locator,String text, String massage ){

        WebElement elementMessage = driver.findElement(By.xpath(locator));
        String errorMessageText = elementMessage.getText();

        if(massage != null ) {
            logger.info("Error message text: " + errorMessageText);
        }
       assertThat(errorMessageText, containsString(text));
    }
}
