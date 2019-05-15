package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EmailController implements Initializable {

	private static Logger logger = Logger.getLogger(MainController.class);
	@FXML
	TextField txtId; // 아이디
	@FXML
	PasswordField txtPass; // 비밀번호
	@FXML
	TextField txtToAddr; // 받는사람 이메일
	@FXML
	TextField txtToName; // 받는사람 이름
	@FXML
	TextField txtFromAddr; // 보내는사람 이메일
	@FXML
	TextField txtFromName; // 보내는사람 이름
	@FXML
	Button btnSend; // 전송
	@FXML
	Button btnClear; // 동기화
	@FXML
	Button btnExir; // 닫기
	@FXML
	TextField txtTitle; // 메일제목
	@FXML
	TextField txtFileAddr; // 첨부파일
	@FXML
	TextArea txtContents; // 메일내용
	@FXML
	Button btnAttachFile; // 파일첨부

	Stage stage = new Stage();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 버튼 이벤트 걸기
		btnSend.setOnAction(event -> sendEmail(event));// 전송
		btnClear.setOnAction(event -> Clear(event));// 동기화
		btnExir.setOnAction(event -> handlerBtnExirActoion(event));// 종료
		btnAttachFile.setOnAction(event -> open_ac(event));// 파일첨부

	}

	// 닫기
	public void handlerBtnExirActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			Stage oldStage = (Stage) btnExir.getScene().getWindow();
			oldStage.close();
			mainMtage.setScene(scane);
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류 " + e);
		}

	}

	// 첨부 파일 메소드
	public void open_ac(ActionEvent event) {
		// 파일 chooser 생성
		FileChooser file = new FileChooser();
		file.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("Image File", "*.png", "*.jpg", ".gif"),
				new ExtensionFilter("Audio File", "*.wav", "*.mp3", ".aac"), new ExtensionFilter("All Files", "*.*"));

		File selectedFile = file.showOpenDialog(stage);

		if (selectedFile != null) {
			txtFileAddr.setText(selectedFile.getPath());
		}
	}

	// 동기화
	public void Clear(ActionEvent event) {
	}

	// 보내는 메소드
	public void sendEmail(ActionEvent event) {
		long beginTime = System.currentTimeMillis();
		String rt = "Failure";

		try {
			// 첨부파일 생성을 위한 이메일 객체 생성
			EmailAttachment attach = new EmailAttachment();
			attach.setDescription("첨부 파일");
			attach.setName("");
			attach.setPath(txtFileAddr.getText());

			// 인스턴스
			MultiPartEmail email = new MultiPartEmail();

			// smtp 서버 연결 설정
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setAuthentication(txtId.getText(), txtPass.getText());

			email.setSSL(true);
			email.setTLS(true);

			// 받는사람
			email.addTo(txtToAddr.getText(), txtToName.getText(), "utf-8");

			// 받는사람
			email.setFrom(txtFromAddr.getText(), txtFromName.getText(), "utf-8");

			email.setSubject(txtTitle.getText());// 제목
			email.setMsg(txtContents.getText());// 본문
			email.attach(attach);// 첨부파일 추가

			rt = email.send();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("메일 전송 완료");
			alert.setHeaderText("메일 전송 성공");
			alert.setContentText("메일 전송을 완료 했습니다");
			alert.showAndWait();

			cancel_ac(event); // 초기화

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("메일 전송 실패");
			alert.setHeaderText("구글아이디 비번을 정확히 입력 하세요");
			alert.setContentText("메일 전송 실패");
			alert.showAndWait();

			logger.warn("Error", e);
		}
		long execTime = System.currentTimeMillis() - beginTime;
		logger.info("exec time = " + execTime + " ms");
		logger.info("RT Msg = " + rt);
	}

	private void cancel_ac(ActionEvent event) {
		txtFileAddr.setText("");
		txtFromAddr.setText("");
		txtFromName.setText("");
		txtId.setText("");
		txtTitle.setText("");
		txtPass.setText("");
		txtContents.setText("");
		txtToName.setText("");
		txtToAddr.setText("");

	}

}
