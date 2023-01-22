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


public class WorkExample {
    WebDriver driver;
    private String baseurl = "https://nmu.org.ua/ua/";

    @BeforeClass
    void setup() {
        System.out.println("Before Class setup ...");
        // Set up the wWebDriverManager for chrome driver
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        //chromeOptions.addArguments("--headless");                         // Option for headless browser
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        // Create the driver object
        driver = new ChromeDriver(chromeOptions);
        driver.get(baseurl);
    }

    @Test
    public void testHeaderExists() {
        WebElement header = driver.findElement(By.id("heder"));
        Assert.assertNotNull(header);
    }

    @Test
    public void testClickOnForStudent() {
        WebElement forStudentButton = driver.findElement(By.xpath(
                "/html/body/center/div[4]/div/div[1]/ul/li[4]/a"
        ));
        Assert.assertNotNull(forStudentButton);
        forStudentButton.click();
        Assert.assertNotEquals(driver.getCurrentUrl(), baseurl);
    }

    @Test
    public void testSearchFileOnForStudentPage() {
        String studentPageUrl = "content/student_life/students/";
        driver.get(baseurl + studentPageUrl);
        WebElement searchBtn = driver.findElement(By.tagName("input"));
        Assert.assertNotNull(searchBtn);
        searchBtn.click();

//        System.out.println(
//            String.format("Name attr: %s", searchField.getAttribute("name")) +
//            String.format("\nID attr: %s", searchField.getAttribute("id")) +
//            String.format("\nType attr: %s", searchField.getAttribute("type")) +
//            String.format("\nValue attr: %s", searchField.getAttribute("value")) +
//            String.format("\nPosition: (%d;%d)", searchField.getLocation().x, searchField.getLocation().y) +
//            String.format("\nSize: %dx%d", searchField.getSize().height, searchField.getSize().width)
//        );

        String inputValue = "I need info";
        WebElement searchField = driver.findElement(By.name("search"));
        searchField.sendKeys(inputValue);
        //Assert.assertEquals(searchField.getText(), inputValue);
        searchField.sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.className("gsc-result-info")).isDisplayed());
        //Assert.assertNotEquals(driver.getCurrentUrl(), studentPageUrl);
    }

    @Test
    public void testSlider() {
        driver.get(baseurl);
        WebElement nextButton = driver.findElement(By.className("next"));
        WebElement nextButtonByCss = driver.findElement(By.cssSelector("a.next"));
        Assert.assertEquals(nextButton, nextButtonByCss);
        WebElement previousButton = driver.findElement(By.className("prev"));
        for (int i = 0; i < 20; i++) {
            if (nextButton.getAttribute("class").contains("disabled")) {
                previousButton.click();
                Assert.assertFalse(previousButton.getAttribute("class").contains("disabled"));
                Assert.assertFalse(nextButton.getAttribute("class").contains("disabled"));
            } else {
                nextButton.click();
                //Assert.assertFalse(nextButton.getAttribute("class").contains("disabled"));
                Assert.assertFalse(previousButton.getAttribute("class").contains("disabled"));
            }
//            try {
//                Thread.sleep(1000);
//            } catch(InterruptedException ie) {
//
//            }
        }
    }

    @AfterClass
    void tearDown() {
        System.out.println("After Class setup ...");
        driver.quit(); // driver.close();
    }
}
