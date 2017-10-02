package bd2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DataBase1Test {
	
	@Before
	public void DataBase1Test() {
		DataBase1 bd = new DataBase1();
		
	}
	
	@Test
	public void testExisteAluno() {
		DataBase1 bd = new DataBase1();
		assertTrue(bd.existeAluno("111222"));
	}

	@Test
	public void testExisteProfessor() {
		DataBase1 bd = new DataBase1();
		assertTrue(bd.existeProfessor("2020"));
	}

	@Test
	public void testExisteTelefone() {
		DataBase1 bd = new DataBase1();
		assertTrue(bd.existeTelefone("83", "298829", "TelefoneAluno"));
	}

	@Test
	public void testExisteDadosPessoais() {
		DataBase1 bd = new DataBase1();
		assertTrue(bd.existeDadosPessoais("22333", "DadosPessoaisAluno"));
		assertTrue(bd.existeDadosPessoais("212333", "DadosPessoaisProfessor")); // 212333
	}
	
	@Test
	public void testeExisteEndereco() {
		DataBase1 bd = new DataBase1();
		assertTrue(bd.existeEndereco("PB","Rio","rua da vargem","Centro", "EnderecoAluno"));
	}

}
