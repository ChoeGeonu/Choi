package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.StudentVO;
import model.SubjectVO;

public class StudentTabController implements Initializable {
	// 학생 등록 탭
	@FXML
	private ComboBox<SubjectVO> cbx_subjectName;
	@FXML
	private TextField txtsd_num;
	@FXML
	private TextField txtsd_name;
	@FXML
	private TextField txtsd_id;
	@FXML
	private PasswordField txtsd_passwd;
	@FXML
	private TextField txtsd_birthday;
	@FXML
	private TextField txtsd_phone;
	@FXML
	private TextField txtsd_address;
	@FXML
	private TextField txtsd_email;
	@FXML
	private Button btnidCheck;// 아이디 체크
	@FXML
	private Button btnStudentInsert; // 학생 등록
	@FXML
	private Button btnStudentUpdate;// 학생 수정
	@FXML
	private Button btnStudentinit;// 학생 초기화
	@FXML
	private Button btnStudentTatolList;// 학생 전체 목록
	@FXML
	private TableView<StudentVO> studentTableView = new TableView<>();

	ObservableList<StudentVO> studentDataList = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent = null;// 학생 등록 테이블에서 선택한 정보 저장
	int selectedStudentindex;// 학생등록 탭에서 선택한 학과정보 인뎃스
	String studentNamber = "";
	private String selectSubjectNum;// 선택한 학과명의 학과코드

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			// 학생등록 초기환
			btnStudentinit.setDisable(true);
			btnStudentUpdate.setDisable(true);
			btnStudentinit.setDisable(true);
			studentTableView.setEditable(false);

			// 학번 수정 금지
			txtsd_num.setEditable(false);

			// 학생 테이블 뷰 컬럼 이름 설정
			@SuppressWarnings("rawtypes")
			TableColumn colStudentNo = new TableColumn("NO");
			colStudentNo.setPrefWidth(30);
			colStudentNo.setStyle("-fx-allignment:CENTER");
			colStudentNo.setCellValueFactory(new PropertyValueFactory("no"));

			TableColumn colStudentNum = new TableColumn("학번");
			colStudentNum.setPrefWidth(30);
			colStudentNum.setStyle("-fx-allignment:CENTER");
			colStudentNum.setCellValueFactory(new PropertyValueFactory("sd_num"));

			TableColumn colStudentName = new TableColumn("이름");
			colStudentName.setPrefWidth(80);
			colStudentName.setStyle("-fx-allignment:CENTER");
			colStudentName.setCellValueFactory(new PropertyValueFactory<>("sd_name"));

			TableColumn colStudentid = new TableColumn("아이디");
			colStudentid.setPrefWidth(80);
			colStudentid.setStyle("-fx-allignment:CENTER");
			colStudentid.setCellValueFactory(new PropertyValueFactory<>("sd_id"));

			TableColumn colStudentPassword = new TableColumn("비밀번호");
			colStudentPassword.setPrefWidth(80);
			colStudentPassword.setStyle("-fx-allignment:CENTER");
			colStudentPassword.setCellValueFactory(new PropertyValueFactory<>("sd_passwd"));

			TableColumn colSubjectNum = new TableColumn("학과명");
			colSubjectNum.setPrefWidth(80);
			colSubjectNum.setStyle("-fx-allignment:CENTER");
			colSubjectNum.setCellValueFactory(new PropertyValueFactory<>("s_num"));

			TableColumn colStudentBirthday = new TableColumn("생년월일");
			colStudentBirthday.setPrefWidth(80);
			colStudentBirthday.setStyle("-fx-allignment:CENTER");
			colStudentBirthday.setCellValueFactory(new PropertyValueFactory<>("sd_birthday"));

			TableColumn colStudentPhone = new TableColumn("연락처");
			colStudentPhone.setPrefWidth(30);
			colStudentPhone.setStyle("-fx-allignment:CENTER");
			colStudentPhone.setCellValueFactory(new PropertyValueFactory("sd_phone"));

			TableColumn colStudentAddress = new TableColumn("주소");
			colStudentPhone.setPrefWidth(30);
			colStudentPhone.setStyle("-fx-allignment:CENTER");
			colStudentPhone.setCellValueFactory(new PropertyValueFactory("sd_address"));

			TableColumn colStudentEmail = new TableColumn("이메일");
			colStudentEmail.setPrefWidth(30);
			colStudentEmail.setStyle("-fx-allignment:CENTER");
			colStudentEmail.setCellValueFactory(new PropertyValueFactory("sd_email"));

