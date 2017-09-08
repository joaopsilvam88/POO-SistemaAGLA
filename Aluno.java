package object;

import javax.swing.ImageIcon;
import javax.swing.JTable;

public class Aluno {
	
	private DadosPessoais dadosPessoais;
	private JTable historico;
	private ImageIcon declaracaoVinculo;
	
	public Aluno(DadosPessoais dadosPessoais, String nome, JTable historico, ImageIcon declaracaoVinculo) {
		this.dadosPessoais = dadosPessoais;
		this.historico = historico;
		this.declaracaoVinculo = declaracaoVinculo;
	}
	
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}
	
	public JTable getHistorico() {
		return historico;
	}
	
	public void setHistorico(JTable historico) {
		this.historico = historico;
	}
	
	public ImageIcon getDeclaracaoVinculo() {
		return declaracaoVinculo;
	}
	
	public void setDeclaracaoVinculo(ImageIcon declaracaoVinculo) {
		this.declaracaoVinculo = declaracaoVinculo;
	}

	
}
