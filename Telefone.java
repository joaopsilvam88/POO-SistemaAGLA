package object;
public class Telefone {
	
	private String prefixo, numero, operadora;
	
	public Telefone() {
		this("","","");
	}
	
	public Telefone(String prefixo, String numero, String operadora) {
		this.prefixo = prefixo;
		this.numero = numero;
		this.operadora = operadora;
	}
	public String getPrefixo() {
		return prefixo;
	}
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getOperadora() {
		return operadora;
	}
	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	
	
	@Override
	public String toString() {
		return getOperadora()+"," + getPrefixo() + "," + getNumero();
	}
}
