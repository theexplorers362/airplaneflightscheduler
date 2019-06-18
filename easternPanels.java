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
		newReservation = new Reservation();
		fcnumReserved = 0;
		cnumReserved = 0;
		numReserved = 0;
		fcseatsReserved = null;
		cseatsReserved = null;
		seatsReserved = null;
		
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
	
	Reservation newReservation;
	JButton seatButton;
	
	int fcnumOfRows;
	int fcnumOfColumns;
	int cnumOfRows;
	int cnumOfColumns;
	int numOfRows;
	int numOfColumns;
	int planeSize;
	
	JToggleButton[][] fcbuttons;
	JToggleButton[][] cbuttons;
	JToggleButton[][] buttons;
	


	/*Makes the state of the toggle button enabled if pressed for the returned seat numbers in an array with the size
	 * toggles between color for premium seat color and grey to signify the need to be confirmed for reservation
	 * 
	 * 
	 * 
	 * 
	 */
	public void stateofButtons() {
		
		for(k = 0; k < fcnumOfRows; k++) {
			for(j = 0; j < fcnumOfColumns; j++) {
				
		fcbuttons[k][j].addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
						JToggleButton tb = (JToggleButton) arg0.getItem();
						if(arg0.getStateChange() == ItemEvent.SELECTED) {
						
						fcseatsReserved[fcnumReserved] = tb.getText();
						System.out.println(tb.getText());
						fcnumReserved+=1;
						System.out.println(fcnumReserved);
						//System.out.println(fcnumReserved);
						totalReserved++;
						tb.setBackground(new Color(192,192,192));
						//JOptionPane.showMessageDialog(null, "Data Saved");*/
						}
						else if(arg0.getStateChange() == ItemEvent.DESELECTED) {
							fcnumReserved--;
							totalReserved--;
							fcseatsReserved[fcnumReserved] = "";
							tb.setBackground(new Color(128,0,128));
						}
						
					
					//JOptionPane.showMessageDialog(null, "Data Saved");
						
					
				}catch(Exception e){
					e.printStackTrace();
				}
					
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
						System.out.println(tb.getText());
						cseatsReserved[cnumReserved] = tb.getText();
						cnumReserved++;
						totalReserved++;
						tb.setBackground(new Color(192,192,192));
						//JOptionPane.showMessageDialog(null, "Data Saved");
						}
						else if(arg0.getStateChange() == ItemEvent.DESELECTED) {
							cnumReserved--;
							totalReserved--;
							cseatsReserved[cnumReserved] = "";
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
						System.out.println(tb.getText());
						numReserved++;
						System.out.println(numReserved);
						totalReserved++;
						tb.setBackground(new Color(192,192,192));
						//JOptionPane.showMessageDialog(null, "Data Saved");
						}
						else if(arg0.getStateChange() == ItemEvent.DESELECTED) {
							numReserved--;
							totalReserved--;
							seatsReserved[numReserved] = "";
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
		fcseatsReserved = new String[fcnumSeats];
		cseatsReserved = new String[cnumSeats];
		seatsReserved = new String[numSeats];
		seatsSelected = new String[planeSize];
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
				if(fcbuttons[k][j].getText() == seatsSelected[v])
					fcbuttons[k][j].setBackground(new Color(0,0,0));
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
		stateofButtons();
		return easternPanel;
	}
}