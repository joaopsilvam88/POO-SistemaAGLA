package object;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.TurmaInexistenteExeption;

public class VerificarTurma extends JDialog implements ActionListener{

	Sistema sistema;
	List<Aluno> lista;
	String[][] matriz;
	ImageIcon imagem = new ImageIcon(getClass().getResource("Ano.png"));
	JLabel rotulo = new JLabel(imagem);
	JPanel painel = new JPanel();
	JTextField caixa = new JTextField();
	JButton botao1 = new JButton("Ok");
	String ano;

	public VerificarTurma(Sistema sistema) {
		this.sistema = sistema;
		new JDialog();
		painel.setLayout(null);
		rotulo.setBounds(0, 0, 697, 470);
		caixa.setBounds(85, 65, 195, 25);

		botao1.setBounds(155, 100, 50, 25);
		botao1.setForeground(getForeground().WHITE);
		botao1.setBackground(new Color(180, 20, 20));
		botao1.addActionListener(this);

		rotulo.add(botao1);
		rotulo.add(caixa);
		painel.add(rotulo);
		add(painel);
		setSize(377, 190);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void iterar(){
		matriz = new String[lista.size()][4];
		for(int k = 0; k < lista.size(); k++){
			matriz[k][0] = (k+1)+"";
			matriz[k][1] = lista.get(k).getDadosPessoais().getNome();
			matriz[k][2] = lista.get(k).getDadosPessoais().getTelefone().toString();
			matriz[k][3] = lista.get(k).getDadosPessoais().getEmail();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botao1){
			setVisible(false);
			ano = caixa.getText();
			try {
				lista = sistema.verificarTurma(ano);
			} catch (TurmaInexistenteExeption e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			iterar();
			new Tabelas(matriz);
		}
	}

	
	
}
