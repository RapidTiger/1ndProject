import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class user_shopping_basket {

	private JFrame frame;
	private java.sql.Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private ResultSet rs2 = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private String[] col = { "도서명", "저자", "등급", "수량", "가격" };
	private String[][] row = new String[20][5];
	private String[] book = new String[20];
	private String id = "", name = "", writer = "", rtg = "";
	private int px = 0;
	private JTable table;
	private int total = 0;
	private int cnt = 0;
	private int qnt = 0;
	private boolean run = true;

	public user_shopping_basket(String book_id_select, String book_rtg, int buy_cnt, String user_id, String state) {
		initialize(book_id_select, book_rtg, buy_cnt, user_id, state);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void initialize(String book_id_select, String book_rtg, int buy_cnt, String user_id, String state) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		if (book_id_select == null) {
			book_id_select = "";
		}
		// 임시 테이블 생성
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "create table " + user_id + "basket (" + "book_id varchar2(5)," + "book_name varchar2(100),"
					+ "book_writer varchar2(50)," + "book_rtg varchar2(3)," + "book_cnt number(3),"
					+ "book_px number(7)," + "book_qnt number(3)," + "CONSTRAINT	" + user_id
					+ "basket_pk PRIMARY KEY(book_id, book_rtg))";
			psmt = conn.prepareStatement(sql);
			int cnt = psmt.executeUpdate();
			int i = 0;
		} catch (SQLException e) {
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}

		// 장바구니 추가
		if (((!book_id_select.equals("")) && book_rtg.equals(""))) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
			}
			try {
				conn = DriverManager.getConnection(url, user, password);

				String sql = "select book_id, book_name, book_writer, book_px, book_qnt from new_books where book_id = '"
						+ book_id_select + "'";
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {
					id = rs.getString(1);
					name = rs.getString(2);
					writer = rs.getString(3);
					rtg = " ";
					px = rs.getInt(4);
					qnt = rs.getInt(5);
				}

			} catch (SQLException e) {
			} finally {
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
				}
			}
		} else if (!book_id_select.equals("")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
			}
			try {
				conn = DriverManager.getConnection(url, user, password);

				String sql = "select book_id, book_name, book_writer, book_rtg, book_px, book_qnt from old_books where book_id = '"
						+ book_id_select + "'";
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {
					id = rs.getString(1);
					name = "(중고)" + rs.getString(2);
					writer = rs.getString(3);
					rtg = book_rtg;
					px = rs.getInt(5);
					qnt = rs.getInt(6);
				}

			} catch (SQLException e) {
			} finally {
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
				}
			}
		}

		// 추가
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "insert into " + user_id + "basket values(?,?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, name);
			psmt.setString(3, writer);
			psmt.setString(4, rtg);
			psmt.setInt(5, buy_cnt);
			psmt.setInt(6, px * buy_cnt);
			psmt.setInt(7, qnt);
			int cnt = psmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}

		// 출력
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		}
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sql = "select * from " + user_id + "basket";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				String name_1 = "";
				int cnt = 0;
				if(rs.getString(2).contains("(중고)")) {
					sql = "select book_qnt from old_books where book_id = '" + rs.getString(1) + "'";
					psmt = conn.prepareStatement(sql);
					rs2 = psmt.executeQuery();
					rs2.next();
					cnt = rs2.getInt(1);
				}
				else {
					sql = "select book_qnt from new_books where book_id = '" + rs.getString(1) + "'";
					psmt = conn.prepareStatement(sql);
					rs2 = psmt.executeQuery();
					rs2.next();
					cnt = rs2.getInt(1);
				}
				if (cnt > 0) {
					name_1 = rs.getString(2);
				} else {
					name_1 = rs.getString(2) + "(매진)";
					run = false;
				}
				book[i] = rs.getString(1);
				row[i][0] = name_1;
				row[i][1] = rs.getString(3);
				row[i][2] = rs.getString(4);
				total += rs.getInt(6);
				row[i][3] = rs.getInt(5) + "";
				row[i++][4] = rs.getString(6) + "원";
			}

		} catch (SQLException e) {
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setOpaque(false);

		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(state.equals("new")) {
					new user_new_book_list(user_id);
				}
				else {
					new user_used_book_list(user_id);
				}
				frame.dispose();
			}
		});
		btn_back.setBounds(215, 515, 219, 46);
		panel.add(btn_back);
		btn_back.setBorderPainted(false);
		btn_back.setContentAreaFilled(false);
		btn_back.setFocusPainted(false);
		btn_back.setOpaque(false); 

		JButton btn_back_1 = new JButton("");
		btn_back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// 판매이력 추가
				// 임시 테이블 삭제
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					String sql = "select * from " + user_id + "basket";
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					if (run) {
						String name = "";
						while (rs.next()) {
							name = rs.getString(2);
							int result;
							if (name.contains("(중고)")) {
								sql = "update old_books set book_qnt = book_qnt - ? where book_id = ? and book_rtg = ? and book_qnt - ? >= 0";
								psmt = conn.prepareStatement(sql);
								psmt.setInt(1, Integer.parseInt(rs.getString(5)));
								psmt.setString(2, rs.getString(1));
								psmt.setString(3, rs.getString(4));
								psmt.setInt(4, Integer.parseInt(rs.getString(5)));
								result = psmt.executeUpdate();
							} else {
								sql = "update new_books set book_qnt = book_qnt - ? where book_id = ? and book_qnt - ? >= 0";
								psmt = conn.prepareStatement(sql);
								psmt.setInt(1, Integer.parseInt(rs.getString(5)));
								psmt.setString(2, rs.getString(1));
								psmt.setString(3, rs.getString(5));
								result = psmt.executeUpdate();

								sql = "update new_books set book_sales = book_sales + ? where book_id = ?";
								psmt = conn.prepareStatement(sql);
								psmt.setInt(1, Integer.parseInt(rs.getString(6)));
								psmt.setString(2, rs.getString(1));
								result = psmt.executeUpdate();
							}
							sql = "insert into sales_history values(?,?,?,?,?,?)";
							psmt = conn.prepareStatement(sql);
							psmt.setString(1, user_id);
							psmt.setString(2, rs.getString(1));
							psmt.setString(3, rs.getString(2));
							psmt.setString(4, rs.getString(4));
							psmt.setInt(5, rs.getInt(5));
							psmt.setInt(6, rs.getInt(6));
							result = psmt.executeUpdate();

						}
						
						sql = "drop table " + user_id + "basket cascade constraints";
						psmt = conn.prepareStatement(sql);
						int cnt = psmt.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"구매가 완료되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
						if(state.equals("new")) {
							new user_new_book_list(user_id);
						}
						else {
							new user_used_book_list(user_id);
						}
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"매진된 상품이 포함되어있습니다.", "경고", JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e) {
				} finally {
					try {
						if (psmt != null) {
							psmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
					}
				}
			}
		});
		btn_back_1.setBounds(253, 475, 134, 30);
		panel.add(btn_back_1);
		btn_back_1.setBorderPainted(false);
		btn_back_1.setContentAreaFilled(false);
	    btn_back_1.setFocusPainted(false);
	    btn_back_1.setOpaque(false); 

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(48, 108, 336, 324);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 336, 347);
		panel_1.add(scrollPane);

		table = new JTable(row, col);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setShowGrid(false);

		JLabel lbl_total = new JLabel("0");
		lbl_total.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_total.setBounds(226, 441, 149, 15);
		panel.add(lbl_total);
		table.getColumn(col[0]).setPreferredWidth(200);
		table.getColumn(col[1]).setPreferredWidth(50);
		table.getColumn(col[2]).setPreferredWidth(30);
		table.getColumn(col[3]).setPreferredWidth(30);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		lbl_total.setText("합계 : " + total + "원");

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				run = true;
				String id = book[table.getSelectedRow()];
				if (!(id == null)) {
					for (int i = 0; i < 20; i++) {
						row[i][0] = null;
						row[i][1] = null;
						row[i][2] = null;
						row[i][3] = null;
						row[i][4] = null;
					}
					total = 0;
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e) {
					}
					try {
						conn = DriverManager.getConnection(url, user, password);
						String sql = "delete from " + user_id + "basket where book_id = '" + id + "'";
						psmt = conn.prepareStatement(sql);
						int cnt = psmt.executeUpdate();

						sql = "select * from " + user_id + "basket";
						psmt = conn.prepareStatement(sql);
						rs = psmt.executeQuery();
						int i = 0;
						while (rs.next()) {
							String name_1 = "";
							if (rs.getInt(7) > 0) {
								name_1 = rs.getString(2);
							} else {
								name_1 = rs.getString(2) + "(매진)";
								run = false;
							}
							book[i] = rs.getString(1);
							row[i][0] = name_1;
							row[i][1] = rs.getString(3);
							row[i][2] = rs.getString(4);
							total += rs.getInt(6);
							row[i][3] = rs.getString(5);
							row[i++][4] = rs.getString(6) + "원";
						}

					} catch (SQLException e) {
					} finally {
						try {
							if (psmt != null) {
								psmt.close();
							}
							if (conn != null) {
								conn.close();
							}
						} catch (SQLException e) {
						}
					}
					int[] i = { 254, 255 };
					table.setBounds(0, 0, 354, i[cnt++ % 2]);
					lbl_total.setText("합계 : " + total + "원");
				}
			}
		});
		btnNewButton.setBounds(48, 475, 134, 30);
		panel.add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false); 
		
		JButton btn_back_2 = new JButton("");
		btn_back_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_Main(user_id);
				frame.dispose();
			}
		});
		btn_back_2.setOpaque(false);
		btn_back_2.setFocusPainted(false);
		btn_back_2.setContentAreaFilled(false);
		btn_back_2.setBorderPainted(false);
		btn_back_2.setBounds(0, 515, 219, 46);
		panel.add(btn_back_2);

		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn(col[1]).setCellRenderer(center);
		table.getColumn(col[2]).setCellRenderer(center);
		table.getColumn(col[3]).setCellRenderer(center);
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumn(col[4]).setCellRenderer(right);
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\basket.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));

	}
}
