package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.StudentVO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class MainController implements Initializable {
	@FXML
	private Label lblTeacherName;// 선생님이름 라벨로 표시
	@FXML
	private Label lblSubjectName;// 과목이름 라벨로 표시
	@FXML
	private Label lblDayName;// 수업 요일 라벨로 표시
	@FXML
	private TableView<StudentVO> tableView = new TableView<>();// 테이블뷰로 저장된 데이터 표시
	@FXML
	private TextField txtName;// 학생이름
	@FXML
	private TextField txtYear;// 학생 학년
	@FXML
	private TextField txtBan;// 학생 반
	@FXML
	private TextField txtNumber;// 출석번호
	@FXML
	private ToggleGroup genderGroup;// 성별 선택
	@FXML
	private RadioButton rbMale;// 남성
	@FXML
	private RadioButton rbFemale;// 여성
	@FXML
	private TextField txtPhone;// 학생 휴대폰
	@FXML
	private TextField txtEmergency;// 비상연락망
	@FXML
	private ToggleGroup CostFreeGroup;// 수업료 유료 무료 선택
	@FXML
	private RadioButton rbFree;// 무료 버튼
	@FXML
	private RadioButton rbCost;// 유료 버튼
	@FXML
	private TextField txtTime;// 수업 시간
	@FXML
	private TextField txtExperience;// 본인경험
	@FXML
	private ToggleGroup LevelGroup;// 등급별 수강료
	@FXML
	private RadioButton rbLow;// 초급
	@FXML
	private RadioButton rbMid;// 중급
	@FXML
	private RadioButton rbHigh;// 고급
	@FXML
	private TextField txtStartdate;// 시작일
	@FXML
	private TextField txtEnddate;// 끝난일
	@FXML
	private TextField txtEmail;// 부모님이메일
	@FXML
	private Button btnInit;// 초기화
	@FXML
	private Button btnRegister;// 등록
	@FXML
	private Button btnEdit;// 수정
	@FXML
	private Button btnDelete;// 삭제
	@FXML
	private Button btnExit; // 종료
	@FXML
	private Button btnEmail; // 이메일 창 전환
	@FXML
	Button btnAttendance;// 출석부
	@FXML
	private HBox imageBox;// 이미지
	@FXML
	private Button btnImageFile;// 이미지 등록 버튼
	@FXML
	private ImageView imageView;// 이미지뷰

	StudentVO student = new StudentVO();
	ObservableList<StudentVO> data = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent;// 테이블에서 선택한 정보 저장

	boolean editDelete = false; // 확인 버튼 상태설정
	int selectedIndex;// 테이블에서 선택한 학생 정보 인덱스 저장

	private Stage primaryStage;
	String selectFileName = "";// 이미지 파일 명
	String localUrl = "";// 이미지파일경로
	Image localimage;//

	// 이미지 저장할 폴더를 매게변수로 파일 객체 선언
	private File dirSave = new File("D:/images");
	File selectedFile = null;// 이미지 불러올 파일 객체 선언

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnInit.setDisable(false);// 초기화버튼 활성
		btnRegister.setDisable(false); // 등록 활성
		btnEdit.setDisable(false);// 수정 활성
		btnDelete.setDisable(false);// 삭제 활성
		btnImageFile.setDisable(false);// 이미지 등록 버튼 사용 활성

		// 학년 반 출석번호에는 숫자만 입력
		DecimalFormat format = new DecimalFormat("###");
		// 학년
		txtYear.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event;
			}
		}));
		// 반
		txtBan.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event;
			}
		}));
		// 출석번호
		txtNumber.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event;

			}
		}));

		// 테이블 뷰 수정금지!
		tableView.setEditable(false);

		// 테이블 뷰 컬럼이름 설정
		TableColumn cols_code = new TableColumn("NO");
		cols_code.setMaxWidth(40);
		cols_code.setCellValueFactory(new PropertyValueFactory<>("s_code"));

		TableColumn cols_name = new TableColumn("이름");
		cols_name.setMaxWidth(50);
		cols_name.setCellValueFactory(new PropertyValueFactory<>("s_name"));

		TableColumn cols_year = new TableColumn("학년");
		cols_year.setMaxWidth(40);
		cols_year.setCellValueFactory(new PropertyValueFactory<>("s_year"));

		TableColumn cols_ban = new TableColumn("반");
		cols_ban.setMaxWidth(40);
		cols_ban.setCellValueFactory(new PropertyValueFactory<>("s_ban"));

		TableColumn cols_number = new TableColumn("출석번호");
		cols_number.setMaxWidth(40);
		cols_number.setCellValueFactory(new PropertyValueFactory<>("s_number"));

		TableColumn cols_gender = new TableColumn("성별");
		cols_gender.setMaxWidth(40);
		cols_gender.setCellValueFactory(new PropertyValueFactory<>("s_gender"));

		TableColumn cols_phone = new TableColumn("핸드폰");
		cols_phone.setMaxWidth(120);
		cols_phone.setCellValueFactory(new PropertyValueFactory<>("s_phone"));

		TableColumn cols_emergency = new TableColumn("비상연락");
		cols_emergency.setMaxWidth(120);
		cols_emergency.setCellValueFactory(new PropertyValueFactory<>("s_emergency"));

		TableColumn colc_costfree = new TableColumn("수업료");
		colc_costfree.setMaxWidth(60);
		colc_costfree.setCellValueFactory(new PropertyValueFactory<>("s_costfree"));

		TableColumn colc_time = new TableColumn("수업시간");
		colc_time.setMaxWidth(60);
		colc_time.setCellValueFactory(new PropertyValueFactory<>("s_time"));

		TableColumn cols_experience = new TableColumn("본인경험");
		cols_experience.setMaxWidth(180);
		cols_experience.setCellValueFactory(new PropertyValueFactory<>("s_experience"));

		TableColumn colc_level = new TableColumn("등급별 가격");
		colc_level.setMaxWidth(100);
		colc_level.setCellValueFactory(new PropertyValueFactory<>("s_level"));

		TableColumn colc_startdate = new TableColumn("시작날짜");
		colc_startdate.setMaxWidth(140);
		colc_startdate.setCellValueFactory(new PropertyValueFactory<>("s_startdate"));

		TableColumn colc_enddate = new TableColumn("끝나는날짜");
		colc_enddate.setMaxWidth(140);
		colc_enddate.setCellValueFactory(new PropertyValueFactory<>("s_enddate"));

		TableColumn cols_email = new TableColumn("부모님 이메일");
		cols_email.setMaxWidth(200);
		cols_email.setCellValueFactory(new PropertyValueFactory<>("s_email"));

		TableColumn cols_image = new TableColumn("이미지");
		cols_image.setMaxWidth(300);
		cols_image.setCellValueFactory(new PropertyValueFactory<>("s_Image"));

		tableView.getColumns().addAll(cols_code, cols_name, cols_year, cols_ban, cols_number, cols_gender, cols_phone,
				cols_emergency, colc_costfree, colc_time, cols_experience, colc_level, colc_startdate, colc_enddate,
				cols_email, cols_image);

		// 학생전체 정보
		totalList();
		tableView.setItems(data);

		// 기본 이미지
		localUrl = "/image/default.png";
		localimage = new Image(localUrl, false);
		imageView.setImage(localimage);

		// 학생정보저장
		btnRegister.setOnAction(event -> {
			try {
				data.removeAll(data);
				StudentVO svo = null;
				StudentDAO sdao = new StudentDAO();
				File dirMake = new File(dirSave.getAbsolutePath());
				// 이미지 저장 폴더 생성
				if (!dirMake.exists()) {
					dirMake.mkdir();
				}
				// 이미지 파일 저장
				String s_image = imageSave(selectedFile);
				
				if (event.getSource().equals(btnRegister)) {
					svo = new StudentVO(txtName.getText(), Integer.parseInt(txtYear.getText().trim()),
							Integer.parseInt(txtBan.getText().trim()), Integer.parseInt(txtNumber.getText().trim()),
							genderGroup.getSelectedToggle().getUserData().toString(), txtPhone.getText(),
							txtEmergency.getText(), CostFreeGroup.getSelectedToggle().getUserData().toString(),
							Integer.parseInt(txtTime.getText().trim()), txtExperience.getText(),
							LevelGroup.getSelectedToggle().getUserData().toString(), txtStartdate.getText(),
							txtEnddate.getText(), txtEmail.getText(),s_image);

					sdao = new StudentDAO();
					sdao.getStudentregiste(svo);
					if (sdao != null) {
						totalList();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("점수 입력");
						alert.setHeaderText("등록 완료!");
						alert.setContentText(txtName.getText() + "학생의 점수가 성공적으로 추가되었습니다.");
						alert.showAndWait();

						btnImageFile.setDisable(true);

						// 기본 이미지
						localUrl = "/image/default.png";
						localimage = new Image(localUrl, false);
						imageView.setImage(localimage);

						txtName.setEditable(true);
						txtYear.setEditable(true);
						txtBan.setEditable(true);
						txtNumber.setEditable(true);
						txtPhone.setEditable(true);
						txtEmergency.setEditable(true);
						txtTime.setEditable(true);
						txtExperience.setEditable(true);
						txtStartdate.setEditable(true);
						txtEnddate.setEditable(true);
						handlerBtnInitAction(event);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("학생 정보 입력");
				alert.setHeaderText("주의합시다");
				alert.setContentText("주의하라고요!");
				alert.showAndWait();

				txtName.setEditable(true);
				txtYear.setEditable(true);
				txtBan.setEditable(true);
				txtNumber.setEditable(true);
				txtPhone.setEditable(true);
				txtEmergency.setEditable(true);
				txtTime.setEditable(true);
				txtExperience.setEditable(true);
				txtStartdate.setEditable(true);
				txtEnddate.setEditable(true);
				totalList();
			}
		});

		// 키 이벤트 등록
		txtName.setOnKeyPressed(event -> handlerTxtNameKeyPressed(event));
		txtYear.setOnKeyPressed(event -> handlerTxtYearKeyPressed(event));
		txtBan.setOnKeyPressed(event -> handlerTxtBanKeyPressed(event));
		txtNumber.setOnKeyPressed(event -> handlerTxtNumberKeyPressed(event));
		rbMale.setOnKeyPressed(event -> handlerRbMalePressed(event));
		rbFemale.setOnKeyPressed(event -> handlerRbFemalePressed(event));
		txtPhone.setOnKeyPressed(event -> handlerTxtPhoneKeyPressed(event));
		txtEmergency.setOnKeyPressed(event -> handlerTxtEmergencyKeyPressed(event));
		rbFree.setOnKeyPressed(event -> handlerRbFreePressed(event));
		rbCost.setOnKeyPressed(event -> handlerRbCostPressed(event));
		txtTime.setOnKeyPressed(event -> handlerTxtTimeKeyPressed(event));
		txtExperience.setOnKeyPressed(event -> handlerTxtExperienceKeyPressed(event));
		rbLow.setOnKeyPressed(event -> handlerRbLowPressed(event));
		rbMid.setOnKeyPressed(event -> handlerRbMidPressed(event));
		rbHigh.setOnKeyPressed(event -> handlerRbHighPressed(event));
		txtStartdate.setOnKeyPressed(event -> handlerTxtStartdate(event));
		txtEnddate.setOnKeyPressed(event -> handlerTxtEnddate(event));
		txtEmail.setOnKeyPressed(event -> handlerTxtKeyEmail(event));

		// 버튼이벤트
		btnExit.setOnAction(event -> handlerBtnExitlActoion(event));// 메인창 종료
		btnEmail.setOnAction(event -> handlerBtnEmalActoion(event)); // 메일창 이동
		btnAttendance.setOnAction(event -> handlerBtnAttendanceActoion(event)); // 출석부 이동
		btnImageFile.setOnAction(event -> handlerBtnImageFileAction(event)); // 이미지 선택 창
		btnInit.setOnAction(event -> handlerBtnInitAction(event));
		lblTeacherName.setText(LoginController.teacherName);//담당 선생님
		lblSubjectName.setText(LoginController.subjectName);//담당 과목
		

	}

//이미지 삭제 메소드
	public boolean imageDelete(String s_image) {
		boolean result = false;
		try {
			File fileDelete = new File(dirSave.getAbsolutePath() + "\\" + s_image);// 삭제 이미지 파일

			if (fileDelete.exists() && fileDelete.isFile()) {
				result = fileDelete.delete();

				// 기본 이미지
				localUrl = "/image/default.png";
				localimage = new Image(localUrl, false);
				imageView.setImage(localimage);
			}
		} catch (Exception ie) {
			System.out.println("ie= [" + ie.getMessage() + "]");
			result = false;
		}
		return result;
	}

//이미지 저장 메소드
	public String imageSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String s_image = null;
		try {
			// 이미지 파일 생성
			s_image = "student" + System.currentTimeMillis() + "_" + file.getName();

			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + s_image));

			// 선택한 이미지 파일 inputstream의 마지막에 이르렀을 경우에 -1
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return s_image;
	}

