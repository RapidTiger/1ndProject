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

	// �޼ҵ� �̸� : getConnection()
	// ���� Ÿ���� ����
	// �Ű� ������ ����
	private void getConnection() {
		
		// 1. ����̹� �ε�
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

		// 2. Connection ��ü ����

	}

	// �޼ҵ� �̸��� getClose()
	// ����Ÿ�� ����
	// �Ű����� ����
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
	// ȸ�������ϴ� �޼ҵ带 ����
	// �޼ҵ� �̸��� Insert()
	// ���� Ÿ���� int -> ���� ����
	// �Ű����� String id, String pw, String name, String address, String phone


	public int Insert(login_main_VO vo) {
		int result = 0;
		getConnection();
		// 1. ����̹��ε� 2. Connection ��ü ���� ����~

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

		getConnection(); // ���� ~

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
