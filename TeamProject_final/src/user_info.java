import java.awt.EventQueue;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class user_info {

	private JFrame frame;
	private String name = "";
	private String number = "";
	private String address = "";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	

	public user_info(String id) {
		initialize(id);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_info_update(id, name, number, address);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(53, 354, 103, 30);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(181, 354, 103, 30);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setOpaque(false); 
		
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "select * from buy_members where member_id = '" + id + "'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				name = rs.getString(2);
				number = rs.getString(3);
				address = rs.getString(4);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(psmt != null) {
					psmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		JLabel lblID = new JLabel("ID");
		lblID.setBackground(new Color(153, 0, 204));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(118, 136, 170, 23);
		frame.getContentPane().add(lblID);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(118, 181, 170, 23);
		frame.getContentPane().add(lblName);
		
		JLabel lblNumber = new JLabel("New label");
		lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumber.setBounds(118, 224, 170, 23);
		frame.getContentPane().add(lblNumber);
		
		JLabel lblAddress = new JLabel("");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBounds(118, 267, 170, 23);
		frame.getContentPane().add(lblAddress);
		frame.setVisible(true);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(118, 267, 170, 23);
		frame.getContentPane().add(lblNewLabel);
		
		lblID.setText(id);
		lblName.setText(name);
		lblNumber.setText(number);
		lblNewLabel.setText(address);
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\user_info.png").getImage().getScaledInstance(325, 425,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 325, 425);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
		
	}
}
