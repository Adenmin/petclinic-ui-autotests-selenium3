import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNGSimpleUITest {

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

    @Test
    public void findOwnersTest() {
        driver.findElement(By.xpath("//a[@title='find owners']")).click();
        driver.findElement(By.id("lastName")).sendKeys("Davis");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String owner = driver.findElement(By.xpath("//a[@href='/owners/2']")).getText();
        Assert.assertEquals("Betty Davis", owner);
    }

    @Test
    public void vetListTest() {
        driver.findElement(By.xpath("//a[@title='veterinarians']")).click();
        String vet = driver.findElement(By.xpath("//td")).getText();
        Assert.assertEquals("James Carter", vet);
    }

}
