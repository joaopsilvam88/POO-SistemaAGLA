package object;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tabelas{

	String[] cabecalho = {"Nº", "Nome", "Telefone", "E-mail"};
	Object[][] dados;
	JTable tabela;
	JScrollPane barra;

	public Tabelas(Object[][] dados){
		this.dados = dados;
		tabela = new JTable(this.dados, cabecalho);
		exibirTabela();
	}
	
	public void exibirTabela(){
		JFrame janela = new JFrame();
		barra = new JScrollPane(tabela);
		tabela.getColumnModel().getColumn(0).setMaxWidth(25);
		janela.add(barra);
		janela.setResizable(false);
		janela.setSize(600, 500);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}

}
