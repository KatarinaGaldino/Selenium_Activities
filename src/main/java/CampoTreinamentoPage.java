import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private MyDSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new MyDSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino(){
		dsl.clicar("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino(){
		dsl.clicar("elementosForm:sexo:1");
	}
	
	public void setComidaCarne(){
		dsl.clicar("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango(){
		dsl.clicar("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza(){
		dsl.clicar("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano(){
		dsl.clicar("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String value) {
		dsl.selecionaCombo("elementosForm:escolaridade", value);
	}
	
	public void setEsporte(String... valores) {
		for(String valor: valores)
			dsl.selecionaCombo("elementosForm:esportes", valor);
	}
	
	public void setCadastro() {
		dsl.clicar("elementosForm:cadastrar");
	}
	
	public String getResultado() {
		return dsl.pegaTexto("resultado");
	}
	
	public String getNome() {
		return dsl.pegaTexto("descNome");
	}
	
	public String getSobrenome() {
		return dsl.pegaTexto("descSobrenome");
	}
	
	public String getSexo() {
		return dsl.pegaTexto("descSexo");
	}
	
	public String getComida() {
		return dsl.pegaTexto("descComida");
	}
	
	public String getEscolaridade() {
		return dsl.pegaTexto("descEscolaridade");
	}
	
	public String getEsporte() {
		return dsl.pegaTexto("descEsportes");
	}
}
