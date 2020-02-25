import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class GUI_Automaatti extends JFrame {

	// Luokkamuuttujat

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnYllpito;
	private JMenu mnTietojaOhjelmasta;
	private JMenuItem mntmAsetaKahvinMr;
	private JMenuItem mntmAsetaTeenMr;
	private JMenuItem mntmAsetaKaakaonMr;
	private JMenuItem mntmTallennaAutomaatinTila_1;
	private JMenuItem mntmLataaAutomaatti_1;
	private JMenuItem mntmLopeta;
	private JMenuItem mntmVersiotiedot;
	private JMenuItem mntmOhje;
	private JButton Kahvi;
	private JButton Tee;
	private JButton Kaakao;
	static JLabel lblTee;
	static JLabel lblKaakao;
	static JLabel lblKahvi;
	static JLabel lblKahvia;
	static JLabel lblTeeta;
	static JLabel lblKaakaota;
	static JLabel Info;
	private JMenuItem mntmAsetaKaikkienMr;

	/**
	 * Main-metodi, joka käynnistää sovelluksen
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Luodaan ensin uusi JuomaAutomaatti-olio
					JuomaAutomaatti ja = new JuomaAutomaatti();

					Tekijatiedot tekija = new Tekijatiedot();
					tekija.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					tekija.setVisible(false);

					Ohje ohje = new Ohje();
					ohje.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					ohje.setVisible(false);

					// Käyttöliittymä saa parametrina oliot, jonka tiedot se näyttää
					GUI_Automaatti frame = new GUI_Automaatti(ja, tekija, ohje);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Konstruktorissa rakennetaan käyttöliittymä. Huomaa, että otetaan parametrina
	 * vastaan alussa luotu juoma-automaatti. Tämä siksi, että voidaan näyttää sen
	 * tiedot GUI:ssa
	 * 
	 */
	public GUI_Automaatti(JuomaAutomaatti ja, Tekijatiedot tekija, Ohje ohje) {

		// Ikkunan otsikko ja koko

		setTitle("Kahviautomaatti GUI v. 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 705);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnYllpito = new JMenu("Ylläpito");
		menuBar.add(mnYllpito);

		// Asetetaan kahvin määrä
		mntmAsetaKahvinMr = new JMenuItem("Aseta kahvin määrä");
		mntmAsetaKahvinMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.fill("Kahvi");
			}
		});
		mnYllpito.add(mntmAsetaKahvinMr);

		// Asetetaan teen määrä
		mntmAsetaTeenMr = new JMenuItem("Aseta teen määrä");
		mntmAsetaTeenMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.fill("Tee");
			}
		});
		mnYllpito.add(mntmAsetaTeenMr);

		// Asetetaan kaakaon määrä
		mntmAsetaKaakaonMr = new JMenuItem("Aseta kaakaon määrä");
		mntmAsetaKaakaonMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.fill("Kaakao");
			}
		});
		mnYllpito.add(mntmAsetaKaakaonMr);

		// Aseta kaikkien määrä
		mntmAsetaKaikkienMr = new JMenuItem("Pikatäyttö");
		mntmAsetaKaikkienMr.setForeground(Color.RED);
		mntmAsetaKaikkienMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.fill("Pikatäyttö");
			}
		});
		mnYllpito.add(mntmAsetaKaikkienMr);

		// Tallennetaan automaatti
		mntmTallennaAutomaatinTila_1 = new JMenuItem("Tallenna automaatin tila");
		mntmTallennaAutomaatinTila_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Sarjallistamista.kirjoitaTiedostoon(ja);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		mnYllpito.add(mntmTallennaAutomaatinTila_1);

		// Ladataan automaatti
		mntmLataaAutomaatti_1 = new JMenuItem("Lataa automaatti");
		mntmLataaAutomaatti_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.reload();
			}
		});
		mnYllpito.add(mntmLataaAutomaatti_1);

		// Lopetetaan ohjelma
		mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnYllpito.add(mntmLopeta);

		mnTietojaOhjelmasta = new JMenu("Tietoja ohjelmasta");
		menuBar.add(mnTietojaOhjelmasta);

		// Tekijätiedot
		mntmVersiotiedot = new JMenuItem("Tekijätiedot");
		mntmVersiotiedot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tekija.isVisible()) {
					tekija.setVisible(true);
				}
			}
		});
		mnTietojaOhjelmasta.add(mntmVersiotiedot);

		// Ohje
		mntmOhje = new JMenuItem("Ohje");
		mntmOhje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!ohje.isVisible()) {
					ohje.setVisible(true);
				}
			}
		});
		mnTietojaOhjelmasta.add(mntmOhje);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Kahvi-nappi
		Kahvi = new JButton("Kahvi");
		Kahvi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.valmistaJuoma("Kahvi");
			}
		});
		Kahvi.setIcon(new ImageIcon(GUI_Automaatti.class.getResource("/img/coffee.jpg")));
		Kahvi.setBounds(55, 63, 127, 135);
		contentPane.add(Kahvi);

		// Tee-nappi
		Tee = new JButton("Tee");
		Tee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.valmistaJuoma("Tee");
			}
		});
		Tee.setIcon(new ImageIcon(GUI_Automaatti.class.getResource("/img/tea.jpg")));
		Tee.setBounds(55, 234, 127, 135);
		contentPane.add(Tee);

		// Kaakao-nappi
		Kaakao = new JButton("Kaakao");
		Kaakao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.valmistaJuoma("Kaakao");
			}
		});
		Kaakao.setIcon(new ImageIcon(GUI_Automaatti.class.getResource("/img/cocoa.jpg")));
		Kaakao.setBounds(55, 405, 127, 135);
		contentPane.add(Kaakao);

		// Labeleita
		lblTee = new JLabel("Tee");
		lblTee.setHorizontalAlignment(SwingConstants.CENTER);
		lblTee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTee.setBounds(55, 380, 127, 14);
		contentPane.add(lblTee);

		lblKaakao = new JLabel("Kaakao");
		lblKaakao.setHorizontalAlignment(SwingConstants.CENTER);
		lblKaakao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKaakao.setBounds(55, 551, 127, 14);
		contentPane.add(lblKaakao);

		lblKahvi = new JLabel("Kahvi");
		lblKahvi.setHorizontalAlignment(SwingConstants.CENTER);
		lblKahvi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKahvi.setBounds(55, 209, 127, 14);
		contentPane.add(lblKahvi);

		lblKahvia = new JLabel("Kahvia: " + ja.getKahvi());
		lblKahvia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKahvia.setBounds(227, 123, 212, 14);
		contentPane.add(lblKahvia);

		lblTeeta = new JLabel("Teetä: " + ja.getTee());
		lblTeeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTeeta.setBounds(227, 299, 212, 14);
		contentPane.add(lblTeeta);

		lblKaakaota = new JLabel("Kaakaota: " + ja.getKaakao());
		lblKaakaota.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKaakaota.setBounds(227, 475, 212, 14);
		contentPane.add(lblKaakaota);

		Info = new JLabel("");
		Info.setBounds(10, 11, 429, 41);
		contentPane.add(Info);
	}
}
