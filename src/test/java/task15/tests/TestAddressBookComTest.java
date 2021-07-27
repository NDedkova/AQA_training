package task15.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;


import static task15.locators.TestAddressBookLocators.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAddressBookComTest extends BeforeAndAfter {

    private static final Logger logger = LogManager.getLogger(TestAddressBookComTest.class);

    @Test
    @Order(1)
    public void addAddress() {
        logger.info("Add a new address to the address list");
        driver.findElement(ADDRESSES_BUTTON).click();
        int firstCount = driver.findElements(ADDRESSES_LIST).size();
        driver.findElement(CREATE_ADDRESS).click();
        driver.findElement(FIRST_NAME_FIELD).sendKeys("Bob");
        driver.findElement(LAST_NAME_FIELD).sendKeys("Marley");
        driver.findElement(STREET_ADDRESS_FIELD).sendKeys("Flowers street 5");
        driver.findElement(CITY_FIELD).sendKeys("Toronto");
        driver.findElement(ZIP_CODE_FIELD).sendKeys("66777");
        driver.findElement(UPDATE_BUTTON).click();
        driver.findElement(LIST_BUTTON).click();
        int updatedCount = driver.findElements(ADDRESSES_LIST).size();
        Assertions.assertEquals(firstCount + 1, updatedCount);
    }

    @Test
    @Order(4)
    @Disabled("The test was ignored")
    public void addIncorrectAddress() {
        driver.findElement(ADDRESSES_BUTTON).click();
        driver.findElement(CREATE_ADDRESS).click();
        driver.findElement(FIRST_NAME_FIELD).sendKeys("Bob");
        driver.findElement(LAST_NAME_FIELD).sendKeys("Marley");
        driver.findElement(CITY_FIELD).sendKeys("Toronto");
        driver.findElement(ZIP_CODE_FIELD).sendKeys("66777");
        driver.findElement(UPDATE_BUTTON).click();
        String massage = driver.findElement(WARNING_MESSAGE).getText();
        Assertions.assertTrue("1 error prohibited this address from being saved:".contains(massage));
    }

    @Test
    @Order(2)
    public void addressEdit() {
        logger.info("Edit a address");
        driver.findElement(ADDRESSES_BUTTON).click();
        driver.findElement(EDIT_ELEMENT).click();
        driver.findElement(FIRST_NAME_FIELD).clear();
        driver.findElement(FIRST_NAME_FIELD).sendKeys("Jim");
        driver.findElement(UPDATE_BUTTON).click();
        String result = driver.findElement(ADDRESS_FIRST_NAME_VALUE).getText();
        Assertions.assertEquals("Jim", result);
    }

    @Test
    @Order(3)
    public void addressDelete() {
        logger.info("Delete a address");
        driver.findElement(ADDRESSES_BUTTON).click();
        int firstCount = driver.findElements(ADDRESSES_LIST).size();
        driver.findElement(DESTROY_BUTTON).click();
        driver.switchTo().alert().accept();
        int updatedCount = driver.findElements(ADDRESSES_LIST).size();
        Assertions.assertEquals(firstCount, updatedCount + 1);
    }

    @Test
    @Order(5)
    public void singOut() {
        logger.info("Sing out of account");
        driver.findElement(SIGN_OUT).click();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals("http://a.testaddressbook.com/sign_in", currentURL, "Incorrect page opened");
    }
}