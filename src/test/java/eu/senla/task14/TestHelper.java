package eu.senla.task14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestHelper {

    public void addAddress(WebDriver driver) {

        driver.findElement(By.xpath("//a[contains(text(), 'Addresses')]")).click();
        driver.findElement(By.cssSelector("[data-test ='create'] ")).click();
        driver.findElement(By.cssSelector("#address_first_name")).sendKeys("Bob");
        driver.findElement(By.cssSelector("#address_last_name")).sendKeys("Marley");
        driver.findElement(By.cssSelector("#address_street_address")).sendKeys("Flowers street 5");
        driver.findElement(By.cssSelector("#address_city")).sendKeys("Toronto");
        driver.findElement(By.cssSelector("#address_zip_code")).sendKeys("66777");
        driver.findElement(By.xpath("//input[@name = \"commit\"]")).click();
    }


    public void addressEdit(WebDriver driver) {

        driver.findElement(By.xpath("//a[@data-test = 'edit']")).click();
        driver.findElement(By.cssSelector("#address_interest_dance")).click();
        driver.findElement(By.xpath("//input[@value='Update Address']")).click();
    }

    public void addressDelete(WebDriver driver){

        String currentURL = driver.getCurrentUrl();
        String[] strings = currentURL.split("/");
        String addressId = strings[strings.length - 1];

        driver.findElement(By.xpath("//a[@data-test = 'list']")).click();
        driver.findElement(By.xpath("//a[@data-test= 'destroy-"+ addressId +"']")).click();
        driver.switchTo().alert().accept();
    }

    public void singOut(WebDriver driver) {

        driver.findElement(By.xpath("//a[@data-test='sign-out']")).click();
    }
}
