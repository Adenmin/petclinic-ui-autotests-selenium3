import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Junit4SimpleUITest {

    public String petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080");
    private WebDriver driver;

    @BeforeClass
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.get(petclinicUrl);
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

    @After
    public void after() {
        driver.quit();
    }
}
