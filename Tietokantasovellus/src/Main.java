import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;

public class Main {
	
	// Main method, establishes connection-functions and shows data in GUI
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		Processor[] data = Methods.getData();
		JFrame window = new TableGUI(data);
		window.getContentPane().setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		window.setVisible(true);
	}
}