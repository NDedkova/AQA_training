import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SaucedemoTest extends BeforeAndAfter {

    MainPage mainPage = new MainPage();

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

    @Test
    public void cleanCartTest() {
        login();
        mainPage.sauceLabsBackpackRemoveButton.click();
        mainPage.sauceLabsBoltTShirtRemoveButton.click();
        mainPage.shoppingCartButton.click();
        mainPage.remove.forEach(SelenideElement::click);
        Assertions.assertFalse(mainPage.itemsInCart.exists());
    }

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

