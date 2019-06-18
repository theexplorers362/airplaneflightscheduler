import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class centerPanels {
	
	JPanel buttonsPanel;
	JLabel bkFastLabel;
	JButton bkButton1;
	JButton bkButton2;
	JButton bkButton3;
	JButton bkButton4;
	JLabel lunchLabel;
	JButton lunchButton1;
	JButton lunchButton2;
	JButton lunchButton3;
	JButton lunchButton4;
	JLabel dinnerLabel;
	JButton dinnerButton1;
	JButton dinnerButton2;
	JButton dinnerButton3;
	JButton dinnerButton4;
	Reservation newReservation;
	
	public void addActionListenerHelperbkButtons(JButton any) {
	any.addActionListener(new ActionListener() { 
		public void actionPerformed(ActionEvent arg0) {
			try {
				newReservation.setBkfastoption(any.getText());
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	});
	}

	public void addActionListenerHelperLunchButtons(JButton any) {
		any.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				try {
					newReservation.setLunchoption(any.getText());
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
	});
	}
	
	public void addActionListenerHelperDinnerButtons(JButton any) {
		any.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				try {
					newReservation.setDinneroption(any.getText());
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
	});
	}
	
	/*Boundary Component of class centerPanels
	 * 
	 * 
	 *
	 */

public Component getOptionsArea() {
	newReservation = new Reservation();
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
	addActionListenerHelperbkButtons(bkButton1);
	addActionListenerHelperbkButtons(bkButton2);
	addActionListenerHelperbkButtons(bkButton3);
	addActionListenerHelperbkButtons(bkButton4);
	
	
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
	addActionListenerHelperLunchButtons(lunchButton1);
	addActionListenerHelperLunchButtons(lunchButton2);
	addActionListenerHelperLunchButtons(lunchButton3);
	addActionListenerHelperLunchButtons(lunchButton4);
	
	
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
	addActionListenerHelperDinnerButtons(dinnerButton1);
	addActionListenerHelperDinnerButtons(dinnerButton2);
	addActionListenerHelperDinnerButtons(dinnerButton3);
	addActionListenerHelperDinnerButtons(dinnerButton4);
	
	
	buttonsPanel.add(Box.createRigidArea(new Dimension(25, 0)));
	buttonsPanel.add(dinnerButton1, BorderLayout.CENTER);
	buttonsPanel.add(dinnerButton2, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(75, 0)));
	buttonsPanel.add(dinnerButton3, BorderLayout.CENTER);
	buttonsPanel.add(dinnerButton4, BorderLayout.CENTER);
	return buttonsPanel;
}
}
