package bd2;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.ImageIcon;

import org.junit.Test;

import exception.*;
import object.*;

public class DataBase2Test {

	@Test // OK
	public void testSalvarAluno() {
		DataBase1 d = new DataBase1();
		
		fail("TUDO OK - salvarALUNO");
		try {
			Aluno a = new Aluno();
			a.setMatricula("2016001"); // Ok
			a.setAno("2017");
			d.salvarAluno(a); //2018288 terah que dar erro
			
		} catch (ErroConexaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (AlunoJaExisteException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test //OK
	public void testSalvarProfessor() {
		DataBase1 d = new DataBase1();
		Professor p = new Professor();
		p.setMatricula("20161101");
		p.setLattes("http://professor.com..");
		
		fail("TUDO OK - salvarPROFESSOR");
		try {
			d.salvarProfessor(p);
		} catch (ErroConexaoException e) {

			e.printStackTrace();
			fail(e.getMessage());
		} catch (ProfessorJaExisteException e) {

			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test //OK
	public void testSalvarDadosPessoais() {
		DataBase1 d = new DataBase1();
		// nome, dataNascimento, sexo, cpf, rg, Endereco endereco, Telefone telefone, String email, List<ImageIcon> documentos
		//DadosPessoais da = new DadosPessoais("Herculano", "06/02/1997","M", "10028839183","3029192",null,null, "herculano@gmail.com",null);
		//int id = 37; // esse registro existe
		
		//fail("TUDO OK");
		DadosPessoais da = new DadosPessoais("Herculano", "06/02/1997","M", "100288322183","3029192",null,null, "herculano@gmail.com",null);
		int id = 3;
		
//		try {
//			//d.salvarDadosPessoais(da, id, "DadosPessoaisAluno");
//		} catch (ErroConexaoException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
		
	}

	@Test // OK
	public void testSalvarEndereco() {
		DataBase1 d = new DataBase1();
		// estado, cidade, logradouro,bairro, int numero, complemento)
		Endereco endereco = new Endereco("SP", "NITEROI", "travessa cangeuiro", "CENTRO", 10010,"PERTO DA PADARIA DO SEU ZE");
		int id = 1;
		
		fail("Todo ok");
		
//		try {
//			d.salvarEndereco(endereco, id, "EnderecoAluno");
//		} catch (ErroConexaoException | AlunoInexistenteException |ProfessorInexistenteException  e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
	}

	@Test
	public void testSalvarTelefone() {
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

	@Test
	public void testRecuperarAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperarProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperarDadosPessoaisAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperarDadosPessoaisProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperarTelefone() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperarEndereco() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteProfessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteTelefone() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteDadosPessoais() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteEndereco() {
		fail("Not yet implemented");
	}

}
