import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class westernPanels{
	westernPanels(){
		operations = 0;
		pickedOperations = 0;
		arriveTimes = null;
		departureTimes = null;
		availableDestinations = null;
	}
	Time arriveTimes[];
	Time departureTimes[];
	String availableDestinations[];
	JPanel westernPanel;
	JLabel arrivalLabel;
	JLabel departureLabel;
	JLabel fromtoLabel;
	JLabel nameLabel;
	JButton confirmButton;
	private JTextField nameField;
	JLabel luggageLabel;
	JRadioButton luggageButton1;
	JRadioButton luggageButton2;
	JComboBox arrivals;
	JComboBox departures;
	JComboBox destinations;
	int operations;
	int pickedOperations;
	Reservation newReservation;
	private String confirmationNum;
	/*If a Confirmed Button is pressed then enter the fields
	 * 
	 * 
	 * 
	 */
	public void pressConfirm() {
		confirmButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			easternPanels east = new easternPanels();
			try {
				String query ="insert into reserved (passenger_name, destination, departureTime, arrivalTime, bkmealplan, lunchmealplan, dinnermealplan, confirmation, luggage) values(?,?,?,?,?,?,?,?,?)";
				String query2 ="insert into confirmedseats (seats, confirmationNum) values(?,?)";
				PreparedStatement pat=newReservation.myConn.prepareStatement (query);
				PreparedStatement pat2=newReservation.myConn.prepareStatement (query2);
				for(int seatIter = 0; seatIter <= east.fcnumReserved; seatIter++) {
					System.out.println(east.fcnumReserved);
					pat2.setString(1, east.fcseatsReserved[seatIter]); //Breaks on confirm right here
					confirmationNum = "9876543"+ seatIter;
					pat2.setString(2, confirmationNum);
					newReservation.setConfirmationNum_(confirmationNum);
					pat2.execute();
				} 
				/*for(seatIter = 0; seatIter<= cnumReserved; seatIter++) {
					System.out.println(cnumReserved);
					pat2.setString(1, cseatsReserved[seatIter]);
					confirmationNum = "9876543"+ seatIter;
					pat2.setString(2, confirmationNum);
					setConfirmationNum(confirmationNum);
					pat2.execute();
				}
				for(seatIter = 0; seatIter <= numReserved; seatIter++) {
					System.out.println(fcnumReserved);
					pat2.setString(1, seatsReserved[seatIter]);
					confirmationNum = "9876543"+ seatIter;
					pat2.setString(2, confirmationNum);
					setConfirmationNum(confirmationNum);
					pat2.execute();
				}*/
					
				pat.setString(1, newReservation.getPassenger());
				pat.setString(2, newReservation.getDestination());
				pat.setString(3, newReservation.getDepartureTime());
				pat.setString(4, newReservation.getArrivalTime());
				pat.setString(5, newReservation.getBkfastoption());
				pat.setString(6, newReservation.getLunchoption());
				pat.setString(7, newReservation.getDinneroption());
				pat.setString(8, newReservation.getConfirmationNum_());
				pat.setBoolean(9,newReservation.getLuggage());
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
	
	
	/*If a comboBox is pressed then we get the specific destinations available and ongoing flight on our JBMS server
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	private void addActionListenerHelperCombo(JComboBox any) {
		any.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			JComboBox cb = (JComboBox) arg0.getSource();
            // Print the selected items and the action command.
            
            try {
            	
            	Statement myStat = newReservation.myConn.createStatement();

            	ResultSet myRs = myStat.executeQuery("SELECT destination FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';" );
            
            	while(myRs.next()) {
            		++pickedOperations;
            	}	
            	
            	arriveTimes = new Time[pickedOperations];
    			departureTimes = new Time[pickedOperations];
            	
    			
    			//System.out.println("SELECT destination, goTime, helloTime FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';");
            	ResultSet myRs1 = myStat.executeQuery("SELECT destination, goTime, helloTime FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';");
            	
            	pickedOperations = 0;
            	
            	while(myRs1.next()) {
            		//availableDestinations[pickedOperations] = myRs1.getString("destination");
            		arriveTimes[pickedOperations] = myRs1.getTime("goTime");
            		System.out.println(myRs1.getTime("goTime"));
            		departureTimes[pickedOperations] = myRs1.getTime("helloTime");
            		System.out.println(myRs1.getTime("helloTime"));
            		++pickedOperations;
            	}
            	arrivals = new JComboBox(arriveTimes);
    			departures = new JComboBox(departureTimes);
            	westernPanel.add(departureLabel);
            	westernPanel.add(departures);
            	westernPanel.add(arrivalLabel);
            	westernPanel.add(arrivals);
            }
            
            /*String command = cb.getActionCommand();
            System.out.println("Action Command = " + command);

            // Detect whether the action command is "comboBoxEdited"
            // or "comboBoxChanged"
            if ("comboBoxEdited".equals(command)) {
                System.out.println("User has typed a string in " +
                        "the combo box.");
            } else if ("comboBoxChanged".equals(command)) {
                System.out.println("User has selected an item " +
                        "from the combo box.");
            }*/
			/*	try {
					newReservation.setArrivalTime(any.getSelectedItem().toString());
					//JOptionPane.showMessageDialog(null, "Data Saved");
				}*/
				catch(Exception exc) {
					exc.printStackTrace();
				}
					
					
				
            
            }
		});
	}
