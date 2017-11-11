package br.com.agla.gui;

import br.com.agla.imagens.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JanelaAvisos extends JFrame implements ActionListener{

	ImageIcon imagem = new ImageIcon(getClass().getResource("/Imagens/JanelaConfirmação.png"));
	JPanel painel = new JPanel();
	JLabel rotulo = new JLabel(imagem);
	JButton[] botoes = new JButton[2];
	boolean remover = false;

	public JanelaAvisos() {
		new JFrame();
		painel.setLayout(null);
		
		for(int k = 0; k < 2; k++) {
			botoes[k] = new JButton();
			botoes[k].setContentAreaFilled(false);
			botoes[k].setVisible(false);
			botoes[k].addActionListener(this);
			rotulo.add(botoes[k]);
		}
		
		botoes[0].setBounds(70, 110, 86, 23);
		botoes[1].setBounds(189, 110, 86, 23);
		
		rotulo.setBounds(0, 0, 1025, 579);

		painel.add(rotulo);
		add(painel);
		setSize(354, 201);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void confirmarExclusao() {
		botoes[0].setVisible(true);
		botoes[1].setVisible(true);
		setVisible(true);
		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/ConfirmarExclusão.png")));
		
	}
	
	public void confirmarCadastro() {
		setVisible(true);
		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/ConfirmaçãoCadastro.png")));
	}
	
	public void avisoExclusao() {
		setVisible(true);
		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/Remoção.png")));
	}
	
	public void erroCadastro() {
		setVisible(true);
		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/ErroCadastro.png")));
	}
	public boolean getRetorno() {
		return remover;
	}
	
	public void avisoSenhas() {
		setVisible(true);
		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/AvisoSenhas.png")));
		}
	
	public static void main(String[] args) {
		new JanelaAvisos().readMe();
	}

	public void naoExiste() {
 		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/NaoEncontrado.png")));
 		setVisible(true);
	}
	
	public void readMe() {
		setSize(516, 606);
 		rotulo.setIcon(new ImageIcon(getClass().getResource("/Imagens/Read-me.png")));
 		setVisible(true);
 		setLocationRelativeTo(null);
 	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botoes[0]) remover = true;
		else if(e.getSource() == botoes[1]) remover = false;
		setVisible(false);
	}
	
}
