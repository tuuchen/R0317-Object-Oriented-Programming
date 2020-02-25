
public class SQLconnection {

	// Encapsulate
	private String URL;
	private String DATABASE;
	private String USERID;
	private String PASSWORD;

	// Define default object values used for connection
	public SQLconnection() {
		URL = "";
		DATABASE = "";
		USERID = "";
		PASSWORD = "";
	}

	// Setters and getters

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getDB() {
		return DATABASE;
	}

	public void setDB(String DB) {
		this.DATABASE = DB;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String USERID) {
		this.USERID = USERID;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}

	// toString to show object values

	@Override
	public String toString() {

		// Hide password from GUI user
		String pswrd;
		if (PASSWORD.length() > 0) {
			pswrd = ", PASSWORD=" + PASSWORD.replaceAll("(?s).", "*");
		} else {
			pswrd = ", No password set";
		}
		return "SQLconnection [URL=" + URL + ", DATABASE=" + DATABASE + ", USERID=" + USERID + pswrd + "]";
	}
}
