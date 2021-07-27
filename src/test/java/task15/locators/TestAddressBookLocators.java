package task15.locators;

import org.openqa.selenium.By;

public class TestAddressBookLocators {

    public static final By EMAIL_FIELD = By.id("session_email");
    public static final By PASSWORD_FIELD = By.id("session_password");
    public static final By SIGN_IN_BUTTON = By.name("commit");
    public static final By ADDRESSES_BUTTON = By.xpath("//a[contains(text(), 'Addresses')]");
    public static final By ADDRESSES_LIST = By.xpath("//tbody/tr");
    public static final By CREATE_ADDRESS = By.cssSelector("[data-test ='create']");
    public static final By FIRST_NAME_FIELD = By.id("address_first_name");
    public static final By LAST_NAME_FIELD = By.id("address_last_name");
    public static final By STREET_ADDRESS_FIELD = By.id("address_street_address");
    public static final By CITY_FIELD = By.id("address_city");
    public static final By ZIP_CODE_FIELD = By.id("address_zip_code");
    public static final By UPDATE_BUTTON = By.name("commit");
    public static final By LIST_BUTTON = By.xpath("//a[@data-test = 'list']");
    public static final By EDIT_ELEMENT = By.xpath("(//a[text() = 'Edit']) [1]");
    public static final By ADDRESS_FIRST_NAME_VALUE = By.xpath("//span[@ data-test = 'first_name']");
    public static final By DESTROY_BUTTON = By.xpath("(//a[text() = 'Destroy']) [1]");
    public static final By SIGN_OUT = By.xpath("//a[@data-test='sign-out']");
    public static final By WARNING_MESSAGE = By.xpath("//h4[contains(text(), 'error prohibited')]");
}
