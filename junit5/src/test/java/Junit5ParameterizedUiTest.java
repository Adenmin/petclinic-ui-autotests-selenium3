import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Junit5ParameterizedUiTest {

    public String petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080");
    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get(petclinicUrl);
    }

    @ParameterizedTest
    @CsvSource({"home page,Welcome",
            "find owners,Find Owners",
            "veterinarians,Veterinarians",
            "trigger a RuntimeException to see how it is handled,Something happened..."})
    public void checkPageHeader(String tabTitle, String expectedHeader) {
        driver.findElement(By.xpath("//a[@title='" + tabTitle + "']")).click();
        String mainPageHeader = driver.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals(expectedHeader, mainPageHeader);
    }

    @AfterEach
    public void after() {
        driver.quit();
    }
}
