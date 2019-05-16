package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.xmlbeans.impl.tool.Extension;

import Model.StudentVO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private Label lblTeacherName;//선생님이름 라벨로 표시
	@FXML
	private Label lblSubjectName;//과목이름 라벨로 표시
	@FXML
	private Label lblDayName;//수업 요일 라벨로 표시
	@FXML
	private TableView<StudentVO> tableView=new TableView<>();//테이블뷰로 저장된 데이터 표시
	@FXML
	private TextField txtName;//학생이름
	@FXML
	private TextField txtYear;// 학생 학년
	@FXML
	private TextField txtBan;//학생 반
	@FXML
	private TextField txtNumber;//출석번호
	@FXML
	private ToggleGroup genderGroup;//성별 선택
	@FXML
	private RadioButton rbMale;//남성
	@FXML
	private RadioButton rbFemale;//여성
	@FXML
	private TextField txtPhone;// 학생 휴대폰
	@FXML
	private TextField txtEmergency;//비상연락망 
	@FXML
	private ToggleGroup CostFreeGroup;//수업료 유료 무료 선택
	@FXML
	private RadioButton rbFree;//무료 버튼
	@FXML
	private RadioButton rbCost;//유료 버튼
	@FXML
	private TextField txtTime;//수업 시간
	@FXML
	private TextField txtExperience;// 본인경험
	@FXML
	private ToggleGroup LevelGroup;//등급별 수강료
	@FXML
	private RadioButton rbLow;//초급
	@FXML
	private RadioButton rbMid;//중급
	@FXML
	private RadioButton rbHigh;//고급
	@FXML
	private TextField txtStartdate;//시작일
	@FXML
	private TextField txtEnddate;//끝난일
	@FXML
	private TextField txtEmail;//부모님이메일
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
	ObservableList<StudentVO> selectStudent;//테이블에서 선택한 정보 저장
	
	boolean editDelete=false; // 확인 버튼 상태설정
	int selectedIndex;//테이블에서 선택한 학생 정보 인덱스 저장
	
	private Stage primaryStage;
	String selectFileName = "";// 이미지 파일 명
	String localUrl="";// 이미지파일경로
	Image localimage;//

	//이미지 저장할 폴더를 매게변수로 파일 객체 선언
	private File dirSave =new File("D:images");
	File selectedFile = null;//이미지 불러올 파일 객체 선언

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnInit.setDisable(false);//초기화버튼 활성
		btnRegister.setDisable(false); //등록 활성
		btnEdit.setDisable(false);//수정 활성
		btnDelete.setDisable(false);//삭제 활성
		btnImageFile.setDisable(true);//이미지 등록 버튼 사용 금지
		
		
		//학년 반 출석번호에는 숫자만 입력
		DecimalFormat format=new DecimalFormat("##");
		//학년
		txtYear.setTextFormatter(new TextFormatter<>(event-> {
			if(event.getControlNewText().isEmpty()) {
				return event;
			}
		ParsePosition parsePosition= new ParsePosition(0);
		Object object = format.parse(event.getControlNewText(),parsePosition);
		if(object==null||parsePosition.getIndex()<event.getControlNewText().length()
				||event.getControlNewText().length() == 3) {
			return null;
		}else {
			return event;
		}
		}));
		//반
		txtBan.setTextFormatter(new TextFormatter<>(event-> {
			if(event.getControlNewText().isEmpty()) {
				return event;
			}
		ParsePosition parsePosition=new ParsePosition(0);
		Object object = format.parse(event.getControlNewText(),parsePosition);
		if(object==null || parsePosition.getIndex()<event.getControlNewText().length()
				||event.getControlNewText().length() == 3) {
			return null;
		}else {
			return event;
		}
		}));
		//출석번호
		txtNumber.setTextFormatter(new TextFormatter<>(event->{
			if(event.getControlNewText().isEmpty()) {
				return event;
			}
		ParsePosition parsePosition=new ParsePosition(0);
		Object object =format.parse(event.getControlNewText(),parsePosition);
		if(object==null || parsePosition.getIndex()<event.getControlNewText().length()
				||event.getControlNewText().length() == 3) {
			return null;
		}else {
			return event;
			
		}
		}));
		
		//테이블 뷰 수정금지!
		tableView.setEditable(false);
		
		//테이블 뷰 컬럼이름 설정
		TableColumn cols_No =new TableColumn("NO");
		cols_No.setMaxWidth(40);
		cols_No.setCellValueFactory(new PropertyValueFactory<>("s_no"));
		
		TableColumn cols_Name =new TableColumn("이름");
		cols_Name.setMaxWidth(50);
		cols_Name.setCellValueFactory(new PropertyValueFactory<>("s_Name"));
		
		TableColumn cols_Year =new TableColumn("학년");
		cols_Year.setMaxWidth(40);
		cols_Year.setCellValueFactory(new PropertyValueFactory<>("s_Year"));
		
		TableColumn cols_Ban =new TableColumn("반");
		cols_Ban.setMaxWidth(40);
		cols_Ban.setCellValueFactory(new PropertyValueFactory<>("s_Ban"));
		
		TableColumn cols_Number =new TableColumn("출석번호");
		cols_Number.setMaxWidth(40);
		cols_Number.setCellValueFactory(new PropertyValueFactory<>("s_Number"));
		
		TableColumn cols_Gender =new TableColumn("성별");
		cols_Gender.setMaxWidth(40);
		cols_Gender.setCellValueFactory(new PropertyValueFactory<>("s_gender"));
		
		TableColumn cols_Phone =new TableColumn("핸드폰");
		cols_Phone.setMaxWidth(120);
		cols_Phone.setCellValueFactory(new PropertyValueFactory<>("s_phone"));
		
		TableColumn cols_Emergency =new TableColumn("비상연락");
		cols_Emergency.setMaxWidth(120);
		cols_Emergency.setCellValueFactory(new PropertyValueFactory<>("s_Emergency"));
		
		TableColumn colc_Costfree =new TableColumn("수업료");
		colc_Costfree.setMaxWidth(60);
		colc_Costfree.setCellValueFactory(new PropertyValueFactory<>("c_Costfree"));
		
		TableColumn colc_Time =new TableColumn("수업시간");
		colc_Time.setMaxWidth(60);
		colc_Time.setCellValueFactory(new PropertyValueFactory<>("Time"));
		
		TableColumn cols_Experience =new TableColumn("본인경험");
		cols_Experience.setMaxWidth(180);
		cols_Experience.setCellValueFactory(new PropertyValueFactory<>("s_Experience"));
		
		TableColumn colc_Level=new TableColumn("등급별 가격");
		colc_Level.setMaxWidth(100);
		colc_Level.setCellValueFactory(new PropertyValueFactory<>("c_Level"));
		
		TableColumn colc_Startdate=new TableColumn("시작날짜");
		colc_Startdate.setMaxWidth(140);
		colc_Startdate.setCellValueFactory(new PropertyValueFactory<>("c_Startdate"));
		
		TableColumn colc_Enddate=new TableColumn("끝나는날짜");
		colc_Enddate.setMaxWidth(140);
		colc_Enddate.setCellValueFactory(new PropertyValueFactory<>("c_Enddate"));
		
		TableColumn cols_Email=new TableColumn("부모님 이메일");
		cols_Email.setMaxWidth(200);
		cols_Email.setCellValueFactory(new PropertyValueFactory<>("s_Email"));
				
		TableColumn cols_Image=new TableColumn("이미지");
		cols_Image.setMaxWidth(300);
		cols_Image.setCellValueFactory(new PropertyValueFactory<>("s_Image"));
		
		tableView.getColumns().addAll(cols_No, cols_Name, cols_Year, cols_Ban, cols_Number, cols_Gender, cols_Phone, cols_Emergency, colc_Costfree, colc_Time, cols_Experience, colc_Level, colc_Startdate, colc_Enddate, cols_Email, cols_Image);
		
		
			
		//학생정보저장
		btnRegister.setOnAction(event->{
			try {
				data.removeAll(data);
				StudentVO svo=null;
				StudentDAO sdao=new StudentDAO();
				
				if(event.getSource().equals(selectedFile)) {
				data.add(new StudentVO(txtName.getText(),Integer.parseInt(txtYear.getText().trim()),Integer.parseInt(txtBan.getText().trim()),
						Integer.parseInt(txtNumber.getText().trim()), genderGroup.getSelectedToggle().getUserData().toString(), 
						txtPhone.getText(),txtEmergency.getText(), CostFreeGroup.getSelectedToggle().getUserData().toString(),
						Integer.parseInt(txtTime.getText().trim()), txtExperience.getText(), LevelGroup.getSelectedToggle().getUserData().toString(),
						txtStartdate.getText(), txtEnddate.getText(),txtEmail.getText()));
			
				txtName.setEditable(true);
				txtYear.setEditable(true);
				txtBan.setEditable(true);
				txtNumber.setEditable(true);
			
			}catch(Exception e) {
				Alert alert= new Alert(AlertType.WARNING);
				alert.setTitle("학생 정보 입력");
				alert.setHeaderText("주의합시다");
				alert.setContentText("주의하라고요!");
				alert.showAndWait();
			
			
			}
		});
		
		
		
		
		
		
		
		btnExit.setOnAction(event -> handlerBtnExitlActoion(event));// 메인창 종료
		btnEmail.setOnAction(event -> handlerBtnEmalActoion(event)); // 메일창 이동
		btnAttendance.setOnAction(event -> handlerBtnAttendanceActoion(event)); // 출석부 이동
		btnImageFile.setOnAction(event -> handlerBtnImageFileAction(event)); // 이미지 선택 창
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

		btnRegister.setDisable(false);// 등록버튼 비활성

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

	// 메인창 종료
	public void handlerBtnExitlActoion(ActionEvent event) {
		Platform.exit();
	}

}
