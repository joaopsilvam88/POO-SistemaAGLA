package object;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.poo.sisagla.exceptions.JaExisteException;

import exception.AlunoJaExisteException;

public class CadastraAluno extends JDialog implements ActionListener{
	
	ImageIcon imagem = new ImageIcon(getClass().getResource("Nome.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();
	JTextField caixa = new JTextField();
	JButton botao1 = new JButton("Cadastrar");
	JButton botao2 = new JButton("Cadastrar");
	JButton botao3 = new JButton("Cadastrar");
	JButton botao4 = new JButton("Cadastrar");
	JButton botao5 = new JButton("Cadastrar");
	Sistema sistema;
	Aluno aluno = new Aluno();
	JFormattedTextField janela = new JFormattedTextField();

	public CadastraAluno(Sistema sistema){
		try {
			MaskFormatter mascara = new MaskFormatter("###.###.###-##");
			mascara.setPlaceholderCharacter('_');
			janela.setFormatterFactory(new DefaultFormatterFactory(mascara));
			
		} catch (ParseException e) {}
		this.sistema = sistema;
		new JDialog();
		painel.setLayout(null);
		rotulo.setBounds(0, 0, 697, 470);
		caixa.setBounds(85, 65, 195, 25);
		janela.setBounds(85, 65, 195, 25);
		rotulo.add(botao1);
		rotulo.add(botao2);
		rotulo.add(botao3);
		rotulo.add(botao4);
		rotulo.add(botao5);
		
		botao1.setBounds(135, 100, 95, 25);
		botao1.setForeground(getForeground().WHITE);
		botao1.setBackground(new Color(180, 20, 20));
		botao1.addActionListener(this);

		botao2.setBounds(135, 100, 95, 25);
		botao2.setForeground(getForeground().WHITE);
		botao2.setBackground(new Color(180, 20, 20));
		botao2.addActionListener(this);

		botao3.setBounds(135, 100, 95, 25);
		botao3.setForeground(getForeground().WHITE);
		botao3.setBackground(new Color(180, 20, 20));
		botao3.addActionListener(this);

		botao4.setBounds(135, 100, 95, 25);
		botao4.setForeground(getForeground().WHITE);
		botao4.setBackground(new Color(180, 20, 20));
		botao4.addActionListener(this);

		botao5.setBounds(135, 100, 95, 25);
		botao5.setForeground(getForeground().WHITE);
		botao5.setBackground(new Color(180, 20, 20));
		botao5.addActionListener(this);
		
		janela.setVisible(false);
		rotulo.add(janela);
		rotulo.add(caixa);
		painel.add(rotulo);
		add(painel);
		setSize(377, 190);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botao1){
			janela.setVisible(true);
			aluno.getDadosPessoais().setNome(caixa.getText());
			botao1.setVisible(false);
			setVisible(false);
			rotulo.setIcon(new ImageIcon(getClass().getResource("CPF.png")));
			setVisible(true);
			caixa.setText(null);
		}		
		if(e.getSource() == botao2){
			janela.setVisible(false);
			aluno.getDadosPessoais().setCpf(janela.getText());
			botao2.setVisible(false);
			setVisible(false);
			rotulo.setIcon(new ImageIcon(getClass().getResource("RG.png")));
			setVisible(true);
			caixa.setText(null);
		}		
		if(e.getSource() == botao3){
			aluno.getDadosPessoais().setRg(caixa.getText());
			botao3.setVisible(false);
			setVisible(false);
			rotulo.setIcon(new ImageIcon(getClass().getResource("Matricula.png")));
			setVisible(true);
			caixa.setText(null);
		}		
		if(e.getSource() == botao4){
			aluno.setMatricula(caixa.getText());
			botao4.setVisible(false);
			setVisible(false);
			rotulo.setIcon(new ImageIcon(getClass().getResource("E-mail.png")));
			setVisible(true);
			caixa.setText(null);
		}
		if(e.getSource() == botao5){
			aluno.getDadosPessoais().setEmail(caixa.getText());
			botao5.setVisible(false);
			caixa.setVisible(false);
			setVisible(false);
			try {
				sistema.addAluno(aluno.getDadosPessoais().getCpf(), aluno);
				rotulo.setIcon(new ImageIcon(getClass().getResource("CadastroAluno.png")));	
				setVisible(true);	
			} catch (AlunoJaExisteException e1) {
				new CaixaAviso().jaExiste();	
			}

		}
	}
	
}
