import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class centerPanels {
	centerPanels(){
		bkgroup = new ButtonGroup();
		lunchgroup = new ButtonGroup();
		dinnergroup = new ButtonGroup();
	}
	private JPanel buttonsPanel;
	private JLabel bkFastLabel;
	JRadioButton bkButton1;
	JRadioButton bkButton2;
	JRadioButton bkButton3;
	JRadioButton bkButton4;
	private JLabel lunchLabel;
	JRadioButton lunchButton1;
	JRadioButton lunchButton2;
	JRadioButton lunchButton3;
	JRadioButton lunchButton4;
	private JLabel dinnerLabel;
	JRadioButton dinnerButton1;
	JRadioButton dinnerButton2;
	JRadioButton dinnerButton3;
	JRadioButton dinnerButton4;
	Reservation newReservation;
	ButtonGroup bkgroup;
	ButtonGroup lunchgroup;
	ButtonGroup dinnergroup;
	
/*Boundary Component of class centerPanels*/
public Component getOptionsArea() {
	newReservation = new Reservation();
	buttonsPanel = new JPanel();
	buttonsPanel.setLayout(new FlowLayout());
	bkFastLabel = new JLabel("BreakFast: ");
	bkFastLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	
	ImageIcon image1 = new ImageIcon(getClass().getResource("omellete.jpg"));
	Image newimg1 = image1.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image1 = new ImageIcon(newimg1);  // transform it back
	JLabel label1 = new JLabel(image1);
	bkButton1 = new JRadioButton("");
	bkButton1.setName("Omellete");
	
	bkButton1.setFont(new Font("Arial", Font.PLAIN, 11));
	
	ImageIcon image2 = new ImageIcon(getClass().getResource("blueberry_waffles.jpg"));
	Image newimg2 = image2.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image2 = new ImageIcon(newimg2);  // transform it back
	JLabel label2 = new JLabel(image2);
	bkButton2 = new JRadioButton("");
	bkButton2.setName("Waffles");
	
	
	bkButton2.setFont(new Font("Arial", Font.PLAIN, 11));
	
	
	ImageIcon image3 = new ImageIcon(getClass().getResource("coffee_cake.jpg"));
	Image newimg3 = image3.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image3 = new ImageIcon(newimg3);  // transform it back
	JLabel label3 = new JLabel(image3);
	bkButton3 = new JRadioButton("");
	bkButton3.setName("Coffee Cake");
	
	
	bkButton3.setFont(new Font("Arial", Font.PLAIN, 11));
	
	
	ImageIcon image4 = new ImageIcon(getClass().getResource("acai_bowl.jpg"));
	Image newimg4 = image4.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image4 = new ImageIcon(newimg4);  // transform it back
	JLabel label4 = new JLabel(image4);
	bkButton4 = new JRadioButton("");
	bkButton4.setName("Acai Bowl");
	
	
	bkButton4.setFont(new Font("Arial", Font.PLAIN, 11));
	
	
	lunchLabel = new JLabel("Lunch: ");
	lunchLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	
	ImageIcon image5 = new ImageIcon(getClass().getResource("blt_sandwich.jpg"));
	Image newimg5 = image5.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image5 = new ImageIcon(newimg5);  // transform it back
	JLabel label5 = new JLabel(image5);
	lunchButton1 = new JRadioButton("");
	lunchButton1.setName("BLT Sandwich");
	
	lunchButton1.setFont(new Font("Arial", Font.PLAIN, 11));
	
	
	ImageIcon image6 = new ImageIcon(getClass().getResource("pizza.jpg"));
	Image newimg6 = image6.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image6 = new ImageIcon(newimg6);  // transform it back
	JLabel label6 = new JLabel(image6);
	lunchButton2 = new JRadioButton("");
	lunchButton2.setName("Pizza");
	
	lunchButton2.setFont(new Font("Arial", Font.PLAIN, 11));
	
	
	ImageIcon image7 = new ImageIcon(getClass().getResource("fettuccine_alfredo.jpg"));
	Image newimg7 = image7.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image7 = new ImageIcon(newimg7);  // transform it back
	JLabel label7 = new JLabel(image7);
	lunchButton3 = new JRadioButton("");
	lunchButton3.setName("Fettuccine Alfredo");
	
	lunchButton3.setFont(new Font("Arial", Font.PLAIN, 11));
	
	ImageIcon image8 = new ImageIcon(getClass().getResource("soup.jpg"));
	Image newimg8 = image8.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image8 = new ImageIcon(newimg8);  // transform it back
	JLabel label8 = new JLabel(image8);
	lunchButton4 = new JRadioButton("");
	lunchButton4.setName("Soup");
	
	lunchButton4.setFont(new Font("Arial", Font.PLAIN, 11));
	
	
	dinnerLabel = new JLabel("Dinner: ");
	dinnerLabel.setFont(new Font("Arial", Font.PLAIN, 11));
	
	ImageIcon image9 = new ImageIcon(getClass().getResource("steak.jpg"));
	Image newimg9 = image9.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image9 = new ImageIcon(newimg9);  // transform it back
	JLabel label9 = new JLabel(image9);
	dinnerButton1 = new JRadioButton("");
	dinnerButton1.setName("Steak");
	dinnerButton1.setFont(new Font("Arial", Font.PLAIN, 11));
	
	ImageIcon image10 = new ImageIcon(getClass().getResource("lobster.jpg"));
	Image newimg10 = image10.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image10 = new ImageIcon(newimg10);  // transform it back
	JLabel label10 = new JLabel(image10);
	dinnerButton2 = new JRadioButton("");
	dinnerButton2.setName("Lobster");
	dinnerButton2.setFont(new Font("Arial", Font.PLAIN, 11));
	
	
	ImageIcon image11 = new ImageIcon(getClass().getResource("shrimp_cocktail.jpg"));
	Image newimg11 = image11.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image11 = new ImageIcon(newimg11);  // transform it back
	JLabel label11 = new JLabel(image11);
	dinnerButton3 = new JRadioButton("");
	dinnerButton3.setName("Shrimp Cocktail");

	dinnerButton3.setFont(new Font("Arial", Font.PLAIN, 11));
	
	ImageIcon image12 = new ImageIcon(getClass().getResource("caesar_salad.jpg"));
	Image newimg12 = image12.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
	image12 = new ImageIcon(newimg12);  // transform it back
	JLabel label12 = new JLabel(image12);
	dinnerButton4 = new JRadioButton("");
	dinnerButton4.setName("Caesar Salad");
	
	dinnerButton4.setFont(new Font("Arial", Font.PLAIN, 11));
	buttonsPanel.add(bkFastLabel);
	bkgroup.add(bkButton1);
	bkgroup.add(bkButton2);
	bkgroup.add(bkButton3);
	bkgroup.add(bkButton4);
	
	
	buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	buttonsPanel.add(bkButton1, BorderLayout.CENTER);
	buttonsPanel.add(label1);
	buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	buttonsPanel.add(bkButton2, BorderLayout.CENTER);
	buttonsPanel.add(label2);
	buttonsPanel.add(Box.createRigidArea(new Dimension(85, 0)));
	buttonsPanel.add(bkButton3, BorderLayout.CENTER);
	buttonsPanel.add(label3);
	buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	buttonsPanel.add(bkButton4, BorderLayout.CENTER);
	buttonsPanel.add(label4);
	buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
	buttonsPanel.add(lunchLabel);
	lunchgroup.add(lunchButton1);
	lunchgroup.add(lunchButton2);
	lunchgroup.add(lunchButton3);
	lunchgroup.add(lunchButton4);
	
	
	buttonsPanel.add(Box.createRigidArea(new Dimension(32, 0)));
	buttonsPanel.add(lunchButton1, BorderLayout.CENTER);
	buttonsPanel.add(label5);
	buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	buttonsPanel.add(lunchButton2, BorderLayout.CENTER);
	buttonsPanel.add(label6);
	buttonsPanel.add(Box.createRigidArea(new Dimension(85, 0)));
	buttonsPanel.add(lunchButton3, BorderLayout.CENTER);
	buttonsPanel.add(label7);
	buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	buttonsPanel.add(lunchButton4, BorderLayout.CENTER);
	buttonsPanel.add(label8);
	buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
	buttonsPanel.add(dinnerLabel);
	dinnergroup.add(dinnerButton1);
	dinnergroup.add(dinnerButton2);
	dinnergroup.add(dinnerButton3);
	dinnergroup.add(dinnerButton4);
	
	
	buttonsPanel.add(Box.createRigidArea(new Dimension(32, 0)));
	buttonsPanel.add(dinnerButton1, BorderLayout.CENTER);
	buttonsPanel.add(label9);
	buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	buttonsPanel.add(dinnerButton2, BorderLayout.CENTER);
	buttonsPanel.add(label10);
	buttonsPanel.add(Box.createRigidArea(new Dimension(75, 0)));
	buttonsPanel.add(dinnerButton3, BorderLayout.CENTER);
	buttonsPanel.add(label11);
	buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	buttonsPanel.add(dinnerButton4, BorderLayout.CENTER);
	buttonsPanel.add(label12);
	return buttonsPanel;
}
}
