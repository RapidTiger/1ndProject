import java.awt.EventQueue;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class user_info_update {

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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	

	public user_info_update(String id, String name, String number, String address) {
		initialize(id, name, number, address);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id, String name, String number, String address) {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_info(id);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(185, 354, 97, 29);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String new_name = textField.getText();
				String new_number = textField_1.getText();
				String new_address = textField_2.getText();
				
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) { 
					e.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "update buy_members set member_name = ?  where member_id = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, new_name);
					psmt.setString(2, id);					
					int result = psmt.executeUpdate();
					
					sql = "update buy_members set member_phone = ?  where member_id = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, new_address);
					psmt.setString(2, id);
					result = psmt.executeUpdate();
					
					sql = "update buy_members set member_address = ?  where member_id = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, new_number);
					psmt.setString(2, id);
					result = psmt.executeUpdate();
					
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
				JOptionPane.showMessageDialog(null,"회원정보 수정이 완료되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
				new user_info(id);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(55, 354, 97, 29);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setOpaque(false); 

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(119, 136, 173, 24);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(119, 181, 173, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		textField_1.setBounds(119, 224, 171, 24);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(119, 267, 171, 24);
		frame.getContentPane().add(textField_2);
		
		lblNewLabel.setText(id);
		textField.setText(name);
		textField_1.setText(number);
		textField_2.setText(address);
		
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\user_info_update.png").getImage().getScaledInstance(325, 425,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 325, 425);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
		
		
		
		
		
		
		
		
		
	}
}
