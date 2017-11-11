package br.com.agla.classes;

import javax.swing.ImageIcon;
import javax.swing.JTable;

public class Aluno extends Pessoa{

	private int id;
	private String matricula, turma;
	private String[][] boletim;
	private DadosPessoais dadosPessoais;
	private JTable historico;
	private ImageIcon declaracao;
	private String[] coluna = {"Português", "Matemática", "Ciências", "Biologia", "Química", "Física", "História", 
		"Geografia", "Inglês", "Espanhol", "Ed. Física"};
	
	public Aluno(){
		this("", "", new String[11][5], new DadosPessoais(), new JTable(), new ImageIcon());
	}
	
	public Aluno(String matricula, String turma, String[][] boletim, DadosPessoais dadosPessoais, JTable historico, ImageIcon declaracao) {
		this.matricula = matricula;
		this.turma = turma;
		for(int k = 0; k < 11; k++) {
			boletim[k][0] = coluna[k];
			boletim[k][1] = "   ---";
			boletim[k][2] = "   ---";
			boletim[k][3] = "   ---";
			boletim[k][4] = "   ---";
		}
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
	
	public String getTurma() {
		return turma;
	}
	
	public void setTurma(String ano) {
		this.turma = ano;
	}
	
	public String[][] getBoletim() {
		return boletim;
	}
	
	public void setBoletim(String[][] boletim) {
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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

    public String toString() {
        return "Id = " + this.getId()+ ", Matricula = " + this.getMatricula()+ ", Ano = " + this.turma
                + ", Boletim = " + this.boletim + ", Dados Pessoais = " + this.dadosPessoais
                + ", Historico = " + this.historico + ", Declaracao = " + this.declaracao;            
    }

	@Override
	public String getLattes() {
		/** Método não retorna nada, pelo fato de aluno não ter um currículo lattes*/
		return "";
	}

	@Override
	public void setLattes(String lattes) {
		// TODO Auto-generated method stub
		return;
	}
	
}
