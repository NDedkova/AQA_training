package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Steps {

    private LoginPage logInPage = new LoginPage();

    @Допустим("^открыта страница \"([^\"]*)\"$")
    public void openPage(String pageURL) {
        open(pageURL);
        getWebDriver().manage().window().maximize();
        String currentUrl = getWebDriver().getCurrentUrl();
        Assertions.assertEquals(pageURL, currentUrl, "URL is different");
    }

    @И("введен логин")
    public void inputLogin() {
        logInPage.inputLogin(LoginPage.getLogin());
    }

    @И("введен пароль")
    public void inputPassword() {
        logInPage.inputPassword(LoginPage.getPassword());
    }

    @И("нажата кнопка входа")
    public void clickSignInButton() {
        $(By.id("login-button")).click();
    }

    @И("^выполнено нажатие на ссылку \"([^\"]*)\"$")
    public void clickOnLink(String link) {
        $(By.xpath("//*[contains(text(), \"" + link + "\")]")).click();
    }

    @Тогда("^цена товара равна \"([^\"]*)\"$")
    public void checkProductPrice(String price) {
        String actualPriceInfo = $(By.className("inventory_details_price")).getText();
        assertTrue(actualPriceInfo.contains(price), "Актуальная и ожидаемая цена не совпадают");
    }

    @И("выбран товар")
    public void addProduct() {
        $(By.id("add-to-cart-sauce-labs-onesie")).click();
    }

    @Тогда("в корзине есть товар")
    public void checkCart() {
        $(By.xpath("//a[@class= 'shopping_cart_link']")).click();
        SelenideElement product = $(By.name("remove-sauce-labs-onesie"));
        Assertions.assertTrue(product.exists());
    }

    @И("^введен \"([^\"]*)\"$")
    public void inputIncorrectPassword(String incorrectPassword) {
        logInPage.inputPassword(incorrectPassword);
    }

    @Тогда("вход на страницу не осуществлен")
    public void hasNotLogin() {
        SelenideElement errorElement = $(By.cssSelector("h3[data-test='error']"));
        Assertions.assertTrue(errorElement.exists());
    }
}
