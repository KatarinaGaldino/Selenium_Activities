import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegrasDeNegocio {

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
	public void testeValidaCampoNome() {
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio" , dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void testeValidaCampoSobrenome() {
		dsl.escrever("elementosForm:nome", "Katarina");
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void testeValidaCampoSexo() {
		dsl.escrever("elementosForm:nome", "Katarina");
		dsl.escrever("elementosForm:sobrenome", "Mariano");
		//driver.findElement(By.id("elementosForm:sexo:1")).click();
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio" , dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void testeValidaCampoComida() {
		dsl.escrever("elementosForm:nome", "Katarina");
		dsl.escrever("elementosForm:sobrenome", "Mariano");
		dsl.clicar("elementosForm:sexo:1");
		dsl.clicar("elementosForm:comidaFavorita:0");
		dsl.clicar("elementosForm:comidaFavorita:3");
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?" , dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void testeValidaCampoEsportes() {
		dsl.escrever("elementosForm:nome", "Katarina");
		dsl.escrever("elementosForm:sobrenome", "Mariano");
		dsl.clicar("elementosForm:sexo:1");
		dsl.clicar("elementosForm:comidaFavorita:0");
		dsl.selecionaCombo("elementosForm:esportes", "Karate");
		dsl.selecionaCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?" , dsl.alertaObterTextoEAceita());
	}
	
}
