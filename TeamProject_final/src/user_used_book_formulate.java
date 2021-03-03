import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.w3c.dom.Text;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class user_used_book_formulate {

	private JFrame frame;
	JComboBox comboBox;

	checklist_DAO dao = new checklist_DAO();


	public user_used_book_formulate(String id) {
		initialize(id);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	   int rank1 = 0;
	   int rank2 = 0;
	   int rank3 = 0;
	   int rank4 = 0;
	   int rank5 = 0;
	   int rank = 0;
	   int result = 0;
	   int num = 0;

	   String Printrank = "";
	   String name = "";
	   String book_name = "";

	   ArrayList<String> bookname = dao.Select1();
	   ArrayList<String> pay = dao.Select2();
	   ArrayList<String> bookid = dao.Select3();

	   private final JTextField tf_search = new JTextField();

	   private void initialize(String id) {
	      frame = new JFrame();
	      frame.setBounds(100, 100, 450, 600);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().setLayout(null);

	      JButton btn_back_1 = new JButton("");
	      btn_back_1.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            new user_Main(id);
	            frame.dispose();
	         }
	      });
	      btn_back_1.setOpaque(false);
	      btn_back_1.setFocusPainted(false);
	      btn_back_1.setContentAreaFilled(false);
	      btn_back_1.setBorderPainted(false);
	      btn_back_1.setBounds(0, 513, 215, 47);
	      frame.getContentPane().add(btn_back_1);

	      tf_search.setBounds(90, 83, 267, 23);
	      frame.getContentPane().add(tf_search);
	      tf_search.setColumns(10);

	      JLabel lbl_Printrank = new JLabel();
	      lbl_Printrank.setHorizontalAlignment(SwingConstants.CENTER);
	      lbl_Printrank.setBounds(92, 436, 57, 23);
	      frame.getContentPane().add(lbl_Printrank);

	      JLabel lbl_Printamount = new JLabel("");
	      lbl_Printamount.setHorizontalAlignment(SwingConstants.CENTER);
	      lbl_Printamount.setBounds(241, 477, 52, 23);
	      frame.getContentPane().add(lbl_Printamount);

	      JLabel lbl_Printcost = new JLabel();
	      lbl_Printcost.setHorizontalAlignment(SwingConstants.CENTER);
	      lbl_Printcost.setBounds(91, 474, 59, 23);
	      frame.getContentPane().add(lbl_Printcost);

	      JLabel lbl_Printamount_difference = new JLabel("");
	      lbl_Printamount_difference.setHorizontalAlignment(SwingConstants.CENTER);
	      lbl_Printamount_difference.setBounds(240, 436, 53, 23);
	      frame.getContentPane().add(lbl_Printamount_difference);

	      comboBox = new JComboBox();

	      // -------------------------------------------------------------------------------------<
	      comboBox = new JComboBox<String>(bookname.toArray(new String[bookname.size()]));
	      // ------------------------------------------------------------------------------------->
	      comboBox.setBounds(90, 107, 329, 23);
	      frame.getContentPane().add(comboBox);

	      JButton btnNewButton = new JButton("");
	      btnNewButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	         }
	      });
	      // -------------------------------------------------------------------------------------<
	      btnNewButton.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseClicked(MouseEvent e) {
	            ArrayList<String> result = new ArrayList<String>();
	            for (String temp : bookname) {

	               if (temp.contains(tf_search.getText())) {
	                  result.add(temp);
	               }

	            }
	            comboBox.setModel(new DefaultComboBoxModel(result.toArray(new String[bookname.size()])));
	         }
	      });

	      comboBox.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            tf_search.setText((String) comboBox.getSelectedItem());
	         }
	      });
	      // ------------------------------------------------------------------------------------->

	      JButton btn_complite = new JButton("");
	      btn_complite.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {

	            rank = rank1 + rank2 + rank3 + rank4 + rank5;
	            if (rank >= 80) {
	               Printrank = "A";
	            } else if (rank < 80 && rank >= 60) {
	               Printrank = "B";
	            } else if (rank < 60 && rank >= 40) {
	               Printrank = "C";
	            } else {
	               Printrank = "F";
	            }

	            lbl_Printrank.setText(Printrank);

	            ArrayList<String> sales = dao.Select4();

	            for (int i = 0; i < bookname.size(); i++) {
	               if (bookname.get(i).equals(tf_search.getText())) {
	                  book_name = bookname.get(i);
	                  num = i;

	                  int oldbookpay = Integer.parseInt(pay.get(i)) * 60 / 100; // 중고책 원가

	                  if (Integer.parseInt(sales.get(i)) >= 500) {
	                     if (Printrank == "B") {
	                        result = oldbookpay * 90 / 100 + (oldbookpay * 90 / 100) * 20 / 100;
	                     } else if (Printrank == "C") {
	                        result = oldbookpay * 80 / 100+ (oldbookpay * 80 / 100) * 20 / 100;
	                     } else if (Printrank == "F") {
	                        result = 0;
	                     } else {
	                        result = oldbookpay + oldbookpay * 20 / 100;
	                     }
	                  } else {
	                     if (Printrank == "B") {
	                        result = oldbookpay * 90 / 100;
	                     } else if (Printrank == "C") {
	                        result = oldbookpay * 80 / 100;
	                     } else if (Printrank == "F") {
	                        result = 0;
	                     } else {
	                        result = oldbookpay;
	                     }
	                  }

	                  lbl_Printamount.setText(Integer.toString(result) + "원");
	                  lbl_Printcost.setText(pay.get(i) + "원");
	                  lbl_Printamount_difference
	                        .setText((Integer.toString(Integer.parseInt(pay.get(i)) - result) + "원"));
	               }

	            }

	         }
	      });
	      btn_complite.setBounds(320, 432, 97, 31);
	      frame.getContentPane().add(btn_complite);
	      btn_complite.setBorderPainted(false);
	      btn_complite.setContentAreaFilled(false);
	      btn_complite.setFocusPainted(false);
	      btn_complite.setOpaque(false);

	      JButton btn_back = new JButton("");
	      btn_back.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            new user_Main(id);
	         }
	      });
	      btn_back.setBounds(218, 513, 215, 47);
	      frame.getContentPane().add(btn_back);
	      btn_back.setBorderPainted(false);
	      btn_back.setContentAreaFilled(false);
	      btn_back.setFocusPainted(false);
	      btn_back.setOpaque(false);

	      JPanel panel = new JPanel();
	      panel.setBackground(new Color(255, 255, 255));
	      panel.setLocation(31, 144);
	      panel.setSize(386, 266);

	      JPanel 스크롤패널 = new JPanel();
	      스크롤패널.setBounds(50, 70, 410, 1000);

	      JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	      jScrollPane.setViewportView(스크롤패널);
	      panel.add(jScrollPane, "name_202531857809300");

	      Dimension size = new Dimension();// 사이즈를 지정하기 위한 객체 생성

	      size.setSize(410, 1000);// 객체의 사이즈를 지정

	      스크롤패널.setPreferredSize(size);// 사이즈 정보를 가지고 있는 객체를 이용해 패널의 사이즈 지정

	      jScrollPane.setViewportView(스크롤패널);
	      스크롤패널.setLayout(new GridLayout(5, 1, 0, 0));

	      jScrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 조절

	      JPanel panel_dsclr = new JPanel();
	      panel_dsclr.setBackground(new Color(255, 255, 255));
	      panel_dsclr.setLayout(null);
	      스크롤패널.add(panel_dsclr);

	      JRadioButton rd_dsclrA = new JRadioButton("\uBCC0\uC0C9 \uC5C6\uC74C");
	      rd_dsclrA.setBackground(new Color(255, 255, 255));
	      rd_dsclrA.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank1 = 20;
	         };
	      });
	      rd_dsclrA.setBounds(18, 50, 121, 23);
	      panel_dsclr.add(rd_dsclrA);

	      JRadioButton rd_dsclrB = new JRadioButton(
	            "\uD76C\uBBF8\uD55C \uBCC0\uC0C9\uC774\uB098 \uC791\uC740 \uC5BC\uB8E9\uC774 \uC788\uC74C");
	      rd_dsclrB.setBackground(new Color(255, 255, 255));
	      rd_dsclrB.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank1 = 15;
	         };
	      });
	      rd_dsclrB.setBounds(18, 90, 318, 23);
	      panel_dsclr.add(rd_dsclrB);

	      JRadioButton rd_dsclrC = new JRadioButton(
	            "\uC804\uCCB4\uC801\uC778 \uBCC0\uC0C9\uC73C\uB85C \uB3C5\uC11C\uC5D0 \uC601\uD5A5\uC744 \uC90C");
	      rd_dsclrC.setBackground(new Color(255, 255, 255));
	      rd_dsclrC.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank1 = 10;
	         }
	      });
	      rd_dsclrC.setBounds(18, 130, 291, 23);
	      panel_dsclr.add(rd_dsclrC);

	      JRadioButton rd_dsclrF = new JRadioButton("\uC2EC\uD55C \uBCC0\uC0C9");
	      rd_dsclrF.setBackground(new Color(255, 255, 255));
	      rd_dsclrF.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank1 = 5;
	         }
	      });
	      rd_dsclrF.setBounds(18, 170, 121, 23);
	      panel_dsclr.add(rd_dsclrF);

	      ButtonGroup Discoloration1 = new ButtonGroup();
	      Discoloration1.add(rd_dsclrA);
	      Discoloration1.add(rd_dsclrB);
	      Discoloration1.add(rd_dsclrC);
	      Discoloration1.add(rd_dsclrF);

	      JLabel lbl_dsclr = new JLabel("\uBCC0\uC0C9");
	      lbl_dsclr.setBackground(new Color(255, 255, 255));
	      lbl_dsclr.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
	      lbl_dsclr.setBounds(18, 10, 121, 32);
	      panel_dsclr.add(lbl_dsclr);

	      JPanel panel_tear = new JPanel();
	      panel_tear.setBackground(new Color(255, 255, 255));
	      panel_tear.setLayout(null);
	      스크롤패널.add(panel_tear);

	      JRadioButton rd_tearA = new JRadioButton("\uCC22\uC5B4\uC9D0 \uC5C6\uC74C");
	      rd_tearA.setBackground(new Color(255, 255, 255));
	      rd_tearA.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank2 = 20;
	         }
	      });
	      rd_tearA.setBounds(18, 50, 121, 23);
	      panel_tear.add(rd_tearA);

	      JRadioButton rd_tearB = new JRadioButton("2cm \uC774\uD558\uC758 \uCC22\uC5B4\uC9D0");
	      rd_tearB.setBackground(new Color(255, 255, 255));
	      rd_tearB.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank2 = 10;
	         }
	      });
	      rd_tearB.setBounds(18, 90, 291, 23);
	      panel_tear.add(rd_tearB);

	      JRadioButton rd_tearF = new JRadioButton("2cm \uCD08\uACFC\uC758 \uCC22\uC5B4\uC9D0");
	      rd_tearF.setBackground(new Color(255, 255, 255));
	      rd_tearF.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank2 = 0;
	         }
	      });
	      rd_tearF.setBounds(18, 130, 257, 23);
	      panel_tear.add(rd_tearF);

	      ButtonGroup Discoloration2 = new ButtonGroup();
	      Discoloration2.add(rd_tearA);
	      Discoloration2.add(rd_tearB);
	      Discoloration2.add(rd_tearF);

	      JLabel lbl_dsclr_1 = new JLabel("\uCC22\uC5B4\uC9D0");
	      lbl_dsclr_1.setBackground(new Color(255, 255, 255));
	      lbl_dsclr_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
	      lbl_dsclr_1.setBounds(18, 10, 121, 32);
	      panel_tear.add(lbl_dsclr_1);

	      JPanel panel_grft = new JPanel();
	      panel_grft.setBackground(new Color(255, 255, 255));
	      panel_grft.setLayout(null);
	      스크롤패널.add(panel_grft);

	      JRadioButton rd_grftA = new JRadioButton("\uB099\uC11C \uC5C6\uC74C");
	      rd_grftA.setBackground(new Color(255, 255, 255));
	      rd_grftA.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank3 = 20;
	         }
	      });
	      rd_grftA.setBounds(18, 50, 121, 23);
	      panel_grft.add(rd_grftA);

	      JRadioButton rd_grftB = new JRadioButton(
	            "5\uCABD \uC774\uD558\uC758 \uD544\uAE30 \uBC0F \uBC11\uC904 \uC788\uC74C");
	      rd_grftB.setBackground(new Color(255, 255, 255));
	      rd_grftB.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank3 = 10;
	         }
	      });
	      rd_grftB.setBounds(18, 90, 291, 23);
	      panel_grft.add(rd_grftB);

	      JRadioButton rd_grftF = new JRadioButton("5\uCABD \uCD08\uACFC \uB099\uC11C \uC788\uC74C");
	      rd_grftF.setBackground(new Color(255, 255, 255));
	      rd_grftF.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank3 = 0;
	         }
	      });
	      rd_grftF.setBounds(18, 130, 200, 23);
	      panel_grft.add(rd_grftF);

	      ButtonGroup Discoloration3 = new ButtonGroup();
	      Discoloration3.add(rd_grftA);
	      Discoloration3.add(rd_grftB);
	      Discoloration3.add(rd_grftF);

	      JLabel lbl_dsclr_1_1_2 = new JLabel("\uB099\uC11C");
	      lbl_dsclr_1_1_2.setBackground(new Color(255, 255, 255));
	      lbl_dsclr_1_1_2.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
	      lbl_dsclr_1_1_2.setBounds(18, 10, 121, 32);
	      panel_grft.add(lbl_dsclr_1_1_2);

	      JPanel panel_cover = new JPanel();
	      panel_cover.setBackground(new Color(255, 255, 255));
	      panel_cover.setLayout(null);
	      스크롤패널.add(panel_cover);

	      JRadioButton rd_coverY = new JRadioButton("\uAC89\uD45C\uC9C0 \uC788\uC74C");
	      rd_coverY.setBackground(new Color(255, 255, 255));
	      rd_coverY.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank4 = 20;
	         }
	      });
	      rd_coverY.setBounds(18, 50, 318, 23);
	      panel_cover.add(rd_coverY);

	      JRadioButton rd_coverN = new JRadioButton("\uAC89\uD45C\uC9C0 \uC5C6\uC74C");
	      rd_coverN.setBackground(new Color(255, 255, 255));
	      rd_coverN.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank4 = 10;
	         }
	      });
	      rd_coverN.setBounds(18, 90, 291, 23);
	      panel_cover.add(rd_coverN);

	      ButtonGroup Discoloration4 = new ButtonGroup();
	      Discoloration4.add(rd_coverY);
	      Discoloration4.add(rd_coverN);

	      JLabel lbl_dsclr_1_1_1 = new JLabel("\uAC89\uD45C\uC9C0");
	      lbl_dsclr_1_1_1.setBackground(new Color(255, 255, 255));
	      lbl_dsclr_1_1_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
	      lbl_dsclr_1_1_1.setBounds(18, 10, 121, 32);
	      panel_cover.add(lbl_dsclr_1_1_1);

	      JPanel panel_folding = new JPanel();
	      panel_folding.setBackground(new Color(255, 255, 255));
	      panel_folding.setLayout(null);
	      스크롤패널.add(panel_folding);

	      JRadioButton rd_foldingA = new JRadioButton("\uC811\uD78C \uD754\uC801 \uC5C6\uC74C");
	      rd_foldingA.setBackground(new Color(255, 255, 255));
	      rd_foldingA.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank5 = 20;
	         }
	      });
	      rd_foldingA.setBounds(18, 50, 121, 23);
	      panel_folding.add(rd_foldingA);

	      JRadioButton rd_foldingB = new JRadioButton(
	            "\uC544\uC8FC \uC57D\uAC04\uC758 \uC811\uD78C \uD754\uC801 \uC788\uC74C");
	      rd_foldingB.setBackground(new Color(255, 255, 255));
	      rd_foldingB.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank5 = 15;
	         }
	      });
	      rd_foldingB.setBounds(18, 90, 318, 23);
	      panel_folding.add(rd_foldingB);

	      JRadioButton rd_foldingC = new JRadioButton(
	            "\uCC45\uC758 \uAD6C\uACA8\uC9D0 \uB610\uB294 \uC811\uD78C \uD754\uC801\uC774 \uC120\uBA85\uD568");
	      rd_foldingC.setBackground(new Color(255, 255, 255));
	      rd_foldingC.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank5 = 10;
	         }
	      });
	      rd_foldingC.setBounds(18, 130, 291, 23);
	      panel_folding.add(rd_foldingC);

	      JRadioButton rd_foldingF = new JRadioButton(
	            "\uBD84\uCC45 \uB610\uB294 \uC811\uD798\uC73C\uB85C \uC778\uD55C \uBD88\uD3B8\uD568 \uBC1C\uC0DD");
	      rd_foldingF.setBackground(new Color(255, 255, 255));
	      rd_foldingF.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            rank5 = 5;
	         }
	      });
	      rd_foldingF.setBounds(18, 170, 260, 23);
	      panel_folding.add(rd_foldingF);

	      ButtonGroup Discoloration5 = new ButtonGroup();
	      Discoloration5.add(rd_foldingA);
	      Discoloration5.add(rd_foldingB);
	      Discoloration5.add(rd_foldingC);
	      Discoloration5.add(rd_foldingF);

	      JLabel lbl_dsclr_1_1 = new JLabel("\uC811\uD798");
	      lbl_dsclr_1_1.setBackground(new Color(255, 255, 255));
	      lbl_dsclr_1_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
	      lbl_dsclr_1_1.setBounds(18, 10, 121, 32);
	      panel_folding.add(lbl_dsclr_1_1);

	      jScrollPane.setBounds(50, 70, 300, 400);
	      jScrollPane.setBounds(50, 70, 300, 800);

	      frame.getContentPane().add(panel);
	      panel.setLayout(new CardLayout(0, 0));

	      btnNewButton.setBounds(359, 82, 59, 23);
	      frame.getContentPane().add(btnNewButton);
	      btnNewButton.setBorderPainted(false);
	      btnNewButton.setContentAreaFilled(false);
	      btnNewButton.setFocusPainted(false);
	      btnNewButton.setOpaque(false);

	      JButton btn_insert = new JButton("");
	      btn_insert.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            if (result != 0) {
	               checklist_VO vo = new checklist_VO(id, bookid.get(num), bookname.get(num), Printrank, result);
	               int cnt = dao.Insert(vo);
	               if (cnt > 0) {
	                  JOptionPane.showInternalMessageDialog(null, "판매 신청완료되었습니다.", "판매 신청",
	                        JOptionPane.INFORMATION_MESSAGE);
	                  new user_Main(id);
	                  frame.dispose();
	               } else {
	                  JOptionPane.showMessageDialog(null, "판매 신청실패하였습니다.", "판매 신청", JOptionPane.ERROR_MESSAGE);
	               }

	            } else {
	               JOptionPane.showMessageDialog(null, "판매 신청실패하였습니다.", "판매 신청", JOptionPane.ERROR_MESSAGE);

	            }
	         }
	      });
	      btn_insert.setBounds(321, 473, 97, 30);
	      frame.getContentPane().add(btn_insert);
	      btn_insert.setBorderPainted(false);
	      btn_insert.setContentAreaFilled(false);
	      btn_insert.setFocusPainted(false);
	      btn_insert.setOpaque(false);

	      String url = this.getClass().getResource("").getPath();
	      Image img = new ImageIcon(url + "image\\check.png").getImage().getScaledInstance(434, 561, 3000);
	      frame.getContentPane().setLayout(null);

	      JLabel lbl_image = new JLabel("");
	      lbl_image.setBounds(0, 0, 434, 561);
	      frame.getContentPane().add(lbl_image);
	      lbl_image.setIcon(new ImageIcon(img));

	   }
	}