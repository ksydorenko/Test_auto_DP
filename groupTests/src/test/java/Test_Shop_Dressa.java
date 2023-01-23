import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_Shop_Dressa {
    WebDriver driver;

    @BeforeClass
    void setup() {
        System.out.println("Before Class setup ...");
        // Set up the wWebDriverManager for chrome driver
        WebDriverManager.chromedriver().setup();
        // Create the driver object
        driver = new ChromeDriver();
        driver.get("https://dressa.com.ua/");
    }

    @Test
    public void testDressaSearch() {
        // знайти пошуку строку
        WebElement searchBox = driver.findElement(By.id("search__field_input"));
        // що пошука строка відображається
        Assert.assertTrue(searchBox.isDisplayed(), "searchbox is displayed");
        searchBox.sendKeys("Плаття");
        searchBox.submit();
        WebElement firstResult = driver.findElement(By.tagName("h1"));
        firstResult.click();
        Assert.assertTrue(driver.getTitle().contains("Повсякденні плаття"));
//        try {
//            Thread.sleep(1000);
//        } catch(InterruptedException ie) {
//            System.out.println(ie);
//        }
    }


    @AfterClass
    void tearDown() {
        System.out.println("After Class setup ...");
        driver.quit();
    }

}
