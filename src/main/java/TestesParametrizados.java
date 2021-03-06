import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class TestesParametrizados {

	private WebDriver driver;
	private MyDSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	
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
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object [][]{
			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Katarina", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Katarina", "Mariano", "",  Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Katarina", "Mariano", "Feminino", Arrays.asList("Carne" , "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Katarina", "Marinano", "Feminino", Arrays.asList("Frango"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	
	@Test
	public void deveValidarRegras(){
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} 
		if(sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		if(comidas.contains("Carne")) page.setComidaCarne(); 
		if(comidas.contains("Pizza")) page.setComidaPizza(); 
		if(comidas.contains("Vegetariano")) page.setComidaVegetariano(); 
		page.setEsporte(esportes);
		page.setCadastro();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	}
}
