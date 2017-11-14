package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Sistema.Aluno;
import Sistema.DadosPessoais;
import Sistema.Endereco;
import Sistema.Telefone;
import dao.AlunoDAO;
import exception.ErroConexaoException;
import exception.JaExisteException;
import exception.NaoExisteException;

public class AlunoDAOTest {
	private AlunoDAO dao;
	private Aluno aluno;
	
//	@Before
	public AlunoDAOTest() {
		
		dao = new AlunoDAO();
		aluno = new Aluno();
		
		aluno.setMatricula("19970101");
		aluno.setAno("2017");
								// estado, cidade, logradouro, bairro, numero, complemento
		Endereco endereco = new Endereco("PB", "JOÃO PESSOA", "AVENIDA DAS INSDUSTRIAIS", "DISTRITO INDUSTRIAL", "102","PERTO DA PADARIA");
								// operadora, prefixo, numero
		Telefone telefone = new Telefone("CLARO", "83", "991717171");
		
		// nome, dataNascimento, sexo, cpf, rg, Endereco endereco, Telefone telefone, email, List<ImageIcon> documentos
		DadosPessoais dados = new DadosPessoais("HERCULES SILVA DE SOUZA", "06/02/1997","M", "11122233344","1234567",endereco,telefone, "herculano@gmail.com",null);

		aluno.setDadosPessoais(dados);
		
		
		// asserEqual(valorEsperado, valorRetornado);
	}
	
	
	@Test
	public void testAdiciona() {fail("Depois testa");
		try {
			dao.adiciona(aluno);
		} catch (ErroConexaoException | JaExisteException e) {
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAltera() {fail("Depois testa");
		
		// Mudança colocando o acento e o souza com s
		aluno.getDadosPessoais().setNome("HÉRCULES SILVA DE SOUSA");
		
		// cpf tem que ser o antigo, caso queira mudar o cpf, porque com isso ele vai achar o cpf, para depois mudar
		try {
			dao.altera(aluno, "19191919191");
		} catch (ErroConexaoException | NaoExisteException e) {
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRemove() {fail("Depois testa");
		
		// cpf do aluno para remover
		try {
			dao.remove("11122233344"); // este é o cpf antigo, se usou o testAltera, provavelmente tenha mudado isso
			
			// asserEqual(valorEsperado, valorRetornado);
			assertEquals(0,dao.existeAluno("0000000000")); // pesquisa pela matricula
			
		} catch (ErroConexaoException | NaoExisteException e) {
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testPesquisa() {fail("Depois testa");
		try {
			dao.pesquisa("11122233344");// este é o cpf antigo, se usou o testAltera, provavelmente tenha mudado isso
		} catch (ErroConexaoException | NaoExisteException e) {
			//e.printStackTrace();
			fail(e.getMessage());
		} 
	}

	@Test
	public void testExisteAluno() {fail("Depois testa");
		try {
			assertTrue(dao.existeAluno("19970101") > 0 );  // esta é a matricula antiga, caso tenha usado o testAltera provavelmente mudou
		} catch (ErroConexaoException e) {
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testPesquisaCpf() {fail("Depois testa");
		try {
			dao.pesquisa("11122233344"); // este é o cpf antigo, se usou o testAltera, provavelmente tenha mudado isso
		} catch (ErroConexaoException | NaoExisteException e) {
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testPesquisaId() {fail("Depois testa");
		try {
			assertTrue(dao.pesquisaID("19970101") > 0);  // esta é a matricula antiga, caso tenha usado o testAltera provavelmente mudou
		} catch (ErroConexaoException e) {
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testPesquisaMatricula() {fail("Depois testa");
		try {
			assertEquals("11122233344",dao.pesquisaMatricula("00000"));  // este é o cpf antigo, se usou o testAltera, provavelmente tenha mudado isso
		} catch (ErroConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void testNomeTelefoneEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testTest() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisa1() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaID() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaPeloPrimeiroNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaPeloSegundoNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaPeloTerceiroNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaNomeLetra() {
		fail("Not yet implemented");
	}

	@Test
	public void testPesquisaIDNome() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPesquisaNomes() {
		fail();
		try {
			String[][] s = dao.pesquisaPrimeiroNomes("Herculano");
			for(int i=0;i<s.length;i++) {
				System.out.println(s[i][0]);
				System.out.println(s[i][1]);
				System.out.println(s[i][2]);
			
			}
			
		} catch (ErroConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testNomesStartWith() {

		try {
			Aluno[] a = dao.nomesStartWith("hercul");
			// System.out.println(a.toString() + " " + a.length);

			for (int i = 0; i < a.length; i++) {
				System.out.println(a[i].toString());
			}

		} catch (ErroConexaoException | NaoExisteException e) {

			e.printStackTrace();
			fail(e.getMessage());
		}

	}
	
}
