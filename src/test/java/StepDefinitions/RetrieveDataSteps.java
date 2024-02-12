package StepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RetrieveDataSteps {

    private WebDriver driver;

    private static String id;
    private static String person;
    private static String food;
    private static String price;
    private static String date;
    private static String country;
    private static String meals;
    private static String chef;

    @Before
    public void beforeScenario() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Given("the user is in the demo.aspnetawesome website")
    public void navigateToWebsite() {

        driver.manage().window().maximize();
        driver.get("https://demo.aspnetawesome.com/");
    }

    @And("there are existing records in the Grid filter row server data table")
    public void verifyExistingRecords() {

        assertTrue(driver
                .findElement(By
                        .xpath("(//div[@class=\"awe-header\"])[1]/following-sibling::div[@class=\"awe-mcontent\"]//tr"))
                .isDisplayed());
    }

    @When("the user provides the ID {string} for a specific record")
    public void verifyIdInTable(String inputID) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById(\"GridFrow2\").scrollIntoView();");

        int nextPage = 2;
        while (true) {
            if (searchForValueInTable(driver, inputID)) {
                break;
            }

            // If 'Next' button is present, click it to go to the next page
            WebElement nextPageButton = driver
                    .findElement(By.xpath("(//div[@class=\"awe-pager\"])[1]//button[text()='" + nextPage + "']"));
            if (nextPageButton.isDisplayed()) {
                nextPageButton.click();
                nextPage++;

                Thread.sleep(1000);
            } else {
                fail("ID not found in the entire table");
                break;
            }
        }
    }

    @Then("the system associated with {string} should return all values {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void verifyValuesInTable(String inputID, String expectedPerson, String expectedFood, String expectedPrice,
            String expectedDate, String expectedCountry, String expectedMeals, String expectedChef) {

        System.out.println("ID = " + id);
        System.out.println("Person = " + person);
        System.out.println("Food = " + food);
        System.out.println("Price = " + price);
        System.out.println("Date = " + date);
        System.out.println("Country = " + country);
        System.out.println("Meals = " + meals);
        System.out.println("Chef = " + chef);

        assertEquals("ID", inputID, id);
        assertEquals("person", expectedPerson, person);
        assertEquals("food", expectedFood, food);
        assertEquals("price", expectedPrice, price);
        assertEquals("date", expectedDate, date);
        assertEquals("country", expectedCountry, country);
        assertEquals("meals", expectedMeals, meals);
        assertEquals("chef", expectedChef, chef);
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static boolean searchForValueInTable(WebDriver driver, String inputID) {

        List<WebElement> rows = driver.findElements(
                By.xpath("(//div[@class=\"awe-header\"])[1]/following-sibling::div[@class=\"awe-mcontent\"]//tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(inputID)) {
                    int currentCell = cells.indexOf(cell);
                    id = cells.get(currentCell).getText();
                    person = cells.get(currentCell + 1).getText();
                    food = cells.get(currentCell + 2).getText();
                    price = cells.get(currentCell + 3).getText();
                    date = cells.get(currentCell + 4).getText();
                    country = cells.get(currentCell + 5).getText();
                    meals = cells.get(currentCell + 6).getText();
                    chef = cells.get(currentCell + 7).getText();

                    return true;
                }
            }
        }
        return false;
    }

}
