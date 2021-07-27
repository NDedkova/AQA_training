package task15.tests;

import helper.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static task15.locators.TestAddressBookLocators.*;

public class BeforeAndAfter {

    private static final Logger logger = LogManager.getLogger(BeforeAndAfter.class);

    protected static WebDriver driver = Driver.getChromeDriver();

    private static String userName = "aqa_test@senla.com";
    private static String password = "qwerty";


    @BeforeAll
    public static void singIn() {
        logger.info("Open a.testaddressbook.com and sign in to account");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://a.testaddressbook.com/sign_in");
        driver.findElement(EMAIL_FIELD).sendKeys(userName);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(SIGN_IN_BUTTON).click();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals("http://a.testaddressbook.com/", currentURL, "Incorrect page opened");
    }

    @AfterAll
    public static void quit() {
        logger.info("Close browser");
        driver.quit();
    }
}
