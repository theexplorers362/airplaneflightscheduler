import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.xml.soap.Text;

import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class easternPanels {
	public easternPanels(){
		k = 0;
		j = 0;
		h = 0;
		z = 0;
		i = 0;
		f = 0;
		index = 0;
		v = 0;
		fcnumSeats = 24;
		cnumSeats = 30;
		numSeats = 132;
		totalReserved = 0;
		fcbuttons = null;
		cbuttons = null;
		buttons = null;
		fcnumOfRows = 6;
		fcnumOfColumns = 4;
		cnumOfRows = 5;
		cnumOfColumns = 6;
		numOfRows = 22;
		numOfColumns = 6;
		planeSize = 186;
		numPlans = 0;
		confirmationNum = "98765430"; 
		fcbuttons = new JToggleButton[fcnumOfRows][fcnumOfColumns];
		cbuttons = new JToggleButton[cnumOfRows][cnumOfColumns];
		buttons = new JToggleButton[numOfRows][numOfColumns];
		fclistener= new ItemListener[fcnumOfRows][fcnumOfColumns];
		clistener= new ItemListener[cnumOfRows][cnumOfColumns];
		listener = new ItemListener[numOfRows][numOfColumns];
		newReservation = new Reservation();
		bkmealsSelected = new String[planeSize];
		lunchmealsSelected = new String[planeSize];
		dinnermealsSelected = new String[planeSize];
		fcseatsReserved = new String[fcnumSeats];
		cseatsReserved = new String[cnumSeats];
		seatsReserved = new String[numSeats];
		seatsSelected = new String[planeSize];
		}
	private JPanel easternPanel;
	private int k,j;
	private int h,z;
	private int i,f;
	private int index;
	private int v;
	private int fcnumSeats;
	private int cnumSeats;
	private int numSeats;
	private String[] fcseatsReserved;
	private String[] cseatsReserved;
	private String[] seatsReserved;
	private int fcnumReserved;
	private int cnumReserved;
	private int numReserved;
	
	private int totalReserved;
	private String[] seatsSelected;
	
	private String[] bkmealsSelected;
	private String[] lunchmealsSelected;
	private String[] dinnermealsSelected;
	Reservation newReservation;
	private JButton seatButton;
	private int fcnumOfRows;
	private int fcnumOfColumns;
	private int cnumOfRows;
	private int cnumOfColumns;
	private int numOfRows;
	private int numOfColumns;
	private int planeSize;
	private String confirmationNum;
	private JToggleButton[][] fcbuttons;
	private JToggleButton[][] cbuttons;
	private JToggleButton[][] buttons;
	
	private ItemListener[][] fclistener;
	private ItemListener[][] clistener;
	private ItemListener[][] listener;
	private JRadioButton a;
	private JRadioButton b;
	private JRadioButton c;
	
	private ButtonGroup a_;
	private ButtonGroup b_;
	private ButtonGroup c_;
	private int numPlans;
	
	
	
	 public void getTimeSpans() //Still working on popping off
	 {
	     boolean firstTime = false, secondTime = false;
	     DateFormat format = new SimpleDateFormat("HH:mm:ss");
	     System.out.println(newReservation.getDepartureTime());
			Date time1 = null;
			Date time2 = null;
			System.out.println(time1);
	    /* if(newReservation.getDepartureTime() > "20:11:13" && newReservation.getArrivalTime() < "14:49:00")
	     {
	        firstTime = true;
	     }

	     if(time2 > "20:11:13" && time2 < "14:49:00")
	     {
	        secondTime = true;
	     }*/
	  }
	 
	public void addActionListenerHelperbkButtons(JRadioButton any, ButtonGroup group) {
		a_ = group;
	any.addActionListener(new ActionListener() { 
		public void actionPerformed(ActionEvent arg0) {
			try {
				a = (JRadioButton) arg0.getSource();
			
				bkmealsSelected[numPlans] = a.getName();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	}

	public void addActionListenerHelperLunchButtons(JRadioButton any, ButtonGroup group) {
		b_ = group;
		any.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				try {
					b = (JRadioButton) arg0.getSource();
					
					lunchmealsSelected[numPlans] = b.getName();
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
	});
	}
	
	public void addActionListenerHelperDinnerButtons(JRadioButton any, ButtonGroup group) {
		c_ = group;
		any.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				try {
					c = (JRadioButton) arg0.getSource();
					
					dinnermealsSelected[numPlans] = c.getName();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
	});
	}
	
	
	/*Makes the state of the toggle button enabled if pressed for the returned seat numbers in an array with the size
	 * toggles between color for premium seat color and grey to signify the need to be confirmed for reservation
	 * will show black if the seat has been claimed already
	 */
	public void stateofButtons() {
		for(k = 0; k < fcnumOfRows; k++) {
			for(j = 0; j < fcnumOfColumns; j++) {
				
		fcbuttons[k][j].addItemListener(fclistener[k][j] = new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
						JToggleButton tb = (JToggleButton) arg0.getItem();
						if(arg0.getStateChange() == ItemEvent.SELECTED) {
						fcseatsReserved[fcnumReserved] = tb.getText();
						numPlans++;
						fcnumReserved++;
						totalReserved++;
						a.setBackground(null); 
						b.setBackground(null);
						c.setBackground(null);
						a_.clearSelection();
						b_.clearSelection();
						c_.clearSelection();
						tb.setBackground(new Color(192,192,192));
						}
						else if(arg0.getStateChange() == ItemEvent.DESELECTED) {
							numPlans--;
							fcnumReserved--;
							totalReserved--;
							fcseatsReserved[fcnumReserved] = "";
							bkmealsSelected[numPlans] = "";
							lunchmealsSelected[numPlans] = "";
							dinnermealsSelected[numPlans] = "";
							tb.setBackground(new Color(147,112,219));
						}
						
					
			}
			
		});
		}
		}
		
		for(h = 0; h < cnumOfRows; h++) {
			for(z = 0; z < cnumOfColumns; z++) {
		
		cbuttons[h][z].addItemListener(clistener[h][z] = new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
						JToggleButton tb = (JToggleButton) arg0.getItem();
						if(arg0.getStateChange() == ItemEvent.SELECTED) {
						cseatsReserved[cnumReserved] = tb.getText();
						numPlans++;
						cnumReserved++;
						totalReserved++;
						a.setBackground(null); 
						b.setBackground(null);
						c.setBackground(null);
						a_.clearSelection();
						b_.clearSelection();
						c_.clearSelection();
						tb.setBackground(new Color(192,192,192));
						}
						else if(arg0.getStateChange() == ItemEvent.DESELECTED) {
							numPlans--;
							cnumReserved--;
							totalReserved--;
							cseatsReserved[cnumReserved] = "";
							bkmealsSelected[numPlans] = "";
							lunchmealsSelected[numPlans] = "";
							dinnermealsSelected[numPlans] = "";
							tb.setBackground(new Color(255,215,0));
						}

					
				}catch(Exception e){
					e.printStackTrace();
				}
			
			}

		});
		}
		}
		
		for(i = 0; i < numOfRows; i++) {
			for(f = 0; f < numOfColumns; f++) {
		
		buttons[i][f].addItemListener(listener[i][f] = new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
						JToggleButton tb = (JToggleButton) arg0.getItem();
					
						if(arg0.getStateChange() == ItemEvent.SELECTED) {
						seatsReserved[numReserved] = tb.getText();
						numPlans++;
						numReserved++;
						totalReserved++;
						a.setBackground(null); 
						b.setBackground(null);
						c.setBackground(null);
						a_.clearSelection();
						b_.clearSelection();
						c_.clearSelection();
						tb.setBackground(new Color(192,192,192));
						}
						else if(arg0.getStateChange() == ItemEvent.DESELECTED) {
							numPlans--;
							numReserved--;
							totalReserved--;
							seatsReserved[numReserved] = "";
							bkmealsSelected[numPlans] = "";
							lunchmealsSelected[numPlans] = "";
							dinnermealsSelected[numPlans] = "";
							tb.setBackground(new Color(205,127,50));
						}
									
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		});
		}
		} 
	}
	
	
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
		
		String[] usedNumbered = new String[usedConfirmation];
		
		while(myRs4.next()) {
			usedNumbered[usedNums] = myRs4.getString("confirmation");
			++usedNums;
		}

		for(int s = 0; s < usedNums; s++) {
			if(Integer.parseInt(usedNumbered[s]) >= generator) {
				generator++;
			}
			
		}
		confirmationNum = "" + generator;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	/*If a Confirmed Button is pressed then enter the fields and this will tell you about a pending entry field
	 * SEATS DESIRED
	 * BKFAST
	 * LUNCH
	 * DINNER
	 * CONFIRMATION NUMBER
	 */
	public void pressConfirm(JButton any) {
		any.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				String query2 ="insert into confirmedseats (seats, bkmealplan, lunchmealplan, dinnermealplan, confirmationNum) values(?,?,?,?,?)";
				PreparedStatement pat2=newReservation.myConn.prepareStatement (query2);
				for(int seatIter = 0; seatIter < fcnumReserved; seatIter++) {
					pat2.setString(1, fcseatsReserved[seatIter]);
					pat2.setString(2, bkmealsSelected[seatIter]);
					pat2.setString(3, lunchmealsSelected[seatIter]);
					pat2.setString(4, dinnermealsSelected[seatIter]);
					
					pat2.setString(5, confirmationNum);
					pat2.execute();
				} 
				for(int seatIter = 0; seatIter < cnumReserved; seatIter++) {
					pat2.setString(1, cseatsReserved[seatIter]);
					pat2.setString(2, bkmealsSelected[seatIter]);
					pat2.setString(3, lunchmealsSelected[seatIter]);
					pat2.setString(4, dinnermealsSelected[seatIter]);
					
					pat2.setString(5, confirmationNum);
					pat2.execute();
				}
				for(int seatIter = 0; seatIter < numReserved; seatIter++) {
					pat2.setString(1, seatsReserved[seatIter]);
					pat2.setString(2, bkmealsSelected[seatIter]);
					pat2.setString(3, lunchmealsSelected[seatIter]);
					pat2.setString(4, dinnermealsSelected[seatIter]);
					
					pat2.setString(5, confirmationNum);
					pat2.execute();
				}
					
				pat2.close();	
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	}
	
	/*Makes buttons black and responsive with GUI because the seat has already been claimed */
	
	public void seeiftaken() {
		String taken = "";
		for(int lmao = 0; lmao < index; lmao++) {
			taken = seatsSelected[lmao];
		
	for(int k_ = 0; k_ < fcnumOfRows; k_++) {
			
			for(int j_ = 0; j_ < fcnumOfColumns; j_++) {
				if(fcbuttons[k_][j_].getText().equals(taken)) {
					fcbuttons[k_][j_].setBackground(new Color(0,0,0));
					fcbuttons[k_][j_].removeItemListener(fclistener[k_][j_]);
					
					fcbuttons[k_][j_].addItemListener(fclistener[k_][j_] = new ItemListener() {
						public void itemStateChanged(ItemEvent arg0) {
							
									JToggleButton tb = (JToggleButton) arg0.getItem();
									if(arg0.getStateChange() == ItemEvent.SELECTED) {
									
									JOptionPane.showMessageDialog(null, "This seat is reserved! Please pick another seat!");
									}
						}
						
					});
				}
			
			}
	
	}
		
		
		for(int h_ = 0; h_ < cnumOfRows; h_++) {
			
			for(int z_ = 0; z_ < cnumOfColumns; z_++) {
				if(cbuttons[h_][z_].getText().equals(taken)) {
					cbuttons[h_][z_].setBackground(new Color(0,0,0));
					cbuttons[h_][z_].removeItemListener(clistener[h_][z_]);
					
					cbuttons[h_][z_].addItemListener(clistener[h_][z_] = new ItemListener() {
						public void itemStateChanged(ItemEvent arg0) {
							
									JToggleButton tb = (JToggleButton) arg0.getItem();
									if(arg0.getStateChange() == ItemEvent.SELECTED) {
									
									JOptionPane.showMessageDialog(null, "This seat is reserved! Please pick another seat!");
									}
						}
						
					});
				}
			
			}
		
		}
		
		
		for(int i_ = 0; i_ < numOfRows; i_++) {
			
			for(int f_ = 0; f_ < numOfColumns; f_++) {
				
				if(buttons[i_][f_].getText().equals(taken)) {
					buttons[i_][f_].setBackground(new Color(0,0,0));
					buttons[i_][f_].removeItemListener(listener[i_][f_]);
					
					buttons[i_][f_].addItemListener(listener[i_][f_] = new ItemListener() {
						public void itemStateChanged(ItemEvent arg0) {
							
									JToggleButton tb = (JToggleButton) arg0.getItem();
									if(arg0.getStateChange() == ItemEvent.SELECTED) {
									
									JOptionPane.showMessageDialog(null, "This seat is reserved! Please pick another seat!");
									}
						}
						
					});
				}
				
			}
			
		}
		}
		
	}
	/*Boundary Component of class easternPanels
	 *When reopened the mySQL JBMS will color in the selectedSeating from previous requests blacked out and responsive with "SEAT RESERVED" GUI
	 */

	public Component getButtonArea() {
		easternPanel = new JPanel();
		Rectangle bounds = easternPanel.getBounds();
		int x = 0;
		int y = 0;
		int buttonWidth = bounds.width / numOfRows;
		int buttonHeight = bounds.height / numOfColumns;
		char seatLetter = 'A';
		try {
		
		Statement myStat = newReservation.myConn.createStatement();

		ResultSet myRs = myStat.executeQuery("select * from confirmedSeats");
		
		while(myRs.next()) {
			seatsSelected[index] = myRs.getString("seats");
			index++;
		}
		}catch(Exception exc) {
		exc.printStackTrace();
		}
		
	
		for(k = 0; k < fcnumOfRows; k++) {
			
			for(j = 0; j < fcnumOfColumns; j++) {
				fcbuttons[k][j] = new JToggleButton((seatLetter + String.valueOf(v)).toString());
				
				fcbuttons[k][j].setBackground(new Color(147,112,219));
				
				if(v % 4 == 0 && v != 0) { 
					seatLetter++;
				}
				fcbuttons[k][j].setFont(new Font("Arial", Font.PLAIN, 5));
				fcbuttons[k][j].setPreferredSize(new Dimension (12,7));
				if(j % 2 == 0) {
				easternPanel.add(Box.createRigidArea(new Dimension(3, 0)));
				}
				if(j % 4 == 0) {
				easternPanel.add(Box.createRigidArea(new Dimension(315, 0)));
				}
				fcbuttons[k][j].setBounds(x ,y,buttonWidth, buttonHeight);
				easternPanel.add(fcbuttons[k][j], BorderLayout.EAST);
				y += buttonHeight;
				v++;
			}
			x += buttonWidth;
		}
		if(k == fcnumOfRows) {
			easternPanel.add(Box.createRigidArea(new Dimension(0,0)));
			}
		seatLetter = 'A';
		for(h = 0; h < cnumOfRows; h++) {
			
			for(z = 0; z < cnumOfColumns; z++) {
				cbuttons[h][z] = new JToggleButton((seatLetter + String.valueOf(v)).toString());
			
				cbuttons[h][z].setBackground(new Color(255,215,0));
				if(v % 6 == 0) { 
					seatLetter++;
				}
				cbuttons[h][z].setFont(new Font("Arial", Font.PLAIN, 5));
				cbuttons[h][z].setPreferredSize(new Dimension(11,10));
			    if(z % 3 == 0) {
				easternPanel.add(Box.createRigidArea(new Dimension(3, 0)));
				}
				if(z % 6 == 0) {
				easternPanel.add(Box.createRigidArea(new Dimension(315, 0)));
				}
				cbuttons[h][z].setBounds(x ,y,buttonWidth, buttonHeight);
				easternPanel.add(cbuttons[h][z], BorderLayout.EAST);
				y += buttonHeight;
				v++;
			}
			x += buttonWidth;
		}
		if(h == cnumOfRows) {
			easternPanel.add(Box.createRigidArea(new Dimension(0, 0)));
		}
		seatLetter = 'A';
		for(i = 0; i < numOfRows; i++) {
			
			for(f = 0; f < numOfColumns; f++) {
				buttons[i][f] = new JToggleButton((seatLetter + String.valueOf(v)).toString());
		
				buttons[i][f].setBackground(new Color(205,127,50));
				if(v % 6 == 0) { 
				seatLetter++;
				}
				buttons[i][f].setFont(new Font("Arial", Font.PLAIN, 5));
				buttons[i][f].setPreferredSize(new Dimension(10,10));
				if(f % 3 == 0) {
					easternPanel.add(Box.createRigidArea(new Dimension(7, 0)));
					}
				if(f % 6 == 0) {
					easternPanel.add(Box.createRigidArea(new Dimension(315, 0)));
				}
				buttons[i][f].setBounds(x ,y,buttonWidth, buttonHeight);
				easternPanel.add(buttons[i][f], BorderLayout.EAST);
				y += buttonHeight;
				v++;
			}
			x += buttonWidth;
		}
		if(i == numOfRows) {
			easternPanel.add(Box.createRigidArea(new Dimension(6, 0)));
		}
		
		return easternPanel;
	}
}
