import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SaucedemoTest extends BeforeAndAfter {

    MainPage mainPage = new MainPage();
    WebDriver driver;

    @Epic("Testing for https://www.saucedemo.com/")
    @Issue("SUD-12")
    @Severity(SeverityLevel.CRITICAL)
    @Description("In this test we log in with correct login and password")
    @Step("Login")
    @Order(1)
    @Test
    public void loginTest() {
        login();
        mainPage.menuMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("PRODUCTS", mainPage.menuMessage.getText());
    }

    private void login() {
        mainPage.loginPageOpen();
        mainPage.login();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("In this test we purchase a product")
    @Step("Purchase product ")
    @Order(2)
    @Test
    public void purchaseTest() {

        login();
        mainPage.sauceLabsBackpackRemoveButton.click();
        mainPage.sauceLabsBoltTShirtRemoveButton.click();
        mainPage.shoppingCartButton.click();
        mainPage.checkoutButton.click();
        mainPage.fillInUser();
        mainPage.finishButton.click();
        mainPage.finishOrderMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", mainPage.finishOrderMessage.getText());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("In this test we cancel purchase")
    @Step("Cancel purchase")
    @Order(3)
    @Test
    public void cancelPurchaseTest() {
        login();
        mainPage.sauceLabsBackpackRemoveButton.click();
        mainPage.sauceLabsBoltTShirtRemoveButton.click();
        mainPage.shoppingCartButton.click();
        mainPage.checkoutButton.click();
        mainPage.fillInUser();
        mainPage.cancelButton.click();
        mainPage.menuMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("PRODUCTS", mainPage.menuMessage.getText());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("In this test we clean cart")
    @Step("Clean cart")
    @Order(4)
    @Test
    public void cleanCartTest() {
        login();
        mainPage.sauceLabsBackpackRemoveButton.click();
        mainPage.sauceLabsBoltTShirtRemoveButton.click();
        mainPage.shoppingCartButton.click();
        mainPage.remove.forEach(SelenideElement::click);
        Assertions.assertFalse(mainPage.itemsInCart.exists());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("In this test we check purchase price")
    @Step("Check purchase price")
    @Order(5)
    @Test
    public void checkPurchasePrice() {
        login();
        mainPage.sauceLabsBackpackRemoveButton.click();
        mainPage.sauceLabsBoltTShirtRemoveButton.click();
        mainPage.shoppingCartButton.click();
        mainPage.checkoutButton.click();
        mainPage.fillInUser();
        Double summary = mainPage.prices.stream()
                .map(SelenideElement::getText)
                .map(s -> s.split("\\$"))
                .map(array -> array[array.length - 1])
                .map(Double::parseDouble)
                .reduce((double) 0, Double::sum);

        String value = mainPage.totalPrice.getText();
        String[] array = value.split("\\$");
        String price = array[array.length - 1];
        double total = Double.parseDouble(price);
        Assertions.assertEquals(total, summary);


    }

}

