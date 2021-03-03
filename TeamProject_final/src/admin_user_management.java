import java.awt.EventQueue;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin_user_management {

	private JFrame frame;
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private String[] col = {"회원ID", "회원명", "전화번호","주소"};
	private String[][] row = new String [100][4];
	private JTable table;
	
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_user_management window = new admin_user_management();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public admin_user_management() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) { 
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from buy_members";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				row[i][0] = rs.getString(1);
				row[i][1] = rs.getString(2);
				row[i][2] = rs.getString(3);
				row[i++][3] = rs.getString(4);
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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_Main().main(null);
				frame.dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_Main().main(null);
				frame.dispose();
			}
		});
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(0, 516, 214, 45);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton.setBounds(220, 516, 214, 45);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 87, 371, 413);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(row, col);
		scrollPane.setViewportView(table);
		

		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\admin_user_management.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
		
		table.getColumn(col[0]).setPreferredWidth(4);
		table.getColumn(col[1]).setPreferredWidth(4);
		table.getColumn(col[2]).setPreferredWidth(40);
		table.getColumn(col[3]).setPreferredWidth(80);
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn(col[0]).setCellRenderer(center);
		table.getColumn(col[1]).setCellRenderer(center);
		table.getColumn(col[2]).setCellRenderer(center);
	}
}
