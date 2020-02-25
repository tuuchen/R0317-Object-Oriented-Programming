import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Sarjallistamista {

	// Otetaan vastaan juoma-automaatti-olio ja kirjoitetaan se XML-muotoisena
	// tiedostoon
	
	// Alustetaan polku
	static String polku = "Automaatti.xml";
	
	public static void kirjoitaTiedostoon(JuomaAutomaatti ja) throws IOException {

		// XML:n kirjoittamista varten

		FileOutputStream tiedosto = new FileOutputStream(polku);
		XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(tiedosto));

		// Kirjoitetaan olio XML-muotoiseen tiedostoon
		enc.writeObject(ja);

		// Lopputoimet tiedostoille
		enc.close();
		tiedosto.close();
		JOptionPane.showMessageDialog(null,
				"Nykyinen juoma-automaatti tallennettu tiedostoon: " + polku);
	}

	public static JuomaAutomaatti lueTiedostosta() throws FileNotFoundException {

		XMLDecoder dec = null;
		FileInputStream tiedosto = new FileInputStream(polku);
		dec = new XMLDecoder(new BufferedInputStream(tiedosto));
		JuomaAutomaatti luettu = (JuomaAutomaatti) dec.readObject();

		// Lopputoimet
		dec.close();
		JOptionPane.showMessageDialog(null,
				"Juoma-automaatti ladattu tiedostosta: " + polku);
		// Palautetaan tiedostosta luettu automaatti
		return luettu;

	}

}