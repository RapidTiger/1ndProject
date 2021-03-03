import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class login_main_DAO {// database access object?

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// 메소드 이름 : getConnection()
	// 리턴 타입은 없음
	// 매개 변수도 없음
	private void getConnection() {
		
		// 1. 드라이버 로딩
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 2. Connection 객체 생성

	}

	// 메소드 이름은 getClose()
	// 리턴타입 없음
	// 매개변수 없음
	private void getClose() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 회원가입하는 메소드를 생성
	// 메소드 이름은 Insert()
	// 리턴 타입은 int -> 행의 개수
	// 매개변수 String id, String pw, String name, String address, String phone


	public int Insert(login_main_VO vo) {
		int result = 0;
		getConnection();
		// 1. 드라이버로딩 2. Connection 객체 생성 끄읏~

		try {

			String sql = "insert into members values (?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTxt_id());
			psmt.setString(2, vo.getTxt_pw());
			result = psmt.executeUpdate();
			
			sql = "insert into buy_members values(?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTxt_id());
			psmt.setString(2, vo.getTxt_name());
			psmt.setString(3, vo.getTxt_address());
			psmt.setString(4, vo.getTxt_phone());
			result = psmt.executeUpdate();

		} catch (SQLException e) {
		} finally {
			getClose();
		}

		return result;
	}

	public int Login(login_main_VO vo) {
		getConnection();
		int cnt = 0;
		try {
			String sql = "select * from members where member_id = ? AND member_pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTxt_id());
			psmt.setString(2, vo.getTxt_pw());
			rs = psmt.executeQuery();
			if (rs.next()) {
				cnt++;
			}

		} catch (SQLException e) {
		} finally {
			getClose();
		}

		return cnt;
	}

	public int Delete(login_main_VO vo) {
		int result = 0;

		getConnection(); // 연결 ~

		try {
			String sql = "delete from members where member_id = ? and member_pw = ? and member_name = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTxt_id());
			psmt.setString(2, vo.getTxt_pw());
			psmt.setString(3, vo.getTxt_name());
			result = psmt.executeUpdate();

		} catch (SQLException e) {
		} finally {
			getClose();
		}

		return result;
	}
	
	public ArrayList<login_main_VO> Select() {
		
		ArrayList<login_main_VO> resultList = new ArrayList<login_main_VO>();
		
		getConnection();
		try {
			String sql="select * from members";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				//ID,PW,NAME,AGE
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				login_main_VO vo = new login_main_VO(id, pw, name, address,phone);
				resultList.add(vo);
				
			}
			
		} catch (SQLException e) {
		}finally {
			getClose();
		}
		
		return resultList;
	}


}
