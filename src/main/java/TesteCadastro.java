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
	public void testeCadastrar() {
		dsl.escrever("elementosForm:nome", "Katarina");
		dsl.escrever("elementosForm:sobrenome", "Mariano");
		dsl.clicar("elementosForm:sexo:1");
		dsl.clicar("elementosForm:comidaFavorita:2");
		dsl.selecionaCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionaCombo("elementosForm:esportes", "Natacao");
		dsl.clicar("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.pegaTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.pegaTexto("descNome").endsWith("Katarina"));
		Assert.assertTrue(dsl.pegaTexto("descSobrenome").endsWith("Mariano"));
		Assert.assertTrue(dsl.pegaTexto("descSexo").endsWith("Feminino"));
		Assert.assertEquals("Comida: Pizza", dsl.pegaTexto("descComida"));
		Assert.assertEquals("Escolaridade: mestrado", dsl.pegaTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.pegaTexto("descEsportes"));
	}
}
