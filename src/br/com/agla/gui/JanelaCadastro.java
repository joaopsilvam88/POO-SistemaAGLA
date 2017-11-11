package br.com.agla.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.com.agla.classes.*;
import br.com.agla.exceptions.JaExisteException;
import br.com.agla.imagens.*;

public class JanelaCadastro extends JFrame implements MouseListener{

	JPanel painel = new JPanel();
	JLabel rotulo = new JLabel();
	JTextField[] caixas = new JTextField[14];
	JFormattedTextField caixaCpf = new 	JFormattedTextField();
	MaskFormatter mascara;
	JButton botao1 = new JButton();
	JButton botao2 = new JButton();
	JButton botao3 = new JButton();
	JButton botao4 = new JButton();
	JRadioButton rbotao1 = new JRadioButton();
	JRadioButton rbotao2 = new JRadioButton();
	ButtonGroup grupo = new ButtonGroup();
	boolean cond1 = false;
	boolean cond2 = false;
	Sistema sistema;
	Pessoa pessoa;
	boolean[] apagar = new boolean[14];
	
	public JanelaCadastro(Sistema sistema) {
		this.sistema = sistema;
		new JFrame();
		painel.setLayout(null);

		for(int k = 0; k < 14; k++) {
			caixas[k] = new JTextField();
			rotulo.add(caixas[k]);
			caixas[k].addMouseListener(this);
			apagar[k] = true;

		}
		try {
			mascara = new MaskFormatter("###.###.###-##");
			mascara.setPlaceholderCharacter('_');
			caixaCpf.setFormatterFactory(new DefaultFormatterFactory(mascara));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		caixas[0].setText("Nome");
		caixas[1].setText("Matrícula");
		caixas[2].setText("E-mail");
		caixas[3].setText("CPF");
		caixas[4].setText("RG");
		caixas[5].setText("D. Nascimento");
		caixas[6].setText("Telefone");
		caixas[7].setText("Estado");
		caixas[8].setText("Cidade");
		caixas[9].setText("Bairro");
		caixas[10].setText("Logradouro");
		caixas[11].setText("Turma");
		caixas[12].setText("Complemento");
		caixas[13].setText("Currículo Lattes");
		
		caixas[0].setBounds(37, 46, 250, 24);
		caixas[1].setBounds(309, 46, 161, 24);
		caixas[2].setBounds(37, 90, 250, 24);
		caixas[3].setBounds(309, 90, 161, 24);
		caixaCpf.setBounds(309, 90, 161, 24);
		caixas[4].setBounds(37, 133, 110, 24);
		caixas[5].setBounds(170, 133, 116, 24);
		caixas[6].setBounds(309, 134, 161, 24);
		caixas[7].setBounds(170, 176, 116, 24);
		caixas[8].setBounds(309, 176, 161, 24);
		caixas[9].setBounds(170, 220, 116, 24);
		caixas[10].setBounds(309, 220, 161, 24);
		caixas[11].setBounds(37, 264, 110, 24);
		caixas[12].setBounds(170, 264, 300, 24);
		caixas[13].setBounds(170, 308, 300, 24);
				
		caixaCpf.setVisible(false);
		
		botao1.setBounds(211, 363, 87, 22);
		
		rbotao1.setBounds(47, 216, 17, 20);
		rbotao2.setBounds(94, 216, 17, 20);
		
		botao1.setContentAreaFilled(false);

		rotulo.add(caixaCpf);
		rotulo.add(botao1);

		rotulo.add(rbotao1);
		rotulo.add(rbotao2);

		rbotao1.setSelected(true);
		grupo.add(rbotao1);
		grupo.add(rbotao2);
		
		rotulo.setBounds(0, 0, 1025, 579);
		painel.add(rotulo);
		add(painel);
		
		setTitle("Cadastrar");
		setSize(515, 450);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void pegarDados() {
		pessoa.getDadosPessoais().setNome(caixas[0].getText());
		pessoa.setMatricula(caixas[1].getText());
		pessoa.getDadosPessoais().setEmail(caixas[2].getText());
		pessoa.getDadosPessoais().setCpf(caixaCpf.getText());
		pessoa.getDadosPessoais().setRg(caixas[4].getText());
		pessoa.getDadosPessoais().setDataNascimento(caixas[5].getText());
		if(rbotao1.isSelected()) pessoa.getDadosPessoais().setSexo("M");
		else if(rbotao2.isSelected()) pessoa.getDadosPessoais().setSexo("F");
		if(caixas[6].getText().length() < 4) caixas[6].setText("     ");
		pessoa.getDadosPessoais().getTelefone().setPrefixo(caixas[6].getText().substring(1, 3));
		pessoa.getDadosPessoais().getTelefone().setNumero(caixas[6].getText().substring(4));
		pessoa.getDadosPessoais().getEndereco().setEstado(caixas[7].getText());
		pessoa.getDadosPessoais().getEndereco().setCidade(caixas[8].getText());
		pessoa.getDadosPessoais().getEndereco().setBairro(caixas[9].getText());
		pessoa.getDadosPessoais().getEndereco().setLogradouro(caixas[10].getText());
		pessoa.setTurma(caixas[11].getText());
		pessoa.getDadosPessoais().getEndereco().setComplemento(caixas[12].getText());
	}
	
	public void cadastraProfessor() {
		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/JanelaCadastroProfessor.png")));
		setVisible(true);
		this.pessoa = new Professor();
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pegarDados();
				/**Pegar o lattes
				 * 
				 * 
				 * 
				 */
				try {
					sistema.addProfessor(pessoa.getDadosPessoais().getCpf(), (Professor) pessoa);
					new JanelaAvisos().confirmarCadastro();
					setVisible(false);
				} catch (JaExisteException e1) {
					new JanelaAvisos().erroCadastro();
				}
			}
		});
	}	

	public void cadastraAluno() {
		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/JanelaCadastroAluno.png")));
		caixas[13].setVisible(false);
		setVisible(true);
		this.pessoa = new Aluno();
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pegarDados();
				try {
					sistema.addAluno(pessoa.getDadosPessoais().getCpf(), (Aluno) pessoa);
					new JanelaAvisos().confirmarCadastro();
					setVisible(false);
				} catch (JaExisteException e1) {
					new JanelaAvisos().erroCadastro();
				}
			}
		});
	}
	
	public void mouseAdapter() {
		
	}
	public static void main(String[] args) {
		new JanelaCadastro(new Sistema()).cadastraProfessor();
	}

	public void mousePressed(MouseEvent e) {
		if(e.getSource() == caixas[3] && apagar[3] == true) {
			rotulo.remove(caixas[3]);
			caixaCpf.setVisible(true);
		}
		for(int k = 0; k < 14; k++) {
			if(e.getSource() == caixas[k] && apagar[k] == true) {
				caixas[k].setText("");
				apagar[k] = false;
			}
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
