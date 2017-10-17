package object;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CaixaAviso extends JDialog{

	ImageIcon imagem = new ImageIcon(getClass().getResource("NaoEncontrado.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();

	public void jaExiste(){
		rotulo.setIcon(new ImageIcon(getClass().getResource("JaExiste.png")));
	}
	public void dadosAlterados(){
		rotulo.setIcon(new ImageIcon(getClass().getResource("DadosAlterados.png")));
	}	
	public void dadosRemovidos(){
		rotulo.setIcon(new ImageIcon(getClass().getResource("DadosRemovidos.png")));
	}
	public CaixaAviso(){
		new JDialog();
		painel.setLayout(null);
		rotulo.setBounds(0, 0, 697, 470);
		painel.add(rotulo);
		add(painel);
		setSize(371, 183);
		setLocationRelativeTo(null);
		setVisible(true);

	}
}
