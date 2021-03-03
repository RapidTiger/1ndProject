import java.awt.EventQueue;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin_record_managment {

	private JFrame frame;
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private JTable table;
	private JTable table_1;
	private String[] col_buy = {"회원ID", "도서번호", "도서명", "등급", "가격"};
	private String[][] row_buy = new String[100][5];
	private String[] col_sale = {"회원ID", "도서번호", "도서명", "등급", "개수", "가격"};
	private String[][] row_sale = new String[100][6];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_record_managment window = new admin_record_managment();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public admin_record_managment() {
		initialize();
	}

	private void initialize() {
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		center.setHorizontalAlignment(SwingConstants.CENTER);
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(panel);
		panel.setOpaque(false);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 79, 410, 426);
		panel.add(tabbedPane);
		
		JPanel 구매이력 = new JPanel();
		tabbedPane.addTab("\uAD6C\uB9E4\uC774\uB825", null, 구매이력, null);
		구매이력.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 381, 467);
		구매이력.add(scrollPane);
		
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) { 
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from buy_history";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				row_buy[i][0] = rs.getString(1);
				row_buy[i][1] = rs.getString(2);
				row_buy[i][2] = rs.getString(3);
				row_buy[i][3] = rs.getString(4);
				row_buy[i++][4] = rs.getInt(5) + "원";
			}
		} catch (SQLException e) {
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
			}
		}
		
		table = new JTable(row_buy, col_buy);
		scrollPane.setViewportView(table);
		
		table.getColumn(col_buy[0]).setPreferredWidth(60);
		table.getColumn(col_buy[1]).setPreferredWidth(70);
		table.getColumn(col_buy[2]).setPreferredWidth(180);
		table.getColumn(col_buy[3]).setPreferredWidth(35);
		table.getColumn(col_buy[0]).setCellRenderer(center);
		table.getColumn(col_buy[1]).setCellRenderer(center);
		table.getColumn(col_buy[3]).setCellRenderer(center);
		table.getColumn(col_buy[4]).setCellRenderer(right);
		
		
		
		JPanel 판매이력 = new JPanel();
		tabbedPane.addTab("\uD310\uB9E4\uC774\uB825", null, 판매이력, null);
		판매이력.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 10, 381, 467);
		판매이력.add(scrollPane_1);
		
		
		
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) { 
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from sales_history";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				row_sale[i][0] = rs.getString(1);
				row_sale[i][1] = rs.getString(2);
				row_sale[i][2] = rs.getString(3);
				row_sale[i][3] = rs.getString(4);
				row_sale[i][4] = rs.getString(5);
				row_sale[i++][5] = rs.getString(6) + "원";
			}
		} catch (SQLException e) {
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
			}
		}
		
		
		table_1 = new JTable(row_sale, col_sale);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_Main().main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(226, 515, 208, 46);
		panel.add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		
		table_1.getColumn(col_sale[0]).setPreferredWidth(60);
		table_1.getColumn(col_sale[1]).setPreferredWidth(70);
		table_1.getColumn(col_sale[2]).setPreferredWidth(200);
		table_1.getColumn(col_sale[3]).setPreferredWidth(35);
		table_1.getColumn(col_sale[4]).setPreferredWidth(35);
		table_1.getColumn(col_sale[0]).setCellRenderer(center);
		table_1.getColumn(col_sale[1]).setCellRenderer(center);
		table_1.getColumn(col_sale[3]).setCellRenderer(center);
		table_1.getColumn(col_sale[4]).setCellRenderer(center);
		table_1.getColumn(col_sale[5]).setCellRenderer(right);
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\record.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
	}
}
