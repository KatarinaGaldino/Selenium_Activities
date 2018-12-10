import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrames_e_Janelas {
	
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
	public void testeFrame() {
		driver.switchTo().frame("frame1");
		dsl.clicar("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);
	}
	
	@Test
	public void testeJanela() {
		dsl.clicar("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		driver.close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");
		
		
	}
	
	@Test
	public void testeJanelaSemTitulo() {
		dsl.clicar("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
	
	@Test
	public void testeFrame2() {
		driver.switchTo().frame("Frame2");
		//driver.findElement(By.id("frameButton")).click();
		dsl.clicar("frameButton");
	}
}
