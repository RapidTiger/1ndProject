import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class Login_Main {

	private JFrame frame;
	private JTextField txt_id;
	private JTextField txt_pw;
	private JTextField txt_name;
	private  login_main_DAO dao = new login_main_DAO();
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Main window = new Login_Main();
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
	public Login_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		txt_id = new JTextField();
		txt_id.setBounds(129, 360, 238, 26);
		frame.getContentPane().add(txt_id);
		txt_id.setColumns(10);
		
		txt_pw = new JPasswordField();
		txt_pw.setColumns(10);
		txt_pw.setBounds(129, 397, 238, 26);
		frame.getContentPane().add(txt_pw);
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn_Login = new JButton("");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 1. textfield에 있는 id와 pw를 가지고온다.
				// 2. 가지고 온 id와 pw를 이용해서 dao가 가지고 있는 login 기능을 수행한다.
				// 3. 로그인이 성공했다면, LoginSystem_login 창으로 이동한다.
				// ** 현재 창 닫지 않고 **
				if(txt_id.getText().equals("admin") &&  txt_pw.getText().equals("admin")) {
					new admin_Main().main(null);
					frame.dispose();
				}
				else {
					String id = txt_id.getText();
					String pw = txt_pw.getText();
					
					int cnt = dao.Login(new login_main_VO(id,pw));
					// 필드에 dao선언 안되어 있다면 선언 해주기~
					//	private  DAO dao = new DAO();
					if (cnt>0){
						new user_Main(id);
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 확인해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
					}
					// 사용자 정보인 이름을 LoginSystem_login 창에서 출력
					// lbl_name -> setText();
					
					//어디로 가야 될지 몰라서 일단 현재 창 닫고 유저  새책 리스트로 보냄
				}
			}
		});
		
		
		btn_Login.setFont(new Font("굴림", Font.BOLD, 16));
		btn_Login.setBounds(227, 436, 147, 29);
		frame.getContentPane().add(btn_Login);
		btn_Login.setBorderPainted(false);
		btn_Login.setContentAreaFilled(false);
		btn_Login.setFocusPainted(false);
		btn_Login.setOpaque(false); 
		
		JButton btn_Join = new JButton("");
		btn_Join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login_Join.main(null);
			}
		});
		btn_Join.setFont(new Font("굴림", Font.BOLD, 16));
		btn_Join.setBounds(59, 436, 148, 30);
		frame.getContentPane().add(btn_Join);
		btn_Join.setBorderPainted(false);
	    btn_Join.setContentAreaFilled(false);
	    btn_Join.setFocusPainted(false);
	    btn_Join.setOpaque(false); 
		
		
		
		String url1 = this.getClass().getResource("").getPath();
		Image image1 = new ImageIcon(url1+"image\\ab.gif").getImage().getScaledInstance(410, 80,Image.SCALE_DEFAULT);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 472, 408, 73);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(image1));
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\Login.png").getImage().getScaledInstance(434,561,3000);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 561);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
		
	}
}
