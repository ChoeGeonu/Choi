package model;
//관리자 로그인

public class LoginVO {
	private String id;

	public LoginVO() {
		super();
	}

	public LoginVO(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
