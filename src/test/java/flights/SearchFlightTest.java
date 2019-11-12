package flights;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.VacationsPage;

public class SearchFlightTest extends BaseTest {

    @Test
    public void searchFlight() {
        VacationsPage vacationsPage = homepage.clickVacations();
        vacationsPage.selectCar();
        int searchResults = vacationsPage.searchFlight("SFO", "LAX");
        Assert.assertTrue(searchResults > 0);
    }
}
