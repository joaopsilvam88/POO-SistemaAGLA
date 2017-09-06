import javax.swing.ImageIcon;
import javax.swing.JTable;

public class Aluno {

	String matricula, ano, boletim;
	DadosPessoais dadosPessoais;
	JTable historico;
	ImageIcon declaracao;
	
	public Aluno(){
		this("", "", "", new DadosPessoais(), new JTable(), new ImageIcon());
	}
	public Aluno(String matricula, String ano, String boletim, DadosPessoais dadosPessoais, JTable historico, ImageIcon declaracao) {
		this.matricula = matricula;
		this.ano = ano;
		this.boletim = boletim;
		this.dadosPessoais = dadosPessoais;
		this.historico = historico;
		this.declaracao = declaracao;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getBoletim() {
		return boletim;
	}
	public void setBoletim(String boletim) {
		this.boletim = boletim;
	}
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}
	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}
	public JTable getHistorico() {
		return historico;
	}
	public void setHistorico(JTable historico) {
		this.historico = historico;
	}
	public ImageIcon getDeclaracao() {
		return declaracao;
	}
	public void setDeclaracao(ImageIcon declaracao) {
		this.declaracao = declaracao;
	}
}
