import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Test_Shop_Dressa {
    WebDriver driver;

    @BeforeClass
    void setup() {
        System.out.println("Before Class setup ...");
        // Set up the wWebDriverManager for chrome driver
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        // Create the driver object
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://dressa.com.ua/");
    }

    @Test
    public void testDressaSearch() {
        // знайти пошукову строку
        WebElement searchField = driver.findElement(By.id("search__field_input"));
        // перевірка що пошукова строка відображається
        Assert.assertTrue(searchField.isDisplayed(), "searchbox is displayed");
        searchField.sendKeys("Плаття");
        searchField.sendKeys(Keys.ENTER);
        WebElement productLink = driver.findElement(By.className("product__link"));
        productLink.click();
        WebElement descriptionTitle = driver.findElement(By.className("info__title_text"));
        Assert.assertNotNull(descriptionTitle);
    }

    @AfterClass
    void tearDown() {
        System.out.println("After Class setup ...");
        driver.quit();
    }
}
