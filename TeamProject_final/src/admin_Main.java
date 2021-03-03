
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class admin_Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_Main window = new admin_Main();
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
	public admin_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(panel);
		panel.setOpaque(false);
		
		JButton btn_newbook_admin = new JButton("");
		btn_newbook_admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_new_book_update().main(null);
				frame.dispose();
			}
		});
		btn_newbook_admin.setFont(new Font("HY헤드라인M", Font.BOLD, 25));
		btn_newbook_admin.setBounds(0, 0, 434, 172);
		panel.add(btn_newbook_admin);
		btn_newbook_admin.setBorderPainted(false);
		btn_newbook_admin.setContentAreaFilled(false);
		btn_newbook_admin.setFocusPainted(false);
		btn_newbook_admin.setOpaque(false); 
		
		JButton btn_oldbook_admin = new JButton("");
		btn_oldbook_admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_used_book_update().main(null);
				frame.dispose();
			}
		});
		btn_oldbook_admin.setFont(new Font("HY헤드라인M", Font.BOLD, 25));
		btn_oldbook_admin.setBounds(0, 173, 434, 176);
		panel.add(btn_oldbook_admin);
		btn_oldbook_admin.setBorderPainted(false);
		btn_oldbook_admin.setContentAreaFilled(false);
		btn_oldbook_admin.setFocusPainted(false);
		btn_oldbook_admin.setOpaque(false); 
		
		JButton btn_profit_admin = new JButton("");
		btn_profit_admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_record_managment().main(null);
				frame.dispose();
			}
		});
		btn_profit_admin.setFont(new Font("HY헤드라인M", Font.BOLD, 25));
		btn_profit_admin.setBounds(0, 348, 434, 168);
		panel.add(btn_profit_admin);
		btn_profit_admin.setBorderPainted(false);
		btn_profit_admin.setContentAreaFilled(false);
		btn_profit_admin.setFocusPainted(false);
		btn_profit_admin.setOpaque(false);
		panel.setOpaque(false);
		
		JButton btn_home = new JButton("");
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new admin_user_management().main(null);
				frame.dispose();
			}
		});
		btn_home.setBounds(0, 516, 217, 45);
		panel.add(btn_home);
		btn_home.setBorderPainted(false);
		btn_home.setContentAreaFilled(false);
		btn_home.setFocusPainted(false);
		btn_home.setOpaque(false); 
		
		JButton btn_back = new JButton("");
		btn_back.setBounds(210, 516, 224, 45);
		panel.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login_Main.main(null);
			}
		});
		btn_back.setBorderPainted(false);
		btn_back.setContentAreaFilled(false);
		btn_back.setFocusPainted(false);
		btn_back.setOpaque(false); 
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\admin_main.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
	}
}
