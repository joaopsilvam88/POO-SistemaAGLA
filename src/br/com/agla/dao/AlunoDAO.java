package br.com.agla.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alga.exceptions.*;
import Sistema.*;


/**
 * Data Access Object - Classe responsavel por acessar os dados do Aluno
 *
 */
public class AlunoDAO implements OperacoesAlunoDAO {

	private Connection connection;
	
	// definicao de constantes de acesso a tabelas
	protected final String ALUNO = "Aluno";
	protected final String DADOS_PESSOAIS_ALUNO = "DadosPessoaisAluno";
	protected final String TELEFONE_ALUNO = "TelefoneAluno";
	protected final String ENDERECO_ALUNO = "EnderecoAluno";

	public AlunoDAO() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			
		}
	}

	@Override
	public void adiciona(Aluno aluno) throws ErroConexaoException, JaExisteException {
		int id = 0;
		id = existeAluno(aluno.getMatricula());

		if (id == 0) {

			String sql = "INSERT INTO " + this.ALUNO + "(matricula,ano) VALUES(?,?)";

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setString(1, aluno.getMatricula());
				pstmt.setString(2, aluno.getTurma());

				pstmt.execute();

			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro na conecao tentar salvar aluno");
			}
			id = existeAluno(aluno.getMatricula());
			/// nome,sexo,cidade,logradouro,numero,bairro,complemento,id_aluno

			String sqlD = "INSERT INTO " + this.DADOS_PESSOAIS_ALUNO
					+ "(nome,sexo,rg,cpf,dataNascimento,email,id_aluno)" + " VALUES (?,?,?,?,?,?,?)";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlD)) {

				pstmt.setString(1, aluno.getDadosPessoais().getNome()); // nome
				pstmt.setString(2, aluno.getDadosPessoais().getSexo()); // sexo
				pstmt.setString(3, aluno.getDadosPessoais().getRg()); // rg
				pstmt.setString(4, aluno.getDadosPessoais().getCpf()); // cpf
				pstmt.setString(5, aluno.getDadosPessoais().getDataNascimento()); // dataNascimento
				pstmt.setString(6, aluno.getDadosPessoais().getEmail()); // email
				pstmt.setInt(7, id); // id_aluno

				pstmt.execute();

			} catch (SQLException e) {
				throw new ErroConexaoException("Erro na conecao tentar salvar dados do aluno");
			}

			String sqlE = "INSERT INTO " + this.ENDERECO_ALUNO
					+ "(estado,cidade,logradouro,numero,bairro,complemento,id_aluno) " + "VALUES(?,?,?,?,?,?,?)";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlE)) {
				pstmt.setString(1, aluno.getDadosPessoais().getEndereco().getEstado()); // entado
				pstmt.setString(2, aluno.getDadosPessoais().getEndereco().getCidade()); // cidade
				pstmt.setString(3, aluno.getDadosPessoais().getEndereco().getLogradouro()); // logradouro
				pstmt.setString(4, aluno.getDadosPessoais().getEndereco().getNumero()); // numero
				pstmt.setString(5, aluno.getDadosPessoais().getEndereco().getBairro()); // bairro
				pstmt.setString(6, aluno.getDadosPessoais().getEndereco().getComplemento()); // complemento
				pstmt.setInt(7, id); // id_aluno

				pstmt.execute();

			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro ao tentar salvar endereco do aluno");
			}

			String sqlT = "INSERT INTO " + this.TELEFONE_ALUNO + "(operadora,prefixo,numero,id_aluno) "
					+ "VALUES(?,?,?,?)";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlT)) {
				pstmt.setString(1, aluno.getDadosPessoais().getTelefone().getOperadora()); // operadora
				pstmt.setString(2, aluno.getDadosPessoais().getTelefone().getPrefixo()); // prefixo
				pstmt.setString(3, aluno.getDadosPessoais().getTelefone().getNumero()); // numero
				pstmt.setInt(4, id); // id_aluno

				pstmt.execute();
				return;
			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro ao tentar salvar telefone do aluno");
			}
		}
		throw new JaExisteException("Aluno ja adicionado no sistema");
	}

	// Esse metodo ira procurar pelo id e assim alterar
	@Override
	public void altera(Aluno aluno, String cpf) throws ErroConexaoException,  NaoExisteException{
		int id = 0;
		id = pesquisaId(cpf);
		// System.out.println(id);
		// Se ele existir entao pode alterar
		if (id > 0) {
			String sql = "UPDATE " + this.ALUNO + " SET matricula=?, ano=? WHERE idAluno=?";
			System.out.println(sql);
			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

				pstmt.setString(1, aluno.getMatricula()); // matricula
				pstmt.setString(2, aluno.getTurma()); // ano
				pstmt.setInt(3, id); // id continua sendo o mesmo

				pstmt.executeUpdate();

			} catch (SQLException e) {
				throw new ErroConexaoException("Erro ao tentar alterar alguns dados do aluno");
			}

			// update em Telefone
			String sqlT = "UPDATE " + this.TELEFONE_ALUNO + " SET operadora=?, prefixo=?, numero=? WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlT)) {
				pstmt.setString(1, aluno.getDadosPessoais().getTelefone().getOperadora()); // operadora
				pstmt.setString(2, aluno.getDadosPessoais().getTelefone().getPrefixo()); // prefixo
				pstmt.setString(3, aluno.getDadosPessoais().getTelefone().getNumero()); // numero

				pstmt.setInt(4, id); // id continua sendo o mesmo

				pstmt.executeUpdate(); // executar acao

			} catch (SQLException e) {
				throw new ErroConexaoException("Erro ao tentar alterar telefone ");
			}

			// update em Endereco
			String sqlE = "UPDATE " + this.ENDERECO_ALUNO
					+ " SET estado=?, cidade=?, logradouro=?, numero=?, bairro=?, complemento=? WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlE)) {
				pstmt.setString(1, aluno.getDadosPessoais().getEndereco().getEstado()); // estado
				pstmt.setString(2, aluno.getDadosPessoais().getEndereco().getCidade()); // cidade
				pstmt.setString(3, aluno.getDadosPessoais().getEndereco().getLogradouro()); // logradouro
				pstmt.setString(4, aluno.getDadosPessoais().getEndereco().getNumero()); // numero
				pstmt.setString(5, aluno.getDadosPessoais().getEndereco().getBairro()); // bairro
				pstmt.setString(6, aluno.getDadosPessoais().getEndereco().getComplemento()); // complemento

				pstmt.setInt(7, id); // id continua sendo o mesmo

				pstmt.executeUpdate(); // executar acao

			} catch (SQLException e) {
				throw new ErroConexaoException("Erro ao tentar alterar endereco do professor");
			}

			// update em DadosPessoais
			String sqlD = "UPDATE " + this.DADOS_PESSOAIS_ALUNO
					+ " SET nome=?, sexo=?, rg=?, cpf=?, dataNascimento=?, email=?  WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlD)) {
				pstmt.setString(1, aluno.getDadosPessoais().getNome()); // nome
				pstmt.setString(2, aluno.getDadosPessoais().getSexo()); // sexo
				pstmt.setString(3, aluno.getDadosPessoais().getRg()); // rg
				pstmt.setString(4, aluno.getDadosPessoais().getCpf()); // cpf
				pstmt.setString(5, aluno.getDadosPessoais().getDataNascimento()); // data nascimento
				pstmt.setString(6, aluno.getDadosPessoais().getEmail()); // email

				pstmt.setInt(7, id); // id continua sendo o mesmo

				pstmt.executeUpdate(); // executar acao
				return; // sair do metodo
			} catch (SQLException e) {
				throw new ErroConexaoException("Erro ao tentar alterar dados pessoais aluno");
			}
		} // if
		throw new NaoExisteException("Aluno nao encontrado no banco de dados");
	}

	@Override
	public void remove(String cpf) throws ErroConexaoException, NaoExisteException {
		int id = 0;
		id = pesquisaId(cpf);
		// isso quer dizer que ele nao existe, e nao tem como remover algo que nao
		// existe
		if (id == 0) {
			throw new NaoExisteException("Impossivel alterar. Aluno nao encontrado");
		}

		// deletar telefone
		String sqlT = "DELETE FROM " + this.TELEFONE_ALUNO + " WHERE id_aluno=?";
		try (PreparedStatement pstmt = connection.prepareStatement(sqlT)) {
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			throw new ErroConexaoException("Erro ao tentar remover telefone do aluno");
		}

		// deletar endereco
		String sqlE = "DELETE FROM " + this.ENDERECO_ALUNO + " WHERE id_aluno=" + id;
		try (PreparedStatement pstmt = connection.prepareStatement(sqlE)) {
			pstmt.execute();
		} catch (SQLException e) {
			throw new ErroConexaoException("Erro ao tentar remover endereco do aluno");
		}

		// deletar dados pessoais
		String sqlD = "DELETE FROM " + this.DADOS_PESSOAIS_ALUNO + " WHERE id_aluno=?";
		try (PreparedStatement pstmt = connection.prepareStatement(sqlD)) {
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			throw new ErroConexaoException("Erro ao tentar remover endereco do aluno");
		}

		// deletar o aluno. Tem que ser por ultimo
		String sql = "DELETE FROM " + this.ALUNO + " WHERE idAluno=?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			throw new ErroConexaoException("Erro ao tentar remover aluno");
		}
	}

	@Override
	public Aluno pesquisa(String cpf) throws ErroConexaoException, NaoExisteException {
		int id = 0;
		id = pesquisaId(cpf);
		System.out.println(id);
		if (id > 0) {
			
			Aluno aluno = new Aluno();
			DadosPessoais dados = new DadosPessoais();
			Telefone tel = new Telefone();
			Endereco endereco = new Endereco();

			// pesquisa telefone
			String sqlT = "SELECT * FROM " + this.TELEFONE_ALUNO + " WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlT)) {

				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					tel.setId(rs.getInt("idTelefone"));
					tel.setOperadora(rs.getString("operadora"));
					tel.setPrefixo(rs.getString("prefixo"));
					tel.setNumero(rs.getString("numero"));
				}
				rs.close();
			} catch (SQLException e) {
				throw new ErroConexaoException(
						"Erro ao tentar recuperar telefone, possivelmente um erro ao tentar se conectar com o banco de dados");
			}

			// pesquisa Endereco
			String sqlE = "SELECT * FROM " + this.ENDERECO_ALUNO + " WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlE)) {

				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					endereco.setId(rs.getInt("idEndereco"));
					endereco.setEstado(rs.getString("estado"));
					endereco.setCidade(rs.getString("cidade"));
					endereco.setLogradouro(rs.getString("logradouro"));
					endereco.setNumero(rs.getString("numero"));
					endereco.setBairro(rs.getString("bairro"));
					endereco.setComplemento(rs.getString("complemento"));
				}
				rs.close(); // fechando rs
			} catch (SQLException e) {
				throw new ErroConexaoException(
						"Erro ao tentar recuperar dados pessoais, possivelmente um erro ao tentar se conectar com o banco de dados");
			}

			// pesquisa DadosPessois
			String sqlD = "SELECT * FROM " + this.DADOS_PESSOAIS_ALUNO + " WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlD)) {

				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				// Se houver dados
				if (rs.next()) {
					dados.setNome(rs.getString("nome"));
					dados.setDataNascimento(rs.getString("dataNascimento"));
					dados.setSexo(rs.getString("sexo"));
					dados.setRg(rs.getString("rg"));
					dados.setCpf(rs.getString("cpf"));
					dados.setEmail(rs.getString("email"));
					dados.setId(rs.getInt("IdDadosPessoais"));
				}
				rs.close(); // fechando rs
			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro ao recuperar dados pessoais");
			}

			// pesquisando aluno
			String sql = "SELECT * FROM " + this.ALUNO + " WHERE idAluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					aluno.setId(rs.getInt("idAluno"));
					aluno.setMatricula(rs.getString("matricula"));
					aluno.setTurma(rs.getString("ano"));
				}
				rs.close(); // fechando rs
			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro ao recuperar dados pessoais");
			}

			dados.setEndereco(endereco);
			dados.setTelefone(tel);

			aluno.setDadosPessoais(dados);

			// System.out.println(tel.toString());

			return aluno;
		} // if
		throw new NaoExisteException("Aluno nao encontrado no banco de dados");
	}

	// Sera private - id
	/** Retorna o id */
	public int existeAluno(String matricula) throws ErroConexaoException {
		String sql = "SELECT idAluno FROM " + this.ALUNO + " WHERE matricula=?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, matricula); // colocar como segundo parametro
											// o que esta em interrogacao
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("idAluno");
			}
			rs.close(); // fechando conexao
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro ao tentar pesquisar aluno.");
		}
		return 0;
	}

	// Sera private - cpf
	/** Pesquisa por matricula e Retorna o cpf */
	public String pesquisaCpf(String matricula) throws ErroConexaoException {
		int id = 0;
		id = existeAluno(matricula);
		if (id > 0) {
			String sql = "SELECT cpf FROM " + this.DADOS_PESSOAIS_ALUNO + " WHERE id_aluno=?";

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getString("cpf");
				}
				rs.close(); // fechando rs
			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro ao tentar pesquisar aluno.");
			}
		}
		return "";
	}

	/** Pesquisa por cpf e Retorna o id */
	public int pesquisaId(String cpf) throws ErroConexaoException {
		String sql = "SELECT id_aluno FROM " + this.DADOS_PESSOAIS_ALUNO + " WHERE cpf=?"; // "+cpf;
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, cpf);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_aluno");
			}
			rs.close(); // fechando rs
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro ao tentar pesquisar aluno.");
		}

		return 0;
	}

	/**
	 * Pesquisa por cpf e retorna a matricula
	 * 
	 * @param cpf
	 * @return String vazio senao houver matricula se houver
	 * 
	 */
	public String pesquisaMatricula(String cpf) throws ErroConexaoException {
		int id = pesquisaId(cpf);
		if (id > 0) {
			String sqlP = "SELECT matricula FROM " + this.ALUNO + " WHERE idAluno=?"; // "+cpf;
			try (PreparedStatement pstmt = connection.prepareStatement(sqlP)) {
				pstmt.setInt(1, id);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getString("matricula");
				}
				rs.close(); // fechando rs
			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro ao tentar pesquisar aluno.");
			}
		}
		return "";
	}

	@Override
	public boolean existe(String matricula) throws ErroConexaoException {
		return existeAluno(matricula) > 0;
	}

	@Override
	public List<Aluno> getLista() throws ListaVaziaException {

		List<Aluno> temp = new ArrayList<Aluno>();

		// ALUNO
		String sqlA = "SELECT * FROM " + this.ALUNO;
		try (PreparedStatement pstmt = connection.prepareStatement(sqlA); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Aluno a = new Aluno();
				a.setId(rs.getInt("idAluno"));
				a.setMatricula(rs.getString("matricula"));
				a.setTurma(rs.getString("ano"));
				temp.add(a);
			}

		} catch (SQLException e) {

		}
		if (temp.size() == 0) {
			throw new ListaVaziaException("Nenhum aluno encontrado");
		}
		System.out.println(temp.size());

		int i = 0;
		while (i < temp.size()) {
			int id = temp.get(i).getId();

			// DADOSPESSOAIS
			String sqlD = "SELECT * FROM " + this.DADOS_PESSOAIS_ALUNO + " WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlD)) {
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					temp.get(i).getDadosPessoais().setNome(rs.getString("nome"));
					temp.get(i).getDadosPessoais().setDataNascimento(rs.getString("dataNascimento"));
					temp.get(i).getDadosPessoais().setSexo(rs.getString("sexo"));
					temp.get(i).getDadosPessoais().setRg(rs.getString("rg"));
					temp.get(i).getDadosPessoais().setCpf(rs.getString("cpf"));
					temp.get(i).getDadosPessoais().setEmail(rs.getString("email"));
					temp.get(i).getDadosPessoais().setId(rs.getInt("IdDadosPessoais"));
				}
				rs.close(); // fechando rs
			} catch (SQLException e) {

			}

			// TELEFONE
			String sqlT = "SELECT * FROM " + this.TELEFONE_ALUNO + " WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlT)) {

				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					temp.get(i).getDadosPessoais().getTelefone().setId(rs.getInt("idTelefone"));
					temp.get(i).getDadosPessoais().getTelefone().setOperadora(rs.getString("operadora"));
					temp.get(i).getDadosPessoais().getTelefone().setPrefixo(rs.getString("prefixo"));
					temp.get(i).getDadosPessoais().getTelefone().setNumero(rs.getString("numero"));
				}
				rs.close();
			} catch (SQLException e) {

			}

			// ENDERECO
			String sqlE = "SELECT * FROM " + this.ENDERECO_ALUNO + " WHERE id_aluno=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlE)) {

				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					temp.get(i).getDadosPessoais().getEndereco().setId(rs.getInt("idEndereco"));
					temp.get(i).getDadosPessoais().getEndereco().setEstado(rs.getString("estado"));
					temp.get(i).getDadosPessoais().getEndereco().setCidade(rs.getString("cidade"));
					temp.get(i).getDadosPessoais().getEndereco().setLogradouro(rs.getString("logradouro"));
					temp.get(i).getDadosPessoais().getEndereco().setNumero(rs.getString("numero"));
					temp.get(i).getDadosPessoais().getEndereco().setBairro(rs.getString("bairro"));
					temp.get(i).getDadosPessoais().getEndereco().setComplemento(rs.getString("complemento"));
				}
				rs.close(); // fechando rs
			} catch (SQLException e) {

			}

			i++;
		}
		return temp;
	}

	/** Retorna nome, telefone, email */
	public String[][] nomeTelefoneEmail(int idAluno) throws ErroConexaoException {
		String[][] temp = new String[1][3]; // linha x coluna
		
		StringBuilder sqlQuery = new StringBuilder().append("SELECT * FROM ").append(this.DADOS_PESSOAIS_ALUNO)
				.append(" INNER JOIN ").append(this.TELEFONE_ALUNO).append(" ON ")
				.append(this.DADOS_PESSOAIS_ALUNO).append(".id_aluno=?")
				.append(" AND ").append(this.TELEFONE_ALUNO).append(".id_aluno=?");

		try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString())) {
			pstmt.setInt(1, idAluno);
			pstmt.setInt(2, idAluno);
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				temp[i][0] = rs.getString("nome");
				temp[i][1] = rs.getString("email");
				temp[i][2] = rs.getString("numero");
			}
			rs.close(); // fechando rs
		} catch (SQLException e) {
			throw new ErroConexaoException("Erro conn nomeTelefoneEmail");
		}

		return temp;
	}
	
	// TODO: Novo metodo
	/**
	 * Pesquisa pela matricula e retorna um objeto Aluno
	 */
	public Aluno pesquisa1(String matricula) throws ErroConexaoException, NaoExisteException {

		int id = pesquisaID(matricula);
		if (id == 0) {
			throw new NaoExisteException(
					new StringBuilder().append("Matricula invalida ->").append(matricula).toString());
		}
		Aluno aluno = new Aluno();
		
		StringBuilder slqQuery = new StringBuilder()
				.append("SELECT * FROM ")
				.append(this.ALUNO).append(" INNER JOIN ")
				.append(this.DADOS_PESSOAIS_ALUNO).append(" INNER JOIN ")
				.append(this.ENDERECO_ALUNO).append(" INNER JOIN ")
				.append(this.TELEFONE_ALUNO)
				.append(" ON matricula=?");// condicao
		
		try (PreparedStatement pstmt = connection.prepareStatement(slqQuery.toString())) {

			pstmt.setString(1, matricula);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				
				// Aluno
				aluno.setId(rs.getInt("idAluno"));
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setTurma(rs.getString("ano"));
				
				// Telefone
				Telefone telefone = new Telefone();
				telefone.setId(rs.getInt("idTelefone"));
				telefone.setOperadora(rs.getString("operadora"));
				telefone.setPrefixo(rs.getString("prefixo"));
				telefone.setNumero(rs.getString("numero"));
				
				// Endereco
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("idEndereco"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setComplemento(rs.getString("complemento"));
				
				// DadosPessoais
				DadosPessoais dados = new DadosPessoais();
				dados.setId(rs.getInt("IdDadosPessoais"));
				dados.setNome(rs.getString("nome"));
				dados.setDataNascimento(rs.getString("dataNascimento"));
				dados.setSexo(rs.getString("sexo"));
				dados.setRg(rs.getString("rg"));
				dados.setCpf(rs.getString("cpf"));
				dados.setEmail(rs.getString("email"));
				
				// setando
				dados.setEndereco(endereco);
				dados.setTelefone(telefone);
				
				// setando
				aluno.setDadosPessoais(dados);
				
			}
			rs.close();
		} catch (SQLException e) {
			throw new ErroConexaoException("Erro connection pesquisa1(matricula)");
		}
		return aluno;
	}

	// TODO: NOVO
	/** Pesquisa por matricula e Retorna o id ou 0 senao achar */
	public int pesquisaID(String matricula) throws ErroConexaoException {
		StringBuilder sqlQuery = new StringBuilder().append("SELECT idAluno FROM ").append(this.ALUNO)
				.append(" WHERE matricula=?");

		try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString())) {
			pstmt.setString(1, matricula);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("idAluno");
			}
			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect pesquisaID(matricula)");
		}

		return 0;
	}
	
	/**
	 * Retorna um Aluno Objeto pelo nomeCompleto
	 * 
	 * @throws AlunoInexistenteException 
	 * 
	 * */
	public Aluno pesquisaNome(String nomeCompleto) throws ErroConexaoException, NaoExisteException {
		Aluno a = new Aluno();
		int id = 0;
		
		// pesquisando id pelo nome completo
		StringBuilder sqlQueryID = new StringBuilder().append("SELECT id_aluno FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO).append(" WHERE nome=?");
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQueryID.toString())) {
			pstmt.setString(1, nomeCompleto);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				 id = rs.getInt("id_aluno");
				 //System.out.println(">>> " + id);
			}
			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect pesquisaNome(nomeCompleto)");
		}
		if(id==0) {
			throw new NaoExisteException("Aluno nao cadastrado no sistema");
		}
		
		// Pesquisando todos os dados jah que agora tem o id do aluno
		StringBuilder sqlQueryDados = new StringBuilder().append("SELECT * FROM ")
				.append(this.ALUNO).append(" INNER JOIN ")
				.append(this.DADOS_PESSOAIS_ALUNO).append(" INNER JOIN ")
				.append(this.TELEFONE_ALUNO).append(" INNER JOIN ")
				.append(this.ENDERECO_ALUNO).append(" ON ")
				
				.append(this.DADOS_PESSOAIS_ALUNO).append(".id_aluno=?").append(" AND ")
				.append(this.TELEFONE_ALUNO).append(".id_aluno=?").append(" AND ")
				.append(this.ENDERECO_ALUNO).append(".id_aluno=?").append(" AND ")
				.append(this.ALUNO).append(".idAluno=?");
		
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQueryDados.toString())) {
			pstmt.setInt(1, id); // Dados id_aluno
			pstmt.setInt(2, id); // Telefone id_aluno
			pstmt.setInt(3, id); // endereco id_aluno
			pstmt.setInt(4, id); // Aluno.idAluno
			ResultSet rs = pstmt.executeQuery();
			
			// Retornando o primeiro valor
			if (rs.next()) {
				
				// aluno
				a.setId(id);
				a.setMatricula(rs.getString("matricula"));
				a.setTurma(rs.getString("ano"));
				
				// Endereco
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("idEndereco"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setComplemento(rs.getString("complemento"));
				
				// Telefone
				Telefone tel = new Telefone();				
				tel.setId(rs.getInt("idTelefone"));
				tel.setOperadora(rs.getString("operadora"));
				tel.setPrefixo(rs.getString("prefixo"));
				tel.setNumero(rs.getString("numero"));
				
				// DadosPessoais
				DadosPessoais dados = new DadosPessoais();
				dados.setNome(rs.getString("nome"));
				dados.setDataNascimento(rs.getString("dataNascimento"));
				dados.setSexo(rs.getString("sexo"));
				dados.setRg(rs.getString("rg"));
				dados.setCpf(rs.getString("cpf"));
				dados.setEmail(rs.getString("email"));
				dados.setId(rs.getInt("IdDadosPessoais"));
				
				// setando informacoes
				dados.setEndereco(endereco);
				dados.setTelefone(tel);
				
				a.setDadosPessoais(dados);
				
			}
			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect pesquisaNome(nomeCompleto)");
		}
		return a;
	}
	
	/**
	 * @param primeiroNome eh o primeiro nome do aluno. Use upperCase ou LowerCase
	 * @throws ErroConexaoException Erro de conexao
	 * @return String Nome completo do aluno ou Uma String vazia caso nao ache
	 * 
	 * */
	public String pesquisaPeloPrimeiroNome(String primeiroNome) throws ErroConexaoException {
		StringBuilder sqlQuery = new StringBuilder().append("SELECT * FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO);
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString());
				ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				 String nome = rs.getString("nome");
				 if(nome.split(" ")[0].equals(primeiroNome));
				 return nome;
			}
			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect pesquisaPrimeiroNome(primeiroNome)");
		}
		return "";
	}
	
	/**
	 * @param segundoNome eh o segundo nome do aluno. Use upperCase ou LowerCase
	 * @throws ErroConexaoException Erro de conexao
	 * @return String Nome completo do aluno ou Uma String vazia caso nao ache
	 * 
	 * */
	public String pesquisaPeloSegundoNome(String segundoNome) throws ErroConexaoException {
		StringBuilder sqlQuery = new StringBuilder().append("SELECT * FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO);
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString());
				ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				 String nome = rs.getString("nome");
				 if(nome.split(" ")[1].equals(segundoNome));
				 return nome;
			}
			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect pesquisaPrimeiroNome(segundoNome)");
		}
		
		return "";
	}
	
	/**
	 * @param segundoNome eh o segundo nome do aluno. Use upperCase ou LowerCase
	 * @throws ErroConexaoException Erro de conexao
	 * @return String Nome completo do aluno ou Uma String vazia caso nao ache
	 * 
	 * */
	public String pesquisaPeloTerceiroNome(String terceiroNome) throws ErroConexaoException {
		StringBuilder sqlQuery = new StringBuilder().append("SELECT * FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO);
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString());
				ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				 String nome = rs.getString("nome");
				 if(nome.split(" ")[2].equals(terceiroNome));
				 return nome;
			}
			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect pesquisaPrimeiroNome(terceiroNome)");
		}
		
		return "";
	}
	
	// TODO: Ainda tirar os bugs, mas ta chegando lah
	/**
	 * @param letra eh um char que seria as letras que o usuario esta digitando
	 * <br>ex: <e>her</e> <- 'r' � a letra que o usu�rio acabou de digitar </br>
	 * 
	 * 
	 * 
	 * @param nomeDigitando eh o nome que esta sendo formado. Tem que ser o nome da maneira que o usuario esta digitando (tratar com upper ou lower)
	 * @return String o nome completado, ou, senao achar, String vazia
	 * @throws ErroConexaoException 
	 */
	public String autoCompleta(char letra, String nomeDigitando) throws ErroConexaoException {
		StringBuilder sqlQuery = new StringBuilder().append("SELECT * FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO);
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString());
				ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				 String nome = rs.getString("nome");
				 char nomechar[] = nome.toCharArray();
				 
				 // indice eh o incide de algum espaco em branco
				 int indice = 0;
				 // for para procurar o espaco em branco. 
				 for(int i=0;i<nomeDigitando.length();i++) {
					 if(nomeDigitando.toCharArray()[i] == ' ') {
						 indice = i;
					 }
				}
				 
				 // for para ir pesquisando nos indices a letra
				 while(indice<nome.length()) {
					 
					 // tem que ser diferente de vazio, para nao mostrar todos os nomes que tem espaco 
					 if(nomechar[indice] != ' '
							 && nomechar[indice] == letra) {
						 
						 // quando ele achar a letra, vai retornar uma string com as letras a frente da letra pesquisada
						 return nome.substring(indice+1, nome.length());
						 //return "---"; 
					 }
					 indice++;
				 }
				 
				 return nome;
			}
			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect autoCompleta(char letra, String nomeDigitando)");
		}
		return "";
	}
	
	public int pesquisaIDNome(String nomeCompleto) throws ErroConexaoException{
		// pesquisando id pelo nome completo
		StringBuilder sqlQueryID = new StringBuilder().append("SELECT id_aluno FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO).append(" WHERE nome=?");
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQueryID.toString())) {
			pstmt.setString(1, nomeCompleto);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.getString("nome").equals(nomeCompleto)) {
				
				return rs.getInt("id_aluno");
			}

			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect pesquisaIDNome(nomeCompleto)");
		}
		
		return 0;
	}
	
	
	/**
	 * Retorna uma matriz com os aluonos que tenham o nome passado como par�metro
	 * <br><b>obs: O nome pode ser de diversas formas <b></br>
	 * 
	 * @param String Nome do aluno
	 * @return String[][] nome, operador prefixo numero, email
	 * @throws ErroConexaoException 
	 * 
	 * 
	 * */
	public String[][] pesquisaNomes(String nome) throws ErroConexaoException{
		String sqlQueryID= new StringBuilder().append("SELECT id_aluno, nome FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO).append(" WHERE nome=?").toString();
		
		// � um pouco bruto usar um array com 1000 'caixinhas'
		int[] ids = new int[1000];
		int i=0;
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQueryID)) {
			pstmt.setString(1, nome);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				// pegando id's dos nomes iguais ignorando maiusculas e minusculas
				if(rs.getString("nome").equalsIgnoreCase((nome))) {
					ids[i] = rs.getInt("id_aluno");
					i++;
				}
			}
			rs.close();
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect metodo pesquisaNomes(nome) bloco1");
		}
		
		// matriz com os dados dos alunos com quantidade de linhas de acordo com as vezes que achou os id's
		String[][] dados = new String[i][3];
		
		// inner join com Dados pessoais e telefone do aluno
		StringBuilder sqlQuery = new StringBuilder().append(" SELECT ")
				.append(this.DADOS_PESSOAIS_ALUNO).append(".nome,")
				.append(this.TELEFONE_ALUNO).append(".operadora,")
				.append(this.TELEFONE_ALUNO).append(".prefixo,")
				.append(this.TELEFONE_ALUNO).append(".numero")
				.append(" FROM ").append(this.DADOS_PESSOAIS_ALUNO).append(" INNER JOIN ").append(this.TELEFONE_ALUNO)
				.append(" ON ").append(this.DADOS_PESSOAIS_ALUNO).append(".id_aluno=?").append(" AND ")
				.append(this.TELEFONE_ALUNO).append(".id_aluno=?");
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString())) {

			// for com a quantidade de id's encontrados
			for (int j = 0; j < i; j++) {
				pstmt.setInt(1, ids[j]);
				pstmt.setInt(2, ids[j]);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					dados[j][0] = rs.getString("nome");
					// operadora prefixo numero
					dados[j][1] = rs.getString("operadora") + " " + rs.getString("prefixo") + " "
							+ rs.getString("numero");
					dados[j][2] = rs.getString("email");
				}
				rs.close();
			}

		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect metodo pesquisaNomes(nome) bloco2");
		}

		return dados;
	}
	
	/**
	 * Retorna uma matriz com os alunos que tenham o mesmo primeiro nome passado como par�metro
	 * <br><b>obs: O nome pode ser de diversas formas <b></br>
	 * 
	 * @param String Primeiro nome do aluno EX: Hercules
	 * @return String[][] nome, operador prefixo numero, email
	 * @throws ErroConexaoException 
	 * 
	 * */
	public String[][] pesquisaPrimeiroNomes(String nome) throws ErroConexaoException{
		String sqlQueryID= new StringBuilder().append("SELECT id_aluno,nome FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO).toString();
		// � um pouco bruto usar um array com 1000 'caixinhas'
		int[] ids = new int[1000];
		int i=0;
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQueryID);
				ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				//System.out.println(rs.getString("nome").split(" ")[0].equalsIgnoreCase(nome) + " " + rs.getInt("id_aluno"));
				// pegando id's dos nomes iguais ignorando maiusculas e minusculas
				if(rs.getString("nome").split(" ")[0].equalsIgnoreCase(nome)) {
					ids[i] = rs.getInt("id_aluno");
					i++;
				}
			}
			
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect metodo pesquisaNomes(nome) bloco1");
		}
		
		// matriz com os dados dos alunos com quantidade de linhas de acordo com as vezes que achou os id's
		String[][] dados = new String[i][3];
		
		for (int j = 0; j < i; j++) {
			
			// inner join com Dados pessoais e telefone do aluno
			StringBuilder sqlQuery = new StringBuilder().append("SELECT ")
					.append(this.DADOS_PESSOAIS_ALUNO).append(".nome,")
					.append(this.DADOS_PESSOAIS_ALUNO).append(".email,")
					.append(this.TELEFONE_ALUNO).append(".operadora,")
					.append(this.TELEFONE_ALUNO).append(".prefixo,")
					.append(this.TELEFONE_ALUNO).append(".numero")
					.append(" FROM ").append(this.DADOS_PESSOAIS_ALUNO).append(" INNER JOIN ").append(this.TELEFONE_ALUNO)
					.append(" WHERE ").append(this.DADOS_PESSOAIS_ALUNO).append(".id_aluno=?")
					.append(" AND ").append(this.TELEFONE_ALUNO).append(".id_aluno=?");
			
			try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString())) {
				pstmt.setInt(1, ids[j]);
				pstmt.setInt(2, ids[j]);
				
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					dados[j][0] = rs.getString("nome");
					dados[j][1] = rs.getString("operadora") + " " + rs.getString("prefixo") + " "
							+ rs.getString("numero");
					dados[j][2] = rs.getString("email");
					
				}
				rs.close();
			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro connect metodo pesquisaNomes(nome) bloco2");
			}
		}
		return dados;
	}
	
	/**
	 *@param String nome - pode ser uma frase inteira que ele ir� pegar apenas a primeira letra
	 * 
	 * 
	 * 
	 * */
	public String[][] pesquisaNomesStartsWith(String nome) throws ErroConexaoException {
		String sqlQueryID= new StringBuilder().append("SELECT id_aluno,nome FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO).toString();
		
		// � um pouco bruto usar um array com 1000 'caixinhas'
		int[] ids = new int[1000];
		int i=0;
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQueryID);
				ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				
				// pegando id's dos nomes iguais ignorando maiusculas e minusculas
				if(rs.getString("nome").split(" ")[0].equalsIgnoreCase(nome)) {
					ids[i] = rs.getInt("id_aluno");
					i++;
				}
			}
			
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect metodo pesquisaNomes(nome) bloco1");
		}
		
		// matriz com os dados dos alunos com quantidade de linhas de acordo com as vezes que achou os id's
		String[][] dados = new String[i][3];
		
		for (int j = 0; j < i; j++) {
			
			// inner join com Dados pessoais e telefone do aluno
			StringBuilder sqlQuery = new StringBuilder().append("SELECT ")
					.append(this.DADOS_PESSOAIS_ALUNO).append(".nome,")
					.append(this.DADOS_PESSOAIS_ALUNO).append(".email,")
					.append(this.TELEFONE_ALUNO).append(".operadora,")
					.append(this.TELEFONE_ALUNO).append(".prefixo,")
					.append(this.TELEFONE_ALUNO).append(".numero")
					.append(" FROM ").append(this.DADOS_PESSOAIS_ALUNO).append(" INNER JOIN ").append(this.TELEFONE_ALUNO)
					.append(" WHERE ").append(this.DADOS_PESSOAIS_ALUNO).append(".id_aluno=?")
					.append(" AND ").append(this.TELEFONE_ALUNO).append(".id_aluno=?");
			
			try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery.toString())) {
				pstmt.setInt(1, ids[j]);
				pstmt.setInt(2, ids[j]);
				
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					dados[j][0] = rs.getString("nome");
					dados[j][1] = rs.getString("operadora") + " " + rs.getString("prefixo") + " "
							+ rs.getString("numero");
					dados[j][2] = rs.getString("email");
					
				}
				rs.close();
			} catch (SQLException ex) {
				throw new ErroConexaoException("Erro connect metodo pesquisaNomes(nome) bloco2");
			}
		}
		return dados;
	}
	
	
	/**
	 * @throws ErroConexaoException 
	 * @throws NaoExisteException 
	 * 
	 * 
	 * */
	public Aluno[] nomesStartWith(String nome) throws ErroConexaoException, NaoExisteException{
		String sqlQueryID= new StringBuilder().append("SELECT id_aluno,nome,cpf FROM ")
				.append(this.DADOS_PESSOAIS_ALUNO).toString();
		
		// � um pouco bruto usar um array com 1000 'caixinhas'
		//int[] ids = new int[1000];
		int i=0;
		
		String cpfs[] = new String[1000];
		
		try (PreparedStatement pstmt = connection.prepareStatement(sqlQueryID);
				ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				// pegando id's dos nomes iguais ignorando maiusculas e minusculas
				if(!nome.equals("") && rs.getString("nome").toUpperCase().startsWith(nome.toUpperCase())) {
					//ids[i] = rs.getInt("id_aluno");
					cpfs[i] = rs.getString("cpf");
					i++;
				}
			}
			rs.close();
			
		} catch (SQLException ex) {
			throw new ErroConexaoException("Erro connect metodo pesquisaNomes(nome)");
		}
		
		// matriz com os dados dos alunos com quantidade de linhas de acordo com as
		// vezes que achou os id's
		Aluno[] dados = new Aluno[i];
		for(int j=0;j<i;j++) {
			dados[j] = pesquisa(cpfs[j]);
		}
		
		return dados;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // fim classe