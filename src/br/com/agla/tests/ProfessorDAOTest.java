package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.ProfessorDAO;
import exception.ErroConexaoException;

public class ProfessorDAOTest {
	private ProfessorDAO dao;
	
	
	@Before
	public void testProfessorDAO() {
		dao = new ProfessorDAO();
	}
	
	

	@Test
	public void testAdiciona() {
		fail("Not yet implemented");
	}

	@Test
	public void testAltera() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisa() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteProfessorString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaCpf() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaId() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaMatricula() {
		fail("Not yet implemented");
	}

	@Test
	public void testExiste() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteProfessorInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaIDNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testProfessorInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaNomesStartsWith() {
		try {
			String n[][] = dao.pesquisaNomesStartsWith("Maria da Conceição");
			System.out.println(n.length);
			System.out.println(n[0][0]);
			
		} catch (ErroConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
