package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import java.util.ArrayList;
import static com.codeborne.selenide.Selenide.*;

public class BookingSteps {

    private String city;
    private static final String URL = "https://www.booking.com/";
    private static final String SEARCH_FIELD = "ss";
    private static final String SEARCH_BUTTON = ".sb-searchbox__button";
    private static final String HOTEL_NAMES = "//*[@data-testid='title']";
    private static final String HOTEL_RATING = "//*[contains(text(),'%s')]/ancestor::*[@data-testid='property-card']//*[contains(@aria-label, 'Оценка')]";

    @Given("User is looking for hotels in {string}")
    public void userIsLookingForHotelsInTheCity(String city) {
        this.city = city;
    }

    @When("User does search")
    public void userDoesSearch() {
        open(URL);
        $(By.id(SEARCH_FIELD)).sendKeys(city);
        $(SEARCH_BUTTON).click();
    }

    @And("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String hotel) {
        ArrayList<String> hotelsNames = new ArrayList<>();
        for (SelenideElement element : $$(By.xpath(HOTEL_NAMES))) {
            hotelsNames.add(element.getText());
        }
        Assert.assertTrue(hotelsNames.contains(hotel));
    }

    @Then("Hotel {string} rating is {string}")
    public void hotelRatingIsExpectedRate(String hotel, String expectedRate) {
        ArrayList<String> hotelsRating = new ArrayList<>();
        for (SelenideElement element : $$(By.xpath(String.format(HOTEL_RATING, hotel)))) {
            hotelsRating.add(element.getText());
        }
        Assert.assertTrue(hotelsRating.contains(expectedRate));
    }
}
