package src.Model;

public class JoinVO {
	
	private String t_id;
	private String t_pw;
	private String t_name;
	private String t_subject;
	private String t_code;

	public JoinVO() {
	}

	public JoinVO(String t_id, String t_pw) {
		super();
		this.t_id = t_id;
		this.t_pw = t_pw;
	}

	
	public JoinVO(String t_id, String t_pw, String t_name, String t_subject, String t_day) {
		super();
		this.t_id = t_id;
		this.t_pw = t_pw;
		this.t_name = t_name;
		this.t_subject = t_subject;
	}

	public JoinVO(String t_id, String t_pw, String t_name, String t_subject, String t_code, String t_day) {
		super();
		this.t_id = t_id;
		this.t_pw = t_pw;
		this.t_name = t_name;
		this.t_subject = t_subject;
		this.t_code = t_code;
	}

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public String getT_pw() {
		return t_pw;
	}

	public void setT_pw(String t_pw) {
		this.t_pw = t_pw;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getT_subject() {
		return t_subject;
	}

	public void setT_subject(String t_subject) {
		this.t_subject = t_subject;
	}

	public String getT_code() {
		return t_code;
	}

	public void setT_code(String t_code) {
		this.t_code = t_code;
	}

	
	
}
