
public class Reservation {
	
	private String passenger;
	private boolean luggage;
	private String bkfastoption;
	private String destination;
	private String lunchoption;
	private String dinneroption;
	private String confirmationNum;
	private String departureTime;
	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	private String arrivalTime;

	

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getConfirmationNum() {
		return confirmationNum;
	}

	public void setConfirmationNum(String confirmationNum) {
		this.confirmationNum = confirmationNum;
	}

	public String getLunchoption() {
		return lunchoption;
	}

	public void setLunchoption(String lunchoption) {
		this.lunchoption = lunchoption;
	}

	public String getDinneroption() {
		return dinneroption;
	}

	public void setDinneroption(String dinneroption) {
		this.dinneroption = dinneroption;
	}

	public String getBkfastoption() {
		return bkfastoption;
	}

	public void setBkfastoption(String bkfastoption) {
		this.bkfastoption = bkfastoption;
	}

	public boolean getLuggage() {
		return luggage;
	}

	public void setLuggage(boolean luggage) {
		this.luggage = luggage;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
