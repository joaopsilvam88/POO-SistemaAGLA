package br.com.agla.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.agla.classes.*;
import br.com.agla.gui.JanelaVerificacao;
import br.com.agla.exceptions.ErroConexaoException;
import br.com.agla.exceptions.NaoExisteException;
import br.com.agla.exceptions.TurmaInexistenteExeption;
import br.com.agla.imagens.*;

public class JanelaPesquisa2 extends JFrame implements MouseListener, ActionListener{
	
	JComboBox<String> lista = new JComboBox<String>();
	Pessoa[] pessoas;
	Pessoa pessoa;
	ImageIcon imagem = new ImageIcon(getClass().getResource("/Imagens/JanelaPesquisa1.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();
	JTextField caixa1 = new JTextField("Nome do indivíduo");
	JButton botao1 = new JButton();
	boolean apagar = true;
	Sistema sistema;
	JScrollPane barra;
	private String[] nomeCpf;
	private boolean cond;
	
	public JanelaPesquisa2(Sistema sistema) {
		this.sistema = sistema;
		painel.setLayout(null);
		
		rotulo.add(lista);
		lista.addActionListener(this);
		lista.setVisible(false);
		lista.setBounds(82, 155, 255, 20);

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
					lista.removeAllItems();
					pessoas = sistema.verificarAluno(caixa1.getText());
					itensLista();
					for(String s: nomeCpf) {
						lista.addItem(s);
					}
					if(!caixa1.getText().equals("")) lista.setVisible(true);
					if(Arrays.toString(pessoas).equals("[]")) {
						new JanelaAvisos().naoExiste();
						lista.setVisible(false);
					}
					cond = true;
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
					lista.removeAllItems();
					pessoas = sistema.verificarProfessor(caixa1.getText());
					itensLista();
					for(String s: nomeCpf) {
						lista.addItem(s);
					}
					if(!caixa1.getText().equals("")) lista.setVisible(true);
					if(Arrays.toString(pessoas).equals("[]")) {
						new JanelaAvisos().naoExiste();
						lista.setVisible(false);
					}
					cond = false;
				} catch(NaoExisteException e1) {
					new JanelaAvisos().naoExiste();
				}
			}
		});
	}
	
	public void verificarTurma() {
		caixa1.setText("Nome da turma");
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
	public void verificarDisciplina() {
		caixa1.setText("Nome da disciplina");
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Tabelas(sistema.professoresDisciplina(caixa1.getText()));
				} catch(NaoExisteException e1) {
					new JanelaAvisos().naoExiste();
				} catch (ErroConexaoException e1) {
					e1.printStackTrace();
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

	public void actionPerformed(ActionEvent e) {
		if(lista.isPopupVisible()) {
			for(int k = 0; k < lista.getModel().getSize(); k++) {
				if(lista.getSelectedIndex() == k) {
					this.pessoa = pessoas[k];
					if(cond) new JanelaVerificacao(sistema, pessoa).verificaAluno();
					else if(cond == false) new JanelaVerificacao(sistema, pessoa).verificaProfessor();
					setVisible(false);
				}
			}	
		}
	}
	
	public static void main(String[] args) {
		new JanelaPesquisa2(new Sistema()).verificarAluno();
	}
}


	
