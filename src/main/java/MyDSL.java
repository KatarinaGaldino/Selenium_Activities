import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyDSL {

	private WebDriver driver;
	
	public MyDSL(WebDriver driver) {
		this.driver = driver;
	}
	
    public void escrever(String id, String texto) {
    	driver.findElement(By.id(id)).sendKeys(texto);	
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
    
    public String pegaValorCombo(String id) {
    	WebElement element = driver.findElement(By.id(id));
		Select comboBox = new Select(element);	
		return comboBox.getFirstSelectedOption().getText();
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
}
