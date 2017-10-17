package object;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class JanelaInicio extends JFrame implements ActionListener{
	ImageIcon imagem = new ImageIcon(getClass().getResource("Inicio.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();
	JTextField caixa = new JTextField("");
	JPasswordField caixa2 = new JPasswordField("");
	JButton entrar = new JButton("Entrar");
	Sistema sistema = new Sistema();
	
	public JanelaInicio(){
		new JFrame();
		painel.setLayout(null);
		rotulo.add(caixa);
		rotulo.add(caixa2);
		rotulo.add(entrar);
		entrar.setBounds(440, 380, 80, 25);
		entrar.setForeground(getForeground().white);
		entrar.setBackground(new Color(180, 20, 20));
		entrar.addActionListener(this);
		caixa.setBounds(410, 288, 180, 25);
		caixa2.setBounds(410, 332, 180, 25);
		rotulo.setBounds(0, 0, 1020, 600);
		painel.add(rotulo);
		add(painel);
		setSize(950, 575);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args){
		new JanelaInicio();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == entrar){
			if(sistema.entra(caixa.getText(), caixa2.getText())){
				setVisible(false);
				new JanelaPrincipal(sistema);
			}else{
				new CaixaAviso();
			}
		}
	}
}
