package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    private String vacationsLocator = "//li[@class='hidden-xs']//a[contains(text(),'Vacations')]";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public VacationsPage clickVacations() {
        driver.findElement(By.xpath(vacationsLocator)).click();
        return new VacationsPage(driver);
    }

}