			TableColumn colStudentDate = new TableColumn("등록일");
			colStudentDate.setPrefWidth(30);
			colStudentDate.setStyle("-fx-allignment:CENTER");
			colStudentDate.setCellValueFactory(new PropertyValueFactory("sd_date"));

			studentTableView.setItems(studentDataList);
			studentTableView.getColumns().addAll(colStudentNo, colStudentNum, colStudentName, colStudentid,
					colStudentPassword, colStudentNum, colStudentBirthday, colStudentPhone, colStudentAddress,
					colStudentEmail, colStudentDate);

			// 학생 전체 목록
			studentTotalList();

			// 추가된 학과명 호출
			// addSubjectName();

			btnStudentInsert.setOnAction(event -> handlerBtnStudentInsertAction(event)); // 학생 등록 이벤트
			cbx_subjectName.setOnAction(event -> handlerCbx_subjectNameActoion(event));// 학생 등록 탭 학과 선택 이벤트
			btnidCheck.setOnAction(event -> handlerBtnIdCheckAction(event));// 아이디 중복 체크
			studentTableView.setOnMouseClicked(event -> handlerStudentTableViewActoion(event)); // 학생 테이블 뷰 더블 클릭
			btnStudentUpdate.setOnAction(event -> handlerBtnStudentUpdateAction(event)); // 학생 정보 수정
			btnStudentinit.setOnAction(event -> handlerBtnStudentInitAction(event)); // 학생 초기화
			btnStudentTatolList.setOnAction(event -> handlerBtnStudentTatolListAction(event)); // 학생 전체 목록

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addSubjectName() throws Exception {
		StudentDAO sDao = new StudentDAO();
		ArrayList subjectNameList = new ArrayList<>();
		subjectNameList = sDao.subjectTotalList();
		// 학생 등록 탭 학과 번호 콤보 값 설정
		cbx_subjectName.setItems(FXCollections.observableArrayList(subjectNameList));
	}

