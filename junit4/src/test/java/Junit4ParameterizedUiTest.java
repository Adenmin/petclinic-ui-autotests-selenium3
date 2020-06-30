import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Junit4ParameterizedUiTest {

    public static String petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080");
    private static WebDriver driver;
    private final String tabTitle;
    private final String expectedHeader;
    public Junit4ParameterizedUiTest(String input, String expected) {
        this.tabTitle = input;
        this.expectedHeader = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"home page", "Welcome"},
                {"find owners", "Find Owners"},
                {"veterinarians", "Veterinarians"},
                {"trigger a RuntimeException to see how it is handled", "Something happened..."}
        });
    }

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

    @Test
    public void checkPageHeader() {
        driver.findElement(By.xpath("//a[@title='" + tabTitle + "']")).click();
        String mainPageHeader = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(expectedHeader, mainPageHeader);
    }
}
