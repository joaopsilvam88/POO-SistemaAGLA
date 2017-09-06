import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.ImageIcon;

/** COLOCAR A CAIXA DE OPÇÕES AO ADICIONAR A IDADE, LIMITANDO AS POSSIBILIDADES ATÉ O ANO ATUAL*/

public class DadosPessoais {

	private String nome, dataNascimento, sexo, cpf, rg, email;
	private Endereco endereco;
	private Telefone telefone;
	private List<ImageIcon> documentos;
	
	public DadosPessoais(){
		this("", "", "", "", "", new Endereco(), new Telefone(), "", new ArrayList<ImageIcon>());
	}
	public DadosPessoais(String nome, String dataNascimento, String sexo, String cpf, String rg, Endereco endereco, Telefone telefone, String email, List<ImageIcon> documentos) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.documentos = documentos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public int getIdade(){
		int vari1 = Integer.parseInt(dataNascimento.substring(0, 2));
		int vari2 = Integer.parseInt(dataNascimento.substring(3, 5));
		int vari3 = Integer.parseInt(dataNascimento.substring(6));
		if(vari2 < pegarDataAtual().get(1)){
			return pegarDataAtual().get(2) - vari3;
		}else if(vari2 > pegarDataAtual().get(1)){
			return pegarDataAtual().get(2) - (vari3 + 1);
		}else{
			if(vari1 < pegarDataAtual().get(0)){
				return pegarDataAtual().get(2) - vari3;
			}else if(vari1 > pegarDataAtual().get(0)){
				return pegarDataAtual().get(2) - (vari3 + 1);
			}else{
				return pegarDataAtual().get(2) - vari3;
			}
		}
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<ImageIcon> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<ImageIcon> documentos) {
		this.documentos = documentos;
	}
	public static List<Integer> pegarDataAtual(){
		List<Integer> lista = new ArrayList<Integer>();
		Date data = GregorianCalendar.getInstance().getTime();
		SimpleDateFormat formatar = new SimpleDateFormat();
		String d = formatar.format(data);
		lista.add(Integer.parseInt(d.substring(0, 2)));
		lista.add(Integer.parseInt(d.substring(3, 5)));
		lista.add(Integer.parseInt("20"+d.substring(6, 8)));
		return lista;
	}
}


