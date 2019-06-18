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
JPanel northernPanel;
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

}
}
