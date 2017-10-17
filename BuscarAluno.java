package object;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import exception.AlunoInexistenteException;

public class BuscarAluno extends JFrame implements ActionListener{

	ImageIcon imagem = new ImageIcon(getClass().getResource("JanelaAluno1.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();
	JButton botao = new JButton("Buscar");
	JButton bot = new JButton("Remover");
	JTextField caixa = new JTextField();	
	JRadioButton botao1 = new JRadioButton("M");
	JRadioButton botao2 = new JRadioButton("F");
	ButtonGroup grupo = new ButtonGroup();
	boolean botao31 = false;
	JButton botao3 = new JButton("Alterar");
	JTextArea area1;
	JTextArea area2;
	JTextArea area3;
	JTextArea area4;
	JTextArea area5;
	JTextArea area6;
	JTextArea area7;
	JTextArea area8;
	JTextArea area9;
	JTextArea area10;
	JTextArea area11;
	JTextArea area12;
	JTextArea area13;
	JTextArea area14;

	Sistema sistema;
	
	public boolean getBotao31(){
		return botao31;
	}
	
	public void addButton(){
		rotulo.add(bot);
		bot.setBounds(200, 670, 95, 25);
		bot.setForeground(getForeground().WHITE);
		bot.setBackground(new Color(180, 20, 20));
		bot.addActionListener(this);
		botao31 = true;

	}

	
	public BuscarAluno(Sistema sistema){
		new JFrame();
		painel.setLayout(null);
		rotulo.add(botao);
		rotulo.add(caixa);
		botao.setBackground(getBackground().WHITE);	
		botao.setForeground(new Color(180, 20, 20));
		botao.setBounds(5, 278, 75, 30);
		caixa.setBounds(80, 282, 110, 23);
		rotulo.setBounds(0, -275, 700, 980);
		botao.addActionListener(this);
		painel.add(rotulo);
		add(painel);
		setSize(500, 520);
		setResizable(false);
		setLocationRelativeTo(null);
		
		this.sistema = sistema;
		
		botao1 = new JRadioButton("M");
		botao2 = new JRadioButton("F");
		area1 = new JTextArea();
		area2 = new JTextArea();
		area3 = new JTextArea();
		area4 = new JTextArea();
		area5 = new JTextArea();
		area6 = new JTextArea();
		area7 = new JTextArea();
		area8 = new JTextArea();
		area9 = new JTextArea();
		area10 = new JTextArea();
		area11 = new JTextArea();
		area12 = new JTextArea();
		area13 = new JTextArea();
		area14 = new JTextArea();
		
		rotulo.add(area1);
		rotulo.add(area2);
		rotulo.add(area3);
		rotulo.add(area4);
		rotulo.add(area5);
		rotulo.add(area6);
		rotulo.add(area7);
		rotulo.add(area8);
		rotulo.add(area9);
		rotulo.add(area10);
		rotulo.add(area11);
		rotulo.add(area12);
		rotulo.add(area13);
		rotulo.add(area14);
		rotulo.add(botao1);
		rotulo.add(botao2);
		
		area1.setBounds(40, 370, 300, 20);
		area2.setBounds(350, 370, 100, 20);
		area3.setBounds(120, 418, 100, 20);
		area4.setBounds(230, 418, 110, 20);
		area5.setBounds(350, 418, 100, 20);
		area6.setBounds(40, 466, 300, 20);
		area7.setBounds(350, 466, 100, 20);
		area8.setBounds(40, 512, 100, 20);
		area9.setBounds(150, 512, 60, 20);
		area10.setBounds(220, 512, 230, 20);
		area11.setBounds(40, 557, 100, 20);
		area12.setBounds(150, 557, 190, 20);
		area13.setBounds(350, 557, 100, 20);
		area14.setBounds(40, 603, 200, 40);
		botao1.setBounds(35, 418, 40, 20);
		botao2.setBounds(75, 418, 40, 20);
		
		area1.setEditable(false);
		area2.setEditable(false);
		area3.setEditable(false);
		area4.setEditable(false);
		area5.setEditable(false);
		area6.setEditable(false);
		area7.setEditable(false);
		area8.setEditable(false);
		area9.setEditable(false);
		area10.setEditable(false);
		area11.setEditable(false);
		area12.setEditable(false);
		area13.setEditable(false);
		area14.setEditable(false);
		
		naoVisivel();
		
		botao1.setSelected(true);
		botao1.setEnabled(false);
		botao2.setEnabled(false);
		grupo.add(botao1);
		grupo.add(botao2);
	
	}
	
	public void naoVisivel(){
		area1.setVisible(false);
		area2.setVisible(false);
		area3.setVisible(false);
		area4.setVisible(false);
		area5.setVisible(false);
		area6.setVisible(false);
		area7.setVisible(false);
		area8.setVisible(false);
		area9.setVisible(false);
		area10.setVisible(false);
		area11.setVisible(false);
		area12.setVisible(false);
		area13.setVisible(false);
		area14.setVisible(false);

		botao1.setVisible(false);
		botao2.setVisible(false);
	}
	
	public void visivel(){
		rotulo.setIcon(new ImageIcon(getClass().getResource("JanelaAluno2.png")));

		area1.setVisible(true);
		area2.setVisible(true);
		area3.setVisible(true);
		area4.setVisible(true);
		area5.setVisible(true);
		area6.setVisible(true);
		area7.setVisible(true);
		area8.setVisible(true);
		area9.setVisible(true);
		area10.setVisible(true);
		area11.setVisible(true);
		area12.setVisible(true);
		area13.setVisible(true);
		area14.setVisible(true);

		botao1.setVisible(true);
		botao2.setVisible(true);
		
	}
	
	public void editor(){
		area1.setEditable(true);
		area2.setEditable(true);
		area3.setEditable(true);
		area4.setEditable(true);
		area5.setEditable(true);
		area6.setEditable(true);
		area7.setEditable(true);
		area8.setEditable(true);
		area9.setEditable(true);
		area10.setEditable(true);
		area11.setEditable(true);
		area12.setEditable(true);
		area13.setEditable(true);
		area14.setEditable(true);
		
		botao1.setEnabled(true);
		botao2.setEnabled(true);
		rotulo.add(botao3);
		botao3.setBackground(new Color(180, 20, 20));	
		botao3.setForeground(getBackground().WHITE);
		botao3.setBounds(210, 670, 75, 30);
		botao3.addActionListener(this);
		botao3.setVisible(false);
	}
	
	public void contraEditor(){
		
		area1.setEditable(false);
		area2.setEditable(false);
		area3.setEditable(false);
		area4.setEditable(false);
		area5.setEditable(false);
		area6.setEditable(false);
		area7.setEditable(false);
		area8.setEditable(false);
		area9.setEditable(false);
		area10.setEditable(false);
		area11.setEditable(false);
		area12.setEditable(false);
		area13.setEditable(false);
		area14.setEditable(false);
		
		botao1.setEnabled(false);
		botao2.setEnabled(false);
		botao3.setVisible(false);
		bot.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Aluno aluno = new Aluno();
		
		

		if(e.getSource() == botao){
			try {
				aluno = sistema.verificarAluno(caixa.getText());
				botao3.setVisible(true);
				visivel();		
				if(botao31){		
					bot.setVisible(true);            		
				}		

			} catch (AlunoInexistenteException e1) {
				rotulo.setIcon(new ImageIcon(getClass().getResource("JanelaAluno1.png")));
				botao3.setVisible(false);
				bot.setVisible(false);
				naoVisivel();
				new CaixaAviso();
			}

			area1.setText((aluno.getDadosPessoais().getNome()));
			area2.setText(aluno.getMatricula());
			area3.setText(aluno.getDadosPessoais().getDataNascimento());
			area4.setText(aluno.getDadosPessoais().getCpf());
			area5.setText(aluno.getDadosPessoais().getRg());
			area6.setText(aluno.getDadosPessoais().getEmail());
			area7.setText(aluno.getDadosPessoais().getTelefone().toString());
			area8.setText(aluno.getDadosPessoais().getTelefone().getOperadora());
			area9.setText(aluno.getDadosPessoais().getEndereco().getEstado());
			area10.setText(aluno.getDadosPessoais().getEndereco().getCidade());
			area11.setText(aluno.getDadosPessoais().getEndereco().getBairro());
			area12.setText(aluno.getDadosPessoais().getEndereco().getLogradouro());
			area13.setText(aluno.getDadosPessoais().getEndereco().getNumero()+"");
			area14.setText(aluno.getDadosPessoais().getEndereco().getComplemento());

		}
		if(e.getSource() == botao3){
			
			setVisible(false);

			aluno.getDadosPessoais().setNome(area1.getText());
			aluno.setMatricula(area2.getText());
			aluno.getDadosPessoais().setDataNascimento(area3.getText());
			aluno.getDadosPessoais().setCpf(area4.getText());
			aluno.getDadosPessoais().setRg(area5.getText());
			aluno.getDadosPessoais().setEmail(area6.getText());
			aluno.getDadosPessoais().getTelefone().setPrefixo(area7.getText().substring(0, 3));
			aluno.getDadosPessoais().getTelefone().setNumero(area7.getText().substring(3));
			aluno.getDadosPessoais().getTelefone().setOperadora(area8.getText());
			aluno.getDadosPessoais().getEndereco().setEstado(area9.getText());
			aluno.getDadosPessoais().getEndereco().setCidade(area10.getText());
			aluno.getDadosPessoais().getEndereco().setBairro(area11.getText());
			aluno.getDadosPessoais().getEndereco().setLogradouro(area12.getText());
			aluno.getDadosPessoais().getEndereco().setNumero(Integer.parseInt(area13.getText()));
			aluno.getDadosPessoais().getEndereco().setComplemento(area14.getText());
			try {
				sistema.alterarAluno(aluno);
				new CaixaAviso().dadosAlterados();
			} catch (AlunoInexistenteException e1) {}
		}
		if(e.getSource() == bot){
			setVisible(false);
			try {
				sistema.removeAluno(area4.getText());
				new CaixaAviso().dadosRemovidos();
			} catch (AlunoInexistenteException e1) {}
		}
	}
}

