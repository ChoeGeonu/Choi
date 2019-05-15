package Model;

public class JoinVO {
	
	private String id;
	private String pw;
	private String name;
	private String subject;
	private String day;

	public JoinVO() {
	}

	public JoinVO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public JoinVO(String id, String pw, String name, String subject, String day) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.subject = subject;
		this.day = day;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	
	
}
