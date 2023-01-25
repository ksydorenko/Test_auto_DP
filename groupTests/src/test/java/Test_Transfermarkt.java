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
import java.util.List;

public class Test_Transfermarkt {
    WebDriver driver;
    private String baseurl = "https://www.transfermarkt.world/";

    @BeforeClass
    void setup() {
        System.out.println("Before Class setup ...");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        driver = new ChromeDriver(chromeOptions);
        driver.get(baseurl);
    }

    @Test
    public void testHeaderExists() {
        WebElement header = driver.findElement(By.tagName("header"));
        Assert.assertNotNull(header);
    }
    @Test
    public void testFooterExists() {
        WebElement header = driver.findElement(By.tagName("footer"));
        Assert.assertNotNull(header);
    }
    @Test
    public void testClubSearch() {
        WebElement searchField = driver.findElement(By.className("tm-header__input--search-field"));
        Assert.assertTrue(searchField.isDisplayed(), "searchbox is displayed");
        searchField.sendKeys("dnipro");
        searchField.sendKeys(Keys.ENTER);
        WebElement tableItems = driver.findElement(By.className("items"));
        List<WebElement> trs = tableItems.findElements(By.tagName("tr"));
        Assert.assertTrue(trs.size() > 0, "teams are displayed");
    }

    @AfterClass
    void tearDown() {
        System.out.println("After Class setup ...");
        driver.quit();
    }
}
