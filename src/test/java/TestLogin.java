import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.time.Duration;
import java.util.stream.Stream;



public class TestLogin {
    WebDriver driver;

    @BeforeEach
    public void setUp() {

        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();

            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("provideUsernames")
    public void testEmptyCredentials(String username) {
        driver.get("https://www.saucedemo.com/");

        Helper.fillInputByLocator(driver, "//input[@id='user-name']", username, "Fill user name");
        Helper.fillInputByLocator(driver, "//input[@id='password']", username, "Fill password");

        Helper.clearInputByLocator(driver, "//input[@id='user-name']", "Clearing username");
        Helper.clearInputByLocator(driver, "//input[@id='password']", "Clearing password");

        Helper.clickButtonByLocator(driver,"//input[@id='login-button']","Clicking the login button");

        Helper.validateMessageByLocator(driver,"//h3[@data-test='error']","Username is required",
                "Error message text: ");

    }

    @ParameterizedTest
    @MethodSource("provideUsernames")
    public void testPasswordRequired(String username) {
        driver.get("https://www.saucedemo.com/");
        Helper.fillInputByLocator(driver, "//input[@id='user-name']", username, "Fill user name");
        Helper.fillInputByLocator(driver, "//input[@id='password']", username, "Fill password");

        Helper.clearInputByLocator(driver, "//input[@id='password']", "Clearing password");
        
        Helper.clickButtonByLocator(driver,"//input[@id='login-button']","Clicking the login button");

        Helper.validateMessageByLocator(driver,"//h3[@data-test='error']",
                "Password is required","Error message text: ");

    }

    @ParameterizedTest
    @MethodSource("provideUsernames")
    public void testValidLogin(String username) {
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Helper.fillInputByLocator(driver, "//input[@id='user-name']", username, "Fill user name");
        Helper.fillInputByLocator(driver, "//input[@id='password']", username, "Fill password");

        Helper.clickButtonByLocator(driver,"//input[@id='login-button']","Clicking the login button");

        wait.until(ExpectedConditions.titleContains("Swag Labs"));

        assertThat(driver.getTitle(), containsString("Swag Labs"));
    }

    private static Stream<Arguments> provideUsernames() {
        return Stream.of(
                Arguments.of("standard_user"),
                Arguments.of("problem_user"),
                Arguments.of("performance_glitch_user"),
                Arguments.of("error_user"),
                Arguments.of("visual_user")
        );
    }
}
