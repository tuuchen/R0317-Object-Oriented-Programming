import java.awt.Color;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class JuomaAutomaatti {

	// Alustetaan muuttujat
	private int tee;
	private int kahvi;
	private int kaakao;

	// Konstruktorit
	public JuomaAutomaatti() {
		tee = 50;
		kahvi = 50;
		kaakao = 50;
	}

	public JuomaAutomaatti(int tee, int kahvi, int kaakao) {
		this();
		this.tee = tee;
		this.kahvi = kahvi;
		this.kaakao = kaakao;
	}

	// Getterit
	public int getKahvi() {
		return kahvi;
	}

	public int getTee() {
		return tee;
	}

	public int getKaakao() {
		return kaakao;
	}

	// Setterit
	public void setTee(int tee) {
		this.tee = tee;
	}

	public void setKahvi(int kahvi) {
		this.kahvi = kahvi;
	}

	public void setKaakao(int kaakao) {
		this.kaakao = kaakao;
	}

	// Metodi, joka lisää 25% mahdollisuuden epäonnistumiselle
	public boolean onnistuuko() {

		int numero = (int) (Math.random() * 100 + 1);
		if (numero >= 25) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null,
					"Ohjelmassa tapahtui virhe. Juomaa ei anneta. Kiitos kuitenkin rahoista!");
			return false;
		}
	}

	// Valmistetaan juomaa
	public void valmistaJuoma(String juoma) {

		GUI_Automaatti.Info.setText("Odota hetki, juomaa valmistetaan..");

		switch (juoma) {

		case "Kahvi":
			// Jos sailiossa on tavaraa, vahennetan 10 ja suoritetaan
			if (this.kahvi >= 10 && onnistuuko()) {
				this.kahvi -= 10;
				GUI_Automaatti.lblKahvia.setText("Kahvia: " + getKahvi());
			} else if (this.kahvi < 10) {
				GUI_Automaatti.Info.setText(juoma + " on loppunut! Täytä säiliö.");
			}
			break;

		case "Tee":
			if (this.tee >= 10 && onnistuuko()) {
				this.tee -= 10;
				GUI_Automaatti.lblTeeta.setText("Teetä: " + getTee());
			} else if (this.tee < 10) {
				GUI_Automaatti.Info.setText(juoma + " on loppunut! Täytä säiliö.");
			}
			break;

		case "Kaakao":
			if (this.kaakao >= 10 && onnistuuko()) {
				this.kaakao -= 10;
				GUI_Automaatti.lblKaakaota.setText("Kaakaota: " + getKaakao());
			} else if (this.kaakao < 10) {
				GUI_Automaatti.Info.setText(juoma + " on loppunut! Täytä säiliö.");
			}
			break;
		}
		setColor();
	}

	// Täyttömetodi
	public void fill(String toiminto) {

		// Täyttömäärä
		int luku = 0;
		// Ehto täytön jatkamiseksi
		boolean jatketaan = true;

		if (toiminto != "Pikatäyttö") {
			// Kysytään täyttömäärää
			String input = JOptionPane.showInputDialog(null, "Anna uusi arvo (max 100): ");
			// Tarkistetaan syöte
			if (input != null) {
				try {
					luku = Integer.parseInt(input);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Virhe syötteessä!");
					jatketaan = !jatketaan;
				}
				// Tarkistetaan täyttömäärä
				if (luku > 100) {
					luku = 100;
				} else if (luku < 0) {
					luku = 0;
				}
				// Jos syötettä ei anneta tai perutaan, täyttöä ei jatketa
			} else {
				jatketaan = !jatketaan;
			}
		}

		// Täyttötoiminto
		if (jatketaan) {

			GUI_Automaatti.Info.setText("");

			switch (toiminto) {

			case "Kahvi":
				setKahvi(luku);
				GUI_Automaatti.lblKahvia.setText("Kahvia: " + getKahvi());
				break;

			case "Tee":
				setTee(luku);
				GUI_Automaatti.lblTeeta.setText("Teetä: " + getTee());
				break;

			case "Kaakao":
				setKaakao(luku);
				GUI_Automaatti.lblKaakaota.setText("Kaakaota: " + getKaakao());
				break;

			case "Pikatäyttö":
				setKahvi(100);
				setTee(100);
				setKaakao(100);
				GUI_Automaatti.lblKahvia.setText("Kahvia: " + getKahvi());
				GUI_Automaatti.lblTeeta.setText("Teetä: " + getTee());
				GUI_Automaatti.lblKaakaota.setText("Kaakaota: " + getKaakao());
				break;
			}

			setColor();
		}
	}

	// Ladataan automaatti tiedostosta
	public void reload() {

		try {

			JuomaAutomaatti uusi = Sarjallistamista.lueTiedostosta();

			setKahvi(uusi.getKahvi());
			setTee(uusi.getTee());
			setKaakao(uusi.getKaakao());

			GUI_Automaatti.lblKahvia.setText("Kahvia: " + getKahvi());
			GUI_Automaatti.lblTeeta.setText("Teetä: " + getKahvi());
			GUI_Automaatti.lblKaakaota.setText("Kaakaota: " + getKahvi());

			setColor();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// Metodi värjäilee tekstiä säiliön tilavuuden mukaan
	public void setColor() {
		if (getKahvi() > 20) {
			GUI_Automaatti.lblKahvia.setForeground(Color.BLACK);
		} else {
			GUI_Automaatti.lblKahvia.setForeground(Color.RED);
		}

		if (getTee() > 20) {
			GUI_Automaatti.lblTeeta.setForeground(Color.BLACK);
		} else {
			GUI_Automaatti.lblTeeta.setForeground(Color.RED);
		}

		if (getKaakao() > 20) {
			GUI_Automaatti.lblKaakaota.setForeground(Color.BLACK);
		} else {
			GUI_Automaatti.lblKaakaota.setForeground(Color.RED);
		}
	}

	// toString
	@Override
	public String toString() {
		return "Kahvia jaljella: " + kahvi + " ml, teeta jaljella: " + tee + " ml, kaakaota jaljella: " + kaakao
				+ " ml.\n";
	}
}
