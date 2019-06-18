import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Reservation {
	public Reservation() {
		/*fcnumReserved_ = 0;
		cnumReserved_ = 0;
		numReserved_ = 0;
		fcseatsReserved_ = null;
		cseatsReserved_ = null;
		seatsReserved_ = null;*/
		try {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booked_flights","root","student");
		}catch(Exception e){
		e.printStackTrace();
	}
	
	
		
		
		
	}
	private String passenger;
	private boolean luggage;
	private String bkfastoption;
	private String lunchoption;
	private String dinneroption;
	private String confirmationNum_;
	private String departureTime;
	private String destination;
	Connection myConn;
	String[] fcseatsReserved_;
	String[] cseatsReserved_;
	String[] seatsReserved_;
	int fcnumReserved_;
	int cnumReserved_;
	int numReserved_;
	
	/*public void setfcnumReserved(int fcnumReserved) {
	this.fcnumReserved_ = fcnumReserved;	
	}
	
	
	public void setfcseatsReserved(String[] fcseatsReserved) {
		this.fcseatsReserved_ = fcseatsReserved;
	}*/
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

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

	public String getConfirmationNum_() {
		return confirmationNum_;
	}

	public void setConfirmationNum_(String confirmationNum_) {
		this.confirmationNum_ = confirmationNum_;
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
	

}


