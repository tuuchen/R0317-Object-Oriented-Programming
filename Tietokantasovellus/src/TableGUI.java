import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// GUI for data-table

public class TableGUI extends JFrame {

	// Row-naming
	String[] rows = { "Model", "Voltage", "Clock speed", "Stepping" };
	Object[][] data = {};

	// Static components
	static JTable table;
	static DefaultTableModel model;
	static JPanel contentPane;

	// Table-window
	public TableGUI(Processor[] list) {

		// GUI Components and styling
		table = new JTable();
		setTitle("Intel Processor chart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 405);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Settings menu
		JMenu settings_menu = new JMenu("Settings");
		menuBar.add(settings_menu);
		JMenuItem settings_connection = new JMenuItem("Setup connection");
		settings_connection.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		settings_connection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Methods.saveSQLcon();
			}
		});
		settings_menu.add(settings_connection);

		// Exit-button
		JMenuItem settings_exit = new JMenuItem("Exit");
		settings_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		settings_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		settings_menu.add(settings_exit);

		// Info menu
		JMenu about_menu = new JMenu("Info");
		menuBar.add(about_menu);
		// About information
		JMenuItem version_menu = new JMenuItem("About");
		version_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		version_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInternalMessageDialog(null,
						"Version 1.0.\n© Tuukka Tihekari, Laurea UAS 2019\nDatabase made with [Remote MySQL]", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JMenuItem connection_menu = new JMenuItem("Current connection");
		connection_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Methods.connectionInfo();
			}
		});
		connection_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		about_menu.add(connection_menu);

		about_menu.add(version_menu);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		// Added JScrollPane to table
		JScrollPane scrollPane = new JScrollPane(table);
		// Deselect rows when clicking scrollPane area
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.getSelectionModel().clearSelection();
			}
		});
		contentPane.add(scrollPane);

		// Set row-naming
		table.setModel(new DefaultTableModel(data, rows));

		// If SQL data passed here as param is not empty, load table with param values
		if (list != null) {
			Methods.loadTable(list);
		}

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		// Add-button to open another GUI for adding items
		JButton add_button = new JButton("Add");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProcessorGUI.openGUI();
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add_button.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(add_button);
		// Remove-button
		JButton remove_button = new JButton("Remove");
		remove_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Methods.deleteRow(table);
			}
		});
		remove_button.setToolTipText("");
		panel.add(remove_button);
		// Refresh-button
		JButton update_button = new JButton("Refresh");
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Methods.updateTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// Search-button
		JButton search_button = new JButton("Search");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Methods.search();
			}
		});
		panel.add(search_button);
		panel.add(update_button);
	}
}
