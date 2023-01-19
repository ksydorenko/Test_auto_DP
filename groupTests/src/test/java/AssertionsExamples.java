import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


public class AssertionsExamples {

    WebDriver driver;

    @BeforeClass
    void setup() {
        System.out.println("Before Class setup ...");
        // Set up the wWebDriverManager for chrome driver
        WebDriverManager.chromedriver().setup();
        // Create the driver object
        driver = new ChromeDriver();
        driver.get("http://www.google.com/");
    }

    @Test
    public void testGoogleSearch() {
        //Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertTrue(searchBox.isDisplayed(), "searchbox is displayed");
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        //Thread.sleep(5000);  // Let the user actually see something!
    }

    @AfterClass
    void tearDown() {
        System.out.println("After Class setup ...");
        driver.quit();
    }
}