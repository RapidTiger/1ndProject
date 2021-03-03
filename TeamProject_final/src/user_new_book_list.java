import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class user_new_book_list {

	static public JFrame frame_new;
	private JTextField txt_search;
	private JTable table;
	private JTable table_1;
	private int cnt = 0;
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private String[] col = {"도서명","저자","카테고리","가격"};
	private String[][] row = new String[100][4];
	private String[] book_id = new String[100];
	private int[] book_qnt = new int[100];
	private String book_id_select = null;
	private int book_qnt_select = 0;
	private String state = "new";

	public user_new_book_list(String id) {
		initialize(id);
		frame_new.setLocationRelativeTo(null);
		frame_new.setVisible(true);
	}

	private void initialize(String id) {
				
		frame_new = new JFrame();
		frame_new.setBounds(100, 100, 450, 600);
		frame_new.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_new.getContentPane().setLayout(null);
		
		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_Main(id);
				frame_new.dispose();
			}
		});
		
		JButton btn_back_1 = new JButton("");
		btn_back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_Main(id);
				frame_new.dispose();
			}
		});
		btn_back_1.setOpaque(false);
		btn_back_1.setFocusPainted(false);
		btn_back_1.setContentAreaFilled(false);
		btn_back_1.setBorderPainted(false);
		btn_back_1.setBounds(0, 517, 220, 44);
		frame_new.getContentPane().add(btn_back_1);
		btn_back.setBounds(214, 517, 220, 44);
		frame_new.getContentPane().add(btn_back);
		btn_back.setBorderPainted(false);
		btn_back.setContentAreaFilled(false);
		btn_back.setFocusPainted(false);
		btn_back.setOpaque(false); 
		
		txt_search = new JTextField();
		txt_search.setBounds(32, 86, 295, 38);
		frame_new.getContentPane().add(txt_search);
		txt_search.setColumns(10);
		
		JButton btnsearch = new JButton("");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < 100; i++) {
					for (int j = 0; j < 4; j++) {
						row[i][j] = null;
						book_id[i] = null;
						book_qnt[i] = 0;
					}
					
				}
			
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) { 
					e.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					String sql = "select * from new_books where book_name like '%" + txt_search.getText() + "%'";
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
						book_qnt[i] = rs.getInt(6);
						row[i][0] = name;
						row[i][1] = rs.getString(3);
						row[i][2] = rs.getString(7);
						row[i++][3] = rs.getString(4)+"원";
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
				txt_search.setText("");
			}
		});
		btnsearch.setBounds(327, 87, 87, 35);
		frame_new.getContentPane().add(btnsearch);
		btnsearch.setBorderPainted(false);
		btnsearch.setContentAreaFilled(false);
		btnsearch.setFocusPainted(false);
		btnsearch.setOpaque(false); 
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(book_id_select == null)) {
					new user_shopping_cnt(book_id_select,"",book_qnt_select, id, state);
				}
			}
		});
		btnNewButton.setBounds(32, 471, 176, 36);
		frame_new.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		
		JComboBox comboBox_category = new JComboBox();
		comboBox_category.setBackground(SystemColor.textHighlightText);
		comboBox_category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < 100; i++) {
					for (int j = 0; j < 4; j++) {
						row[i][j] = null;
						book_id[i] = null;
						book_qnt[i] = 0;
					}
					
				}
				
				try { 
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) { 
					e.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					String ctg = "";
					if(!comboBox_category.getSelectedItem().equals("전체")) {
						 ctg = " where book_ctg = '"+comboBox_category.getSelectedItem()+"'";
					}
					String sql = "select * from new_books"+ctg;
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
						book_qnt[i] = rs.getInt(6);
						row[i][0] = name;
						row[i][1] = rs.getString(3);
						row[i][2] = rs.getString(7);
						row[i++][3] = rs.getString(4)+"원";
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
			}
		});
		comboBox_category.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uB9CC\uD654", "\uC2DC", "\uC790\uAE30\uACC4\uBC1C", "\uC18C\uC124", "\uAD50\uC7AC"}));
		comboBox_category.setToolTipText("");
		comboBox_category.setBounds(160, 137, 254, 39);
		frame_new.getContentPane().add(comboBox_category);
		
		
		
		
		
		
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
				book_qnt[i] = rs.getInt(6);
				row[i][0] = name;
				row[i][1] = rs.getString(3);
				row[i][2] = rs.getString(7);
				row[i++][3] = rs.getString(4)+"원";
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 201, 371, 252);
		frame_new.getContentPane().add(scrollPane);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		table = new JTable(row, col);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				book_id_select = book_id[table.getSelectedRow()];
				book_qnt_select = book_qnt[table.getSelectedRow()];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_shopping_basket("","",0,id,state);
				frame_new.dispose();
			}
		});
		btnNewButton_1.setBounds(236, 468, 171, 39);
		frame_new.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setOpaque(false); 
		
		center.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn(col[0]).setPreferredWidth(240);
		table.getColumn(col[1]).setPreferredWidth(100);
		table.getColumn(col[2]).setPreferredWidth(70);
		table.getColumn(col[1]).setCellRenderer(center);
		table.getColumn(col[2]).setCellRenderer(center);
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumn(col[3]).setCellRenderer(right);
		
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
		
        
        
        
        String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\newbooklist.png").getImage().getScaledInstance(434,561,3000);
		frame_new.getContentPane().setLayout(null);
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame_new.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
	}
}
