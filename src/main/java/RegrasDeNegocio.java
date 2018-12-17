import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegrasDeNegocio {

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
	public void testeValidaCampoNome() {
		page.setCadastro();
		Assert.assertEquals("Nome eh obrigatorio" , dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void testeValidaCampoSobrenome() {
		page.setNome("Mariano");
		page.setCadastro();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void testeValidaCampoSexo() {
		page.setNome("Katarina");
		page.setSobrenome("Mariano");
		//driver.findElement(By.id("elementosForm:sexo:1")).click();
		page.setCadastro();
		Assert.assertEquals("Sexo eh obrigatorio" , dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void testeValidaCampoComida() {
		page.setNome("Katarina");
		page.setSobrenome("Mariano");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.setCadastro();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?" , dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void testeValidaCampoEsportes() {
		page.setNome("Katarina");
		page.setSobrenome("Mariano");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?" );
		page.setCadastro();
		Assert.assertEquals("Voce faz esporte ou nao?" , dsl.alertaObterTextoEAceita());
	}
	
}
