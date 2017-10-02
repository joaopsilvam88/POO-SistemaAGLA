package bd2;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.*;
import object.*;

public class DataBase3Test {

	@Test
	public void testSalvarAluno() {
		DataBase1 db = new DataBase1();
		Aluno a = new Aluno();
		a.setMatricula("20110001"); 
		a.setAno("2017");
		
		fail("TUDO OK");
		try {
			db.salvarAluno(a);
		} catch (ErroConexaoException | AlunoJaExisteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSalvarProfessor() {
		DataBase1 db = new DataBase1();
		Professor p = new Professor();
		p.setMatricula("20110001"); 
		p.setLattes("http://lates...");
		
		fail("TUDO OK");
		try {
			db.salvarProfessor(p);
		} catch (ErroConexaoException | ProfessorJaExisteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSalvarDadosPessoaisAluno() {
		DataBase1 db = new DataBase1();
		DadosPessoais d = new DadosPessoais("Herculano", "06/02/1997","M", "100288322183","3029192",null,null, "herculano@gmail.com",null);
		
		fail("TUDO OK");
		try {
			db.salvarDadosPessoaisAluno(d, "20110001");
		} catch (ErroConexaoException | AlunoInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSalvarDadosPessoaisProfessor() {
		DataBase1 db = new DataBase1();
		DadosPessoais d = new DadosPessoais("Palhano", "06/02/1997","M", "100288322183","3029192",null,null, "palhano@gmail.com",null);
		
		fail("TODO OK");
		try {
			db.salvarDadosPessoaisProfessor(d, "20110001");
		} catch (ErroConexaoException | ProfessorInexistenteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testSalvarEnderecoAluno() {
		DataBase1 db = new DataBase1();
		Endereco e = new Endereco("SP", "NITEROI", "travessa cangeuiro", "CENTRO", 10010,"PERTO DA PADARIA DO SEU ZE");
		
		fail("TODO OK");
		try {
			db.salvarEnderecoAluno(e, "20110001");
		} catch (ErroConexaoException | AlunoInexistenteException ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testSalvarEnderecoProfessor() {
		DataBase1 db = new DataBase1();
		Endereco e = new Endereco("SP", "NITEROI", "travessa cangeuiro", "CENTRO", 10010,"PERTO DA PADARIA DO SEU ZE");
		
		fail("TODO OK");
		try {
			db.salvarEnderecoProfessor(e, "20110001");
		} catch (ErroConexaoException | ProfessorInexistenteException ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testSalvarTelefoneAluno() {
		DataBase1 db = new DataBase1();
		Telefone t = new Telefone("Tim", "83", "991717171");
		
		fail("TODO OK");
		try {
			db.salvarTelefoneAluno(t, "20110001");
		} catch (ErroConexaoException | AlunoInexistenteException | TelefoneJaExisteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSalvarTelefoneProfessor() {
		DataBase1 db = new DataBase1();
		Telefone t = new Telefone("Tim", "83", "991717171");
		
		fail("TODO OK");
		try {
			db.salvarTelefoneProfessor(t, "20110001-");
		} catch (ErroConexaoException | ProfessorInexistenteException | TelefoneJaExisteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSalvarAlunoCompleto() {
		
		fail("TODO OK");
		
		Aluno a = new Aluno();
		a.setMatricula("20110001");
		a.setAno("2017");
		DadosPessoais d = new DadosPessoais("Herculano", "06/02/1997","M", "100288322183","3029192",null,null, "herculano@gmail.com",null);
		Endereco e = new Endereco("SP", "NITEROI", "travessa cangeuiro", "CENTRO", 10010,"PERTO DA PADARIA DO SEU ZE");
		Telefone t = new Telefone("Tim", "83", "991717171");
		
		DataBase1 db = new DataBase1();
		d.setEndereco(e);
		d.setTelefone(t);
		a.setDadosPessoais(d);
		
		System.out.println(a.toString());
		
		
		try {
			db.salvarAlunoCompleto(a);
		} catch (ErroConexaoException | AlunoJaExisteException | AlunoInexistenteException
				| TelefoneJaExisteException ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		
	}

	@Test
	public void testSalvarProfessorCompleto() {
		
		fail("TUDO OK");
		
		DataBase1 db = new DataBase1();
		Professor p = new Professor();
		p.setMatricula("20110001");
		p.setLattes("http://www.lattes.com.br");
		DadosPessoais d = new DadosPessoais("wagner", "06/02/1997","M", "100288322183","3029192",null,null, "wagner@gmail.com",null);
		Endereco e = new Endereco("SP", "NITEROI", "travessa cangeuiro", "CENTRO", 10010,"PERTO DA PADARIA DO SEU ZE");
		Telefone t = new Telefone("Tim", "83", "991717171");
		
		d.setEndereco(e);
		d.setTelefone(t);
		p.setDadosPessoais(d);
		
		try {
			db.salvarProfessorCompleto(p);
		} catch (ErroConexaoException | ProfessorJaExisteException | ProfessorInexistenteException
				| AlunoInexistenteException | TelefoneJaExisteException ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		
	}

}
