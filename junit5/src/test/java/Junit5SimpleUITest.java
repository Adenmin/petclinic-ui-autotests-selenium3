import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Junit5SimpleUITest {

    public static String petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080");
    private static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(petclinicUrl);

    }

    @AfterAll
    public static void after() {
        driver.quit();
    }

    @Test
    public void findOwnersTest() {
        driver.findElement(By.xpath("//a[@title='find owners']")).click();
        driver.findElement(By.id("lastName")).sendKeys("Davis");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String owner = driver.findElement(By.xpath("//a[@href='/owners/2']")).getText();
        Assertions.assertEquals("Betty Davis", owner);
    }

    @Test
    public void vetListTest() {
        driver.findElement(By.xpath("//a[@title='veterinarians']")).click();
        String vet = driver.findElement(By.xpath("//td")).getText();
        Assertions.assertEquals("James Carter", vet);
    }

}
