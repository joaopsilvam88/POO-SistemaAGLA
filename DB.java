package gravador;

import java.io.IOException;
import java.util.List;

import object.DadosPessoais;

/**
 * @author Herco
 * 
 * */
public interface DB {
	
	
	/** 
	 * @param dadosPessoais - Uma linta de dadosPessoais
	 * @throws IOException - Se o arquivo n�o for encontrado
	 * */
	public void gravaDadosPessoais(List<DadosPessoais> dadosPessoais) throws IOException;
	
	/** 
	 * @param cpf - Uma String que � o cpf a pesquisar
	 * @throws IOException - Se o arquivo n�o for encontrado
	 * @return DadosPessoais - Retorna <b>null<br> se n�o encontrar
	 * */
	public DadosPessoais pesquisaUsuario(String cpf) throws IOException;
	
	/**
	 * @throws IOException - Se n�o encontrar o arquivo db
	 * @return getDadosPessoais - Uma matriz de String com os dados pessoais
	 * 
	 * */
	public String[][] getDadospessoais() throws IOException;
	
}
