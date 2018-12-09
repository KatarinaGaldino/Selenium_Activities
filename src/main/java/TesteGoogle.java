import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

	@Test
	public void teste() {
		System.setProperty("webdriver.chrome.driver", "/Users/Katarina/Downloads/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(700, 700));
		driver.get("http://projetolead.com.br/");
		Assert.assertEquals("Dell – Aprendizado Acessível", driver.getTitle());
		driver.quit();
		
		
	}
}
