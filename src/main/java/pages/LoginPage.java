package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    private final static String login;
    private final static String password;
    private final static String firstName;
    private final static String lastName;
    private final static String postalCode;
    private final static String incorrectPassword = "qwerty";
    private final SelenideElement loginField = $(By.id("user-name"));

    private final SelenideElement passwordField = $(By.id("password"));
    private final SelenideElement loginButton = $(By.id("login-button"));
    private final SelenideElement firstNameField = $(By.id("first-name"));
    private final SelenideElement lastNameField = $(By.id("last-name"));
    private final SelenideElement postalCodeField = $(By.id("postal-code"));
    private final SelenideElement continueButton = $(By.id("continue"));

    static {
        Properties property = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            property.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        login = property.getProperty("test-user.login");
        password = property.getProperty("test-user.password");
        firstName = property.getProperty("test-user.firstname");
        lastName = property.getProperty("test-user.lastname");
        postalCode = property.getProperty("test-user.postalcode");
    }

    @Step("Login")
    public void login() {
        logger.info("Open main page");
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @Step("Fill in user")
    public void fillInUser() {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
        continueButton.click();
    }

    @Step("Login page open")
    public void loginPageOpen() {
        open("https://www.saucedemo.com/");
    }

    @Step("Input login")
    public void inputLogin(String text) {
        this.loginField.val(text);
    }

    @Step("Input password")
    public void inputPassword(String text) {
        this.passwordField.val(text);
    }


    @Step("Get login")
    public static String getLogin() {
        return login;
    }

    @Step("Get password")
    public static String getPassword() {
        return password;
    }
}
