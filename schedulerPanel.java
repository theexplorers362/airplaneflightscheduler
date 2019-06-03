import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import java.sql.*;

public class schedulerPanel extends JPanel {
Reservation newReservation = new Reservation();
JLabel arrivalLabel;
JLabel departureLabel;
JLabel fromtoLabel;
JPanel northernPanel;
JPanel westernPanel;
JPanel buttonsPanel;
JPanel easternPanel;
JLabel bkFastLabel;
JLabel lunchLabel;
JLabel dinnerLabel;
JLabel nameLabel;
JTextField nameField;
JLabel luggageLabel;
JRadioButton luggageButton1;
JRadioButton luggageButton2;
JButton bkButton1;
JButton bkButton2;
JButton bkButton3;
JButton bkButton4;
JButton lunchButton1;
JButton lunchButton2;
JButton lunchButton3;
JButton lunchButton4;
JButton dinnerButton1;
JButton dinnerButton2;
JButton dinnerButton3;
JButton dinnerButton4;
JButton seatButton;
JButton confirmButton;
JComboBox arrivals;
JComboBox departures;
JComboBox destinations;
Connection myConn;
String[] seatsSelected;
String[] seatsReserved;
int index = 0;
int v = 0;
String confirmationNum;
public schedulerPanel() {
	setLayout(new BorderLayout());
	//this.add(getHeader(), BorderLayout.NORTH);
	this.add(getTextArea(), BorderLayout.WEST);
	this.add(getOptionsArea(),BorderLayout.CENTER);
	this.add(getButtonArea(), FlowLayout.RIGHT);
	confirmButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booked_flights","root","student");
				String query ="insert into reserved (passenger_name, destination, departureTime, arrivalTime, bkmealplan, lunchmealplan, dinnermealplan, confirmation, luggage) values(?,?,?,?,?,?,?,?,?)";
				String query2 ="insert into confirmedseats (seats, confirmationNum) values(?,?)";
				PreparedStatement pat=myConn.prepareStatement (query);
				PreparedStatement pat2=myConn.prepareStatement (query2);
				for(int i = 0; i <= v; i++) {
					pat2.setString(1, seatsReserved[v]);
					if(i % v == 0)
					confirmationNum = "9876543"+ v;
					pat2.setString(2, confirmationNum);
					newReservation.setConfirmationNum(confirmationNum);
					}
					pat2.execute();
				newReservation.setPassenger(nameField.getText());
				pat.setString(1, newReservation.getPassenger());
				pat.setString(2, null);
				pat.setString(3, newReservation.getDepartureTime());
				pat.setString(4, newReservation.getArrivalTime());
				pat.setString(5, newReservation.getBkfastoption());
				pat.setString(6, newReservation.getLunchoption());
				pat.setString(7, newReservation.getDinneroption());
				pat.setString(8, newReservation.getConfirmationNum());
				pat.setBoolean(9, newReservation.getLuggage());
				pat.execute();
				
				JOptionPane.showMessageDialog(null, "Data Saved");
				
				pat.close();
				
				//confirmationNumber
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
}

protected JComponent getHeader() {
return null;
}

private Component getTextArea() {
	westernPanel = new JPanel();
	westernPanel.setLayout(new FlowLayout());
	nameLabel = new JLabel("Name: ");
	departureLabel = new JLabel("Departure: ");
	arrivalLabel = new JLabel("Arrival: ");
	fromtoLabel = new JLabel("LAX -> NARNIA");
	nameField = new JTextField(20);
	luggageLabel = new JLabel("Luggage?: ");
	luggageButton1 = new JRadioButton("Yes");
	luggageButton2 = new JRadioButton("No");
	confirmButton = new JButton("Confirm");
	Time arriveTimes[] = null;
	Time departureTimes[] = null;
	String availableDestinations[] = null;
	int i = 0;
	try {
	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booked_flights","root","student");
	
	Statement myStat = myConn.createStatement();

	ResultSet myRs = myStat.executeQuery("select * from airplane");
	
	while(myRs.next()) {
		++i;
	}
	arriveTimes = new Time[i];
	departureTimes = new Time[i];
	while(myRs.next()) {
		arriveTimes[i] = myRs.getTime("goTime");
		departureTimes[i] = myRs.getTime("byeTime");
		availableDestinations[i] = myRs.getString("destination");

		++i;
	}
	
	}catch(Exception exc) {
	exc.printStackTrace();
	}
	arrivals = new JComboBox(arriveTimes);
	//arrivals.setSelectedIndex(i);
	departures = new JComboBox(departureTimes);
	//departures.setSelectedIndex(i);
	//destinations = new JComboBox(availableDestinations);
	//availableDestinations.setSelectedIndex(i);
	arrivals.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				newReservation.setArrivalTime(arrivals.getSelectedItem().toString());
				try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booked_flights","root","student");
				
				Statement myStat = myConn.createStatement();
			
				ResultSet myRs = myStat.executeQuery("select * from airplane");
				
				while(myRs.next()) {
					newReservation.setArrivalTime(myRs.getString("byeTime"));
				}
			}catch(Exception exc) {
				exc.printStackTrace();
			}
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	/*destinations.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				newReservation.setArrivalTime(destinations.getSelectedItem().toString());
				try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booked_flights","root","student");
				
				Statement myStat = myConn.createStatement();
			
				ResultSet myRs = myStat.executeQuery("select * from airplane");
				
				while(myRs.next()) {
					newReservation.setArrivalTime(myRs.getString("byeTime"));
				}
			}catch(Exception exc) {
				exc.printStackTrace();
			}
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});*/
	/*departures = new JComboBox(departureTimes);
	departures.setSelectedIndex(i);
	departures.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setLuggage(true);
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});*/
	westernPanel.add(Box.createRigidArea(new Dimension(30, 0)));
	westernPanel.add(nameLabel);
	westernPanel.add(nameField);
	westernPanel.add(luggageLabel);
	ButtonGroup group = new ButtonGroup();
	group.add(luggageButton1);
	group.add(luggageButton2);
	westernPanel.add(luggageButton1);
	westernPanel.add(luggageButton2);
	westernPanel.add(Box.createRigidArea(new Dimension(73, 0)));
	westernPanel.add(fromtoLabel);
	westernPanel.add(Box.createRigidArea(new Dimension(800, 0)));
	westernPanel.add(departureLabel);
	westernPanel.add(departures);
	westernPanel.add(arrivalLabel);
	westernPanel.add(arrivals);
	westernPanel.add(confirmButton);
	luggageButton1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setLuggage(true);
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	luggageButton2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				newReservation.setLuggage(false);
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
		
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	
	return westernPanel;
}


