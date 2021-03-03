import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;


import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class admin_used_book_insert {

	private JFrame frame;
	private JTextField txtcode;
	private JTextField txtname;
	private JTextField txtpx;
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private JTextField txtwriter;
	private int result;
	public admin_used_book_insert(String book_id_select, String book_name_select, String book_writer_select, String book_ctg_select, int book_px_select, int book_sales_select) {
		initialize(book_id_select, book_name_select, book_writer_select, book_ctg_select, book_px_select, book_sales_select);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String book_id_select, String book_name_select, String book_writer_select, String book_ctg_select,  int book_px_select, int book_sales_select) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_used_book_insert_select().main(null);
				frame.dispose();
			}
		});
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(68, 350, 128, 36);
		frame.getContentPane().add(btnNewButton_2);
		
		txtcode = new JTextField();
		txtcode.setHorizontalAlignment(SwingConstants.CENTER);
		txtcode.setBounds(169, 89, 199, 26);
		frame.getContentPane().add(txtcode);
		txtcode.setColumns(10);
		
		JComboBox cbrtg = new JComboBox();
		cbrtg.setEditable(true);
		cbrtg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int oldbookpay = book_px_select * 60 / 100;
	              if (book_sales_select >= 500) {
	                 if (String.valueOf(cbrtg.getSelectedItem()) == "B") {
	                    result = oldbookpay * 90 / 100 + (oldbookpay * 90 / 100) * 20 / 100;
	                 } else if (String.valueOf(cbrtg.getSelectedItem()) == "C") {
	                    result = oldbookpay * 80 / 100+ (oldbookpay * 80 / 100) * 20 / 100;
	                 } else if (String.valueOf(cbrtg.getSelectedItem()) == "F") {
	                    result = 0;
	                 } else {
	                    result = oldbookpay + oldbookpay * 20 / 100;
	                 }
	              } else {
	                 if (String.valueOf(cbrtg.getSelectedItem()) == "B") {
	                    result = oldbookpay * 90 / 100;
	                 } else if (String.valueOf(cbrtg.getSelectedItem()) == "C") {
	                    result = oldbookpay * 80 / 100;
	                 } else if (String.valueOf(cbrtg.getSelectedItem()) == "F") {
	                    result = 0;
	                 } else {
	                    result = oldbookpay;
	                 }
	              }
				txtpx.setText(result+"");;
			}
		});
		cbrtg.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "F"}));
		cbrtg.setBounds(169, 127, 199, 26);
		frame.getContentPane().add(cbrtg);
		
		JSpinner spinqnt = new JSpinner();
		spinqnt.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinqnt.setBounds(169, 277, 199, 26);
		frame.getContentPane().add(spinqnt);
		
		txtname = new JTextField();
		txtname.setHorizontalAlignment(SwingConstants.CENTER);
		txtname.setColumns(10);
		txtname.setBounds(169, 164, 199, 26);
		frame.getContentPane().add(txtname);
		
		txtwriter = new JTextField();
		txtwriter.setHorizontalAlignment(SwingConstants.CENTER);
		txtwriter.setColumns(10);
		txtwriter.setBounds(169, 200, 199, 26);
		frame.getContentPane().add(txtwriter);
		
		txtpx = new JTextField();
		txtpx.setHorizontalAlignment(SwingConstants.CENTER);
		txtpx.setColumns(10);
		txtpx.setBounds(169, 239, 199, 26);
		frame.getContentPane().add(txtpx);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_used_book_update().main(null);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(223, 515, 211, 46);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setOpaque(false); 
		
		JComboBox cbctg = new JComboBox();
		cbctg.setModel(new DefaultComboBoxModel(new String[] {"\uD55C\uAD6D\uC18C\uC124", "\uB9CC\uD654", "\uC790\uAE30\uACC4\uBC1C", "\uB300\uD559\uAD50\uC7AC/\uC804\uACF5\uC11C\uC801", "\uC2DC"}));
		cbctg.setBounds(169, 314, 199, 26);
		frame.getContentPane().add(cbctg);
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bk_code = txtcode.getText();
				String bk_rtg = String.valueOf(cbrtg.getSelectedItem());
				String bk_name = txtname.getText();
				String bk_writer = txtwriter.getText();
				int bk_px = Integer.parseInt(txtpx.getText());
				int bk_qnt = (int) spinqnt.getModel().getValue();
				String bk_ctg =  String.valueOf(cbctg.getSelectedItem());
				
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e1) { 
					e1.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "insert into old_books values(?,?,?,?,?,?,?,0)";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, bk_code);
					psmt.setString(2, bk_rtg);
					psmt.setString(3, bk_name);
					psmt.setString(4, bk_writer);
					psmt.setInt(5, bk_px);
					psmt.setInt(6, bk_qnt);
					psmt.setString(7, bk_ctg);
					int result = psmt.executeUpdate();
					if(result > 0) {
						JOptionPane.showMessageDialog(null,"등록이 완료되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
						new admin_used_book_update().main(null);
						frame.dispose();
					}
				} catch (SQLException e1) {
				}
				finally {
					try {
						if(psmt != null) {
							psmt.close();
						}
						if(conn != null) {
							conn.close();
						}
					} catch (SQLException e1) {
					}
				}
				
			}
		});
		btnNewButton.setBounds(240, 350, 128, 36);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\old_insert.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
		
		txtcode.setText(book_id_select);
		txtname.setText(book_name_select);
		txtwriter.setText(book_writer_select);
		
		
		

	
	}
}
