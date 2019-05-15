package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.JoinVO;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class JoinController implements Initializable {

	@FXML
	private TextField txtId; // 아이디
	@FXML
	private PasswordField txtPw; // 비밀번호
	@FXML
	private PasswordField txtPwRepeat; // 비밀번호 확인
	@FXML
	private TextField txtName; // 담임 선생님
	@FXML
	private TextField txtSubject; // 담임 담당과목
	@FXML
	private TextField txtDay; // 수업요일

	@FXML
	private Button btnCancel; // 닫기
	@FXML
	private Button btnJoin; // 등록
	@FXML
	private Button btnOverlap; // 아이디 중복 확인
	JoinVO joinVO = new JoinVO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnJoin.setDisable(true);
		txtPw.setEditable(false);
		txtPwRepeat.setEditable(false);

		btnOverlap.setOnAction(event -> handlerBtnOverlapActoion(event)); // 아이디 중복 확인
		btnJoin.setOnAction(event -> handlerBtnJoinActoion(event)); // 아이디 등록
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event)); // 닫기 버튼
	}

	// 아이디 중복 확인
	public void handlerBtnOverlapActoion(ActionEvent event) {
		btnJoin.setDisable(false);
		btnOverlap.setDisable(true);

		JoinDAO jDao = null;

		String searchId = "";
		boolean searchResult = true;

		try {
			searchId = txtId.getText().trim();
			jDao = new JoinDAO();
			searchResult = (boolean) jDao.getIdOverlap(searchId);

			if (!searchResult && !searchId.equals("")) {
				txtId.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + " 를 사용할 수 있습니다.");
				alert.setContentText("패스워드 입력하세요.");
				alert.showAndWait();

				btnJoin.setDisable(false);
				btnOverlap.setDisable(true);
				txtPw.setEditable(true);
				txtPwRepeat.setEditable(true);
				txtPw.requestFocus();

			} else if (searchId.equals("")) {
				btnJoin.setDisable(true);
				btnOverlap.setDisable(false);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복 검색");
				alert.setHeaderText("아이디 입력 하시오.");
				alert.setContentText("등록할 아이디를 입력하세요!");
				alert.showAndWait();
			} else {
				btnJoin.setDisable(true);
				btnOverlap.setDisable(false);
				txtId.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "사용 불가입니다.");
				alert.setContentText("패스워드 입력 하세요.");
				alert.showAndWait();

				txtId.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사 오류 발생.");
			alert.setContentText("다시하세요");
			alert.showAndWait();
		}
	}

	// 전환
	public void handlerBtnCancelActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/loginView.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("관리 선생님 로그인");
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnJoin.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류 " + e);
		}
	}

	// 선생님 등록
	public void handlerBtnJoinActoion(ActionEvent event) {

		JoinVO jvo = null;
		JoinDAO jdao = null;

		boolean joinSucess = false;

		// 패스워드 확인
		if (txtPw.getText().trim().equals(txtPwRepeat.getText().trim()) && !txtName.getText().trim().equals("")) {
			jvo = new JoinVO(txtId.getText().trim(), txtPw.getText().trim(), txtName.getText().trim(),
					txtSubject.getText().trim(), txtDay.getText().trim());
			jdao = new JoinDAO();
			try {
				joinSucess = jdao.getTeacherRegiste(jvo);
				if (joinSucess) {
					handlerBtnCancelActoion(event);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			txtPw.clear();
			txtPwRepeat.clear();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("패스워드 확인");
			alert.setHeaderText("패스워드 확인 검사에 오류 발생.");
			alert.setContentText("패스워드를 다시 입력하세요.");
			alert.showAndWait();
		}
	}
}
