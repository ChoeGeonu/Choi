package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.StudentVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class OutputTabController implements Initializable {
	@FXML
	private Button btnExcel; // 엑셀
	@FXML
	private Button btnSaveFileDir;// 저장폴더
	@FXML
	private TextField txtSaveFileDir;// 파일경로
	
	private Stage primaryStage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//btnExcel.setOnAction(event -> handlerBtnExcelFileAction(event));// 엑셀파일생성
		btnSaveFileDir.setOnAction(event -> handlerBtnSaveFileDirAction(event));// 파일 저장 폴더

	}

	// 파일 저장 폴더
	public void handlerBtnSaveFileDirAction(ActionEvent event) {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		final File selectedDirectory = directoryChooser.showDialog(primaryStage);

		if (selectedDirectory != null) {
			txtSaveFileDir.setText(selectedDirectory.getAbsolutePath());
			btnExcel.setDisable(false);

		}

	}

	// 엑셀 파일 생성
	//public void handlerBtnExcelFileAction(ActionEvent event) {
	//	StudentDAO sDao = new StudentDAO();
	//	boolean saveSuccess;

	//	ArrayList<StudentVO> list;
	//	list = sDao.getStudentTotal();
	//	StudentExcel excelWriter = new StudentExcel();

	//	// xlsx 파일 쓰기
	//	saveSuccess = excelWriter.xlsxWiter(list, txtSaveFileDir.getText());
	//	if (saveSuccess) {
	//		Alert alert = new Alert(AlertType.INFORMATION);
	//		alert.setTitle("엑셀 파일 생성");
	//		alert.setHeaderText("학생 목록 엑셀 파일 생성 성공");
	//		alert.setContentText("학생 목록 엑셀 파일");
	//		alert.showAndWait();
	//	}
	//	btnExcel.setDisable(true);
	//}
//
}
