package object;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		int cargo = Integer.parseInt(JOptionPane.showInputDialog("Voc� � aluno ou professor?\n1. Aluno\n2. Professor"));
		switch(cargo){
		case(1):
			String cond = JOptionPane.showInputDialog("Voc� deseja:\n1. Conferir seus dados pessoais.\n2. Verificar o seu hist�rico escolar.\n3. Obter uma declara��o de v�nculo com a institui��o.\n4. Verificar seu boletim.");
		}
	}

}
