package object;

import exception.*;
import java.util.*;

import dao.AlunoDAO;
import dao.EntraDAO;
import dao.ProfessorDAO;
import object.*;

public class Sistema {
	
	private ProfessorDAO pdao;
	private AlunoDAO adao;
	private EntraDAO edao;
	
    Map<String, Professor> professores;
    Map<String, Aluno> alunos;
    
    public Sistema() {
        this(new HashMap<String, Professor>(), new HashMap<String, Aluno>());
    }

    public Sistema(Map<String, Professor> professores, Map<String, Aluno> alunos) {
        this.professores = professores;
        this.alunos = alunos;
        this.pdao = new ProfessorDAO();
        this.adao = new AlunoDAO();
        this.edao = new EntraDAO();
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

    public void addAluno(String cpf, Aluno aluno) throws AlunoJaExisteException {
        
    	try {
    		adao.adiciona(aluno);
		} catch (ErroConexaoException e) {
			throw new AlunoJaExisteException("Aluno <"+aluno.getDadosPessoais().getCpf()+"> ja cadastrado no sistema");
		}
    }

    public void addProfessor(String cpf, Professor professor) throws ProfessorJaExisteException {
        
    	try {
			pdao.adiciona(professor);
		} catch (ErroConexaoException e) {
			 throw new ProfessorJaExisteException("Professor <"+professor.getDadosPessoais().getCpf()+"> ja cadastrado no sistema");
		}	
    }

    public void removeAluno(String cpf) throws AlunoInexistenteException {
    	
    	try {
    		adao.remove(cpf);
		} catch (ErroConexaoException e) {
			throw new AlunoInexistenteException("Erro ao alterar. Aluno <"+cpf+"> nao encontrado no banco de dados");
		}
    }

    public void removeProfessor(String cpf) throws ProfessorInexistenteException {
    	
    	try {
    		pdao.remove(cpf);
		} catch (ErroConexaoException e) {
			throw new ProfessorInexistenteException("Erro ao remover professor. Professor <"+cpf+"> nao encontrado no banco de dados");
		}
    }

    public Aluno verificarAluno(String cpf) throws AlunoInexistenteException {
        Aluno a = new Aluno();
        try {
			a = adao.pesquisa(cpf);
		} catch (ErroConexaoException e) {
			throw new AlunoInexistenteException("Aluno <"+cpf+"> nao encontrado no banco de dados");
		}
        
        return a;
    }

    public Professor verificarProfessor(String cpf) throws ProfessorInexistenteException {
        Professor p = new Professor();
        
		try {
			p=pdao.pesquisa(cpf);
		} catch (ErroConexaoException e) {
			throw new ProfessorInexistenteException("Professor <"+cpf+"> nao encontrado no banco de dados");
		}
		return p;
    }

    public List<Aluno> verificarTurma(String ano) throws TurmaInexistenteExeption {
        List<Aluno> lista = new ArrayList<Aluno>();
        for (Aluno a : alunos.values()) {
            if (a.getAno().equals(ano)) {
                lista.add(a);
            }
        }
        if(lista.size() == 0){
            throw new TurmaInexistenteExeption("Esta turma não foi cadastrada");
        }
        return lista;
    }

    //@SuppressWarnings("unlikely-arg-type")
	public List<Professor> professoresDaDisciplina(String nomeDisciplina) throws ProfessorInexistenteException {
        List<Professor> lista = new ArrayList<Professor>();
        for (Professor p : getListaDisciplinas()) {
            if (p.getDisciplinas().contains(nomeDisciplina)) {
                lista.add(p);
            }
        }
        if(lista.size() == 0){
            throw new ProfessorInexistenteException("Não exite professores para esta disciplina");
        }
        return lista;
    }
	
	public void alterarProfessor(Professor professor) throws ProfessorInexistenteException{
		try {
			pdao.altera(professor, professor.getDadosPessoais().getCpf());
			
			//TODO: Arrumar o cpf depois!!
		} catch (ErroConexaoException e) {
			throw new ProfessorInexistenteException("Professor não encontrado.");
		}
	}	
	public void alterarAluno(Aluno aluno) throws AlunoInexistenteException{
		try {
			adao.altera(aluno, aluno.getDadosPessoais().getCpf());
			
			//TODO: Arrumar o cpf depois!!
		} catch (ErroConexaoException e) {
			throw new AlunoInexistenteException("Professor não encontrado.");
		}
	}
	
	public boolean entra(String login, String senha){
		try {
			if(edao.entra(login, senha)) return true;
		} catch (ErroConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String[][] getListaProf(){
		try {
			return pdao.getLista();
		} catch (ListaVaziaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Professor> getListaDisciplinas(){
		try {
			return pdao.getListaProf();
		} catch (ListaVaziaException e) {}
		return null;
	}
}
