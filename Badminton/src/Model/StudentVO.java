package Model;

public class StudentVO {
	private int s_no;//학생코드
	private String s_name; //학생이름
	private int s_year; //학년
	private int s_ban; //반
	private int s_number; //출석번호
	private String s_gender; //성별
	private String s_phone; //휴대폰번호
	private String s_emergency; //비상연락망
	private String c_costfree; //수업료
	private int c_time;//수업시간
	private String s_experience;//본인경험
	private String c_level;//등급별 가격
	private String c_startdate;//시작일
	private String c_enddate;//종료일
	private String s_email;//부모님이메일
	private String s_image =null;//이미지 파일 경로
	
	
	
	public StudentVO() {
		super();
	}


	public StudentVO(int s_no, String s_name, int s_year, int s_ban, int s_number, String s_gender, String s_phone,
			String s_emergency, String c_costfree, int c_time, String s_experience, String c_level, String c_startdate,
			String c_enddate, String s_email, String s_image) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_ban = s_ban;
		this.s_number = s_number;
		this.s_gender = s_gender;
		this.s_phone = s_phone;
		this.s_emergency = s_emergency;
		this.c_costfree = c_costfree;
		this.c_time = c_time;
		this.s_experience = s_experience;
		this.c_level = c_level;
		this.c_startdate = c_startdate;
		this.c_enddate = c_enddate;
		this.s_email = s_email;
		this.s_image = s_image;
	}

	
	public StudentVO(String s_name, int s_year, int s_ban, int s_number, String s_gender, String s_phone,
			String s_emergency, String c_costfree, int c_time, String s_experience, String c_level, String c_startdate,
			String c_enddate, String s_email, String s_image) {
		super();
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_ban = s_ban;
		this.s_number = s_number;
		this.s_gender = s_gender;
		this.s_phone = s_phone;
		this.s_emergency = s_emergency;
		this.c_costfree = c_costfree;
		this.c_time = c_time;
		this.s_experience = s_experience;
		this.c_level = c_level;
		this.c_startdate = c_startdate;
		this.c_enddate = c_enddate;
		this.s_email = s_email;
		this.s_image = s_image;
	}


//무료일때 이미지 x, 초급중급고급 수업료 선택 x
public StudentVO(String s_name, int s_year, int s_ban, int s_number, String s_gender, String s_phone,
		String s_emergency, String c_costfree, int c_time, String s_experience, String c_startdate,
		String c_enddate, String s_email) {
	super();
	this.s_name = s_name;
	this.s_year = s_year;
	this.s_ban = s_ban;
	this.s_number = s_number;
	this.s_gender = s_gender;
	this.s_phone = s_phone;
	this.s_emergency = s_emergency;
	this.c_costfree = c_costfree;
	this.c_time = c_time;
	this.s_experience = s_experience;
	this.c_startdate = c_startdate;
	this.c_enddate = c_enddate;
	this.s_email = s_email;
}
//유료이지만 이미지가없을경우
	public StudentVO(String s_name, int s_year, int s_ban, int s_number, String s_gender, String s_phone,
			String s_emergency, String c_costfree, int c_time, String s_experience, String c_level, String c_startdate,
			String c_enddate, String s_email) {
		super();
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_ban = s_ban;
		this.s_number = s_number;
		this.s_gender = s_gender;
		this.s_phone = s_phone;
		this.s_emergency = s_emergency;
		this.c_costfree = c_costfree;
		this.c_time = c_time;
		this.s_experience = s_experience;
		this.c_level = c_level;
		this.c_startdate = c_startdate;
		this.c_enddate = c_enddate;
		this.s_email = s_email;
	}

	//접근자 설정자
	
	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public int getS_year() {
		return s_year;
	}

	public void setS_year(int s_year) {
		this.s_year = s_year;
	}

	public int getS_ban() {
		return s_ban;
	}

	public void setS_ban(int s_ban) {
		this.s_ban = s_ban;
	}

	public int getS_number() {
		return s_number;
	}

	public void setS_number(int s_number) {
		this.s_number = s_number;
	}

	public String getS_gender() {
		return s_gender;
	}

	public void setS_gender(String s_gender) {
		this.s_gender = s_gender;
	}

	public String getS_phone() {
		return s_phone;
	}

	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}

	public String getS_emergency() {
		return s_emergency;
	}

	public void setS_emergency(String s_emergency) {
		this.s_emergency = s_emergency;
	}

	

	public int getC_time() {
		return c_time;
	}

	public void setC_time(int c_time) {
		this.c_time = c_time;
	}

	public String getS_experience() {
		return s_experience;
	}

	public void setS_experience(String s_experience) {
		this.s_experience = s_experience;
	}

	public String getC_level() {
		return c_level;
	}

	public void setC_level(String c_level) {
		this.c_level = c_level;
	}

	public String getC_startdate() {
		return c_startdate;
	}

	public void setC_startdate(String c_startdate) {
		this.c_startdate = c_startdate;
	}

	public String getC_enddate() {
		return c_enddate;
	}

	public void setC_enddate(String c_enddate) {
		this.c_enddate = c_enddate;
	}

	public String getS_email() {
		return s_email;
	}

	public void setS_email(String s_email) {
		this.s_email = s_email;
	}

	public String getS_image() {
		return s_image;
	}

	public void setS_image(String s_image) {
		this.s_image = s_image;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}


	public String getC_costfree() {
		return c_costfree;
	}


	public void setC_costfree(String c_costfree) {
		this.c_costfree = c_costfree;
	}

	
	

 
}