private Component getOptionsArea() {
	buttonsPanel = new JPanel();
	buttonsPanel.setLayout(new FlowLayout());
	bkFastLabel = new JLabel("BreakFast: ");
	bkButton1 = new JButton("Omelette");
	bkButton2 = new JButton("Waffles");
	bkButton3 = new JButton("Coffee Cake");
	bkButton4 = new JButton("Acai Bowl");
	lunchLabel = new JLabel("Lunch: ");
	lunchButton1 = new JButton("BLT Sandwich");
	lunchButton2 = new JButton("Pizza");
	lunchButton3 = new JButton("Pasta Alfredo");
	lunchButton4 = new JButton("Soup");
	dinnerLabel = new JLabel("Dinner: ");
	dinnerButton1 = new JButton("Steak");
	dinnerButton2 = new JButton("Lobster");
	dinnerButton3 = new JButton("Shrimp Cocktail");
	dinnerButton4 = new JButton("Caesar Salad");
	buttonsPanel.add(bkFastLabel);
	ButtonGroup bkgroup = new ButtonGroup();
	bkgroup.add(bkButton1);
	bkgroup.add(bkButton2);
	bkgroup.add(bkButton3);
	bkgroup.add(bkButton4);
	bkButton1.addActionListener(new ActionListener() {//..add tasks later
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setBkfastoption(bkButton1.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	bkButton2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setBkfastoption(bkButton2.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	bkButton3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setBkfastoption(bkButton3.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	bkButton4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setBkfastoption(bkButton4.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	
	buttonsPanel.add(bkButton1, BorderLayout.CENTER);
	buttonsPanel.add(bkButton2, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(93, 0)));
	buttonsPanel.add(bkButton3, BorderLayout.CENTER);
	buttonsPanel.add(bkButton4, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
	buttonsPanel.add(lunchLabel);
	ButtonGroup lunchgroup = new ButtonGroup();
	lunchgroup.add(lunchButton1);
	lunchgroup.add(lunchButton2);
	lunchgroup.add(lunchButton3);
	lunchgroup.add(lunchButton4);
	lunchButton1.addActionListener(new ActionListener() { //..add tasks later
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setLunchoption(lunchButton1.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	lunchButton2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setLunchoption(lunchButton2.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	lunchButton3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setLunchoption(lunchButton3.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	lunchButton4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setLunchoption(lunchButton4.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	
	buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
	buttonsPanel.add(lunchButton1, BorderLayout.CENTER);
	buttonsPanel.add(lunchButton2, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(82, 0)));
	buttonsPanel.add(lunchButton3, BorderLayout.CENTER);
	buttonsPanel.add(lunchButton4, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(50, 0)));
	buttonsPanel.add(dinnerLabel);
	ButtonGroup dinnergroup = new ButtonGroup();
	dinnergroup.add(dinnerButton1);
	dinnergroup.add(dinnerButton2);
	dinnergroup.add(dinnerButton3);
	dinnergroup.add(dinnerButton4);
	dinnerButton1.addActionListener(new ActionListener() { //..add tasks later
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setDinneroption(dinnerButton1.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	dinnerButton2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setDinneroption(dinnerButton2.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	dinnerButton3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setDinneroption(dinnerButton3.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	dinnerButton4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			
				newReservation.setDinneroption(dinnerButton4.getText());
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	buttonsPanel.add(Box.createRigidArea(new Dimension(25, 0)));
	buttonsPanel.add(dinnerButton1, BorderLayout.CENTER);
	buttonsPanel.add(dinnerButton2, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(75, 0)));
	buttonsPanel.add(dinnerButton3, BorderLayout.CENTER);
	buttonsPanel.add(dinnerButton4, BorderLayout.CENTER);
	return buttonsPanel;
}


private Component getButtonArea() {
	easternPanel = new JPanel();
	Rectangle bounds = easternPanel.getBounds();
	int fcnumOfRows = 6;
	int fcnumOfColumns = 4;
	int cnumOfRows = 5;
	int cnumOfColumns = 6;
	int numOfRows = 22;
	int numOfColumns = 6;
	int planeSize = 186;
	seatsSelected = new String[planeSize];
	int x = 0;
	int y = 0;
	int buttonWidth = bounds.width / numOfRows;
	int buttonHeight = bounds.height / numOfColumns;
	int k = 0;
	int h = 0;
	int i = 0;
	char seatLetter = 'A';
	
	try {
	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booked_flights","root","student");
	
	Statement myStat = myConn.createStatement();

	ResultSet myRs = myStat.executeQuery("select * from confirmedSeats");
	
	while(myRs.next()) {
		seatsSelected[index] = myRs.getString("seats");
		index++;
	}
	}catch(Exception exc) {
	exc.printStackTrace();
	}
	for(k = 0; k < fcnumOfRows; k++) {
		
		for(int j = 0; j < fcnumOfColumns; j++) {
			
			seatButton = new JButton(seatLetter + String.valueOf(k));
			if(seatLetter + String.valueOf(k) == seatsSelected[v]);
			seatButton.setBackground(new Color(192,192,192));
			
			seatButton.setBackground(new Color(128,0,128));
			
			if(j % 4 == 0) { 
				seatLetter++;
			}
			seatButton.setFont(new Font("Arial", Font.PLAIN, 5));
			seatButton.setPreferredSize(new Dimension (12,7));
			if(j % 2 == 0) {
			easternPanel.add(Box.createRigidArea(new Dimension(3, 0)));
			}
			if(j % 4 == 0) {
			easternPanel.add(Box.createRigidArea(new Dimension(315, 0)));
			}
			seatButton.setBounds(x ,y,buttonWidth, buttonHeight);
			easternPanel.add(seatButton, BorderLayout.EAST);
			seatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						seatsReserved[v] = seatButton.getText();
						//JOptionPane.showMessageDialog(null, "Data Saved");
						
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			});
			y += buttonHeight;
		}
		x += buttonWidth;
		v++;
	}
	if(k == fcnumOfRows) {
		easternPanel.add(Box.createRigidArea(new Dimension(0,15)));
		}
	for(h = 0; h < cnumOfRows; h++) {
		
		for(int z = 0; z < cnumOfColumns; z++) {
			seatButton = new JButton(seatLetter + String.valueOf(z));
			if(seatLetter + String.valueOf(z) == seatsSelected[v]);
			seatButton.setBackground(new Color(192,192,192));
			
			seatButton.setBackground(new Color(255,215,0));
			if(z % 6 == 0) { 
				seatLetter++;
			}
			seatButton.setFont(new Font("Arial", Font.PLAIN, 5));
			seatButton.setPreferredSize(new Dimension(11,10));
		    if(z % 3 == 0) {
			easternPanel.add(Box.createRigidArea(new Dimension(3, 0)));
			}
			if(z % 6 == 0) {
			easternPanel.add(Box.createRigidArea(new Dimension(315, 0)));
			}
			seatButton.setBounds(x ,y,buttonWidth, buttonHeight);
			easternPanel.add(seatButton, BorderLayout.EAST);
			seatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						seatsReserved[v] = seatButton.getText();
						//JOptionPane.showMessageDialog(null, "Data Saved");
						
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				
			});
			y += buttonHeight;
		}
		x += buttonWidth;
		v++;
	}
	if(h == cnumOfRows) {
		easternPanel.add(Box.createRigidArea(new Dimension(0, 15)));
	}
	for(i = 0; i < numOfRows; i++) {
		
		for(int f = 0; f < numOfColumns; f++) {
			seatButton = new JButton(seatLetter + String.valueOf(i));
			if(seatLetter + String.valueOf(k) == seatsSelected[i]);
			seatButton.setBackground(new Color(192,192,192));
			
			seatButton.setBackground(new Color(205,127,50));
			if(f % 6 == 0) { 
			seatLetter++;
			}
			seatButton.setFont(new Font("Arial", Font.PLAIN, 5));
			seatButton.setPreferredSize(new Dimension(10,10));
			if(f % 3 == 0) {
				easternPanel.add(Box.createRigidArea(new Dimension(7, 0)));
				}
			if(f % 6 == 0) {
				easternPanel.add(Box.createRigidArea(new Dimension(315, 0)));
			}
			seatButton.setBounds(x ,y,buttonWidth, buttonHeight);
			easternPanel.add(seatButton, BorderLayout.EAST);
			seatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						seatsReserved[v] = seatButton.getText();
						//JOptionPane.showMessageDialog(null, "Data Saved");
						
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				
			});
			y += buttonHeight;
		}
		x += buttonWidth;
		v++;
	}
	if(i == numOfRows) {
		easternPanel.add(Box.createRigidArea(new Dimension(6, 0)));
	}
	return easternPanel;
}



}