import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login_Join {

	private JFrame frame;
	private JTextField txt_id;
	private JTextField txt_pw;
	private JTextField txt_name;
	private JTextField txt_phone;
	private JTextField txt_address;
	private login_main_DAO dao = new login_main_DAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Join window = new Login_Join();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login_Join() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt_id = new JTextField();
		txt_id.setBounds(150, 257, 198, 27);
		frame.getContentPane().add(txt_id);
		txt_id.setColumns(10);
		
		txt_pw = new JPasswordField();
		txt_pw.setBounds(150, 294, 198, 27);
		frame.getContentPane().add(txt_pw);
		txt_pw.setColumns(10);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(150, 331, 198, 27);
		frame.getContentPane().add(txt_name);
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(150, 405, 198, 27);
		frame.getContentPane().add(txt_phone);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(150, 368, 198, 27);
		frame.getContentPane().add(txt_address);
		
		JButton btn_join = new JButton("");
//		btn_join.setBackground(Color.white);     new Color(255, 0, 0, 0)
		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//가입하기 버튼 눌렀을 때!
				String id = txt_id.getText();
				String pw = txt_pw.getText();
				String name = txt_name.getText();
				String address = txt_phone.getText();
				String phone = txt_address.getText();
				
				login_main_VO vo = new login_main_VO(id, pw ,name,address,phone);
				
				int cnt = dao.Insert(vo);
				
				//회원가입이 성공하면 main 창으로 이동하게 해주세요! 기존의 창은 닫고!!
				if (cnt>0) {
					frame.dispose();
					Login_Main.main(null);
				}else {
					//경고창(부모 컴포넌트, 출력하고 싶은 메시지, 경고창의 타이틀, 아이콘)
					JOptionPane.showMessageDialog(null, "이미 존재하는 ID이거나 회원정보를 잘못 기입했습니다.","경고",JOptionPane.ERROR_MESSAGE);
					
				}
				
				
				
				
				}
			});
		btn_join.setBorderPainted(false);
		btn_join.setContentAreaFilled(false);
		btn_join.setFocusPainted(false);
		btn_join.setOpaque(false);	
				
				
				
				
	
		btn_join.setFont(new Font("돋움", Font.BOLD, 16));
		btn_join.setBounds(150, 442, 198, 32);
		frame.getContentPane().add(btn_join);
		
		JButton btn_back = new JButton("");
		
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
		
		
		
		
		btn_back.setFont(new Font("돋움", Font.BOLD, 12));
		btn_back.setBounds(66, 443, 75, 31);
		frame.getContentPane().add(btn_back);
		
		String url1 = this.getClass().getResource("").getPath();
		Image image1 = new ImageIcon(url1+"image\\ab.gif").getImage().getScaledInstance(410, 80,Image.SCALE_DEFAULT);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 484, 408, 73);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(image1));
		
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\join.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
		
		
		
	}
}
