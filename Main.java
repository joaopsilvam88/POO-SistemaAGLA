package object;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		int cargo = Integer.parseInt(JOptionPane.showInputDialog("Você é aluno ou professor?\n1. Aluno\n2. Professor"));
		switch(cargo){
		case(1):
			String cond = JOptionPane.showInputDialog("Você deseja:\n1. Conferir seus dados pessoais.\n2. Verificar o seu histórico escolar.\n3. Obter uma declaração de vínculo com a instituição.\n4. Verificar seu boletim.");
		}
	}

}
