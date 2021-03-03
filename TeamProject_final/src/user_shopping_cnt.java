import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;

public class user_shopping_cnt {

	private JFrame frame;
	
	public user_shopping_cnt(String book_id_select, String book_rtg, int book_qnt_select, String id, String state) {
		initialize(book_id_select, book_rtg, book_qnt_select, id, state);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String book_id_select, String book_rtg, int book_qnt_select, String id, String state) {
		frame = new JFrame();
		frame.setBounds(100, 100, 214, 186);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(113, 42, 54, 22);
		frame.getContentPane().add(spinner);
		
		JLabel lblNewLabel = new JLabel("\uAD6C\uB9E4\uC218\uB7C9");
		lblNewLabel.setBounds(24, 41, 57, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\uC7A5\uBC14\uAD6C\uB2C8 \uB123\uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int buy_cnt = (int)spinner.getModel().getValue();
				if(buy_cnt <= book_qnt_select) {
					new user_shopping_basket(book_id_select, book_rtg, buy_cnt, id, state);
					if(state.equals("new")) {
						user_new_book_list.frame_new.dispose();
					}
					else {
						user_used_book_list.frame_used.dispose();
					}
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "구매수량이 재고를 초과하였습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(37, 74, 123, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uB3CC\uC544\uAC00\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(37, 107, 123, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("\uC7AC\uACE0\uC218\uB7C9");
		lblNewLabel_1.setBounds(24, 10, 57, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblqnt = new JLabel("0");
		lblqnt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblqnt.setBounds(110, 10, 57, 22);
		frame.getContentPane().add(lblqnt);
		
		lblqnt.setText(""+book_qnt_select);
		
		String url = this.getClass().getResource("").getPath();
		Image img = new ImageIcon(url+"image\\cnt.png").getImage().getScaledInstance(199, 148, 3000);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 199, 148);
		frame.getContentPane().add(lbl_image);
		lbl_image.setIcon(new ImageIcon(img));
	}
}
