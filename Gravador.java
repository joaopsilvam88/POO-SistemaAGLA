package gravador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import object.DadosPessoais;
import object.Endereco;
import object.Telefone;

public class Gravador implements DB{
	private final String PATH_DADOS_PESSOAIS = "bd/dadospessoais.txt";
	
	@Override
	public void gravaDadosPessoais(List<DadosPessoais> dadosPessoais) throws IOException {
		File arquivo = new File("bd/dadospessoais.txt");
		if (!arquivo.exists()) {
			throw new IOException(
					"Arquivo Inexistente\nVocê pode criar uma pasta nesse mesmo diretório, chamado 'bd' e criar um arquivo 'dadospessoais.txt\nOu contactar o criador do método'");
		}
		
		List<String> texto = new ArrayList<String>();

		try(BufferedReader leitor = new BufferedReader(new FileReader(PATH_DADOS_PESSOAIS))){
			while(leitor.ready()) {
				texto.add(leitor.readLine());
			}
		} // end try-with-resources
		
		try(BufferedWriter escritor = new BufferedWriter(new FileWriter(PATH_DADOS_PESSOAIS))){
			for (String linha : texto) {
				escritor.write(linha);
				escritor.newLine();
			}
			
			for (DadosPessoais d: dadosPessoais) {
				escritor.write(d.toString());			
				escritor.newLine();
			}
		} // end try-with-resources
		
	}
	
	
	@Override
	public DadosPessoais pesquisaUsuario(String cpf) throws IOException {
		DadosPessoais dados = null;
		
		File arquivo = new File("bd/dadospessoais.txt");
		if (!arquivo.exists()) throw new IOException("Arquivo Inexistente\nVocê pode criar uma pasta nesse mesmo diretório, chamado 'bd' e criar um arquivo 'dadospessoais.txt\n"
				+ "Ou contactar o criador do método'");
		
		try(BufferedReader leitor = new BufferedReader(new FileReader(PATH_DADOS_PESSOAIS))){
			
			while(leitor.ready()) {
				String linha[] = leitor.readLine().split(",");
				
				if(linha[4].equals(cpf)) {
					System.out.println(linha[4]+" --- " + cpf);
					dados = new DadosPessoais();
					dados.setNome(linha[0]);
					dados.setDataNascimento(linha[1]);
					dados.setSexo(linha[2]);
					dados.setRg(linha[3]);
					dados.setCpf(linha[4]);
					dados.setEmail(linha[5]);
					dados.setTelefone(new Telefone(linha[6], linha[7], linha[8]));
					dados.setEndereco(new Endereco(linha[9], linha[10], linha[11],linha[12], Integer.parseInt(linha[13]), linha[14]));
					return dados;
				}
			}
		} // end try-with-resources
		
		return null;
	}
	
	
	@Override
	public String[][] getDadospessoais() throws IOException{
		File arquivo = new File("bd/dadospessoais.txt");
		if (!arquivo.exists()) throw new IOException("Arquivo Inexistente\nVocê pode criar uma pasta nesse mesmo diretório, chamado 'bd' e criar um arquivo 'dadospessoais.txt\n"
				+ "Ou contactar o criador do método'");
		
		String[][] linhas = new String[20][15];
		try(BufferedReader leitor = new BufferedReader(new FileReader(PATH_DADOS_PESSOAIS))){
			int i = 0;
			while(leitor.ready()) {
				String[] varLinha = leitor.readLine().split(",");
				if (i!=0) {
					for(int a=0;a<15;a++) {
						linhas[i-1][a] = varLinha[0];
					}// for
				}// if
				i++;
			} // while
		} // try
		return linhas;
	}
	
	
} // end class