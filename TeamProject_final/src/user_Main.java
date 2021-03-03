import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class user_Main {

	private JFrame frame;


	public user_Main(String id) {
		initialize(id);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	private void initialize(String id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\user_main.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlightText);
		panel.setLayout(null);
		panel.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(panel);
		panel.setOpaque(false); 
		
		JButton btn_newbook_buy = new JButton("");
		btn_newbook_buy.setBackground(Color.WHITE);
		
		btn_newbook_buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_new_book_list(id);
				frame.dispose();
			}
		});
		btn_newbook_buy.setFont(new Font("HY헤드라인M", Font.BOLD, 25));
		btn_newbook_buy.setBounds(0, 0, 434, 176);
		panel.add(btn_newbook_buy);
		
		JButton btn_oldbook_buy = new JButton("");
		btn_oldbook_buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_used_book_list(id);
				frame.dispose();
			}
		});
		btn_oldbook_buy.setFont(new Font("HY헤드라인M", Font.BOLD, 25));
		btn_oldbook_buy.setBounds(0, 176, 434, 171);
		panel.add(btn_oldbook_buy);
		btn_oldbook_buy.setBorderPainted(false);
		btn_oldbook_buy.setContentAreaFilled(false);
		btn_oldbook_buy.setFocusPainted(false);
		btn_oldbook_buy.setOpaque(false);   
		
		
		JButton btn_oldbook_sell = new JButton("");
		btn_oldbook_sell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_used_book_formulate(id);
				frame.dispose();
			}
		});
		btn_oldbook_sell.setFont(new Font("HY헤드라인M", Font.BOLD, 25));
		btn_oldbook_sell.setBounds(0, 349, 434, 166);
		panel.add(btn_oldbook_sell);
		btn_oldbook_sell.setBorderPainted(false);
		btn_oldbook_sell.setContentAreaFilled(false);
		btn_oldbook_sell.setFocusPainted(false);
		btn_oldbook_sell.setOpaque(false);   
		panel.setOpaque(false);   
		
		btn_newbook_buy.setBorderPainted(false);
		btn_newbook_buy.setContentAreaFilled(false);
		btn_newbook_buy.setFocusPainted(false);
		btn_newbook_buy.setOpaque(false);
		
		JPanel panel_home = new JPanel();
		panel_home.setLayout(null);
		panel_home.setBounds(0, 505, 434, 56);
		panel.add(panel_home);
		panel_home.setOpaque(false);
		
		JButton btn_into = new JButton("");
		btn_into.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new user_info(id);
			}
		});
		btn_into.setBounds(0, 10, 217, 46);
		panel_home.add(btn_into);
		btn_into.setBorderPainted(false);
		btn_into.setContentAreaFilled(false);
		btn_into.setFocusPainted(false);
		btn_into.setOpaque(false);   
		
		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login_Main.main(null);
			}
		});
		btn_back.setBounds(217, 10, 217, 46);
		panel_home.add(btn_back);
		btn_back.setBorderPainted(false);
		btn_back.setContentAreaFilled(false);
		btn_back.setFocusPainted(false);
		btn_back.setOpaque(false);
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
	}

}
