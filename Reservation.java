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
		try {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booked_flights","root","student");
		passenger = "";
		luggage = false;
		//confirmation = "";
		departureTime = "00:00:00";
		arrivalTime = "00:00:00";
		destination = "";
		}catch(Exception e){
		e.printStackTrace();
	}
	
	
		
		
		
	}
	
	private String passenger;
	private boolean luggage;
	//private String confirmation;
	private String departureTime;
	private String destination;
	private String arrivalTime;
	Connection myConn;
	

	
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


	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/*public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}*/

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


