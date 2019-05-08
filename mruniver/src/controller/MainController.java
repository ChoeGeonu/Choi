package control;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.Subject;
import control.SubjectTabController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController implements Initializable {
	@FXML
	private TabPane mainPan;
	@FXML
	private Tab subject;
	@FXML
	private SubjectTabController subjectTabController;
	@FXML
	private Tab student;
	@FXML
	private studentTabController studentTabController;
	@FXML
	private Tab lesson;
	@FXML
	private LessonTabController lessonTabController; 
	@FXML
	private Tab
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
