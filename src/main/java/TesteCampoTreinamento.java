import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCampoTreinamento {

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
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Escreva isso!");
		Assert.assertEquals("Escreva isso!", dsl.pegaValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testeTextArea() {	
		dsl.escrever("elementosForm:sugestoes", "Texto do TextArea");
		Assert.assertEquals("Texto do TextArea", dsl.pegaValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void testeRadioButton() {
		dsl.clicar("elementosForm:sexo:0");	
		Assert.assertTrue(dsl.verificaSelecao("elementosForm:sexo:0"));				
	}	
	
	@Test
	public void testeCheckBox() {
		dsl.clicar("elementosForm:comidaFavorita:1");
		Assert.assertTrue(dsl.verificaSelecao("elementosForm:comidaFavorita:1"));	
	}
	
	@Test
	public void testeComboBox() {
		dsl.selecionaCombo("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.pegaValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void testeVerificaValoresComboBox() {
		/*WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboBox = new Select(element);
		List<WebElement> options = comboBox.getOptions();
		Assert.assertEquals(8, options.size()); //verifica a quantidade de opções
		
		boolean encontrou = false;
		for(WebElement option : options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);*/
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void testeComboBoxMultiplo() {
		dsl.selecionaCombo("elementosForm:esportes", "Natacao");
		dsl.selecionaCombo("elementosForm:esportes", "Corrida");
		dsl.selecionaCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void testeBotoes() {
		dsl.clicar("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.pegaValueElemento("buttonSimple"));
	}
	
	@Test
	public void testeLinks() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.pegaTexto("resultado"));
	}
	
	@Test
	public void testeBuscarTextosNaPagina() {
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.pegaTextoPorElemento(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.pegaTextoPorElemento(By.className("facilAchar")));
	}
}
