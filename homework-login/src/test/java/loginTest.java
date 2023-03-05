import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.Assert.assertEquals;

public class loginTest {

    @Test
    public void loginSuccessful() {
        ChromeDriver varDriver = new ChromeDriver();
        varDriver.manage().window().maximize();
        varDriver.get("http://training.skillo-bg.com:4200/users/login");

        WebElement usernameInput = varDriver.findElement(By.id("defaultLoginFormUsername"));
        usernameInput.sendKeys("Kat_stifi");

        WebElement passwordInput = varDriver.findElement(By.id("defaultLoginFormPassword"));
        passwordInput.sendKeys("K444444");

        WebElement signInButton = varDriver.findElement(By.id("sign-in-button"));
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(varDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/posts/all"));

        assertEquals(varDriver.getCurrentUrl(), "http://training.skillo-bg.com:4200/posts/all");

        varDriver.close();
    }

    @Test
    public void invalidPassword() {
        ChromeDriver varDriver = new ChromeDriver();
        varDriver.manage().window().maximize();
        varDriver.get("http://training.skillo-bg.com:4200/users/login");

        WebElement usernameInput = varDriver.findElement(By.id("defaultLoginFormUsername"));
        usernameInput.sendKeys("Kat_stifi");

        WebElement passwordInput = varDriver.findElement(By.id("defaultLoginFormPassword"));
        passwordInput.sendKeys("kkkk");

        WebElement signInButton = varDriver.findElement(By.id("sign-in-button"));
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(varDriver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-error")));

        assertEquals(varDriver.getCurrentUrl(), "http://training.skillo-bg.com:4200/users/login");
        assertEquals(errorMessage.getText(), "Ivalid password");

        varDriver.close();
    }

    @Test
    public void invalidUsername() {
        ChromeDriver varDriver = new ChromeDriver();
        varDriver.manage().window().maximize();
        varDriver.get("http://training.skillo-bg.com:4200/users/login");

        WebElement usernameInput = varDriver.findElement(By.id("defaultLoginFormUsername"));
        usernameInput.sendKeys("kkkk");

        WebElement passwordInput = varDriver.findElement(By.id("defaultLoginFormPassword"));
        passwordInput.sendKeys("kkkk");

        WebElement signInButton = varDriver.findElement(By.id("sign-in-button"));
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(varDriver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-error")));

        assertEquals(varDriver.getCurrentUrl(), "http://training.skillo-bg.com:4200/users/login");
        assertEquals(errorMessage.getText(), "User not found");

        varDriver.close();
    }
}
