import java.awt.EventQueue;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin_used_book_insert_select {

	private JFrame frame;
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private String[] col = {"도서ID","도서명","저자"};
	private String[][] row = new String[100][3];
	private String[] book_id = new String[100];
	private String[] book_name = new String[100];
	private String[] book_writer = new String[100];
	private String[] book_ctg = new String[100];
	private int[] book_sales = new int[100];	
	private int[] book_px = new int[100];		
	private JTextField textField;
	private JTable table;
	private int cnt = 0;
	private String bk_id;
	private String bk_name;
	private String bk_qnt;
	private String bk_ctg;
	private int bk_sales;
	private JScrollPane scrollPane;
	private JButton btnNewButton_3;
	private String book_id_select = "";
	private String book_name_select = "";
	private String book_writer_select = "";
	private String book_ctg_select = "";
	private int book_sales_select = 0;
	private int book_px_select = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_used_book_insert_select window = new admin_used_book_insert_select();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public admin_used_book_insert_select() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(43, 95, 239, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < 100; i++) {
					for (int j = 0; j < 3; j++) {
						row[i][j] = null;
						book_id[i] = null;
						book_name[i] = null;
						book_writer[i] = null;
						book_ctg[i] = null;
						book_sales[i] = 0;	
						book_px[i] = 0;	
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
						book_id[i] = rs.getString(1);
						book_name[i] = rs.getString(2);
						book_writer[i] = rs.getString(3);
						book_ctg[i] = rs.getString(7);
						book_sales[i] = rs.getInt(8);
						book_px[i] = rs.getInt(4);
						row[i][0] = rs.getString(1);
						row[i][1] = rs.getString(2);
						row[i++][2] = rs.getString(3);
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
				int[] i = {289,290};
				table.setBounds(22, 189, 400, i[cnt++%2]);
				textField.setText("");
			}
		});
		btnNewButton.setBounds(289, 96, 109, 31);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_used_book_insert("","","","",0,0);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(222, 517, 216, 43);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setOpaque(false); 
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_Main().main(null);
			}
		});
		btnNewButton_2.setBounds(2, 517, 216, 45);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setOpaque(false); 
		
		
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
				book_id[i] = rs.getString(1);
				book_name[i] = rs.getString(2);
				book_writer[i] = rs.getString(3);
				book_ctg[i] = rs.getString(7);
				book_sales[i] = rs.getInt(8);
				book_px[i] = rs.getInt(4);
				row[i][0] = rs.getString(1);
				row[i][1] = rs.getString(2);
				row[i++][2] = rs.getString(3);
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 139, 355, 304);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable(row, col);
		scrollPane.setViewportView(table);
		
		btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			book_id_select = book_id[table.getSelectedRow()];
			book_name_select = book_name[table.getSelectedRow()];
			book_writer_select = book_writer[table.getSelectedRow()];
			book_ctg_select = book_ctg[table.getSelectedRow()];
			book_px_select = book_px[table.getSelectedRow()];
			book_sales_select = book_sales[table.getSelectedRow()];
			new admin_used_book_insert(book_id_select, book_name_select, book_writer_select, book_ctg_select, book_px_select, book_sales_select);
			frame.dispose();
			}
		});
		btnNewButton_3.setBounds(153, 453, 130, 32);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setOpaque(false); 
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\old_insert_select.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
	}

}
