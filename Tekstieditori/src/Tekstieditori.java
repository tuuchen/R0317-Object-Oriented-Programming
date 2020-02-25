import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Scrollbar;

public class Tekstieditori extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Alustetaan komponentit
	private JPanel contentPane;
	private JPanel tietoja;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JEditorPane dtrpnTesti;
	private JMenu mnTiedosto;
	private JMenu mnMuokkaa;
	private JMenu mnTietoja;
	private JMenuItem mntmAvaa;
	private JMenuItem mntmTallenna;
	private JMenuItem mntmLopeta;
	private JMenuItem mntmSulje;
	private JMenuItem mntmEtsi;
	private JMenuItem mntmKorvaa;
	private JMenuItem mntmTietojaOhjelmasta;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JFileChooser fileChooser;
	// Muuttujaan tallennetaan nykyisen tiedoston sijainti pikatallennusta varten.
	private String fname;
	private JMenuItem mntmTulosta;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Kutsutaan tekstieditori-ikkuna
					Tekstieditori mainFrame = new Tekstieditori();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Luodaan tekstieditori ikkunan sisältö
	public Tekstieditori() {
		setTitle("Tekstieditori V3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 723);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);

		// Avaa napin toiminta riippuvalikossa
		mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.setIcon(new ImageIcon(Tekstieditori.class.getResource("/res/folder_test.png")));
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open(e);
			}
		});
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);

		// Tallenna napin toimnta riippuvalikossa
		mntmTallenna = new JMenuItem("Tallenna nimellä");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveFile(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnTiedosto.add(mntmTallenna);

		// Lopeta napin toiminta riippuvalikossa
		mntmLopeta = new JMenuItem("Lopeta ohjelma");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Tulosta napin toiminta riippuvalikossa
		mntmTulosta = new JMenuItem("Tulosta");
		mntmTulosta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dtrpnTesti.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnTiedosto.add(mntmTulosta);
		mnTiedosto.add(mntmLopeta);

		// Sulje napin toiminta riippuvalikossa
		mntmSulje = new JMenuItem("Sulje");
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Koodia
			}
		});
		mnTiedosto.add(mntmSulje);

		mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);

		// Etsi napin toiminta
		mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etsi(e);
			}
		});
		mnMuokkaa.add(mntmEtsi);

		mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				korvaa(e);
			}
		});
		mnMuokkaa.add(mntmKorvaa);

		mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);

		mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Kutsutaan tietoja ohjelmasta -ikkuna
					template frame = new template();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		toolBar = new JToolBar();
		toolBar.setToolTipText("");
		contentPane.add(toolBar, BorderLayout.NORTH);

		// Luodaan uusi tiedosto
		btnNewButton = new JButton("Uusi");
		btnNewButton.setIcon(new ImageIcon(Tekstieditori.class.getResource("/res/add_test.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "Create a new blank file?", "Choose option",
						JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					try {
						createFile("Tiedosto");
						dtrpnTesti.setText("I'm a new file!");
						fastSave(e);
					} catch (Exception p) {
						System.out.println("Error while saving file!");
						p.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(btnNewButton);

		// Pikatallennus
		btnNewButton_1 = new JButton("Pikatallennus");
		btnNewButton_1.setIcon(new ImageIcon(Tekstieditori.class.getResource("/res/save_test.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fastSave(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(btnNewButton_1);

		// Tekstieditorin Leikkaa -toiminto
		btnNewButton_2 = new JButton("Leikkaa");
		btnNewButton_2.setIcon(new ImageIcon(Tekstieditori.class.getResource("/res/cut_test.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtrpnTesti.selectAll();
				dtrpnTesti.cut();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		toolBar.add(btnNewButton_2);

		dtrpnTesti = new JEditorPane() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean getScrollableTracksViewportWidth() {
				return true;
			}
		};
		contentPane.add(dtrpnTesti);
		JScrollPane scroll = new JScrollPane(dtrpnTesti);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);

		// Tekstieditorikenttään muutama kuuntelija, jotka poistaa löydettyjen osumien
		// korostuksen kun tekstikenttään tehdään muutoksia
		dtrpnTesti.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				dtrpnTesti.getHighlighter().removeAllHighlights();
			}

			public void removeUpdate(DocumentEvent e) {
				dtrpnTesti.getHighlighter().removeAllHighlights();
			}

			public void insertUpdate(DocumentEvent e) {
				dtrpnTesti.getHighlighter().removeAllHighlights();
			}
		});
	}

	// Metodi avaa tiedoston
	public void open(ActionEvent e) {
		fileChooser = new JFileChooser();
		String rivi = "";
		Scanner lukija = null;
		int arvo = fileChooser.showOpenDialog((Component) e.getSource());
		if (arvo == JFileChooser.APPROVE_OPTION) {
			String uusiTiedosto = fileChooser.getSelectedFile().getAbsolutePath();
			File tiedosto = new File(uusiTiedosto);
			setTitle("Tekstieditori V3 (" + tiedosto.getName() + ")");
			fname = uusiTiedosto;
			try {
				lukija = new Scanner(tiedosto, "UTF-8");
				while (lukija.hasNextLine()) {
					rivi += lukija.nextLine() + "\n";
					dtrpnTesti.setText(rivi);
				}
			} catch (Exception p) {
				System.out.println("problem accessing file" + tiedosto.getAbsolutePath());
			}
		} else {
			System.out.println("File access cancelled by user.");
		}
	}
	
	// Metodi etsii tiedostosta
	public void etsi(ActionEvent e) {
		// Hakutoiminto pyörii käynnissä, kunnes osuma löytyy tai käyttäjä sulkee
		// hakutoiminnon.
		boolean haetaan = true;
		do {
			dtrpnTesti.getHighlighter().removeAllHighlights();
			Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
			JTextField search = new JTextField(10);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("Search:"));
			myPanel.add(search);
			int result = JOptionPane.showConfirmDialog(null, myPanel, "Search tool",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					String sisalto = dtrpnTesti.getText().toLowerCase();
					int indeksi = sisalto.indexOf(search.getText().toLowerCase());
					int pituus = search.getText().toLowerCase().length();
					if (search.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Type keyword!");
					} else {
						if (indeksi == -1) {
							JOptionPane.showMessageDialog(null, "No match.");
						}
						while (indeksi != -1) {
							// Korostetaan löydetyt osumat
							dtrpnTesti.getHighlighter().addHighlight(indeksi, indeksi + pituus, painter);
							indeksi = sisalto.indexOf(search.getText().toLowerCase(), indeksi + 1);
							haetaan = false;
						}
					}
				} catch (Exception p) {
					JOptionPane.showMessageDialog(null, "Virhe syötteessä!");
				}
			} else {
				haetaan = false;
			}
		} while (haetaan == true);
	}

	// Metodi korvaa tiedoston tekstiä 
	public void korvaa(ActionEvent e) {
		// Hakutoiminto pyörii käynnissä, kunnes osuma löytyy tai käyttäjä sulkee
		// hakutoiminnon.
		boolean haetaan = true;
		do {
			dtrpnTesti.getHighlighter().removeAllHighlights();
			Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
			JTextField search = new JTextField(10);
			JTextField replace = new JTextField(10);
			JPanel myPanel = new JPanel();
			JPanel confirm = new JPanel();
			confirm.add(new JLabel("Replace all occurrencies?"));
			myPanel.add(new JLabel("Search:"));
			myPanel.add(search);
			myPanel.add(new JLabel("Replace:"));
			myPanel.add(replace);
			int result = JOptionPane.showConfirmDialog(null, myPanel, "Replace tool",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					String sisalto = dtrpnTesti.getText().toLowerCase();
					int indeksi = sisalto.indexOf(search.getText().toLowerCase());
					int pituus = search.getText().toLowerCase().length();
					if (search.getText().length() == 0 || replace.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Type keyword!");
					} else {
						if (indeksi != -1) {
							while (indeksi != -1) {
								// Korostetaan löydetyt osumat
								dtrpnTesti.getHighlighter().addHighlight(indeksi, indeksi + pituus, painter);
								indeksi = sisalto.indexOf(search.getText().toLowerCase(), indeksi + 1);
								haetaan = false;
							}
							int res = JOptionPane.showConfirmDialog(null, confirm, "WARNING!",
									JOptionPane.YES_NO_OPTION);
							if (res == JOptionPane.YES_OPTION) {
								String muutettuRivi = sisalto.replaceAll(search.getText().toLowerCase(),
										replace.getText());
								dtrpnTesti.setText(muutettuRivi);
								sisalto = dtrpnTesti.getText().toLowerCase();
								indeksi = sisalto.indexOf(replace.getText().toLowerCase());
								pituus = replace.getText().toLowerCase().length();
								while (indeksi != -1) {
									// Korostetaan muutetut osumat
									dtrpnTesti.getHighlighter().addHighlight(indeksi, indeksi + pituus,
											painter);
									indeksi = sisalto.indexOf(replace.getText().toLowerCase(), indeksi + 1);
								}
								haetaan = false;
							} else {
								dtrpnTesti.getHighlighter().removeAllHighlights();
							}
						} else {
							JOptionPane.showMessageDialog(null, "No match.");
						}
					}
				} catch (Exception p) {
					JOptionPane.showMessageDialog(null, "Virhe syötteessä!");
				}
			} else {
				haetaan = false;
			}
		} while (haetaan == true);
	}
	
	// Luodaan uusi tiedosto
	public void createFile(String name) throws IOException {
		File f = new File(name + ".txt");
		if (!f.exists()) {
			name = name + ".txt";
			setTitle("Tekstieditori V3 (" + name + ")");
		} else {
			name = name + "_" + (int) (Math.random() * 100001) + ".txt";
			setTitle("Tekstieditori V3 (" + name + ")");
		}
		fname = name;
		PrintWriter writer = new PrintWriter(name);
		String sisalto = dtrpnTesti.getText();
		writer.println(sisalto);
		writer.flush();
		writer.close();
	}

	// Tallennetaan -metodi
	public void saveFile(ActionEvent e) throws IOException {
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save as");
		int arvo = fileChooser.showSaveDialog((Component) e.getSource());
		if (arvo == JFileChooser.APPROVE_OPTION) {
			String uusiTiedosto = fileChooser.getSelectedFile().getAbsolutePath();
			File tiedosto = new File(uusiTiedosto);
			if (tiedosto.getName().endsWith(".txt")) {
				setTitle("Tekstieditori V3 (" + tiedosto.getName() + ")");
			} else {
				setTitle("Tekstieditori V3 (" + tiedosto.getName() + ".txt)");
			}
			try {
				String fileName = uusiTiedosto.toString();
				if (!fileName.endsWith(".txt"))
					fileName += ".txt";
				fname = fileName;
				PrintWriter writer = new PrintWriter(fileName);
				String sisalto = dtrpnTesti.getText();
				writer.println(sisalto);
				writer.flush();
				writer.close();

			} catch (Exception p) {
				System.out.println("problem accessing file" + tiedosto.getAbsolutePath());
			}
		} else {
			System.out.println("File access cancelled by user.");
		}
	}

	// Pikatallennus
	public void fastSave(ActionEvent e) throws IOException {
		if (fname == null) {
			try {
				createFile("Tiedosto");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			File tiedosto = new File(fname);
			PrintWriter writer;
			try {
				writer = new PrintWriter(tiedosto);
				String sisalto = dtrpnTesti.getText();
				writer.println(sisalto);
				writer.flush();
				writer.close();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
}
