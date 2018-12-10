import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class MyDSL {

	private WebDriver driver;
	
	public MyDSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public void escrever(By by, String texto){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void escrever(String id, String texto){
		escrever(By.id(id), texto);
	}
    
    public String pegaValorCampo(String id) {
    	return driver.findElement(By.id(id)).getAttribute("value");
    }
    
    public void clicar(String id) {
    	driver.findElement(By.id(id)).click();
    }
    
    public boolean verificaSelecao(String id) {
    	return driver.findElement(By.id(id)).isSelected();
    }
    
    public void selecionaCombo(String id, String valor) {
    	WebElement element = driver.findElement(By.id(id));
		Select comboBox = new Select(element);
		//comboBox.selectByIndex(2);
		//comboBox.selectByValue("superior");
		comboBox.selectByVisibleText(valor);	
    }
    
    public void deselecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}
    
    public String pegaValorCombo(String id) {
    	WebElement element = driver.findElement(By.id(id));
		Select comboBox = new Select(element);	
		return comboBox.getFirstSelectedOption().getText();
    }
    
    public List<String> obterValoresCombo(String id) {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
    
	public String pegaValueElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
    public void clicarLink(String valor) {
    	driver.findElement(By.linkText(valor)).click();
    }
    
    public String pegaTextoPorElemento(By by) {
    	return driver.findElement(by).getText();
    }
    
    public String pegaTexto(String id) {
    	return pegaTextoPorElemento(By.id(id));
    }
    
    public String alertaObterTexto(){
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
		
	}
	
	public String alertaObterTextoENega(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
		
	}
	
	public void alertaEscrever(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}
	
	public void sairFrame(){
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
}
