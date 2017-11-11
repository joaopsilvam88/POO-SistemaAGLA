package br.com.agla.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.agla.classes.Sistema;
import br.com.agla.exceptions.ErroConexaoException;
import br.com.agla.exceptions.LoginJaExisteException;
import br.com.agla.exceptions.NaoExisteException;

public class JanelaRegistro extends JFrame implements ActionListener, MouseListener{

	ImageIcon imagem = new ImageIcon(getClass().getResource("/Imagens/JanelaRegistro.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();
	JTextField[] caixas = new JTextField[5];
	JButton entrar = new JButton();
	Sistema sistema;
	boolean[] apagar = new boolean[5];

	
	public JanelaRegistro(Sistema sistema){
		this.sistema = sistema;
		new JFrame();
		painel.setLayout(null);
		
		entrar.setBounds(139, 275, 86, 24);
		entrar.setContentAreaFilled(false);
		
		for(int k = 0; k < 5; k++) {
			caixas[k] = new JTextField();
			caixas[k].addMouseListener(this);
			rotulo.add(caixas[k]);
			apagar[k] = true;
		}
		
		caixas[0].setText("Nome");
		caixas[1].setText("Matrícula");
		caixas[2].setText("Login");
		caixas[3].setText("Senha");
		caixas[4].setText("Confirmar senha");

		caixas[0].setBounds(40, 141, 288, 26);
		caixas[1].setBounds(40, 185, 131, 26);
		caixas[2].setBounds(192, 185, 134, 26);
		caixas[3].setBounds(40, 228, 131, 26);
		caixas[4].setBounds(192, 228, 132, 26);
		
		rotulo.setBounds(-3, -97, 1030, 768);
		
		entrar.addActionListener(this);

		rotulo.add(entrar);
		painel.add(rotulo);
		add(painel);
		
		getRootPane().setDefaultButton(entrar);
		
		setSize(364, 270);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == entrar) {
			if(caixas[3].getText().equals(caixas[4].getText())) {
				try {
					sistema.registra(caixas[2].getText(), caixas[3].getText());
					new JanelaAvisos().confirmarCadastro();
					setVisible(false);
				} catch (NaoExisteException e1) {
					e1.printStackTrace();
				} catch (ErroConexaoException e1) {
					e1.printStackTrace();
				} catch (LoginJaExisteException e1) {
					new JanelaAvisos().erroCadastro();
				}
			}else new JanelaAvisos().avisoSenhas();
		}
	}
	
	public static void main(String[] args) {
		new JanelaRegistro(new Sistema());
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
	public void mousePressed(MouseEvent e) {
		for(int k = 0; k < 5; k++) {
			if(e.getSource() == caixas[k] && apagar[k] == true) {
				caixas[k].setText("");
				apagar[k] = false;
			}
		}	
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
