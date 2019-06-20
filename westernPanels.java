import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class westernPanels{
	westernPanels(){
		availableDestinations = null;
		newReservation = new Reservation();
	}
	Time arriveTimes[];
	Time departureTimes[];
	String availableDestinations[];
	JPanel westernPanel;
	JLabel arrivalLabel;
	JLabel departureLabel;
	JLabel fromtoLabel;
	JLabel nameLabel;
	JTextField nameField;
	JLabel luggageLabel;
	JRadioButton luggageButton1;
	JRadioButton luggageButton2;
	JComboBox destinations;
	JButton confirmButton;
	String confirmation;
    Reservation newReservation;
	JComboBox arrivals;
	JComboBox departures;
	boolean window = false;
	/*If a Confirmed Button is pressed then enter the fields
	 * 
	 * 
	 * 
	 */
	public void pressConfirm() {
		confirmButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				int  gener = 0;
				confirmation = "9876543" + gener;
				newReservation.setPassenger(nameField.getText());
				String query ="insert into reserved (passenger_name, destination, departureTime, arrivalTime, confirmation, luggage) values(?,?,?,?,?,?)";
				PreparedStatement pat=newReservation.myConn.prepareStatement (query);
				pat.setString(1, newReservation.getPassenger());
				pat.setString(2, newReservation.getDestination());
				pat.setString(3, newReservation.getDepartureTime());
				pat.setString(4, newReservation.getArrivalTime());
				pat.setString(5, confirmation);
				pat.setBoolean(6,newReservation.getLuggage());
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
	
	public void addActionListenerHelperComboGO(JComboBox GO) {
		GO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			JComboBox cb = (JComboBox) arg0.getSource();
			
			newReservation.setDepartureTime(cb.getSelectedItem().toString());
            }
		});
	}
	
	public void addActionListenerHelperComboHELLO(JComboBox HELLO) {
		arrivals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			JComboBox cb = (JComboBox) arg0.getSource();
			// Print the selected items and the action command.
            
           
            newReservation.setArrivalTime(cb.getSelectedItem().toString());
			}	
            });
	}
	
	public void addActionListenerHelperComboBennie() {
		destinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			JComboBox cb = (JComboBox) arg0.getSource();
			int pickedOperations = 0;
            // Print the selected items and the action command.
            
            try {
            	Statement myStat = newReservation.myConn.createStatement();
            	newReservation.setDestination(cb.getSelectedItem().toString());
            	ResultSet myRs = myStat.executeQuery("SELECT destination FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';" );
            
            	while(myRs.next()) {
            		++pickedOperations;
            	}	
            	
            	
            	Time arriveTimes[] = new Time[pickedOperations];
            	Time departureTimes[] = new Time[pickedOperations];	
    			
    			//System.out.println("SELECT destination, goTime, helloTime FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';");
            	ResultSet myRs1 = myStat.executeQuery("SELECT destination, goTime, helloTime FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';");
            	
            	pickedOperations = 0;
            	
            	while(myRs1.next()) {
            		//availableDestinations[pickedOperations] = myRs1.getString("destination");
            		arriveTimes[pickedOperations] = myRs1.getTime("goTime");
            		//System.out.println(myRs1.getTime("goTime"));
            		departureTimes[pickedOperations] = myRs1.getTime("helloTime");
            		//System.out.println(myRs1.getTime("helloTime"));
            		++pickedOperations;
            	}
            	departures = new JComboBox(departureTimes);
            	arrivals = new JComboBox(arriveTimes);
            	
                westernPanel.add(departures); //Still figuring out how to change the times
                westernPanel.add(arrivals);
                addActionListenerHelperComboGO(departures);
                addActionListenerHelperComboHELLO(arrivals);
               /*  departures.revalidate();
            	departures.repaint();
            	arrivals.revalidate();
            	arrivals.repaint();*/
    			westernPanel.revalidate();
            	westernPanel.repaint();
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
	public void grabMetheOperations() {
		try {
			int operations = 0;
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
	public void togglethemButtons() {
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
	grabMetheOperations();
	westernPanel.add(Box.createRigidArea(new Dimension(25, 0)));
	westernPanel.add(nameLabel);
	westernPanel.add(nameField);
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
	/*westernPanel.add(departureLabel);
	westernPanel.add(departures);
	westernPanel.add(arrivalLabel);
	westernPanel.add(arrivals);*/ 
	westernPanel.add(confirmButton);
	
	return westernPanel;
}
}
