import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class centerPanels {
	centerPanels(){
		bkgroup = new ButtonGroup();
		lunchgroup = new ButtonGroup();
		dinnergroup = new ButtonGroup();
	}
	JPanel buttonsPanel;
	JLabel bkFastLabel;
	JRadioButton bkButton1;
	JRadioButton bkButton2;
	JRadioButton bkButton3;
	JRadioButton bkButton4;
	JLabel lunchLabel;
	JRadioButton lunchButton1;
	JRadioButton lunchButton2;
	JRadioButton lunchButton3;
	JRadioButton lunchButton4;
	JLabel dinnerLabel;
	JRadioButton dinnerButton1;
	JRadioButton dinnerButton2;
	JRadioButton dinnerButton3;
	JRadioButton dinnerButton4;
	Reservation newReservation;
	ButtonGroup bkgroup;
	ButtonGroup lunchgroup;
	ButtonGroup dinnergroup;
	 /*public void getTimeSpans()
	 {
	     boolean firstTime = false, secondTime = false;

	     if(time1 > "20:11:13" && time1 < "14:49:00")
	     {
	        firstTime = true;
	     }

	     if(time2 > "20:11:13" && time2 < "14:49:00")
	     {
	        secondTime = true;
	     }
	  }*/
	 

	
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
	bkButton1 = new JRadioButton("Omelette");
	bkButton2 = new JRadioButton("Waffles");
	bkButton3 = new JRadioButton("Coffee Cake");
	bkButton4 = new JRadioButton("Acai Bowl");
	lunchLabel = new JLabel("Lunch: ");
	lunchButton1 = new JRadioButton("BLT Sandwich");
	lunchButton2 = new JRadioButton("Pizza");
	lunchButton3 = new JRadioButton("Pasta Alfredo");
	lunchButton4 = new JRadioButton("Soup");
	dinnerLabel = new JLabel("Dinner: ");
	dinnerButton1 = new JRadioButton("Steak");
	dinnerButton2 = new JRadioButton("Lobster");
	dinnerButton3 = new JRadioButton("Shrimp Cocktail");
	dinnerButton4 = new JRadioButton("Caesar Salad");
	buttonsPanel.add(Box.createRigidArea(new Dimension(7, 0)));
	buttonsPanel.add(bkFastLabel);
	bkgroup.add(bkButton1);
	bkgroup.add(bkButton2);
	bkgroup.add(bkButton3);
	bkgroup.add(bkButton4);
	
	
	buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	buttonsPanel.add(bkButton1, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
	buttonsPanel.add(bkButton2, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(108, 0)));
	buttonsPanel.add(bkButton3, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(13, 0)));
	buttonsPanel.add(bkButton4, BorderLayout.CENTER);
	buttonsPanel.add(lunchLabel);
	lunchgroup.add(lunchButton1);
	lunchgroup.add(lunchButton2);
	lunchgroup.add(lunchButton3);
	lunchgroup.add(lunchButton4);
	
	
	buttonsPanel.add(Box.createRigidArea(new Dimension(32, 0)));
	buttonsPanel.add(lunchButton1, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(2, 0)));
	buttonsPanel.add(lunchButton2, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(110, 0)));
	buttonsPanel.add(lunchButton3, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(6, 0)));
	buttonsPanel.add(lunchButton4, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
	buttonsPanel.add(dinnerLabel);
	dinnergroup.add(dinnerButton1);
	dinnergroup.add(dinnerButton2);
	dinnergroup.add(dinnerButton3);
	dinnergroup.add(dinnerButton4);
	
	
	buttonsPanel.add(Box.createRigidArea(new Dimension(35, 0)));
	buttonsPanel.add(dinnerButton1, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(51, 0)));
	buttonsPanel.add(dinnerButton2, BorderLayout.CENTER);
	buttonsPanel.add(Box.createRigidArea(new Dimension(97, 0)));
	buttonsPanel.add(dinnerButton3, BorderLayout.CENTER);
	buttonsPanel.add(dinnerButton4, BorderLayout.CENTER);
	return buttonsPanel;
}
}
