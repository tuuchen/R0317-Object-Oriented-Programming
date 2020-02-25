import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Ohje extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblPikatyttTytt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ohje frame = new Ohje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ohje() {
		setTitle("Ohje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("1. Paina kuvaketta saadaksesi haluamasi juoma");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 59, 424, 21);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("2. Ylläpitovalikosta voit täyttää valitun säiliön");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 91, 424, 21);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("4. Nautinnollisia juomahetkiä!");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(10, 155, 424, 21);
		contentPane.add(lblNewLabel_2);
		
		lblPikatyttTytt = new JLabel("3. Pikatäyttö täyttää kaikki säiliöt!");
		lblPikatyttTytt.setHorizontalAlignment(SwingConstants.CENTER);
		lblPikatyttTytt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPikatyttTytt.setBounds(10, 123, 424, 21);
		contentPane.add(lblPikatyttTytt);
	}
}
