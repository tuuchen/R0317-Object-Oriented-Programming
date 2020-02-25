import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// Contains all methods

public class Methods {

	// Static variables for use between methods
	static Connection con;

	// Method that opens connection and collects data from database
	public static Processor[] getData() {

		// Define array for data
		Processor[] details = null;
		// Define Statement and resultSet
		Statement statement = null;
		ResultSet rs;

		try {
			// Establish connection routes
			getConnection();
			// Query
			statement = con.createStatement();
			rs = statement.executeQuery("SELECT * FROM items");
			int i = 0;
			details = new Processor[100];
			// While there are results, output them to console and add to array
			while (rs.next()) {
				System.out.println(
						rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4));
				details[i] = new Processor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				i++;
			}
			// Catch errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Catch errors
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Finally block used to close resources
			try {
				if (statement != null)
					statement.close();
				// Catch errors
			} catch (SQLException e) {
				e.printStackTrace();
			} // Nothing we can do
			try {
				if (con != null)
					con.close();
				// Catch errors
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // End finally
		return details;
	}

	// Method for refreshing table
	public static void updateTable() throws SQLException {
		// Query data into array
		Processor[] details = getData();
		// Clear existing table
		clearTable();
		// Load new table with array
		loadTable(details);
	}

	// Method populates Jtable
	public static void loadTable(Processor[] list) {

		// Create table model
		DefaultTableModel model = (DefaultTableModel) TableGUI.table.getModel();
		// Loop data
		for (int row = 0; row < list.length; row++) {
			if (list[row] != null) {
				// Add data into table
				model.addRow(new Object[] { list[row].getModelName(), list[row].getClockspeed(),
						"" + list[row].getVoltage(), list[row].getStepping() });
			}
		}
	}

	// Method for adding new item to database
	public static void saveItem(Processor newItem) {

		try {
			// Establish connection route
			getConnection();
			// SQL query for adding items
			String sql = "INSERT INTO items (model, speed, voltage, stepping) values (?,?,?,?)";
			PreparedStatement value = con.prepareStatement(sql);
			// Set values from new Processor object passed as param
			value.setString(1, newItem.getModelName());
			value.setString(2, newItem.getClockspeed());
			value.setString(3, newItem.getVoltage());
			value.setString(4, newItem.getStepping());
			value.execute();
			// Close connection
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, try again: " + e);
			e.printStackTrace();
		}
	}

	// Method for creating new SQL-connection
	public static void saveSQLcon() {

		// Define components for data input
		JTextField url = new JTextField(15);
		JTextField database = new JTextField(15);
		JTextField userid = new JTextField(15);
		JTextField password = new JTextField(15);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Url:"));
		myPanel.add(url);
		myPanel.add(new JLabel("Database:"));
		myPanel.add(database);
		myPanel.add(new JLabel("Userid:"));
		myPanel.add(userid);
		myPanel.add(new JLabel("Password:"));
		myPanel.add(password);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer

		// Open dialog
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter new values: ", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {

			// Create new SQLconnection-object with pre-defined data
			SQLconnection SQLcon = new SQLconnection();

			try {

				// If all required data is not given, do nothing and prompt message
				if (url.getText().equals("") || database.getText().equals("") || userid.getText().equals("")) {

					String error = "Missing values: ";

					if (url.getText().equals("")) {
						error += " [URL]";
					}
					if (database.getText().equals("")) {
						error += " [Database]";
					}
					if (userid.getText().equals("")) {
						error += " [Userid]";
					}
					JOptionPane.showMessageDialog(null, error, "Could not connect!", JOptionPane.ERROR_MESSAGE);
				} else {

					// Set new values and pass object to save connection method, show user saved
					// values, and
					// update table
					SQLcon.setURL(url.getText());
					SQLcon.setDB(database.getText());
					SQLcon.setUSERID(userid.getText());
					SQLcon.setPASSWORD(password.getText());

					FileConnection.saveConnection(SQLcon);
					JOptionPane.showMessageDialog(null, "Connection saved to file: " + FileConnection.polku + "\n"
							+ "Connection settings: " + SQLcon);
					try {
						updateTable();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// Method that chooses connection between connection file and pre-defined
	public static void getConnection() {

		// Define SQLconnection objects
		SQLconnection fromFile = null;
		SQLconnection SQLcon = new SQLconnection();

		// If connection-file.xml exists, use it as a primary source of connection
		File f = new File("SQLconnection.xml");
		if (f.exists()) {
			try {
				// Load values for connection object
				fromFile = FileConnection.loadConnection();
				conPaths(fromFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// Else use default values set in SQLconnection.java
			conPaths(SQLcon);
		}
	}

	// Method that defines connection values and catches connection errors
	public static void conPaths(SQLconnection obj) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + obj.getURL() + "/" + obj.getDB(), obj.getUSERID(),
					obj.getPASSWORD());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Error connecting database. Please specify connection and try again: " + e);
		}
	}

	public static void connectionInfo() {

		// Define SQLconnection objects
		SQLconnection fromFile = null;
		SQLconnection SQLcon = new SQLconnection();

		// If connection-file.xml exists, use it as a primary source of connection
		File f = new File("SQLconnection.xml");
		if (f.exists()) {
			try {
				fromFile = FileConnection.loadConnection();
				JOptionPane.showMessageDialog(null, "Using SQLconnection.xml values: \n" + fromFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Using predefined connection values: \n" + SQLcon);
		}
	}

	// Method for clearing JTable
	public static void clearTable() {

		TableGUI.model = (DefaultTableModel) TableGUI.table.getModel();
		TableGUI.model.getDataVector().removeAllElements();
		TableGUI.table.revalidate();
	}

	// Method for search-GUI functionality and search values
	public static void search() {

		// Define components and inputs
		JPanel search = new JPanel();
		JLabel first = new JLabel("Model: ");
		JTextField firstField = new JTextField(5);
		search.add(first);
		search.add(firstField);

		JLabel second = new JLabel("Voltage: ");
		JTextField secondField = new JTextField(5);
		search.add(second);
		search.add(secondField);

		JLabel third = new JLabel("Speed: ");
		JTextField thirdField = new JTextField(5);
		search.add(third);
		search.add(thirdField);

		JLabel fourth = new JLabel("Stepping: ");
		JTextField fourthField = new JTextField(5);
		search.add(fourth);
		search.add(fourthField);

		// Open dialog
		int result = JOptionPane.showConfirmDialog(null, search, "Query", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			// SQL query with search-params
			String SQL = "SELECT * FROM items WHERE model LIKE '" + firstField.getText() + "' OR voltage LIKE '"
					+ secondField.getText() + "' OR speed LIKE '" + thirdField.getText() + "' OR stepping LIKE '"
					+ fourthField.getText() + "'";

			// Passed as a param into searchDB-method
			searchDB(SQL);

		}
	}

	// Method for delivering search params into actual db-search
	public static void searchDB(String SQL) {

		try {

			// Define response
			String res = "";
			// Establish connection route
			getConnection();
			// Create statement and resultset with search params
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);

			// While loop for collecting results
			while (rs.next()) {
				res += ("Model: " + rs.getString(1) + ", Voltage: " + rs.getString(3) + ", Speed: " + rs.getString(2)
						+ ", Stepping: " + rs.getString(4) + "\n");
			}

			// Show dialog with search results
			rs.last();
			JOptionPane.showMessageDialog(TableGUI.contentPane,
					"Total results: " + rs.getRow() + " rows.\n\n" + res + "\n");

			// Close connection
			con.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error:" + e);
		}
	}

	// Method for adding item, takes params as values passed to new Processor object
	public static void addProcessor(String newModel, String newClockSpeed, String newVoltage, String newStepping)
			throws SQLException {
		// Create new object with values passed as param
		Processor newRow = new Processor(newModel, newClockSpeed, newVoltage, newStepping);
		// Logging new item to console
		System.out.println(newRow);
		// Pass new object into saveItem method for adding object into DB
		saveItem(newRow);
		// Add new row with newly defined object
		addRow(newRow);
		// Update table
		updateTable();
		// When all done!
		JOptionPane.showMessageDialog(null, "Done!");
	}

	// Method for adding row while adding item
	public static void addRow(Processor newItem) {
		// Define table model
		DefaultTableModel model = (DefaultTableModel) TableGUI.table.getModel();
		// Add values passed as param
		model.addRow(new Object[] { newItem.getModelName(), newItem.getClockspeed(), newItem.getVoltage(),
				newItem.getStepping() });
	}

	// Method for deleting data from SQL-db
	public static void deleteRow(JTable table) {

		// Specify rows
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int[] rows = table.getSelectedRows();
		// Condition for showing waring message
		boolean msg = true;
		int result = 0;
		// Loop rows
		for (int i = 0; i < rows.length; i++) {
			// Get value from selected rows
			String selected = model.getValueAt(rows[i] - i, 0).toString();
			// Check if row is selected (has value)
			if (!selected.equals("")) {
				// Condition for warning message + show message
				if (msg) {
					result = JOptionPane.showConfirmDialog(null, "This will delete data!", "Confirm?",
							JOptionPane.OK_CANCEL_OPTION);
				}
				if (result == JOptionPane.OK_OPTION) {
					// Hide warning message for deleting multiple rows
					msg = false;
					// Delete rows from GUI
					model.removeRow(rows[i] - i);
					// Open connection, and delete rows from DB
					try {
						getConnection();
						PreparedStatement ps = con
								.prepareStatement("delete from items where model='" + selected + "' ");
						ps.executeUpdate();
						// Close connection
						con.close();
					} catch (Exception w) {
						// Show error dialog, if something goes wonky
						w.printStackTrace();
						JOptionPane.showMessageDialog(table, "Connection Error!" + w);
					}
				} else {
					msg = false;
				}
			}
		}
	}
}
