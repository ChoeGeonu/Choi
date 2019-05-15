package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.xmlbeans.impl.tool.Extension;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class MainController implements Initializable {

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

	private Stage primaryStage;
	String selectFileName = "";// 이미지 파일 명
	String localUrl;// 이미지파일경로
	Image localimage;//

	File selectedFile = null;//

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnInit.setDisable(false);//초기화버튼 비활성
		btnRegister.setDisable(true); //등록 활성
		btnEdit.setDisable(false);//수정 비활성
		btnDelete.setDisable(false);//삭제 비활성
		
		
		
		btnExit.setOnAction(event -> handlerBtnExitlActoion(event));// 메인창 종료
		btnEmail.setOnAction(event -> handlerBtnEmalActoion(event)); // 메일창 이동
		btnAttendance.setOnAction(event -> handlerBtnAttendanceActoion(event)); // 출석부 이동
		btnImageFile.setOnAction(event -> handlerBtnImageFileAction(event)); // 이미지 선택 창
	}

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
