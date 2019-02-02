import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteProjetoLead extends Manager{
	
	@Test
	public void testeLogin() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1200, 700));
		driver.get("https://teste.projetolead.com.br/ead2pcd/app/login?ctrl=1");
		
		driver.findElement(By.id("usuario")).sendKeys("alunosurdo");
		driver.findElement(By.id("senha")).sendKeys("abcd1234");
		driver.findElement(By.name("_spring_security_remember_me")).click();
		driver.findElement(By.tagName("button")).click();
		
		driver.findElement(By.id("imgUsuario")).click();
		driver.findElement(By.id("logoff")).click();
		
		driver.quit();
		
	}
	
	@Test
	public void testeRecuperaSenha() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1200, 700));
		driver.get("https://teste.projetolead.com.br/ead2pcd/app/login?ctrl=1");
		
		driver.findElement(By.linkText("Esqueci minha senha.")).click();
		driver.findElement(By.id("formRecupera:usuario")).sendKeys("email.errado@gmail.com");
		driver.findElement(By.id("formRecupera:enviar")).click();
		
		//System.out.println(driver.findElement(By.xpath("//div/div/div/ul/li")).getText());
		//driver.findElement(By.tagName("div")).getText().contains("Erro no pedido de mudan�a de senha");
		//driver.findElement(By.tagName("h4")).getText().contains("Pedido realizado com sucesso");
		//driver.findElement(By.tagName("div")).getText().contains("Foi enviado para voc� um e-mail contendo informa��es para recupera��o da senha.");
		
		driver.quit();		
	}
	
	@Test
	public void testeLogout1() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1200, 700));
		driver.get("https://teste.projetolead.com.br/ead2pcd/app/login?ctrl=1");
		
		driver.findElement(By.id("usuario")).sendKeys("alunosurdo");
		driver.findElement(By.id("senha")).sendKeys("abcd1234");
		driver.findElement(By.name("_spring_security_remember_me")).click();
		driver.findElement(By.tagName("button")).click();
		
		driver.findElement(By.id("imgUsuario")).click();
		driver.findElement(By.id("logoff")).click();
		
		driver.quit();
		
	}
	@Test
	public void testeLogout2() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(700, 700));
		driver.get("https://teste.projetolead.com.br/ead2pcd/app/login?ctrl=1");
		
		driver.findElement(By.id("usuario")).sendKeys("alunosurdo");
		driver.findElement(By.id("senha")).sendKeys("abcd1234");
		driver.findElement(By.name("_spring_security_remember_me")).click();
		driver.findElement(By.tagName("button")).click();
		
		driver.findElement(By.id("imgUsuario")).click();
		driver.findElement(By.id("logoff")).click();
		
		driver.quit();
		
	}
	
	
	@Test
	public void testeLogout3() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1200, 700));
		driver.get("https://teste.projetolead.com.br/ead2pcd-spa/");
		
		driver.findElement(By.id("login")).sendKeys("alunosurdo");
		driver.findElement(By.id("inputPassword")).sendKeys("abcd1234");
		driver.findElement(By.name("remember")).click();
		driver.findElement(By.tagName("button")).click();
		
		driver.findElement(By.id("avatar")).click();
		driver.findElement(By.id("logout")).click();
		
		driver.quit();
		
	}
	
	
	
	
}
