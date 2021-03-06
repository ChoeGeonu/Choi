package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.SubjectVO;

public class SubjectTabController implements Initializable {
	// 관리자 명
	@FXML
	private Label lblManagerName;
	// 학과등록 탭
	@FXML
	private TextField txtSubjectNum;
	@FXML
	private TextField txtSubjectName;
	@FXML
	private TableView<SubjectVO> subjectTableView = new TableView<>();
	@FXML
	private Button btnInsert; // 학과등록
	@FXML
	private Button btnUpdte;// 학과 수정
	@FXML
	private Button btnDelete;// 학과 삭제
	@FXML
	private Button btnRead;

	public static ObservableList<SubjectVO> subjectDataList = FXCollections.observableArrayList();
	ObservableList<SubjectVO> selectSubject = null;// 테이블에서 선택한 정보 저장
	int selectedindex;// 테이블에서 선택한 학과 정보 인덱스 저장

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			lblManagerName.setText(LoginController.managerName);

			// 학과등록 초기화
			btnUpdte.setDisable(true);
			btnDelete.setDisable(true);
			subjectTableView.setEditable(false);

			// 학과테이블 뷰컬럼이름 설정
			TableColumn colNo = new TableColumn("NO.");
			colNo.setPrefWidth(50);
			colNo.setStyle("-fx-allignment:CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory("no"));

			TableColumn colSNum = new TableColumn("학과 번호.");
			colSNum.setPrefWidth(90);
			colSNum.setStyle("-fx-allignment:CENTER");
			colSNum.setCellValueFactory(new PropertyValueFactory("s_num"));

			TableColumn colSName = new TableColumn("학과명.");
			colSName.setPrefWidth(50);
			colSName.setStyle("-fx-allignment:CENTER");
			colSName.setCellValueFactory(new PropertyValueFactory("s_name"));

			subjectTableView.setItems(subjectDataList);
			subjectTableView.getColumns().addAll(colNo, colSNum, colSName);

			// 학과 전체 목록
			subjectTotalList();

			// 학과 키 이벤트
			txtSubjectNum.setOnKeyPressed(event -> handlerTxtSubjectNumKeyPressed(event));// 학과등록 텍스트필드 키 이벤트
			// 학과 등록 수저 삭제 이벤트
			btnInsert.setOnAction(event -> handlerBtnInsertActoion(event)); // 학과 등록 이벤트
			btnDelete.setOnAction(event -> handlerBtnDeleteActoion(event)); // 학과 삭제
			btnUpdte.setOnAction(event -> handlerBtnUpdateActoion(event)); // 학과 수정
			subjectTableView.setOnMouseClicked(event -> handlerSubjectTableViewActoion(event)); // 학과 테이블뷰 더블 클릭 선댁
			btnRead.setOnAction(event -> handlerBtnReadAction(event)); // 테이블 뷰 읽기
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 학과 테이블뷰 더블 클릭 선택
	public void handlerSubjectTableViewActoion(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectSubject = subjectTableView.getSelectionModel().getSelectedItems();
				selectedindex = selectSubject.get(0).getNo();
				String selectedS_num = selectSubject.get(0).getS_name();
				String selectedS_name = selectSubject.get(0).getS_name();

				txtSubjectNum.setText(selectedS_num);
				txtSubjectName.setText(selectedS_name);

				btnUpdte.setDisable(false);
				btnDelete.setDisable(false);
				btnInsert.setDisable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 학과등록 텍스트필드 키 이벤트
	public void handlerTxtSubjectNumKeyPressed(KeyEvent event) {

		if ((txtSubjectNum.getText().length() >= 3)) {
			txtSubjectNum.clear();
		}
		if (event.getCode() == KeyCode.ENTER) {
			txtSubjectName.requestFocus();
		}
	}

	// 학과 전체 리스트
	public void subjectTotalList() throws Exception {
		subjectDataList.removeAll(subjectDataList);

		SubjectDAO sDao = new SubjectDAO();
		SubjectVO sVo = null;
		ArrayList<String> title;
		ArrayList<SubjectVO> list;

		title = sDao.getSubjectColumnName();
		int columnCount = title.size();

		list = sDao.getSubjectTotaList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			subjectDataList.add(sVo);
		}
	}

	// 학과 등록 이벤트
	public void handlerBtnInsertActoion(ActionEvent event) {
		try {
			subjectDataList.removeAll(subjectDataList);

			SubjectVO svo = null;
			SubjectDAO sdao = null;
			svo = new SubjectVO(txtSubjectNum.getText().trim(), txtSubjectName.getText().trim());
			sdao = new SubjectDAO();
			sdao.getSubjectRegiste(svo);

			if (sdao != null) {
				subjectTotalList();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학과 입력");
				alert.setHeaderText(txtSubjectName.getText() + " 학과가 성공적으로 추가 되었습니다");
				alert.setContentText("다음 학과를 입력 하세요");
				alert.showAndWait();

				txtSubjectNum.clear();
				txtSubjectName.clear();
				txtSubjectNum.requestFocus();
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("학과 정보 입력");
			alert.setHeaderText(" 학과 정보를 정화히 입력 하시오");
			alert.setContentText("다음 에 주의 하세요");
			alert.showAndWait();
		}
	}

	// 학과 수정
	public void handlerBtnUpdateActoion(ActionEvent event) {
		try {
			boolean sucess;

			SubjectDAO sdao = new SubjectDAO();
			sucess = sdao.getSubjectUpdate(selectedindex, txtSubjectNum.getText().trim(),
					txtSubjectName.getText().trim());

			if (sucess) {
				subjectDataList.removeAll(subjectDataList);
				subjectTotalList();

				txtSubjectNum.clear();
				txtSubjectName.clear();
				btnInsert.setDisable(false);
				btnUpdte.setDisable(true);
				btnDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 학과 삭제
	public void handlerBtnDeleteActoion(ActionEvent event) {
		try {
			boolean sucess;

			SubjectDAO sdao = new SubjectDAO();
			sucess = sdao.getSubjectDelete(selectedindex);
			if (sucess) {
				subjectDataList.removeAll(subjectDataList);
				subjectTotalList();

				txtSubjectNum.clear();
				txtSubjectName.clear();
				btnInsert.setDisable(false);
				btnUpdte.setDisable(true);
				btnDelete.setDisable(true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 테이블 뷰 읽기
	public void handlerBtnReadAction(ActionEvent event) {
		try {
			int count = subjectTableView.getItems().size();
			System.out.println("count: " + count);
			for (int i = 0; i < count; i++) {
				selectSubject = subjectTableView.getItems();
				int index = selectSubject.get(i).getNo();
				String selectedS_num = selectSubject.get(i).getS_num();
				String selectedS_name = selectSubject.get(i).getS_name();

				System.out.println(index + "");
				System.out.println(selectedS_num + "");
				System.out.println(selectedS_name + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