// 학생 전체리스트
	public void totalList() {
		Object[][] totalData;
		StudentDAO sDao = new StudentDAO();
		StudentVO sVo = null;
		ArrayList<String> title;
		ArrayList<StudentVO> list;
		title = sDao.getColumnName();
		int columnCount = title.size();
		list = sDao.getStudentTotal();
		int rowCount = list.size();
		totalData = new Object[rowCount][columnCount];
		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			data.add(sVo);
		}

	}

//텍스트 이벤트
	public void handlerTxtKeyEmail(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			btnRegister.requestFocus();
		}

	}

	public void handlerTxtEnddate(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtEmail.requestFocus();
		}

	}

	public void handlerTxtStartdate(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtEnddate.requestFocus();
		}

	}

	public void handlerRbHighPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtStartdate.requestFocus();
		}

	}

	public void handlerRbMidPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtStartdate.requestFocus();
		}

	}

	public void handlerRbLowPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtStartdate.requestFocus();
		}
	}

	public void handlerTxtExperienceKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			rbLow.requestFocus();
		}
	}

	public void handlerTxtTimeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtExperience.requestFocus();
		}

	}

	public void handlerRbCostPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtTime.requestFocus();
		}

	}

	public void handlerRbFreePressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtTime.requestFocus();
		}
	}

	public void handlerTxtEmergencyKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			rbFree.requestFocus();
		}
	}

	public void handlerTxtPhoneKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtEmergency.requestFocus();
		}

	}

	public void handlerRbFemalePressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {
			txtPhone.requestFocus();
		}

	}

	public void handlerRbMalePressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtPhone.requestFocus();
		}

	}

	public void handlerTxtNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			rbMale.requestFocus();
		}

	}

	public void handlerTxtBanKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtNumber.requestFocus();
		}

	}

	public void handlerTxtYearKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtBan.requestFocus();
		}
	}

	public void handlerTxtNameKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtYear.requestFocus();
		}
	}

