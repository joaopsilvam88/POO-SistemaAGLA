package object;
import java.util.*;
import com.poo.sisagla.exceptions.*;

public interface SistemaInterface {

	public Map<String, Professor> getProfessores() throws NaoExisteException;
	public void setProfessores(Map<String, Professor> professores);
	public Map<String, Aluno> getAlunos() throws NaoExisteException;
	public void setAlunos(Map<String, Aluno> alunos);
	public void addAluno(String cpf, Aluno aluno) throws JaExisteException;
	public void addProfessor(String cpf, Professor professor);
	public void removeAluno(String cpf);
	public void removeProfessor(String cpf);
	public Aluno verificarAluno(String cpf);
	public Professor verificarProfessor(String cpf);
	public List<Aluno> verificarTurma(String ano);
	public List<Professor> professoresDaDisciplina(String nomeDisciplina);
}