	// 학생 등록 이벤트
	public void handlerBtnStudentInsertAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);
			StudentVO svo = null;
			StudentDAO sdao = null;

			svo = new StudentVO(txtsd_num.getText().trim(), txtsd_name.getText().trim(), txtsd_id.getText().trim(),
					txtsd_passwd.getText().trim(), selectSubjectNum, txtsd_birthday.getText().trim(),
					txtsd_phone.getText().trim(), txtsd_address.getText().trim(), txtsd_email.getText().trim());
			sdao = new StudentDAO();
			sdao.getStudentRegiste(svo);

			if (sdao != null) {
				studentTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 입력");
				alert.setHeaderText(txtsd_name.getText() + " 학생이 성공적으로 추가 되었습니다");
				alert.setContentText("다음 학생을 입력 하세요");
				alert.showAndWait();

				txtsd_num.clear();
				txtsd_name.clear();
				txtsd_id.clear();
				txtsd_passwd.clear();
				selectSubjectNum = "";
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();
				txtsd_name.clear();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("학과 정보 입력");
			alert.setHeaderText("학과 정보를 정확히 입력 하시오");
			alert.setContentText("다음에는 주의 하세요");
			alert.showAndWait();
		}
	}

	// 아이디 중복 체크
	public void handlerBtnIdCheckAction(ActionEvent event) {
		btnStudentInsert.setDisable(false);
		btnidCheck.setDisable(true);

		StudentDAO sDao = null;

		String searchld = "";
		boolean searchResult = true;
		try {
			searchld = txtsd_id.getText().trim();
			sDao = new StudentDAO();
			searchResult = (boolean) sDao.getStudentidOverlap(searchld);

			if (!searchResult && !searchld.equals("")) {
				txtsd_id.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchld + " 를 사용 할수있습니다");
				alert.setContentText("패스워드를 입력 하세요");
				alert.showAndWait();

				btnStudentInsert.setDisable(false);
				btnidCheck.setDisable(true);
			} else if (searchld.equals("")) {
				btnStudentInsert.setDisable(true);
				btnidCheck.setDisable(false);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복 검색");
				alert.setHeaderText("아이디를 입력 하시오");
				alert.setContentText("등록할 아이디를 입력 하세요");
				alert.showAndWait();
			} else {
				btnStudentInsert.setDisable(true);
				btnidCheck.setDisable(false);
				txtsd_id.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchld + " 를 사용할수 없습니다");
				alert.setContentText("다른 것을 입력 하세요");
				alert.showAndWait();

				txtsd_id.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사 오류 발생");
			alert.setContentText("다시하세요");
			alert.showAndWait();
		}
	}

	// 학생 등록 탭 학과 선택 이벤트
	public void handlerCbx_subjectNameActoion(ActionEvent event) {
		SubjectDAO sudao = new SubjectDAO();
		StudentDAO sdao = new StudentDAO();
		String serialNumber = "";// 일련번호
		String sdYear = "";

		try {
			selectSubjectNum = sudao.getSubjectNum(cbx_subjectName.getSelectionModel().getSelectedItem() + "");

			// 학번은 8자리로 구성한다
			SimpleDateFormat sdf = new SimpleDateFormat("yy");
			sdYear = sdf.format(new Date());

			serialNumber = sdao.getStudentCount(selectSubjectNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		studentNamber = sdYear + selectSubjectNum + serialNumber;
		txtsd_num.setText(studentNamber);
	}

	// 학생 전체 목록
	public void studentTotalList() throws Exception {
		studentDataList.removeAll(studentDataList);
		StudentDAO sDao = new StudentDAO();
		StudentVO sVo = null;
		ArrayList<String> title;
		ArrayList<StudentVO> list;

		title = sDao.getStudnetColumnName();
		int columnCount = title.size();

		list = sDao.getStudentTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			studentDataList.add(sVo);
		}
		// 추가 학과명 호출
		addSubjectName();
	}

	// 학생 테이블 뷰 더블 클릭
	public void handlerStudentTableViewActoion(MouseEvent event) {
		if (event.getClickCount() == 2) {

			try {
				selectStudent = studentTableView.getSelectionModel().getSelectedItems();
				selectedStudentindex = selectStudent.get(0).getNo();
				String selectedS_num = selectStudent.get(0).getSd_num();
				String selecteSd_name = selectStudent.get(0).getSd_name();
				String selecteSd_id = selectStudent.get(0).getSd_id();
				String selecteSd_passwd = selectStudent.get(0).getSd_passwd();
				String selecteSd_birthday = selectStudent.get(0).getSd_birthday();
				String selecteSd_phone = selectStudent.get(0).getSd_phone();
				String selecteSd_address = selectStudent.get(0).getSd_address();
				String selecteSd_email = selectStudent.get(0).getSd_email();

				txtsd_num.setText(selectedS_num);
				txtsd_name.setText(selecteSd_name);
				txtsd_id.setText(selecteSd_id);
				txtsd_passwd.setText(selecteSd_passwd);
				txtsd_birthday.setText(selecteSd_birthday);
				txtsd_phone.setText(selecteSd_phone);
				txtsd_address.setText(selecteSd_address);
				txtsd_email.setText(selecteSd_email);

				txtsd_num.setEditable(false);
				txtsd_name.setEditable(false);
				txtsd_id.setEditable(false);

				btnidCheck.setDisable(true);
				cbx_subjectName.setDisable(true);
				btnStudentUpdate.setDisable(false);
				btnStudentinit.setDisable(false);
				btnStudentInsert.setDisable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 학생 전체 목록
	public void handlerBtnStudentTatolListAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 학생 초기화
	public void handlerBtnStudentInitAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList();

			txtsd_num.clear();
			txtsd_name.clear();
			txtsd_id.clear();
			txtsd_passwd.clear();
			txtsd_birthday.clear();
			txtsd_phone.clear();
			txtsd_address.clear();
			txtsd_email.clear();

			txtsd_num.setEditable(true);
			txtsd_name.setEditable(true);
			txtsd_id.setEditable(true);

			btnidCheck.setDisable(false);
			cbx_subjectName.setDisable(false);
			btnStudentUpdate.setDisable(true);
			btnStudentinit.setDisable(true);
			btnStudentInsert.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 학생정보 수정
	public void handlerBtnStudentUpdateAction(ActionEvent event) {
		try {
			boolean suces;

			StudentDAO sdao = new StudentDAO();
			suces = sdao.getStudentUpdate(selectedStudentindex, txtsd_passwd.getText().trim(),
					txtsd_birthday.getText().trim(), txtsd_phone.getText().trim(), txtsd_address.getText().trim(),
					txtsd_email.getText().trim());

			if (suces) {
				studentDataList.removeAll(studentDataList);
				studentTotalList();

				txtsd_num.clear();
				txtsd_name.clear();
				txtsd_id.clear();
				txtsd_passwd.clear();
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();

				txtsd_num.setEditable(true);
				txtsd_name.setEditable(true);
				txtsd_id.setEditable(true);

				btnidCheck.setDisable(false);
				cbx_subjectName.setDisable(false);
				btnStudentUpdate.setDisable(true);
				btnStudentinit.setDisable(true);
				btnStudentInsert.setDisable(true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
