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
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.time.Duration;
import java.util.stream.Stream;



public class TestLogin {
    WebDriver driver;

    private static final String USERNAME_INPUT_LOCATOR = "//input[@id='user-name']";
    private static final String PASSWORD_INPUT_LOCATOR = "//input[@id='password']";
    private static final String LOGIN_BUTTON_LOCATOR = "//input[@id='login-button']";
    private static final String ERROR_MESSAGE_LOCATOR = "//h3[@data-test='error']";
    private static final String USERNAME_REQUIRED_ERROR = "Username is required";
    private static final String PASSWORD_REQUIRED_ERROR = "Password is required";
    private static final String CLEAR_USERNAME_MESSAGE = "Clearing username";
    private static final String CLEAR_PASSWORD_MESSAGE = "Clearing password";
    private static final String CLICK_LOGIN_BUTTON_MESSAGE = "Clicking the login button";
    private static final String SITE_URL = "https://www.saucedemo.com/";

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
    public void testLoginWithEmptyCredentialsShouldShowUsernameRequiredError(String username) {
        driver.get(SITE_URL);

        fillLoginAndPassword(driver,username,"password");

        Helper.clearInputByLocator(driver, USERNAME_INPUT_LOCATOR, CLEAR_USERNAME_MESSAGE);
        Helper.clearInputByLocator(driver, PASSWORD_INPUT_LOCATOR, CLEAR_PASSWORD_MESSAGE);

        Helper.clickButtonByLocator(driver,LOGIN_BUTTON_LOCATOR,CLICK_LOGIN_BUTTON_MESSAGE);

        assertThat(Helper.validateMessageByLocator(driver,ERROR_MESSAGE_LOCATOR,"Error message text: "),
                containsString(USERNAME_REQUIRED_ERROR));

    }

    @ParameterizedTest
    @MethodSource("provideUsernames")
    public void testLoginWithEmptyPasswordShouldShowPasswordRequiredError(String username) {
        driver.get(SITE_URL);

        fillLoginAndPassword(driver,username,"password");

        Helper.clearInputByLocator(driver, PASSWORD_INPUT_LOCATOR, CLEAR_PASSWORD_MESSAGE);
        
        Helper.clickButtonByLocator(driver,LOGIN_BUTTON_LOCATOR,CLICK_LOGIN_BUTTON_MESSAGE);

        assertThat(Helper.validateMessageByLocator(driver,ERROR_MESSAGE_LOCATOR,"Error message text: "),
                containsString(PASSWORD_REQUIRED_ERROR));
    }

    @ParameterizedTest
    @MethodSource("provideUsernames")
    public void testLoginWithValidCredentialsShouldRedirectToSwagLabs(String username) {
        driver.get(SITE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        fillLoginAndPassword(driver,username,"secret_sauce");

        Helper.clickButtonByLocator(driver,LOGIN_BUTTON_LOCATOR,CLICK_LOGIN_BUTTON_MESSAGE);

        String expected_url = driver.getCurrentUrl();
        wait.until(
                d -> {
                    Helper.logger.info("Redirect to inventory page");
                    assertThat(expected_url, is("https://www.saucedemo.com/inventory.html"));
                    Helper.logger.info("Expected title Swag Labs");
                    assertThat(driver.getTitle(), containsString("Swag Labs"));
                    return true;
                });

    }

    private void fillLoginAndPassword(WebDriver driver, String username, String password){
        Helper.fillInputByLocator(driver, USERNAME_INPUT_LOCATOR, username, "Fill user name");
        Helper.fillInputByLocator(driver, PASSWORD_INPUT_LOCATOR, password, "Fill password");
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
