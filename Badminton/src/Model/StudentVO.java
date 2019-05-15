package Model;

public class StudentVO {
	private int no;//학생코드?
	private String name; //학생이름
	private int year; //학년
	private int ban; //반
	private int nwmber; //출석번호
	private String gender; //성별
	private String phone; //휴대폰번호
	private String emergency; //비상연락망
	private String freecost; //수업료
	private int time;//수업시간
	private String startdate;//시작일
	private String enddate;//종료일
	private String email;//부모님이메일
	private String image =null;//이미지 파일 경로
	
	public StudentVO() {
		super();
	}

	public StudentVO(int no, String name, int year, int ban, int nwmber, String gender, String phone, String emergency,
			String freecost, int time, String startdate, String enddate, String email, String image) {
		super();
		this.no = no;
		this.name = name;
		this.year = year;
		this.ban = ban;
		this.nwmber = nwmber;
		this.gender = gender;
		this.phone = phone;
		this.emergency = emergency;
		this.freecost = freecost;
		this.time = time;
		this.startdate = startdate;
		this.enddate = enddate;
		this.email = email;
		this.image = image;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getBan() {
		return ban;
	}

	public void setBan(int ban) {
		this.ban = ban;
	}

	public int getNwmber() {
		return nwmber;
	}

	public void setNwmber(int nwmber) {
		this.nwmber = nwmber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getFreecost() {
		return freecost;
	}

	public void setFreecost(String freecost) {
		this.freecost = freecost;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
