package object;

import java.util.*;

public class Disciplina {

	NomeDisciplina nome;
	String turno, turma;
	List<Aluno> alunos;
	
	public Disciplina(){
		this(NomeDisciplina.ADEFINIR, "", "", new ArrayList<Aluno>());
	}
	public Disciplina(NomeDisciplina nome, String turno, String turma, List<Aluno> alunos) {
		this.nome = nome;
		this.turno = turno;
		this.turma = turma;
		this.alunos = alunos;
	}
	public NomeDisciplina getNome() {
		return nome;
	}
	public void setNome(NomeDisciplina nome) {
		this.nome = nome;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}