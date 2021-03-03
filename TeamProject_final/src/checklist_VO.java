import javax.swing.ComboBoxModel;

public class checklist_VO {

	// VO -> Value Object / DTO -> Data Transfer Object
	// 아이디, 비밀번호, 이름, 나이 field 작성
	// private
	
	
	private String member_id;
	private String book_id;
	private String book_name;
	private String book_rtg;
	private int book_px;
	
	public String getMember_id() {
		return member_id;
	}

	public String getBook_id() {
		return book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public String getBook_rtg() {
		return book_rtg;
	}

	public int getBook_px() {
		return book_px;
	}

	checklist_VO(String member_id, String book_id, String book_name, String book_rtg, int book_px) {
		this.member_id = member_id;
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_rtg = book_rtg;
		this.book_px = book_px;
	}
	
	// 생성자
	// getter, setter 메서드
	// alt + shift + s
	


}
