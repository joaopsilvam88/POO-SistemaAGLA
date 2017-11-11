package br.com.agla.gui;

import br.com.agla.imagens.*;
import br.com.agla.classes.*;
import br.com.agla.exceptions.JaExisteException;
import br.com.agla.exceptions.NaoExisteException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JanelaVerificacao extends JFrame implements ActionListener{

	ImageIcon imagem = new ImageIcon(getClass().getResource("/Imagens/JanelaVerificaçãoAluno.png"));
	ImageIcon imagem2 = new ImageIcon(getClass().getResource("/Imagens/JanelaVerificaçãoProfessor.png"));
	ImageIcon imagem3 = new ImageIcon(getClass().getResource("/Imagens/JanelaVerificaçãoAluno2.png"));
	ImageIcon imagem4 = new ImageIcon(getClass().getResource("/Imagens/JanelaVerificaçãoProfessor2.png"));
	JPanel painel = new JPanel();
	JLabel rotulo = new JLabel();
	JTextField[] caixas = new JTextField[14];
	JTextArea area = new JTextArea();
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
	JanelaAvisos janela = new JanelaAvisos();
	String[] textos = new String[14];
	JTable tabela;
	String[] indices = {"", "1º", "2º", "3º", "4º"};
	String[][] dados;
	
	public JanelaVerificacao(Sistema sistema, Pessoa pessoa) {
		this.sistema = sistema;
		this.pessoa = pessoa;
		new JFrame();
		painel.setLayout(null);
		
		for(int k = 0; k < 14; k++) {
			caixas[k] = new JTextField();
		}
		
		caixas[0].setBounds(26, 28, 250, 24);
		caixas[1].setBounds(300, 28, 160, 24);
		caixas[2].setBounds(26, 72, 250, 24);
		caixas[3].setBounds(300, 72, 160, 24);
		caixas[4].setBounds(26, 116, 110, 24);
		caixas[5].setBounds(160, 116, 116, 24);
		caixas[6].setBounds(300, 116, 160, 24);
		caixas[7].setBounds(160, 158, 116, 24);
		caixas[8].setBounds(300, 158, 160, 24);
		caixas[9].setBounds(160, 202, 116, 24);
		caixas[10].setBounds(300, 202, 160, 24);
		caixas[11].setBounds(26, 246, 110, 24);
		caixas[12].setBounds(160, 246, 300, 24);
		caixas[13].setBounds(160, 290, 300, 24);
		
		caixas[0].setText(pessoa.getDadosPessoais().getNome());
		caixas[1].setText(pessoa.getMatricula());
		caixas[2].setText(pessoa.getDadosPessoais().getEmail());
		caixas[3].setText(pessoa.getDadosPessoais().getCpf());
		caixas[4].setText(pessoa.getDadosPessoais().getRg());
		caixas[5].setText(pessoa.getDadosPessoais().getDataNascimento());
		caixas[6].setText(pessoa.getDadosPessoais().getTelefone().toString());
		caixas[7].setText(pessoa.getDadosPessoais().getEndereco().getEstado());
		caixas[8].setText(pessoa.getDadosPessoais().getEndereco().getCidade());
		caixas[9].setText(pessoa.getDadosPessoais().getEndereco().getBairro());
		caixas[10].setText(pessoa.getDadosPessoais().getEndereco().getLogradouro());
		caixas[11].setText(pessoa.getTurma());
		caixas[12].setText(pessoa.getDadosPessoais().getEndereco().getComplemento());
		caixas[13].setText(pessoa.getLattes());
		if(pessoa.getDadosPessoais().getSexo().equals("M")) rbotao1.setSelected(true);
		else rbotao2.setSelected(true);
		caixas[13].setVisible(false);
		area.setBounds(555, 60, 146, 293);
		
		
		botao1.setBounds(28, 290, 127, 24);
		botao2.setBounds(180, 290, 127, 23);
		botao3.setBounds(29, 335, 126, 23);
		botao4.setBounds(180, 335, 127, 23);
		
		rbotao1.setBounds(43, 189, 17, 20);
		rbotao2.setBounds(90, 189, 17, 20);
		
		for(JTextField j: caixas) {
			j.setEnabled(false);
		}
		
		rbotao1.setEnabled(false);
		rbotao2.setEnabled(false);
		
		botao1.setContentAreaFilled(false);
		botao2.setContentAreaFilled(false);
		botao3.setContentAreaFilled(false);
		botao4.setContentAreaFilled(false);
		
		botao1.addActionListener(this);
		botao2.addActionListener(this);
		botao3.addActionListener(this);
		botao4.addActionListener(this);

		for(JTextField j: caixas) {
			rotulo.add(j);
		}
		
		rotulo.add(botao1);
		rotulo.add(botao2);
		rotulo.add(botao3);
		rotulo.add(botao4);
		rotulo.add(rbotao1);
		rotulo.add(rbotao2);

		rbotao1.setSelected(true);
		grupo.add(rbotao1);
		grupo.add(rbotao2);
		
		rotulo.setBounds(0, 0, 1025, 579);
		painel.add(rotulo);
		getContentPane().add(painel);
		
		setTitle("Verificar");
		setSize(745, 417);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void verificaAluno() {
		this.dados = pessoa.getBoletim();
		String[] nomeCpf = {"", "1º", "2º", "3º", "4º"};
		tabela = new JTable(pessoa.getBoletim(), nomeCpf);
		JScrollPane barra = new JScrollPane(tabela);
		barra.setBounds(490, 40, 214, 313);
		tabela.setEnabled(false);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(200);	
		rotulo.add(barra);		
		rotulo.setIcon(imagem);
		setVisible(true);
	}
	
	public void verificaProfessor() {
		rotulo.setIcon(imagem2);
		botao1.setVisible(false);
		botao2.setVisible(false);
		caixas[13].setVisible(true);
		setSize(492, 415);
		setVisible(true);
	}
	public void editor() {
		for(JTextField j: caixas) {
			j.setEnabled(true);
		}
		rbotao1.setEnabled(true);
		rbotao2.setEnabled(true);
		cond1 = true;
	}
	
	public void contraEditor() {
		for(JTextField j: caixas) {
			j.setEnabled(false);
		}
		rbotao1.setEnabled(false);
		rbotao2.setEnabled(false);
		cond1 = false;
	}
	
	public void alterador() {
		for(JTextField j: caixas) {
			j.setText(j.getText());
		}
	}
	
	public void guardaTextosDasCaixas() {
		for(int k = 0; k < 14; k++) {
			textos[k] = caixas[k].getText();
		}
	}
	
	public void cancelaRemocao() {
		for(int k = 0; k < 14; k++) {
			caixas[k].setText(textos[k]);
		}
	}
	
	public void guardarDados() {
		pessoa.getDadosPessoais().setNome(caixas[0].getText());
		pessoa.setMatricula(caixas[1].getText());
		pessoa.getDadosPessoais().setEmail(caixas[2].getText());
		pessoa.getDadosPessoais().setCpf(caixas[3].getText());
		pessoa.getDadosPessoais().setRg(caixas[4].getText());
		pessoa.getDadosPessoais().setDataNascimento(caixas[5].getText());
		pessoa.getDadosPessoais().getTelefone().setPrefixo(caixas[6].getText().substring(0, 4));
		pessoa.getDadosPessoais().getTelefone().setNumero(caixas[6].getText().substring(4));
		pessoa.getDadosPessoais().getEndereco().setEstado(caixas[7].getText());
		pessoa.getDadosPessoais().getEndereco().setCidade(caixas[8].getText());
		pessoa.getDadosPessoais().getEndereco().setBairro(caixas[9].getText());
		pessoa.getDadosPessoais().getEndereco().setLogradouro(caixas[10].getText());
		pessoa.setTurma(caixas[11].getText());
		pessoa.getDadosPessoais().getEndereco().setComplemento(caixas[12].getText());
		pessoa.setLattes(caixas[13].getText());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botao3 && cond1 == false) {
			setTitle("Alterar");
			if(rotulo.getIcon() == imagem) {
				tabela.setEnabled(true);
				rotulo.setIcon(imagem3);
			}
			else rotulo.setIcon(imagem4);
			botao1.setVisible(false);
			botao2.setVisible(false);
			guardaTextosDasCaixas();
			editor();
			if(e.getSource() == botao2) {
				contraEditor();
				
			}
		}
		else if(e.getSource() == botao3 && cond1) {
			alterador();
			System.out.println(pessoa.getDadosPessoais().getDataNascimento());
			setTitle("Verificar");
			if(rotulo.getIcon() == imagem3) {
				rotulo.setIcon(imagem);
				retornaBoletim();
				tabela.setEnabled(false);
				tabela.setCellSelectionEnabled(false);
				System.out.println(Arrays.toString(pessoa.getBoletim()));
				try {
					sistema.removeAluno(pessoa.getDadosPessoais().getCpf());
					guardarDados();
					sistema.addAluno(pessoa.getDadosPessoais().getCpf(), (Aluno) pessoa);
				} catch (NaoExisteException | JaExisteException e1) {
					e1.printStackTrace();
				}
			}
			else {
				try {
					sistema.removeProfessor(pessoa.getDadosPessoais().getCpf());
					guardarDados();
					sistema.addProfessor(pessoa.getDadosPessoais().getCpf(), (Professor) pessoa);
				} catch (NaoExisteException | JaExisteException e1) {
					e1.printStackTrace();
				}
				rotulo.setIcon(imagem2);
			}

			botao3.setVisible(true);
			botao4.setVisible(true);
			contraEditor();
		}
		else if(e.getSource() == botao4 && cond1) {
			cancelaRemocao();
			setTitle("Verificar");


			if(rotulo.getIcon() == imagem3) {
				verificaAluno();			
				tabela.setEnabled(false);
				tabela.setCellSelectionEnabled(false);

				rotulo.setIcon(imagem);
				botao1.setVisible(true);
				botao2.setVisible(true);
			}
			else rotulo.setIcon(imagem2);
			contraEditor();
		}
		else if(e.getSource() == botao4 && cond1 == false) {
			if(rotulo.getIcon() == imagem3) {
				try {
					sistema.removeAluno(caixas[3].getText());
					setVisible(false);
					new JanelaAvisos().avisoExclusao();
				} catch (NaoExisteException e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					sistema.removeProfessor(caixas[3].getText());
					setVisible(false);
					new JanelaAvisos().avisoExclusao();
				} catch (NaoExisteException e1) {
					e1.printStackTrace();
				}			}
		}
	}
	
	public Pessoa retornaPessoa() {
		return pessoa;
	}
	
	public void retornaBoletim() {
		String[][] array = new String[11][5];
		for(int k = 0; k < 11; k++) {
			for(int i = 0; i < 5; i++) {
				array[k][i] = (String) tabela.getValueAt(k, i);
			}
		}
		pessoa.setBoletim(array);
	}
	
	public static void main(String[] args) {
		new JanelaVerificacao(new Sistema(), new Aluno()).verificaProfessor();
	}
}
