package object;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import exception.ProfessorInexistenteException;
import exception.ProfessorJaExisteException;


public class BuscarProfDisciplina extends JDialog implements ActionListener{

	Sistema sistema;
	String[][] dados;
	String disciplina;
	List<Professor> professores;
	ImageIcon imagem = new ImageIcon(getClass().getResource("Janelinha.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();
	JTextField caixa = new JTextField();
	JButton botao = new JButton("Confirmar");
	
	public BuscarProfDisciplina(Sistema sistema){
		this.sistema = sistema;
		new JDialog();
		painel.setLayout(null);
		rotulo.add(caixa);
		rotulo.add(botao);
		caixa.setBounds(101, 70, 150, 25);
		botao.setBounds(130, 108, 95, 25);
		botao.setForeground(getForeground().WHITE);
		botao.setBackground(new Color(180, 20, 20));
		botao.addActionListener(this);
		rotulo.setBounds(0, 0, 697, 470);
		painel.add(rotulo);
		add(painel);
		setSize(377, 190);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	public void buscar(){
		try {
			professores = sistema.professoresDaDisciplina(disciplina);
		} catch (ProfessorInexistenteException e) {
			e.printStackTrace();
		}
		dados = new String[professores.size()][4];
		for(int i = 0; i < professores.size(); i++){
			dados[i][0] = (i+1)+"";
			dados[i][1] = professores.get(i).getDadosPessoais().getNome();
			dados[i][2] = professores.get(i).getDadosPessoais().getTelefone().toString();
			dados[i][3] = professores.get(i).getDadosPessoais().getEmail();
		}
		new Tabelas(dados);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botao){
			disciplina = caixa.getText();
			setVisible(false);
			buscar();
		}
	}
}
