package object;
public class Endereco {
	
	private int id;
	private String estado;
	private String cidade;
	private String logradouro;
	private int numero;
	private String bairro;
	private String complemento;
	
	public Endereco() {
		this("","","","",0,"");
	}
	
	public Endereco(String estado, String cidade, String logradouro,String bairro, int numero, String complemento) {
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Estado = " + this.estado + ", Cidade = " + this.cidade +
                        ", Bairro = " + this.bairro + ", Logradouro = " + this.logradouro +
                        ", Numero = " + this.numero + ", Complemento = " + this.complemento;
	}
	
}
