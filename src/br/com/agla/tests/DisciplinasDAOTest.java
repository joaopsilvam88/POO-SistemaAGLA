package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Sistema.Disciplina;
import dao.DisciplinasDAO;
import exception.ErroConexaoException;

public class DisciplinasDAOTest {
	
	private DisciplinasDAO dao;
	
	@Before
	public void testDisciplinasDAO() {
		dao = new DisciplinasDAO();
	}

	@Test
	public void testAdicionaAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdicionaAlunos() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAlunos() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdicionaProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaAlunos() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaProfessores() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaDisciplina() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaAlunosDaDisciplina() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaProfessoresDaDisciplina() {
		fail("Not yet implemented");
	}

	@Test
	public void testExiste() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaID() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisciplinaNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlunosDaDisciplina() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteTurma() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteTurma1() {
		fail("Not yet implemented");
	}

	@Test
	public void testLecionaProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteProfessor1() {
		fail("Not yet implemented");
	}

	@Test
	public void testQdtAlunosTurma() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLecionador() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteDisciplinaInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteDisciplinaStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testProfessorLeciona() {
		fail("Not yet implemented");
	}

	@Test
	public void testHistorico() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisciplinaID() {
		fail("Not yet implemented");
	}

	@Test
	public void testHistorico1() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDisciplinas() {
		try {
			List<Disciplina> list = dao.getDisciplinas();
			System.out.println(list.size());
			for(Disciplina d:list) {
				System.out.println(d.toString() + "  -  "+d.getId() + "  -  " + d.getProfessor());
				//System.out.println("id disciplina: " +d.getId() + "  " + d.getProfessor().getId());
				
//				/System.out.println(d.getProfessor().toString());
			}
			
			
			
//			System.out.println(list.toString());
//			System.out.println(list.get(0).getProfessor().toString());
		} catch (ErroConexaoException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testBoletim() {
		try {
			String[][] s= dao.boletim(29);
			
			//System.out.println(s.length);
			System.out.print(s[0][0] + " " + " " + s[0][1] + " " + s[0][2] + " " + s[0][3] + " " + s[0][4]+";");
			//System.out.println(s[1][2]);
		} catch (ErroConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void bimestreAluno() {
		try {
			String[][] d = dao.bimestreAluno(29, 1);
			//System.out.println(d.length);
			for(int a=0;a<d.length;a++) {
				
			}
			System.out.println(d[0][0] + " " + d[0][1] + " " + d[0][2] + " " + d[0][3] + " " +d[0][4]);
			System.out.print(d[1][0] + " " + d[1][1] + " " + d[1][2] + " " + d[1][3] + " " +d[1][4]);
			
			
		} catch (ErroConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	
}

