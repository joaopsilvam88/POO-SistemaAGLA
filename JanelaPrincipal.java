package object;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.poo.sisagla.exceptions.JaExisteException;
import exception.*;

public class JanelaPrincipal extends JFrame implements ActionListener{

	ImageIcon imagem = new ImageIcon(getClass().getResource("Principal.png"));
	Sistema sistema;
	BuscarProfessor buscar;
	BuscarAluno buscar2;
	JPanel painel = new JPanel();
	JLabel rotulo = new JLabel(imagem);
	JComboBox<String> caixa = new JComboBox<String>();
	JComboBox<String> caixa2 = new JComboBox<String>();
	JComboBox<String> caixa3 = new JComboBox<String>();
	JButton botao = new JButton("Professores");
	JButton botao2 = new JButton("Alunos");
	JButton botao3 = new JButton("Sistema");
	BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
	
	public JanelaPrincipal(Sistema sistema){

		this.sistema = sistema;
		
		new JFrame();
		painel.setLayout(null);
		
		UIResource.setHorizontalAlignment(SwingConstants.CENTER);

		rotulo.add(botao);
		rotulo.add(botao2);
		rotulo.add(botao3);
		
		rotulo.add(caixa);
		rotulo.add(caixa2);
		rotulo.add(caixa3);
		
		caixa.setRenderer(UIResource);
		caixa2.setRenderer(UIResource);
		caixa3.setRenderer(UIResource);
		
		caixa.addItem("              ");
		caixa2.addItem("              ");
		caixa3.addItem("              ");
		
		caixa.addItem("Lista de professores da escola");
		caixa.addItem("Verificar os dados de algum professor");
		caixa.addItem("Verificar professor de alguma disciplina");
		caixa.addItem("Alterar dados de algum professor");
		caixa.addItem("Cadastrar um professor no sistema");
		caixa.addItem("Remover um professor do sistema");
		
		caixa2.addItem("Verificar alguma turma");
		caixa2.addItem("Verificar os dados de algum aluno");
		caixa2.addItem("Alterar dados de algum aluno");
		caixa2.addItem("Cadastrar um aluno no sistema");
		caixa2.addItem("Remover um aluno do sistema");

		caixa3.addItem("Sair");


		botao.setBounds(0, 0, 320, 37);
		botao2.setBounds(320, 0, 320, 37);
		botao3.setBounds(640, 0, 320, 37);
		
		caixa.addActionListener(this);
		caixa2.addActionListener(this);
		caixa3.addActionListener(this);

		caixa.setForeground(getForeground().WHITE);
		caixa2.setForeground(getForeground().WHITE);
		caixa3.setForeground(getForeground().WHITE);

		botao.setForeground(getForeground().WHITE);
		botao2.setForeground(getForeground().WHITE);
		botao3.setForeground(getForeground().WHITE);

		caixa.setBounds(0, 0, 320, 37);
		caixa2.setBounds(320, 0, 320, 37);
		caixa3.setBounds(640, 0, 320, 37);

		botao.addActionListener(this);
		botao2.addActionListener(this);
		botao3.addActionListener(this);

		botao.setBackground(new Color(180, 20, 20));
		botao2.setBackground(new Color(180, 20, 20));
		botao3.setBackground(new Color(180, 20, 20));

		caixa.setBackground(new Color(180, 20, 20));
		caixa2.setBackground(new Color(180, 20, 20));
		caixa3.setBackground(new Color(180, 20, 20));

		rotulo.setBounds(0, 0, 1040, 570);
		painel.add(rotulo);
		add(painel);
		setSize(966, 560);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	

	public static void main(String[] args){
		new JanelaPrincipal(new Sistema());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(caixa.isPopupVisible()){
			buscar = new BuscarProfessor(sistema);
			if(caixa.getSelectedIndex() == 1){
				caixa.setSelectedIndex(0);
				new Tabelas(sistema.getListaProf());			
			}else if(caixa.getSelectedIndex() == 2){
				buscar.contraEditor();
				buscar.setVisible(true);
			}else if(caixa.getSelectedIndex() == 3){
				new BuscarProfDisciplina(sistema);
			}else if(caixa.getSelectedIndex() == 4){
				buscar.editor();
				buscar.setVisible(true);
			}else if(caixa.getSelectedIndex() == 5){
				new CadastraProfessor(sistema);
			}else if(caixa.getSelectedIndex() == 6){
				buscar.contraEditor();
				buscar.setVisible(true);
				buscar.addButton();
			}
		}else if(caixa2.isPopupVisible()){
			buscar2 = new BuscarAluno(sistema);
			if(caixa2.getSelectedIndex() == 1){
				new VerificarTurma(sistema);
			}else if(caixa2.getSelectedIndex() == 2){
				buscar2.contraEditor();
				buscar2.setVisible(true);
			}else if(caixa2.getSelectedIndex() == 3){
				buscar2.editor();
				buscar2.setVisible(true);
			}else if(caixa2.getSelectedIndex() == 4){
				new CadastraAluno(sistema);
			}else if(caixa2.getSelectedIndex() == 5){
				buscar2.contraEditor();
				buscar2.setVisible(true);
				buscar2.addButton();
			}

		}else if(caixa3.isPopupVisible()){
			if(caixa3.getSelectedIndex() == 1){
				System.exit(0);
			}
		}
		
		if(e.getSource() == botao){
			caixa.showPopup();
		}else if(e.getSource() == botao2){
			caixa2.showPopup();
		}else if(e.getSource() == botao3){
			caixa3.showPopup();
		}
	}
}
