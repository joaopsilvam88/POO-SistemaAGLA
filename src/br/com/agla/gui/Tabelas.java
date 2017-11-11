package br.com.agla.gui;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.agla.classes.Aluno;

public class Tabelas extends JFrame{

	String[] cabecalho = {"Nº", "Nome", "CPF", "E-mail"};
	Object[][] dados;
	JTable tabela;
	JScrollPane barra;

	public Tabelas(Object[][] dados){
		this.dados = dados;
		tabela = new JTable(this.dados, cabecalho);
		barra = new JScrollPane(tabela);
		tabela.getColumnModel().getColumn(0).setMaxWidth(25);
		add(barra);
		setResizable(false);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setVisible(true);	
	}

	
	public static void main(String[] args){
		new Tabelas(new Object[3][4]);
	}

}