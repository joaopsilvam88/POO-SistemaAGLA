package br.com.agla.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Sistema.Aluno;
import Sistema.Pessoa;
import Sistema.Professor;
import Sistema.Sistema;
import GUI.JanelaVerificacao;
import exception.NaoExisteException;
import exception.TurmaInexistenteExeption;
import Imagens.*;

public class JanelaPesquisa extends JFrame implements MouseListener, ListSelectionListener{
	
	JList lista;
	Pessoa[] pessoas;
	Pessoa pessoa;
	ImageIcon imagem = new ImageIcon(getClass().getResource("/Imagens/JanelaPesquisa1.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();
	JTextField caixa1 = new JTextField("Nome do indivíduo");
	JButton botao1 = new JButton();
	boolean apagar = true;
	Sistema sistema;
	private String[] nomeCpf = {"A", "B", "S"};
	JScrollPane barra;
	
	public JanelaPesquisa(Sistema sistema) {
		this.sistema = sistema;
		painel.setLayout(null);
		lista = new JList(nomeCpf);
		lista.setVisibleRowCount(5);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		
		barra = new JScrollPane(lista);
		barra.setBounds(79, 60, 255, 26);
		painel.add(barra);
		barra.setVisible(false);
		
		getRootPane().setDefaultButton(botao1);
		botao1.setContentAreaFilled(false);
		caixa1.addMouseListener(this);

		caixa1.setBounds(82, 130, 255, 26);
		botao1.setBounds(337, 128, 33, 30);

		rotulo.add(caixa1);
		rotulo.add(botao1);
		
		rotulo.setBounds(-3, -95, 1030, 768);

		painel.add(rotulo);
		getContentPane().add(painel);
		setSize(442, 126);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void verificarAluno() {
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//System.out.println(Arrays.toString(sistema.verificarAluno(caixa1.getText())));
					pessoas = sistema.verificarAluno(caixa1.getText());
					barra.setVisible(true);
					barra.setColumnHeaderView(lista);
					itensLista();
					String[] nomeCpf = {"X", "y", "q"};
					new JanelaVerificacao(sistema, (Aluno) retornaPessoa());
				} catch(NaoExisteException e1) {
					new JanelaAvisos().naoExiste();
				}
			}
				
		});
	}
	
	public void verificarProfessor() {
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				//	pessoas = sistema.verificarProfessor(caixa1.getText());
					
					itensLista();
					lista = new JList<String>(nomeCpf);
					new JanelaVerificacao(sistema, (Professor) retornaPessoa());
//				} catch(NaoExisteException e1) {
//					new JanelaAvisos().naoExiste();
//				}
			}
				
		});
	}
	
	public void verificarTurma() {
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Tabelas(sistema.verificarTurma(caixa1.getText()));
				} catch(TurmaInexistenteExeption e1) {
					new JanelaAvisos().naoExiste();
				}
			}
				
		});
	}
	public void itensLista() {
		nomeCpf = new String[pessoas.length];
		for(int k = 0; k < pessoas.length; k++) {
			nomeCpf[k] = pessoas[k].getDadosPessoais().nomeCpf();
		}
	}
	
	public Pessoa retornaPessoa() {
		return pessoa;
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mousePressed(MouseEvent e) {
		if(apagar) {
			caixa1.setText("");
			apagar = false;
		}
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	public static void main(String[] args) {
		new JanelaPesquisa(new Sistema());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		for(int k = 0; k < lista.getModel().getSize(); k++) {
			if(lista.getSelectedIndex() == k) {
				this.pessoa = pessoas[k];
			}
		}
	}

}


	
