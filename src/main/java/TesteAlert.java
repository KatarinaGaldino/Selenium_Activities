import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TesteAlert {

	private WebDriver driver;
	private MyDSL dsl;
	
	@Before
	public void before() {
		System.setProperty("webdriver.chrome.driver", "/Users/Katarina/Downloads/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();		
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().setSize(new Dimension(700, 700));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new MyDSL(driver);
	}
	
	@After
	public void after() {
		driver.quit();
	}
	
	@Test
	public void testeAlertSimples() {
		//driver.findElement(By.id("alert")).click();
		dsl.clicar("alert");
		Alert alerta = driver.switchTo().alert();
		String textoAlerta = alerta.getText();
		Assert.assertEquals("Alert Simples",textoAlerta );
		alerta.accept();
		//driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta);
		dsl.escrever("elementosForm:nome", textoAlerta);
	}
	
	@Test
	public void testeAlertConfirm() {
		//driver.findElement(By.id("confirm")).click();
		dsl.clicar("confirm");
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();
		
		//driver.findElement(By.id("confirm")).click();
		dsl.clicar("confirm");
		alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.dismiss();
	}
	
	@Test
	public void testeAlertPrompt() {
		//driver.findElement(By.id("prompt")).click();
		dsl.clicar("prompt");
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
	}
}
