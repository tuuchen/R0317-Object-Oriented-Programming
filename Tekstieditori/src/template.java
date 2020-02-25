import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Font;
import javax.swing.ImageIcon;

public class template extends JFrame {

	private JPanel contentPane;
	private JLabel lblTuukkaTihekari;
	private JLabel lblLaureaUas;
	private JLabel lblWwwgooglefi;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					template frame = new template();
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
	public template() {
		setTitle("Tietoja");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTuukkaTihekari = new JLabel("© Tuukka Tihekari");
		lblTuukkaTihekari.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblTuukkaTihekari.setVerticalAlignment(SwingConstants.TOP);
		lblTuukkaTihekari.setBounds(10, 11, 232, 38);
		contentPane.add(lblTuukkaTihekari);
		
		lblLaureaUas = new JLabel("Laurea UAS 2019");
		lblLaureaUas.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblLaureaUas.setBounds(20, 46, 193, 38);
		contentPane.add(lblLaureaUas);
		
		lblWwwgooglefi = new JLabel("https://www.facebook.com/Tuuchen");
		lblWwwgooglefi.setForeground(Color.blue);
		lblWwwgooglefi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWwwgooglefi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    try {
			       
			        Desktop.getDesktop().browse(new URI("https://www.facebook.com/Tuuchen"));
			         
			    } catch (IOException | URISyntaxException e1) {
			        e1.printStackTrace();
			    }
			}
		});
		lblWwwgooglefi.setBounds(20, 95, 222, 21);
		contentPane.add(lblWwwgooglefi);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(template.class.getResource("/res/profile.png")));
		lblNewLabel.setBounds(255, 43, 169, 159);
		contentPane.add(lblNewLabel);
	}
}
