package view.panels;
//@author Jef Uytterhoeven 2018
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.PropertiesManager;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private Controller controller;
	private Stage parent;
	private ArrayList<String> antwoorden;
	//private PropertiesManager prop;
	private int testid;
	
	public TestPane (Stage secundStage , int testid){
		this.parent = secundStage;
		controller= Controller.getInstance();
		//prop = new PropertiesManager();
		
		
		this.testid = testid;
		System.out.println("er word een nieuwe testpane gemaakt");
		
		
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		questionField = new Label();
		this.add(questionField, 0, 0, 1, 1);
		questionField.setText(controller.getTest(testid).getCurrentQuestion().getVraagStelling());
		
		
		statementGroup = new ToggleGroup();
		Toggle newtoggle ;
		newtoggle = new RadioButton("this is a radio button");
		ArrayList<String> vragen = controller.getTest(testid).getCurrentQuestion().getAllAnswers();
		statementGroup.getToggles().add(newtoggle);
		for(int i = 0; i< vragen.size();i++) {
			RadioButton newradio = new RadioButton();
			newradio.setToggleGroup(statementGroup);
			newradio.setUserData(vragen.get(i));
			newradio.setText(vragen.get(i));
			if(i == 0) {
				newradio.setSelected(true);
			}
			this.add(newradio, 0, 2 + i);
		}

		 
		submitButton = new Button("Submit");
		submitButton.setOnAction(new SubmitActionListener(this));
		this.add(submitButton, 0, 11, 2, 2);
	}
	
	class SubmitActionListener implements EventHandler<ActionEvent> {
		TestPane pane;
		public SubmitActionListener(TestPane pane) {
			this.pane = pane;
		}
		@Override
		public void handle(ActionEvent e) {
			String answer = pane.getSelectedStatements().get(0);
			System.out.println("answer submitted " + answer);
			controller.controlAnswer(answer, testid);
			pane.parent.close();
			
			if(controller.isLaatsteVraag(testid)) {
				controller.getPropertiesManager().setPropertie("testdone", "true");
				if(controller.getTest(testid).isPerfectScore()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("proficiat");
					alert.setHeaderText("congrats on finnishing with a perfect score");
	
					alert.showAndWait();
				}
				controller.notifyObserver();
				if(controller.getPropertiesManager().getPropertieOf("evaluationmode").equals("score") || controller.getPropertiesManager().getPropertieOf("evaluationmode").equals("both")) {
					// show score to user
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("your score");
					alert.setHeaderText("congrats on finnishing");
					
					String score = "";
					score = controller.getTest(testid).getScoreToString();
					alert.setContentText(score);
	
					alert.showAndWait();
				}
				if(!controller.getTest(testid).isPerfectScore()) {
				if(controller.getPropertiesManager().getPropertieOf("evaluationmode").equals("tips") || controller.getPropertiesManager().getPropertieOf("evaluationmode").equals("both")) {
				// show tips to user
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("your mistakes where");
				alert2.setHeaderText("you seem tho have made some mistakes");
				
				String mistakeString = controller.getTest(testid).getMistakeString();
				alert2.setContentText(mistakeString);

				alert2.showAndWait();
				}
				}
				
			}else {
			Stage secundStage = new Stage();
			TestPane catergoryDtailsPane = new TestPane(secundStage , testid);
			Group root = new Group();
			Scene scene = new Scene(root, 750, 300);
			root.getChildren().add(catergoryDtailsPane);
			secundStage.setScene(scene);
			secundStage.sizeToScene();
			secundStage.show();
			}
		}
	}
	
	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			System.out.println(statementGroup.getSelectedToggle().getUserData().toString());
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}
}
