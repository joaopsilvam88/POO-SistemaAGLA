package br.com.agla.classes;

public abstract class Pessoa {
	
	public abstract String getMatricula();
	public abstract void setMatricula(String matricula);
	public abstract DadosPessoais getDadosPessoais();
	public abstract void setDadosPessoais(DadosPessoais dados);
	public abstract int getId();
	public abstract void setId(int id);
	public abstract String[][] getBoletim();
	public abstract void setBoletim(String[][] dados);
	public abstract String getLattes();
	public abstract void setLattes(String lattes);
	public abstract String getTurma();
	public abstract void setTurma(String turma);
}
