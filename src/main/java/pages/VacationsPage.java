package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class VacationsPage {

    private WebDriver driver;

    private String selectCarLocator = "//button[@class='hw-btn hw-btn-check']";
    private String flyFromInputField = "//input[@id='farefinder-package-origin-location-input']";
    private String listLocator = "//div[@class='farefinder-destination-group']//a[1]";
    private String flyToInputField = "//input[@id='farefinder-package-destination-location-input']";
    private String departingTimeLocator = "//select[@id='farefinder-package-pickuptime-input']";
    private String returningTimeLocator = "//select[@id='farefinder-package-dropofftime-input']";
    private String startDateInputField = "//input[@id='farefinder-package-startdate-input']";
    private String endDateInputField = "//input[@id='farefinder-package-enddate-input']";
    private String findButtonLocator = "//button[@id='farefinder-package-search-button']";
    private String spinningLocator = "//span[@class='loader loader-primary loader-light loader-animated loading pageInterstitialLoader']";
    private String searchResultsListLocator = "//article[starts-with(@class,'hotel listing col')]";
    WebDriverWait wait;

    public VacationsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void selectCar() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selectCarLocator))).click();
    }

    public int searchFlight(String flyFrom, String flyTo) {
        driver.findElement(By.xpath(flyFromInputField)).sendKeys(flyFrom);
        driver.findElement(By.xpath(listLocator)).click();
        driver.findElement(By.xpath(flyToInputField)).sendKeys(flyTo);
        driver.findElement(By.xpath(listLocator)).click();
        driver.findElement(By.xpath(startDateInputField)).clear();
        driver.findElement(By.xpath(startDateInputField)).sendKeys(getTomorrowsDay());
        driver.findElement(By.xpath(endDateInputField)).clear();
        driver.findElement(By.xpath(endDateInputField)).sendKeys(getDateThreeWeeksLater());

        WebElement departTimeElement = driver.findElement(By.xpath(departingTimeLocator));
        Select departTime = new Select(departTimeElement);
        departTime.selectByVisibleText("Evening");

        WebElement departToElement = driver.findElement(By.xpath(returningTimeLocator));
        Select returnTime = new Select(departToElement);
        returnTime.selectByVisibleText("Morning");

        driver.findElement(By.xpath(findButtonLocator)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(spinningLocator)));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResultsListLocator)));
        List searchResults = driver.findElements(By.xpath(searchResultsListLocator));
        return searchResults.size();
    }

    private String getTomorrowsDay() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.add(Calendar.DATE, 1);
        return new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
    }

    private String getDateThreeWeeksLater() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.add(Calendar.DATE, 1);
        cal.add(Calendar.WEEK_OF_YEAR, 3);
        return new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
    }

}
