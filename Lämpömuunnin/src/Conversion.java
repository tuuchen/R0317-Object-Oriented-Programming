import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SpringLayout;

public class Conversion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JTextField textField;

	
	// Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversion frame = new Conversion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 // Create the frame.
	 
	public Conversion() {
		setResizable(false);
		setTitle("Converter app");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 150);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		lblNewLabel_1 = new JLabel("Celsius");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 48, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -67, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -225, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton("Convert");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 18, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 25, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -24, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("Fahrenheit: ");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 139, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -41, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -26, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.NORTH, btnNewButton);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.NORTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -6, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, textField, -41, SpringLayout.EAST, getContentPane());
		getContentPane().add(textField);
		textField.setColumns(10);
		
		// Convert napin toiminnallisuus "laskeMuunnos" metodilla
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kenttä = textField.getText();
				String tarkistus = kenttä.replace(",", ".");
				try {
					double celsius = Double.parseDouble(tarkistus);
					lblNewLabel.setText("Fahrenheit: " + laskeMuunnos(celsius));
				} catch (Exception p) {
					JOptionPane.showMessageDialog(null, "Virhe syötteessä! Syötä luku. \n (From: " + e.getActionCommand() + ")");
				}
			}
		});
	}

	// Metodi laskee muunnoksen
	
	public double laskeMuunnos(double celsius) {
		double fahrenheit = celsius * 9.0 / 5.0 + 32.0;
		return fahrenheit;
	}
}