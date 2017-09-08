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
	 * @throws IOException - Se o arquivo não for encontrado
	 * */
	public void gravaDadosPessoais(List<DadosPessoais> dadosPessoais) throws IOException;
	
	/** 
	 * @param cpf - Uma String que é o cpf a pesquisar
	 * @throws IOException - Se o arquivo não for encontrado
	 * @return DadosPessoais - Retorna <b>null<br> se não encontrar
	 * */
	public DadosPessoais pesquisaUsuario(String cpf) throws IOException;
	
	/**
	 * @throws IOException - Se não encontrar o arquivo db
	 * @return getDadosPessoais - Uma matriz de String com os dados pessoais
	 * 
	 * */
	public String[][] getDadospessoais() throws IOException;
	
}
