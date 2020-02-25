import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileConnection {

	// Functionality to save SQL-connection into XML-file, and fetch it
	
	// Define path
	static String polku = "SQLconnection.xml";
	
	public static void saveConnection(SQLconnection con) throws IOException {

		// For writing to XML
		FileOutputStream tiedosto = new FileOutputStream(polku);
		XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(tiedosto));
		enc.writeObject(con);

		// End tasks
		enc.close();
		tiedosto.close();
		// Optional
		/* JOptionPane.showMessageDialog(null,
				"Connection saved to file: " + polku); */
	}

	public static SQLconnection loadConnection() throws FileNotFoundException {

		// For reading from XML
		XMLDecoder dec = null;
		FileInputStream tiedosto = new FileInputStream(polku);
		dec = new XMLDecoder(new BufferedInputStream(tiedosto));
		SQLconnection luettu = (SQLconnection) dec.readObject();
		// End tasks
		dec.close();
		// Return connection-object from XML
		return luettu;

	}
}