import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;



public class checklist_DAO {

   private Connection conn = null;
   private PreparedStatement psmt = null;
   private ResultSet rs = null;

   public void getConnection() {

      // 1. 드라이버 로딩
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String user = "hr";
      String password = "hr";

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");

         conn = DriverManager.getConnection(url, user, password);
      } catch (ClassNotFoundException e) {

         e.printStackTrace();
      }

      catch (SQLException e) {

         e.printStackTrace();
      }

   }

   public void getClose() {

      try {
         if (rs != null)
            rs.close();
         if (psmt != null)
            psmt.close();
         if (conn != null)
            conn.close();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   public int Insert(checklist_VO vo) {

      int result = 0;
      getConnection();
      // 1. 드라이버로딩 2. Connection객체 생성 끝

      String sql = "Insert into buy_history values (?,?,?,?,?)";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, vo.getMember_id());
         psmt.setString(2, vo.getBook_id());
         psmt.setString(3, vo.getBook_name());
         psmt.setString(4, vo.getBook_rtg());
         psmt.setInt(5, vo.getBook_px());

         result = psmt.executeUpdate();

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();

      } finally {
         getClose();
      }
      return result;
   }

   public ArrayList<String> Select1() {

      ArrayList<String> resultList = new ArrayList<String>();
      getConnection();

      String sql = "Select * from new_books";

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();
         while (rs.next()) {
            String book_name = rs.getString(2);
            resultList.add(book_name);

         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         getClose();
      }
      return resultList;

   }

   public ArrayList<String> Select2() {

      ArrayList<String> resultList = new ArrayList<String>();
      getConnection();

      String sql = "Select * from new_books";

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();
         while (rs.next()) {
            String book_pay = rs.getString(4);

            resultList.add(book_pay);

         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         getClose();
      }
      return resultList;

   }

   public ArrayList<String> Select3() {

      ArrayList<String> resultList = new ArrayList<String>();
      getConnection();

      String sql = "Select * from new_books";

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();
         while (rs.next()) {

            String book_id = rs.getString(1);

            resultList.add(book_id);

         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         getClose();
      }
      return resultList;

   }
   
   public ArrayList<String> Select4() {

      ArrayList<String> resultList = new ArrayList<String>();
      getConnection();

      String sql = "Select * from new_books";

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();
         while (rs.next()) {

            String book_sale = rs.getString(8);

            resultList.add(book_sale);

         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         getClose();
      }
      return resultList;

   }

}