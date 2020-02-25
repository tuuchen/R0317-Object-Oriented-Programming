public class Processor {

	// Encapsulate
	private String modelName;
	private String clockspeed;
	private String voltage;
	private String stepping;

	// Define default object values 
	public Processor() {
		modelName = "undefined";
		clockspeed = "undefined";
		voltage = "undefined";
		stepping = "undefined";
	}

	// Setter for new obejct
	public Processor(String modelName, String voltage, String clockspeed, String stepping) {
		this();
		this.modelName = modelName;
		this.voltage = voltage;
		this.clockspeed = clockspeed;
		this.stepping = stepping;
	}

	// Setters and getters 
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getClockspeed() {
		return clockspeed;
	}

	public void setClockspeed(String clockspeed) {
		this.clockspeed = clockspeed;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getStepping() {
		return stepping;
	}

	public void setStepping(String stepping) {
		this.stepping = stepping;
	}

	// toString to show object values 
	
	@Override
	public String toString() {
		return "Processor [modelName=" + modelName + ", clockspeed=" + clockspeed + ", voltage=" + voltage
				+ ", stepping=" + stepping + "]";
	}

}
