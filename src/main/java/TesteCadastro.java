import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {

	private WebDriver driver;
	private MyDSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void before() {
		System.setProperty("webdriver.chrome.driver", "/Users/Katarina/Downloads/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();		
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().setSize(new Dimension(700, 700));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new MyDSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void after() {
		driver.quit();
	}
	
	@Test
	public void testeCadastrar() {
		page.setNome("Katarina");
		page.setSobrenome("Mariano");
		page.setSexoFeminino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.setCadastro();
		
		Assert.assertTrue(page.getResultado().startsWith("Cadastrado!"));
		Assert.assertTrue(page.getNome().endsWith("Katarina"));
		Assert.assertTrue(page.getSobrenome().endsWith("Mariano"));
		Assert.assertTrue(page.getSexo().endsWith("Feminino"));
		Assert.assertEquals("Comida: Pizza", page.getComida());
		Assert.assertEquals("Escolaridade: mestrado", page.getEscolaridade());
		Assert.assertEquals("Esportes: Natacao", page.getEsporte());
	}
}
