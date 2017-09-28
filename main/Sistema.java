package main;

import java.util.*;
import object.*;

public class Sistema {

	Map<String, Professor> professores;
	Map<String, Aluno> alunos;
	
	public Sistema(){
		this(new HashMap<String, Professor>(), new HashMap<String, Aluno>());
	}
	public Sistema(Map<String, Professor> professores, Map<String, Aluno> alunos) {
		this.professores = professores;
		this.alunos = alunos;
	}
	public Map<String, Professor> getProfessores() {
		return professores;
	}
	public void setProfessores(Map<String, Professor> professores) {
		this.professores = professores;
	}
	public Map<String, Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(Map<String, Aluno> alunos) {
		this.alunos = alunos;
	}
	public void addAluno(String cpf, Aluno aluno){
		alunos.put(cpf, aluno);
	}
	public void addProfessor(String cpf, Professor professor){
		professores.put(cpf, professor);
	}
	public void removeAluno(String cpf){
		alunos.remove(cpf);
	}
	public void removeProfessor(String cpf){
		professores.remove(cpf);
	}
	public String verificarAluno(String cpf){
		return alunos.get(cpf).toString();
	}
	public String verificarProfessor(String cpf){
		return professores.get(cpf).toString();
	}
	public List<Aluno> verificarTurma(String ano){
		List<Aluno> lista = new ArrayList<Aluno>();
		for(Aluno a: alunos.values()){
			if(a.getAno().equals(ano)){
				lista.add(a);
			}
		}
		return lista;
	}
	public List<Professor> professoresDaDisciplina(String nomeDisciplina){
		List<Professor> lista = new ArrayList<Professor>();
		for(Professor p: professores.values()){
			if(p.getDisciplinas().contains(nomeDisciplina)){
				lista.add(p);
			}
		}
		return lista;
	}
	
	public void gravarDados(){
		
		
		
		//TODO:
		// hERCULES IMPLEMENTAR
	}
	public void recuperarDados(){
		//TODO:
		// hERCULES IMPLEMENTAR
		
	}
}