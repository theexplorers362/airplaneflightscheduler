import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.*;
public class Scheduler {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booked_flights","root","student");
			
			Statement myStat = myConn.createStatement();
		
			ResultSet myRs = myStat.executeQuery("select * from reserved");
			
			while(myRs.next()) {
				System.out.println(myRs.getString("passenger_name"));
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scheduler window = new Scheduler();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Scheduler() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Airplane Flight Scheduler");
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		schedulerPanel schedulerPanel_ = new schedulerPanel();
		frame.setContentPane(schedulerPanel_);
		schedulerPanel_.setLayout(new GridLayout(1, 0, 0, 0));
		
	
		
		JPanel panel = new JPanel();
		//frame.getContentPane().add(panel);
	}

}
