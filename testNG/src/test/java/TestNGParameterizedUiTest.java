import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGParameterizedUiTest {

    public static String petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080");
    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(petclinicUrl);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[4][2];
        data[0][0] = "home page";
        data[0][1] = "Welcome";
        data[1][0] = "find owners";
        data[1][1] = "Find Owners";
        data[2][0] = "veterinarians";
        data[2][1] = "Veterinarians";
        data[3][0] = "trigger a RuntimeException to see how it is handled";
        data[3][1] = "Something happened...";
        return data;
    }

    @Test(dataProvider = "getData")
    public void checkPageHeader(String tabTitle, String expectedHeader) {
        driver.findElement(By.xpath("//a[@title='" + tabTitle + "']")).click();
        String mainPageHeader = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(expectedHeader, mainPageHeader);
    }
}
