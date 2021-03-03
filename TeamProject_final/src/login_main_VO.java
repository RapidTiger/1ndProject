
public class login_main_VO {

	// VO -> Value Object / DTO => Data Transfer Object
	// 아이디,비밀번호,이름,나이 field를 작성!!
	// 전부 private 으로 걸어주세요~
	// 게터 앤 세터도 만들기~

	private String txt_id;
	private String txt_pw;
	private String txt_name;
	private String txt_address;
	private String txt_phone;

	public login_main_VO(String txt_id, String txt_pw, String txt_name, String txt_address, String txt_phone) {
		super();
		this.txt_id = txt_id;
		this.txt_pw = txt_pw;
		this.txt_name = txt_name;
		this.txt_address = txt_address;
		this.txt_phone = txt_phone;
	}



	public login_main_VO(String txt_id, String txt_pw) {
		super();
		this.txt_id = txt_id;
		this.txt_pw = txt_pw;
	}



	public String getTxt_id() {
		return txt_id;
	}

	public void setTxt_id(String txt_id) {
		this.txt_id = txt_id;
	}

	public String getTxt_pw() {
		return txt_pw;
	}

	public void setTxt_pw(String txt_pw) {
		this.txt_pw = txt_pw;
	}

	public String getTxt_name() {
		return txt_name;
	}

	public void setTxt_name(String txt_name) {
		this.txt_name = txt_name;
	}

	public String getTxt_address() {
		return txt_address;
	}

	public void setTxt_address(String txt_address) {
		this.txt_address = txt_address;
	}

	public String getTxt_phone() {
		return txt_phone;
	}

	public void setTxt_phone(String txt_phone) {
		this.txt_phone = txt_phone;
	}

}
