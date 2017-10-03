package bd;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import exception.*;
import object.*;

public class DataBase4Test {

//	@Test
//	public void testDataBase1() {
//		//fail("Not yet implemented");
//	}

	@Test
	public void testSalvarAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarDadosPessoaisAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarDadosPessoaisProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarEnderecoAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarEnderecoProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarTelefoneAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarTelefoneProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarAlunoCompleto() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarProfessorCompleto() {
		fail("Not yet implemented");
	}

	
	//
	
	@Test
	public void testRecuperarAluno() {
		DataBase1 db = new DataBase1();
		
		fail("TUDO OK");
		try {
			
			Aluno a = db.recuperarAluno("20110001");
			System.out.println(a + "- " +a.getId());
			assertEquals(a.getId(),0);
			
		} catch (ErroConexaoException | AlunoInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRecuperarProfessor() {
		DataBase1 db = new DataBase1();
		
		fail("TUDO OK");
		try {	
			Professor p = db.recuperarProfessor("201100021");
			
			System.out.println(p.toString() + "- " +p.getId());
			//assertEquals(p.getId(),0);
			
		} catch (ErroConexaoException | ProfessorInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRecuperarDadosPessoaisAlunoInt() {
		DataBase1 db = new DataBase1();
		
		fail("TUDO OK");
		try {	
			DadosPessoais p = db.recuperarDadosPessoaisAluno(4);
			
			System.out.println(p.toString() + "- " +p.getId());
			
			
		} catch (ErroConexaoException | AlunoInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
		
		
	}

	@Test
	public void testRecuperarDadosPessoaisAlunoString() {
		DataBase1 db = new DataBase1();
		
		fail("TUDO OK");
		try {	
			DadosPessoais p = db.recuperarDadosPessoaisAluno("100288322183");
			
			System.out.println(p.toString() + "- " +p.getId());
			
		} catch (ErroConexaoException | AlunoInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		

		
		
	}

	@Test
	public void testRecuperarDadosPessoaisProfessorInt() {
		DataBase1 db = new DataBase1();
		fail("TUDO OK");
		
		try {	
			DadosPessoais p = db.recuperarDadosPessoaisProfessor(4);//(4);
			
			System.out.println(p.toString() + "- " +p.getId());
			
		} catch (ErroConexaoException | ProfessorInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRecuperarDadosPessoaisProfessorString() {
		DataBase1 db = new DataBase1();
		
		fail("TUDO OK");
		try {	
			DadosPessoais p = db.recuperarDadosPessoaisProfessor("100288322183");
			
			System.out.println(p.toString() + "- " +p.getId());
			
		} catch (ErroConexaoException | ProfessorInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRecuperaTelefonesAluno() {
		DataBase1 db = new DataBase1();
		
		fail("TUDO OK");
		try {
			List<Telefone> tel = db.recuperaTelefonesAluno(4);
			
			for(Telefone t: tel) {
				System.out.println(t);
			}
			
			assertEquals(tel.size(), 2);
		
		} catch (ErroConexaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRecuperaTelefonesProfessor() {
		DataBase1 db = new DataBase1();
		fail("TUDO OK");
		try {
			List<Telefone> tel = db.recuperaTelefonesProfessor(4);
			for(Telefone t: tel) {
				System.out.println(t);
			}
			
			assertEquals(tel.size(),1);
		} catch (ErroConexaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}

	@Test
	public void testRecuperarEnderecoAluno() {
		DataBase1 db = new DataBase1();
		fail("TUDO OK");
		
		try {
			db.recuperarEnderecoAluno(4);
		} catch (ErroConexaoException | EnderecoInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testRecuperarEnderecoProfessor() {
		DataBase1 db = new DataBase1();
		
		//fail("TODO OK");
		try {
			Endereco e = db.recuperarEnderecoProfessor(4);
			System.out.println(e.toString());
		} catch (ErroConexaoException | EnderecoInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testPesquisaAluno() {
		DataBase1 db = new DataBase1();
		
		fail("TUDO OK");
		try {
			Aluno a =db.pesquisaAluno("20110001");
			System.out.println(a.toString());
			
		} catch (ErroConexaoException | AlunoInexistenteException | EnderecoInexistenteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	
	}

	@Test
	public void testPesquisaProfessor() {
		DataBase1 db = new DataBase1();
		
		fail("TUDO OK");
		try {
			Professor b = db.pesquisaProfessor("20110001");
			System.out.println(b.toString());
			
		} catch (ErroConexaoException | ProfessorInexistenteException | EnderecoInexistenteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	
	}

	@Test
	public void testExisteAluno() {
		fail("TUDO OK");
		DataBase1 db = new DataBase1();
		
		// 20110001  201100021
		assertTrue(db.existeAluno("20110001"));
	}

	@Test
	public void testExisteProfessor() {
		fail("TUDO OK");
		DataBase1 db = new DataBase1();
		
		assertTrue(db.existeProfessor("201100021"));
	}

	@Test
	public void testExisteTelefone() {
		fail("TUDO OK");
		DataBase1 db = new DataBase1();
		assertTrue(db.existeTelefone("CLARO", "83", "991718217", "TelefoneAluno"));
		
		//Tim	83	991717171
		assertTrue(db.existeTelefone("Tim", "83", "991717171", "TelefoneProfessor"));
	}

	@Test
	public void testExisteDadosPessoais() {
		fail("TUDO OK");
		DataBase1 db = new DataBase1();
		
		assertTrue(db.existeDadosPessoais("100288322183","DadosPessoaisAluno"));
		
		assertTrue(db.existeDadosPessoais("11101919","DadosPessoaisProfessor"));
	}

	@Test
	public void testExisteEndereco() {
		fail("TUDO OK");
		DataBase1 db = new DataBase1();
		
		//assertTrue(db.existeEndereco("SP", "NITEROI", "travessa cangeuiro", "centrinho", "EnderecoAluno"));
		
		//assertTrue(db.existeEndereco("SO", "CIDADE", "travessa cangeuiro", "CENTRO", "EnderecoProfessor"));
		
	}

}