//}
// 이미지 선택 창
	public void handlerBtnImageFileAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				// 이미지 파일 경로
				localUrl = selectedFile.toURI().toURL().toString();

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		localimage = new Image(localUrl, false);

		imageView.setImage(localimage);
		imageView.setFitHeight(250);
		imageView.setFitWidth(230);

		btnRegister.setDisable(false);

		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
		}
	}

	// 출석부창 이동
	public void handlerBtnAttendanceActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/adprintView.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("출석부");
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainMtage.setScene(scane);
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류 " + e);
		}

	}

	// 이메일창이동
	public void handlerBtnEmalActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/emailView.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("이메일");
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainMtage.setScene(scane);
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류 " + e);
		}
	}

	// 초기화
	public void handlerBtnInitAction(ActionEvent event) {
		txtName.clear();
		txtName.setEditable(true);
		txtYear.clear();
		txtYear.setEditable(true);
		txtBan.clear();
		txtBan.setEditable(true);
		txtNumber.clear();
		txtNumber.setEditable(true);
		txtPhone.clear();
		txtEmergency.clear();
		txtTime.clear();
		txtTime.setEditable(true);
		txtExperience.clear();
		txtStartdate.clear();
		txtEnddate.clear();
		txtEmail.clear();

		// 기본 이미지
		localUrl = "/image/default.png";
		localimage = new Image(localUrl, false);
		imageView.setImage(localimage);

	}

	// 메인창 종료
	public void handlerBtnExitlActoion(ActionEvent event) {
		Platform.exit();
	}

}
