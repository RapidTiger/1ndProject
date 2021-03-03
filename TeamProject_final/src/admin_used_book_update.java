import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class admin_used_book_update {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private String[] col = {"도서코드", "등급", "도서명"};
	private String[][] row = new String[100][3];
	private String[] book_id = new String[100];
	private String[] book_rtg = new String[100];
	private String bk_id;
	private String bk_name;
	private String bk_rtg;
	private String bk_qnt;
	private String bk_ctg;
	private String bk_px;
	private int cnt = 0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_used_book_update window = new admin_used_book_update();
					window.frame.setLocationRelativeTo(null);
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
	public admin_used_book_update() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		textField.setBounds(134, 84, 180, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(262, 430, 117, 33);
		frame.getContentPane().add(spinner);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String book_id_select = book_id[table.getSelectedRow()];
				String book_rtg_select = book_rtg[table.getSelectedRow()];
				int add_cnt = (int)spinner.getModel().getValue();
				
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e1) { 
					e1.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "update old_books set book_qnt = book_qnt + ? where book_id = ? and book_rtg = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, add_cnt);
					psmt.setString(2, book_id_select);
					psmt.setString(3, book_rtg_select);
					int result = psmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"입고가 완료되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
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
				frame.dispose();
				new admin_used_book_update().main(null);
			}
		});
		btnNewButton.setBounds(236, 472, 145, 31);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_Main().main(null);
				frame.dispose();
			}
		});
		btnNewButton_1_1.setBounds(217, 516, 217, 45);
		frame.getContentPane().add(btnNewButton_1_1);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setFocusPainted(false);
		btnNewButton_1_1.setOpaque(false); 
		
		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_used_book_insert("","","","",0,0);
				frame.dispose();
			}
		});
		btnNewButton_1_1_1.setBounds(56, 472, 145, 31);
		frame.getContentPane().add(btnNewButton_1_1_1);
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setContentAreaFilled(false);
		btnNewButton_1_1_1.setFocusPainted(false);
		btnNewButton_1_1_1.setOpaque(false); 
		
		JLabel lblcode = new JLabel("\uB3C4\uC11C\uCF54\uB4DC");
		lblcode.setHorizontalAlignment(SwingConstants.CENTER);
		lblcode.setBounds(255, 194, 124, 21);
		frame.getContentPane().add(lblcode);
		
		JLabel lblname = new JLabel("\uB3C4\uC11C\uBA85");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setBounds(255, 236, 124, 21);
		frame.getContentPane().add(lblname);
		
		JLabel lblrtg = new JLabel("\uB4F1\uAE09");
		lblrtg.setHorizontalAlignment(SwingConstants.CENTER);
		lblrtg.setBounds(255, 275, 124, 21);
		frame.getContentPane().add(lblrtg);
		
		JLabel lblqnt = new JLabel("\uC218\uB7C9");
		lblqnt.setHorizontalAlignment(SwingConstants.CENTER);
		lblqnt.setBounds(255, 313, 124, 21);
		frame.getContentPane().add(lblqnt);
		
		JLabel lblctg = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		lblctg.setHorizontalAlignment(SwingConstants.CENTER);
		lblctg.setBounds(255, 351, 124, 21);
		frame.getContentPane().add(lblctg);
		
		JLabel lblpx = new JLabel("\uAC00\uACA9");
		lblpx.setHorizontalAlignment(SwingConstants.CENTER);
		lblpx.setBounds(255, 386, 124, 21);
		frame.getContentPane().add(lblpx);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 183, 180, 232);
		frame.getContentPane().add(scrollPane);
		
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "select * from old_books";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				String name = "";
				if(rs.getInt(6) > 0) {
					name = rs.getString(3);
				}
				else {
					name = rs.getString(3)+"(매진)";
				}
				book_id[i] = rs.getString(1);
				book_rtg[i] = rs.getString(2);
				row[i][0] = rs.getString(1);
				row[i][1] = rs.getString(2);
				row[i++][2] = name;
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
		
		table = new JTable(row, col);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String book_id_select = book_id[table.getSelectedRow()];
				String book_rtg_select = book_rtg[table.getSelectedRow()];
				
				
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e1) { 
					e1.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from old_books where book_id = ? and book_rtg = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, book_id_select);
					psmt.setString(2, book_rtg_select);
					rs = psmt.executeQuery();
					while(rs.next()) {
						bk_id = rs.getString(1);
						bk_name = rs.getString(3);
						bk_rtg = "등급 : " + rs.getString(2);
						bk_qnt ="재고 : " + rs.getString(6) + "권";
						bk_ctg = rs.getString(7);
						bk_px = rs.getString(5) + "원";
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
				lblcode.setText(bk_id);
				lblname.setText(bk_name);
				lblrtg.setText(bk_rtg);
				lblqnt.setText(bk_qnt);
				lblctg.setText(bk_ctg);
				lblpx.setText(bk_px);
				
			}
		});
		scrollPane.setViewportView(table);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < 100; i++) {
					for (int j = 0; j < 3; j++) {
						row[i][j] = null;
						book_id[i] = null;
						book_id[i] = null;
						book_rtg[i] = null;
					}
				}
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) { 
					e.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from old_books where book_name like '%" + textField.getText() + "%'";
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					int i = 0;
					while(rs.next()) {
						String name = "";
						if(rs.getInt(6) > 0) {
							name = rs.getString(3);
						}
						else {
							name = rs.getString(3)+"(매진)";
						}
						book_id[i] = rs.getString(1);
						book_rtg[i] = rs.getString(2);
						row[i][0] = rs.getString(1);
						row[i][1] = rs.getString(2);
						row[i++][2] = name;
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
				int[] i = {268,269};
				table.setBounds(34, 130, 276, i[cnt++%2]);
				textField.setText("");
			}
		});
		btnNewButton_1.setBounds(315, 84, 66, 31);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setOpaque(false); 
		
		center.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn(col[0]).setPreferredWidth(30);
		table.getColumn(col[1]).setPreferredWidth(5);
		table.getColumn(col[2]).setPreferredWidth(50);
		table.getColumn(col[0]).setCellRenderer(center);
		table.getColumn(col[1]).setCellRenderer(center);
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\old_update.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 1, 434, 563);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
	}
}
