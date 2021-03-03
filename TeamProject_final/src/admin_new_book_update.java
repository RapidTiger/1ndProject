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

public class admin_new_book_update {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private String[] col = {"도서코드", "도서명"};
	private String[][] row = new String[100][2];
	private String[] book_id = new String[100];
	private String bk_id;
	private String bk_name;
	private String bk_qnt;
	private String bk_ctg;
	private String bk_px;
	private String bk_sales;
	private int cnt = 0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_new_book_update window = new admin_new_book_update();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public admin_new_book_update() {
		initialize();
	}

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
				int add_cnt = (int)spinner.getModel().getValue();
				
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e1) { 
					e1.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "update new_books set book_qnt = book_qnt + ? where book_id = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, add_cnt);
					psmt.setString(2, book_id_select);
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
				new admin_new_book_update().main(null);
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
				new admin_new_book_insert().main(null);
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
		
		JLabel lblqnt = new JLabel("\uC218\uB7C9");
		lblqnt.setHorizontalAlignment(SwingConstants.CENTER);
		lblqnt.setBounds(255, 275, 124, 21);
		frame.getContentPane().add(lblqnt);
		
		JLabel lblctg = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		lblctg.setHorizontalAlignment(SwingConstants.CENTER);
		lblctg.setBounds(255, 313, 124, 21);
		frame.getContentPane().add(lblctg);
		
		JLabel lblpx = new JLabel("\uAC00\uACA9");
		lblpx.setHorizontalAlignment(SwingConstants.CENTER);
		lblpx.setBounds(255, 355, 124, 21);
		frame.getContentPane().add(lblpx);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 183, 180, 232);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblsale = new JLabel("\uD310\uB9E4\uB7C9");
		lblsale.setHorizontalAlignment(SwingConstants.CENTER);
		lblsale.setBounds(255, 391, 124, 21);
		
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "select * from new_books";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				String name = "";
				if(rs.getInt(6) > 0) {
					name = rs.getString(2);
				}
				else {
					name = rs.getString(2)+"(매진)";
				}
				book_id[i] = rs.getString(1);
				row[i][0] = rs.getString(1);
				row[i++][1] = name;
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
				
				
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e1) { 
					e1.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from new_books where book_id = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, book_id_select);
					rs = psmt.executeQuery();
					while(rs.next()) {
						bk_id = rs.getString(1);
						bk_name = rs.getString(2);
						bk_qnt ="잔여 : " + rs.getString(6) + "권";
						bk_ctg = rs.getString(7);
						bk_px = rs.getString(4) + "원";
						bk_sales = "판매량 : "  + rs.getString(8) + "권";
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
				lblqnt.setText(bk_qnt);
				lblctg.setText(bk_ctg);
				lblpx.setText(bk_px);
				lblsale.setText(bk_sales);
				
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
					for (int j = 0; j < 2; j++) {
						row[i][j] = null;
						book_id[i] = null;
					}
				}
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) { 
					e.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					
					String sql = "select * from new_books where book_name like '%" + textField.getText() + "%'";
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					int i = 0;
					while(rs.next()) {
						String name = "";
						if(rs.getInt(6) > 0) {
							name = rs.getString(2);
						}
						else {
							name = rs.getString(2)+"(매진)";
						}
						book_id[i] = rs.getString(1);
						row[i][0] = rs.getString(1);
						row[i++][1] = name;
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
		
		
		frame.getContentPane().add(lblsale);
		center.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn(col[0]).setPreferredWidth(10);
		table.getColumn(col[1]).setPreferredWidth(50);
		table.getColumn(col[0]).setCellRenderer(center);
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\new_update.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 563);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
	}
}
