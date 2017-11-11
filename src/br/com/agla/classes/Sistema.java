package br.com.agla.classes;
import java.util.*;

import br.com.agla.dao.*;
import br.com.agla.exceptions.*;

public class Sistema {
	
	private ProfessorDAO pdao;
	private AlunoDAO adao;
	private EntraDAO edao;
	private TurmaDAO tdao;
	private DisciplinasDAO ddao;
	
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

    public void addAluno(String cpf, Aluno aluno) throws JaExisteException {
        
    	try {
    		adao.adiciona(aluno);
		} catch (ErroConexaoException e) {
			throw new JaExisteException("Aluno <"+aluno.getDadosPessoais().getCpf()+"> ja cadastrado no sistema");
		}
    }

    public void addProfessor(String cpf, Professor professor) throws JaExisteException {
        
    	try {
			pdao.adiciona(professor);
		} catch (ErroConexaoException e) {
			 throw new JaExisteException("Professor <"+professor.getDadosPessoais().getCpf()+"> ja cadastrado no sistema");
		}	
    }

    public void removeAluno(String cpf) throws NaoExisteException {
    	
    	try {
    		adao.remove(cpf);
		} catch (ErroConexaoException e) {
			throw new NaoExisteException("Erro ao alterar. Aluno <"+cpf+"> nao encontrado no banco de dados");
		}
    }

    public void removeProfessor(String cpf) throws NaoExisteException {
    	
    	try {
    		pdao.remove(cpf);
		} catch (ErroConexaoException e) {
			throw new NaoExisteException("Erro ao remover professor. Professor <"+cpf+"> nao encontrado no banco de dados");
		}
    }

    public Aluno[] verificarAluno(String nome) throws NaoExisteException {
        Aluno[] a;
        try {
			a = adao.nomesStartWith(nome);
		} catch (ErroConexaoException e) {
			throw new NaoExisteException("Aluno <"+nome+"> nao encontrado no banco de dados");
		}
        
        return a;
    }

    public Professor[] verificarProfessor(String nome) throws NaoExisteException {
        Professor[] p;
        
		try {
			p=pdao.nomesStartWith(nome);
		} catch (ErroConexaoException e) {
			throw new NaoExisteException("Professor <"+nome+"> nao encontrado no banco de dados");
		}
		return p;
    }

    public String[][] verificarTurma(String turma) throws TurmaInexistenteExeption {
    	
    	
    	//tdao.alunosDaDisciplina(idDisciplina);
        
    	
    	List<Aluno> lista = new ArrayList<Aluno>();
        for (Aluno a : alunos.values()) {
            if (a.getTurma().equals(turma)) {
                lista.add(a);
            }
        }
        if(lista.size() == 0){
            throw new TurmaInexistenteExeption("Esta turma não foi cadastrada");
        }
        Aluno[] objetos = (Aluno[]) lista.toArray();
        String[][] dados = new String[4][4];
		for(int k = 0; k < objetos.length; k++) {
			dados[k][0] = ""+(k+1);
			dados[k][1] = objetos[k].getDadosPessoais().getNome();
			dados[k][2] = objetos[k].getDadosPessoais().getCpf();
			dados[k][3] = objetos[k].getDadosPessoais().getEmail();
		}
        return dados;
    }
    


    //@SuppressWarnings("unlikely-arg-type")
	public List<Professor> professoresDaDisciplina(String nomeDisciplina) throws NaoExisteException {
        List<Professor> lista = new ArrayList<Professor>();
        for (Professor p : getListaDisciplinas()) {
//            if (p.getDisciplinas().contains(nomeDisciplina)) {
//                lista.add(p);
//            }
        }
        if(lista.size() == 0){
            throw new NaoExisteException("Não exite professores para esta disciplina");
        }
        return lista;
    }
	
	public void alterarProfessor(Professor professor) throws NaoExisteException{
		try {
			pdao.altera(professor, professor.getDadosPessoais().getCpf());
			
			//TODO: Arrumar o cpf depois!!
		} catch (ErroConexaoException e) {
			throw new NaoExisteException("Professor não encontrado.");
		}
	}	
	public void alterarAluno(Aluno aluno) throws NaoExisteException{
		try {
			adao.altera(aluno, aluno.getDadosPessoais().getCpf());
			
			//TODO: Arrumar o cpf depois!!
		} catch (ErroConexaoException e) {
			throw new NaoExisteException("Professor não encontrado.");
		}
	}
	
	public void entra(String login, String senha) throws NaoExisteException, ErroConexaoException{
		if(edao.entra(login, senha)) return;
		throw new NaoExisteException("Login não encontrado");
	}	
	
	public void registra(String login, String senha) throws NaoExisteException, ErroConexaoException, LoginJaExisteException{
		edao.adiciona(login, senha);
	}
	
	public String[][] professoresDisciplina(String nomeDisciplina) throws ErroConexaoException, NaoExisteException {
		return ddao.pesquisaProfessoresDaDisciplina(nomeDisciplina);
	}
	public List<Professor> getListaProf(){
		try {
			return pdao.getLista();
		} catch (ListaVaziaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//	public List<Professor> getListaDisciplinas(){
//		try {
//			return pdao.getListaProf();
//		} catch (ListaVaziaException e) {}
//		return null;
//	}
}
