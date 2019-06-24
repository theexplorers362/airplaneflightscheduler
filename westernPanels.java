import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class westernPanels{
	westernPanels(){
		confirmation = "98765430";
		availableDestinations = null;
		newReservation = new Reservation();
	}
	private String availableDestinations[];
	private JPanel westernPanel;
	private JLabel arrivalLabel;
	private JLabel departureLabel;
	private JLabel fromtoLabel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel luggageLabel;
	private JRadioButton luggageButton1;
	private JRadioButton luggageButton2;
	private JComboBox destinations;
	private String confirmation;
    private Reservation newReservation;
	private ItemListener destinationListener;
	private ItemListener departureListener;
	private ItemListener arrivalListener;
	private JComboBox departures;
	private JComboBox arrivals;
	JButton confirmButton;
	
	
	/*Generate a new confirmation number based on the numbers already generated in the JBMS*/
	public void generateConfirmationNum() {
		try {
		int gener = 0;
		int usedConfirmation = 0;
		int usedNums = 0;
		int generator = 98765430 + gener;
		Statement myStat = newReservation.myConn.createStatement();

		ResultSet myRs3 = myStat.executeQuery("select * from reserved");
		
		while(myRs3.next()) {
			++usedConfirmation;
		}
		
		
		ResultSet myRs4 = myStat.executeQuery("select * from reserved");
		
		String[] usedNumbers = new String[usedConfirmation];
		
		while(myRs4.next()) {
			usedNumbers[usedNums] = myRs4.getString("confirmation");
			++usedNums;
		}

		for(int s = 0; s < usedNums; s++) {
			if(Integer.parseInt(usedNumbers[s]) >= generator) {
				generator++;
				confirmation = "" + generator;
			}
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	/*If a Confirmed Button is pressed then enter the fields and this will tell you about a pending entry field
	 * PASSENGER NAME
	 * DESTINATION
	 * DEPARTURE TIME 
	 * ARRIVAL TIME
	 * CONFIRMATION NUMBER
	 * LUGGAGE OPTION
	 */
	public void pressConfirm() {
		confirmButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				if(!nameField.getText().equals(""))
				newReservation.setPassenger(nameField.getText().toString());
				else if(nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill in Name");
					return;
				}
			
				String query ="insert into reserved (passenger_name, destination, departureTime, arrivalTime, confirmation, luggage) values(?,?,?,?,?,?)";
				PreparedStatement pat=newReservation.myConn.prepareStatement (query);
					
				pat.setString(1, newReservation.getPassenger());
				
				if(newReservation.getDestination() != "")
				pat.setString(2, newReservation.getDestination());
				else if(newReservation.getDestination() == "") {
					JOptionPane.showMessageDialog(null, "Please Place a Destination");
					return;
				}
				if(newReservation.getDepartureTime() != null)
				pat.setString(3, newReservation.getDepartureTime());
				else if (newReservation.getDepartureTime() == null) {
					JOptionPane.showMessageDialog(null, "Please Schedule a Departure Time");
					return;
				}
				
				if(newReservation.getArrivalTime() != null)
				pat.setString(4, newReservation.getArrivalTime());
				else if (newReservation.getArrivalTime() == null) {
					JOptionPane.showMessageDialog(null, "Please Schedule a Arrival Time");
					return;
				}
				pat.setString(5, confirmation);
				pat.setBoolean(6,newReservation.getLuggage());
				pat.execute();
				
				JOptionPane.showMessageDialog(null, "Data Saved");
				
				pat.close();
			
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	}
	
	
	
	
	/*Enable a listener on the departures menu that picks the referenced arrival time for that flight*/
	public void addItemListenerHelperComboGO() {
		departures.addItemListener(departureListener = new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			JComboBox cb = (JComboBox) arg0.getSource();
			if(arg0.getStateChange() == ItemEvent.SELECTED) {
				
			newReservation.setDepartureTime(cb.getSelectedItem().toString());
		    
            try {
            	Statement myStat = newReservation.myConn.createStatement();
            
          		ResultSet myRs1 = myStat.executeQuery("SELECT helloTime FROM airplane WHERE goTime = \'" + cb.getSelectedItem().toString() + "\';");
            	
            	while(myRs1.next()) {
            		newReservation.setArrivalTime(((myRs1.getTime("helloTime"))).toString()); //See if this works
            	}
            	arrivals.setSelectedIndex(cb.getSelectedIndex());
        		westernPanel.revalidate();
            	westernPanel.repaint();
            	
            }catch(Exception exc) {
					exc.printStackTrace();
				}
			}
            }    
		});
	}
	
	/*Disable item listener after the we refresh the click on destinations*/
	public void disableListenerHelperComboGO() {
		departures.removeItemListener(departureListener);
	}
	
	/*Enable a listener on the arrivals menu that picks the referenced departure time for that flight*/
	public void addItemListenerHelperComboHELLO() {
		arrivals.addItemListener(arrivalListener = new ItemListener() {
			public void itemStateChanged (ItemEvent arg0) {
				JComboBox cb = (JComboBox) arg0.getSource();
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
				newReservation.setArrivalTime(cb.getSelectedItem().toString());
				
	            try {
	            	Statement myStat = newReservation.myConn.createStatement();   	
	            	
	    			
	    			ResultSet myRs1 = myStat.executeQuery("SELECT goTime FROM airplane WHERE helloTime = \'" + cb.getSelectedItem().toString() + "\';");
	            	 
	            	
	            	while(myRs1.next()) {
	            		newReservation.setDepartureTime((myRs1.getTime("goTime")).toString()); //See if this works
	            	}
	               	departures.setSelectedIndex(cb.getSelectedIndex());
	                
            		westernPanel.revalidate();
	            	westernPanel.repaint();
	            	
	            	
	            }catch(Exception exc) {
						exc.printStackTrace();
					}
				}
				   
			}
            });
	}
	
	
	/*Disable item listener after the we refresh the click on destinations*/
	public void disableListenerHelperComboHELLO() {
		arrivals.removeItemListener(arrivalListener);
	}
	
	
	/*Item Listener for Destinations box and will display departure/arrival/confirmation button on interaction*/
	public void addItemListenerHelperComboBennie() {
		destinations.addItemListener(destinationListener = new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			JComboBox cb = (JComboBox) arg0.getSource();
			if(arg0.getStateChange() == ItemEvent.SELECTED) {
			int pickedOperations = 0;
            
            try {
            	Statement myStat = newReservation.myConn.createStatement();
            	newReservation.setDestination(cb.getSelectedItem().toString());
            	ResultSet myRs = myStat.executeQuery("SELECT destination FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';" );
            
            	while(myRs.next()) {
            		++pickedOperations;
            	}	
            	
            	
            	Time arriveTimes[] = new Time[pickedOperations];
            	Time departureTimes[] = new Time[pickedOperations];	
    			
    			ResultSet myRs1 = myStat.executeQuery("SELECT destination, goTime, helloTime FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';");
            	
            	int operations = 0;
            		 
            	
            	while(myRs1.next()) {
            		departureTimes[operations] = myRs1.getTime("goTime");
            		arriveTimes[operations] = myRs1.getTime("helloTime");
            		++operations;
            	}
            	departures = new JComboBox(departureTimes);
            	arrivals = new JComboBox(arriveTimes);
            	departures.setFont(new Font("Arial", Font.PLAIN, 11));
            	arrivals.setFont(new Font("Arial", Font.PLAIN, 11));
            	
            	
            	disableListenerHelperComboBennie();
            	
            	
            
            }catch(Exception exc) {
					exc.printStackTrace();
				}
			}
            }
		});
	}
	
	
	/* Controls looking in JBMS for airplane operation of ongoing flights*/
	public void grabMetheOperations() {
		try {
			int pickedOperations = 0;
			Statement myStat = newReservation.myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from airplane");
			
			while(myRs.next()) {
				++pickedOperations;
			}
			

			availableDestinations = new String[pickedOperations];
			
			ResultSet myRs1 = myStat.executeQuery("select * from airplane");
			
			int operations = 0;
			
			while(myRs1.next()) {
				availableDestinations[operations] = myRs1.getString("destination");
				++operations;
			}
			destinations = new JComboBox(availableDestinations);
			destinations.setFont(new Font("Arial", Font.PLAIN, 11));
        	destinations.setSelectedIndex(-1);
			}catch(Exception exc) {
			exc.printStackTrace();
			}

	}
	
	
	/*Stop displaying Departures/Arrivals/Confirm buttons after first interaction*/
	public void disableListenerHelperComboBennie() {
		westernPanel.add(Box.createRigidArea(new Dimension(23, 0)));
		westernPanel.add(departureLabel);
		westernPanel.add(departures);
		westernPanel.add(Box.createRigidArea(new Dimension(35, 0)));
		westernPanel.add(arrivalLabel);
        westernPanel.add(arrivals);
        westernPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        westernPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        westernPanel.add(confirmButton);
        
    	departures.setSelectedIndex(-1);
    	arrivals.setSelectedIndex(-1);
    	westernPanel.revalidate();
    	westernPanel.repaint();
    	addItemListenerHelperComboGO();
    	addItemListenerHelperComboHELLO();
		destinations.removeItemListener(destinationListener);
			destinations.addItemListener(destinationListener = new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
				JComboBox cb = (JComboBox) arg0.getSource();
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					
				disableListenerHelperComboGO();
				disableListenerHelperComboHELLO();
				
				int pickedOperations = 0;
	            
	            try {
	            	Statement myStat = newReservation.myConn.createStatement();
	            	newReservation.setDestination(cb.getSelectedItem().toString());
	            	ResultSet myRs = myStat.executeQuery("SELECT destination FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';" );
	            
	            	while(myRs.next()) {
	            		++pickedOperations;
	            	}	
	            	
	            	Time[] arriveTimes = new Time[pickedOperations];
	            	Time[] departureTimes = new Time[pickedOperations];	
	    			
	    	    	ResultSet myRs1 = myStat.executeQuery("SELECT destination, goTime, helloTime FROM airplane WHERE destination = \'" + cb.getSelectedItem().toString() + "\';");
	            	
	            	
	            	int operations = 0;
	            	while(myRs1.next()) {
	            		departureTimes[operations] = myRs1.getTime("goTime");
	            		arriveTimes[operations] = myRs1.getTime("helloTime");
	            		++operations;
	            	}
	            	departures.removeAllItems();
	            	arrivals.removeAllItems();
	            	westernPanel.revalidate();
	            	westernPanel.repaint();
	            	departures.setModel(new DefaultComboBoxModel(departureTimes));
	            	arrivals.setModel(new DefaultComboBoxModel(arriveTimes));
	            	departures.setSelectedIndex(-1);
	            	arrivals.setSelectedIndex(-1);
	            	westernPanel.revalidate();
	            	westernPanel.repaint();
	        		
	            	addItemListenerHelperComboGO();
	            	addItemListenerHelperComboHELLO();
	      
	    		}catch(Exception exc) {
						exc.printStackTrace();
					}
				}
				
						
					
	            
	            }
			});
		}
	
	
	/*Toggles controls finding Yes? or No? for luggage*/
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
	
	
/*Boundary Component of class westernPanels */
public Component getTextArea() {
	newReservation = new Reservation();
	westernPanel = new JPanel();
	
	westernPanel.setLayout(new FlowLayout());
	nameLabel = new JLabel("Name: ");
	nameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	departureLabel = new JLabel("Departure: ");
	departureLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	arrivalLabel = new JLabel("Arrival: ");
	arrivalLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	fromtoLabel = new JLabel("LAX -> ");
	fromtoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	nameField = new JTextField("", 31);
	nameField.setFont(new Font("Arial", Font.PLAIN, 11));
	luggageLabel = new JLabel("Luggage?: ");
	luggageLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	luggageButton1 = new JRadioButton("Yes");
	luggageButton1.setFont(new Font("Arial", Font.PLAIN, 11));
	luggageButton2 = new JRadioButton("No");
	luggageButton2.setFont(new Font("Arial", Font.PLAIN, 11));
	confirmButton = new JButton("Confirm");
	confirmButton.setFont(new Font("Arial", Font.PLAIN, 11));
	
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
	
	return westernPanel;
}
}
