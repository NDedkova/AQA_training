import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends LoginPage {

    public SelenideElement menuMessage = $(By.xpath("//span[@class= 'title']"));
    public SelenideElement sauceLabsBackpackRemoveButton = $(By.name("add-to-cart-sauce-labs-backpack"));
    public SelenideElement sauceLabsBoltTShirtRemoveButton = $(By.name("add-to-cart-sauce-labs-bolt-t-shirt"));
    public SelenideElement shoppingCartButton = $(By.xpath("//a[@class= 'shopping_cart_link']"));
    public SelenideElement checkoutButton = $(By.name("checkout"));
    public SelenideElement finishButton = $(By.name("finish"));
    public SelenideElement finishOrderMessage = $(By.cssSelector("h2[class^='complete-header']"));
    public SelenideElement cancelButton = $(By.name("cancel"));
    public ElementsCollection remove = $$(By.cssSelector("button[class='btn btn_secondary btn_small cart_button']"));
    public ElementsCollection prices = $$(By.cssSelector("div[class='inventory_item_price']"));
    public SelenideElement itemsInCart = $(By.cssSelector("span[class='shopping_cart_badge']"));
    public SelenideElement totalPrice = $(By.cssSelector("div[class='summary_subtotal_label']"));
}