/* Controls looking in JBMS for airplane operation of ongoing flights
 * 
 * 
 * 
 * 
 */
	public void grabMetheStuff() {
		try {
			
			Statement myStat = newReservation.myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from airplane");
			
			while(myRs.next()) {
				++operations;
			}
			

			availableDestinations = new String[operations];
			
			ResultSet myRs1 = myStat.executeQuery("select * from airplane");
			
			operations = 0;
			
			while(myRs1.next()) {
				availableDestinations[operations] = myRs1.getString("destination");
				++operations;
			}
			destinations = new JComboBox(availableDestinations);
				
			}catch(Exception exc) {
			exc.printStackTrace();
			}

	}
	/*Controls finding Yes? or No? for luggage
	 * 
	 * 
	 * 
	 * 
	 */
	public void toggleDemButtons() {
		luggageButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					newReservation.setLuggage(true);
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
		luggageButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					newReservation.setLuggage(false);
					
			
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
	}
	
	
	/*Boundary Component of class westernPanels
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
public Component getTextArea() {
	newReservation = new Reservation();
	westernPanel = new JPanel();
	westernPanel.setLayout(new FlowLayout());
	nameLabel = new JLabel("Name: ");
	departureLabel = new JLabel("Departure: ");
	arrivalLabel = new JLabel("Arrival: ");
	fromtoLabel = new JLabel("LAX -> ");
	nameField = new JTextField(20);
	luggageLabel = new JLabel("Luggage?: ");
	luggageButton1 = new JRadioButton("Yes");
	luggageButton2 = new JRadioButton("No");
	confirmButton = new JButton("Confirm");
	grabMetheStuff();
	westernPanel.add(Box.createRigidArea(new Dimension(25, 0)));
	westernPanel.add(nameLabel);
	westernPanel.add(nameField);
	newReservation.setPassenger(nameField.getText());
	westernPanel.add(Box.createRigidArea(new Dimension(20, 0)));
	westernPanel.add(luggageLabel);
	ButtonGroup group = new ButtonGroup();
	group.add(luggageButton1);
	group.add(luggageButton2);
	westernPanel.add(luggageButton1);
	westernPanel.add(luggageButton2);
	westernPanel.add(Box.createRigidArea(new Dimension(20, 0)));
	westernPanel.add(fromtoLabel);
	westernPanel.add(destinations);
	westernPanel.add(Box.createRigidArea(new Dimension(800, 0)));
	addActionListenerHelperCombo(destinations);
	/*westernPanel.add(departureLabel);
	westernPanel.add(departures);
	westernPanel.add(arrivalLabel);
	westernPanel.add(arrivals);*/ 
	westernPanel.add(confirmButton);
	toggleDemButtons();
	pressConfirm();
	
	return westernPanel;
}
}
