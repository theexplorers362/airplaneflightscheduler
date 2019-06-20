import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.mysql.cj.util.StringUtils;

import java.sql.*;

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
		confirmationNum = ""; 
		newReservation = new Reservation();
		bkmealsSelected = new String[planeSize];
		lunchmealsSelected = new String[planeSize];
		dinnermealsSelected = new String[planeSize];
		fcseatsReserved = new String[fcnumSeats];
		cseatsReserved = new String[cnumSeats];
		seatsReserved = new String[numSeats];
		seatsSelected = new String[planeSize];
		}
	JPanel easternPanel;
	int k,j;
	int h,z;
	int i,f;
	int index;
	int v;
	int fcnumSeats;
	int cnumSeats;
	int numSeats;
	String[] fcseatsReserved;
	String[] cseatsReserved;
	String[] seatsReserved;
	int fcnumReserved;
	int cnumReserved;
	int numReserved;
	
	int totalReserved;
	String[] seatsSelected;
	
	String[] bkmealsSelected;
	String[] lunchmealsSelected;
	String[] dinnermealsSelected;
	Reservation newReservation;
	JButton seatButton;
	
	int fcnumOfRows;
	int fcnumOfColumns;
	int cnumOfRows;
	int cnumOfColumns;
	int numOfRows;
	int numOfColumns;
	int planeSize;
	
	String confirmationNum;
	JToggleButton[][] fcbuttons;
	JToggleButton[][] cbuttons;
	JToggleButton[][] buttons;
	JRadioButton a;
	JRadioButton b;
	JRadioButton c;
	
	ButtonGroup a_;
	ButtonGroup b_;
	ButtonGroup c_;
	seat[] passengerPlan;
	int numPlans;
	/*Makes the state of the toggle button enabled if pressed for the returned seat numbers in an array with the size
	 * toggles between color for premium seat color and grey to signify the need to be confirmed for reservation
	 * 
	 * 
	 * 
	 * 
	 */
	public void addActionListenerHelperbkButtons(JRadioButton any, ButtonGroup group) {
		a_ = group;
	any.addActionListener(new ActionListener() { 
		public void actionPerformed(ActionEvent arg0) {
			try {
				boolean  open = false;
				if(open == false) {
				a = (JRadioButton) arg0.getSource();
			
				bkmealsSelected[numPlans] = a.getText();
				//a.setBackground(new Color(54,81,94));
				open = true;
				}
				
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
					boolean  open = false;
					if(open == false) {
					b = (JRadioButton) arg0.getSource();
					
					lunchmealsSelected[numPlans] = b.getText();
					//b.setBackground(new Color(54,81,94));
					open = true;
					}
					
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
					boolean  open = false;
					if(open == false) {
					c = (JRadioButton) arg0.getSource();
					
					dinnermealsSelected[numPlans] = c.getText();
					
					//c.setBackground(new Color(54,81,94));
					open = true;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
	});
	}
	public void stateofButtons() {
		for(k = 0; k < fcnumOfRows; k++) {
			for(j = 0; j < fcnumOfColumns; j++) {
				
		fcbuttons[k][j].addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
						JToggleButton tb = (JToggleButton) arg0.getItem();
						if(arg0.getStateChange() == ItemEvent.SELECTED) {
						fcseatsReserved[fcnumReserved] = tb.getText();
						numPlans++;
						fcnumReserved++;
						totalReserved++;
						/*a.setBackground(null); 
						b.setBackground(null);
						c.setBackground(null);*/
						a_.clearSelection();
						b_.clearSelection();
						c_.clearSelection();
						tb.setBackground(new Color(192,192,192));
						//JOptionPane.showMessageDialog(null, "Data Saved");*/
						}
						else if(arg0.getStateChange() == ItemEvent.DESELECTED) {
							numPlans--;
							fcnumReserved--;
							totalReserved--;
							fcseatsReserved[fcnumReserved] = "";
							bkmealsSelected[numPlans] = "";
							lunchmealsSelected[numPlans] = "";
							dinnermealsSelected[numPlans] = "";
							tb.setBackground(new Color(128,0,128));
						}
						
					
					//JOptionPane.showMessageDialog(null, "Data Saved");
			}
			
		});
		}
		}
		
		for(h = 0; h < cnumOfRows; h++) {
			for(z = 0; z < cnumOfColumns; z++) {
		
		cbuttons[h][z].addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
						JToggleButton tb = (JToggleButton) arg0.getItem();
						if(arg0.getStateChange() == ItemEvent.SELECTED) {
						//System.out.println(tb.getText());
						cseatsReserved[cnumReserved] = tb.getText();
						numPlans++;
						cnumReserved++;
						totalReserved++;
						/*a.setBackground(null); 
						b.setBackground(null);
						c.setBackground(null);*/
						a_.clearSelection();
						b_.clearSelection();
						c_.clearSelection();
						tb.setBackground(new Color(192,192,192));
						//JOptionPane.showMessageDialog(null, "Data Saved");
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

					//JOptionPane.showMessageDialog(null, "Data Saved");
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
			
			}

		});
		}
		}
		
		for(i = 0; i < numOfRows; i++) {
			for(f = 0; f < numOfColumns; f++) {
		
		buttons[i][f].addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
						JToggleButton tb = (JToggleButton) arg0.getItem();
					
						if(arg0.getStateChange() == ItemEvent.SELECTED) {
						seatsReserved[numReserved] = tb.getText();
						//System.out.println(tb.getText());
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
						//JOptionPane.showMessageDialog(null, "Data Saved");
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
						
						
					//JOptionPane.showMessageDialog(null, "Data Saved");
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		});
		}
		} 
	}
	
	public void pressConfirm(JButton any) {
		any.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				int  gener = 0;
				String query2 ="insert into confirmedseats (seats, bkmealplan, lunchmealplan, dinnermealplan, confirmationNum) values(?,?,?,?,?)";
				PreparedStatement pat2=newReservation.myConn.prepareStatement (query2);
				for(int seatIter = 0; seatIter < fcnumReserved; seatIter++) {
					pat2.setString(1, fcseatsReserved[seatIter]);
					pat2.setString(2, bkmealsSelected[seatIter]);
					pat2.setString(3, lunchmealsSelected[seatIter]);
					pat2.setString(4, dinnermealsSelected[seatIter]);
					
					confirmationNum = "9876543" + gener; // still needs to be changed to more viable confirmation option
					pat2.setString(5, confirmationNum);
					pat2.execute();
				} 
				for(int seatIter = 0; seatIter < cnumReserved; seatIter++) {
					pat2.setString(1, cseatsReserved[seatIter]);
					pat2.setString(2, bkmealsSelected[seatIter]);
					pat2.setString(3, lunchmealsSelected[seatIter]);
					pat2.setString(4, dinnermealsSelected[seatIter]);
					
					confirmationNum = "9876543" + gener; // still needs to be changed to more viable confirmation option
					pat2.setString(5, confirmationNum);
					pat2.execute();
				}
				for(int seatIter = 0; seatIter < numReserved; seatIter++) {
					pat2.setString(1, seatsReserved[seatIter]);
					pat2.setString(2, bkmealsSelected[seatIter]);
					pat2.setString(3, lunchmealsSelected[seatIter]);
					pat2.setString(4, dinnermealsSelected[seatIter]);
					
					confirmationNum = "9876543" + gener; // still needs to be changed to more viable confirmation option
					pat2.setString(5, confirmationNum);
					pat2.execute();
				}
				
				
				//JOptionPane.showMessageDialog(null, "Data Saved");
				
				pat2.close();
				
				//confirmationNumber
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	}
	
	public void seeiftaken(JToggleButton tglb) {
		int q = 0;
		while(q < index) {
			System.out.println("\"" + tglb.getText()+ "\"");
		if(tglb.getText() == seatsSelected[q]) {
			System.out.println("\"" + seatsSelected[q] + "\"");
			tglb.setBackground(new Color(0,0,0));
		}
		
		q++;
		}
	}
	/*Boundary Component of class easternPanels
	 *When reopened the mySQL JBMS will color in the selectedSeating from previous requests blacked out and non-responsive.
	 * 
	 * 
	 * 
	 * 
	 */

	public Component getButtonArea() {
		easternPanel = new JPanel();
		Rectangle bounds = easternPanel.getBounds();
		int x = 0;
		int y = 0;
		int buttonWidth = bounds.width / numOfRows;
		int buttonHeight = bounds.height / numOfColumns;
		char seatLetter = 'A';
		fcbuttons = new JToggleButton[fcnumOfRows][fcnumOfColumns];
		cbuttons = new JToggleButton[cnumOfRows][cnumOfColumns];
		buttons = new JToggleButton[numOfRows][numOfColumns];
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
				fcbuttons[k][j] = new JToggleButton(seatLetter + String.valueOf(v));
				//System.out.println(fcbuttons[k][j].getText());
					
				fcbuttons[k][j].setBackground(new Color(128,0,128));
				
				
				if(v % 4 == 0) { 
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
				cbuttons[h][z] = new JToggleButton(seatLetter + String.valueOf(v));
				
				if(cbuttons[h][z].getText() == seatsSelected[v])
					cbuttons[h][z].setBackground(new Color(0,0,0));
				cbuttons[h][z].setBackground(new Color(255,215,0));
				if(v % 6 == 0) { 
					seatLetter++;
					//System.out.println(seatLetter);
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
				buttons[i][f] = new JToggleButton(seatLetter + String.valueOf(v));

				if(buttons[i][f].getText() == seatsSelected[v])
					buttons[i][f].setBackground(new Color(0,0,0));
				buttons[i][f].setBackground(new Color(205,127,50));
				if(v % 6 == 0) { 
				seatLetter++;
				//System.out.println(seatLetter);
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
