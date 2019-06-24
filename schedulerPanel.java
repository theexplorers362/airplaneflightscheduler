import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import java.sql.*;

public class schedulerPanel extends JPanel {
public schedulerPanel() {
	setLayout(new BorderLayout());
	westernPanels neww = new westernPanels();
	centerPanels newc = new centerPanels();
	easternPanels newe = new easternPanels();
	/* The layout for the WEST CENTER AND EASTERN PANELS initialized 
	 * 
	 * 
	 */
	this.add(neww.getTextArea(), BorderLayout.WEST);
	this.add(newc.getOptionsArea(),BorderLayout.CENTER);
	this.add(newe.getButtonArea(), FlowLayout.RIGHT);
	newe.stateofButtons();
	newe.seeiftaken();
	neww.togglethemButtons();
	neww.addItemListenerHelperComboBennie();
	newe.addActionListenerHelperbkButtons(newc.bkButton1, newc.bkgroup);
	newe.addActionListenerHelperbkButtons(newc.bkButton2, newc.bkgroup);
	newe.addActionListenerHelperbkButtons(newc.bkButton3, newc.bkgroup);
	newe.addActionListenerHelperbkButtons(newc.bkButton4, newc.bkgroup);
	newe.addActionListenerHelperLunchButtons(newc.lunchButton1, newc.lunchgroup);
	newe.addActionListenerHelperLunchButtons(newc.lunchButton2, newc.lunchgroup);
	newe.addActionListenerHelperLunchButtons(newc.lunchButton3, newc.lunchgroup);
	newe.addActionListenerHelperLunchButtons(newc.lunchButton4, newc.lunchgroup);
    newe.addActionListenerHelperDinnerButtons(newc.dinnerButton1, newc.dinnergroup);
    newe.addActionListenerHelperDinnerButtons(newc.dinnerButton2, newc.dinnergroup);
    newe.addActionListenerHelperDinnerButtons(newc.dinnerButton3, newc.dinnergroup);
    newe.addActionListenerHelperDinnerButtons(newc.dinnerButton4, newc.dinnergroup);
    newe.generateConfirmationNum();
    neww.generateConfirmationNum();
    newe.pressConfirm(neww.confirmButton);
	neww.pressConfirm();
	
}
}
