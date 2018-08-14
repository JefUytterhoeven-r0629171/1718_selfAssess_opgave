package view.panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Category;
import model.Vraag;
import view.panels.CategoryDetailPane.AddCategoryListener;
import view.panels.CategoryDetailPane.cancelAddCategoryListener;

public class QuestionDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextArea statementsArea , categorieArea;
	private TextField questionField, statementField, feedbackField;
	private Button btnAdd, btnRemove , btnAddCat;
	private ComboBox categoryField;
	private Map<String , Integer> categories = new HashMap<>();
	private Controller controller;
	int vraagid;
	private Stage parent;

	private ArrayList<String> badstatements = new ArrayList<>();
	
	public QuestionDetailPane(Stage secundStage) {
		this.parent = secundStage;
		controller= Controller.getInstance();

		this.buildScreen(-1);
	}
	public QuestionDetailPane(Stage secundStage , int vraagid) {
		this.parent = secundStage;
		controller= Controller.getInstance();

		this.buildScreen(vraagid);
	}


	private void buildScreen(int vraagid) {
		this.vraagid = vraagid;
		this.setPrefHeight(300);
		this.setPrefWidth(320);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		
		questionField = new TextField();
		
		statementField = new TextField();
		
		statementsArea = new TextArea();
		statementsArea.setPrefRowCount(5);
		statementsArea.setEditable(false);
		

		Pane addRemove = new HBox();
		btnAdd = new Button("add");
		btnAdd.setOnAction(new AddStatementListener());
		addRemove.getChildren().add(btnAdd);

		btnRemove = new Button("remove");
		btnRemove.setOnAction(new RemoveStatementListener());
		addRemove.getChildren().add(btnRemove);
		

		categorieArea = new TextArea();
		categorieArea.setPrefRowCount(2);
		categorieArea.setEditable(false);
		
		
		categoryField = new ComboBox();
		// add all category's in de categorystub
        for(int i = 0 ; i< controller.getCategorys().size() ; i++) {
        	categoryField.getItems().add(controller.getCategorys().get(i).getTitle());
        }
		
        btnAddCat= new Button("add categogy");
        btnAddCat.isDefaultButton();
        btnAddCat.setText("add categogy");
        btnAddCat.setOnAction(new AddCategorieListener());

		
		feedbackField = new TextField();
		

		btnCancel = new Button("Cancel");
		btnCancel.setText("Cancel");
		btnCancel.setOnAction(new cancelAddvraagListener());
		

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		btnOK.setText("Save");
		btnOK.setOnAction(new AddQuestionListener());

		
		if(vraagid != -1) {
			questionField.setText(controller.getVragen().get(vraagid).getVraagStelling());
			statementField.setText(controller.getVragen().get(vraagid).getCorrectStatement());
			statementsArea.setText(this.getStatementString());
			categorieArea.setText(this.getCategoryString());
			feedbackField.setText(controller.getVragen().get(vraagid).getFeedback());

		}
		
		add(new Label("Question: "), 0, 0, 1, 1);
		add(questionField, 1, 0, 2, 1);
		add(new Label("Statement: "), 0, 1, 1, 1);
		add(statementField, 1, 1, 2, 1);
		add(new Label("Statements: "), 0, 2, 1, 1);
		add(statementsArea, 1, 2, 2, 5);
		add(addRemove, 1, 8, 2, 1);
		add(categorieArea, 1, 9, 2, 1);
		add(new Label("Category: "), 0, 9, 1, 1);
		//add(categoryField, 1, 12, 1, 1);
		add(btnAddCat, 2, 12, 1, 1);
		add(new Label("Feedback: "), 0, 13, 1, 1);
		add(feedbackField, 1, 13, 2, 1);
		add(btnCancel, 0, 14, 1, 1);
		add(btnOK, 1, 14, 2, 1);
	}
	
	
	private String getCategoryString() {
	String categorystring = "";
	categories = controller.getVragen().get(vraagid).getCategoriesString();
	
	for (String entry : categories.keySet())
	{
		categorystring = categorystring + entry +"   " +categories.get(entry) + "\n";
	}
	return categorystring;
	}
	
	private String getStatementString() {
		String allstatementsstring = "";
		badstatements = controller.getVragen().get(vraagid).getAllAnswers();
		for(int i = 0; i < badstatements.size(); i++) {
			allstatementsstring = allstatementsstring + badstatements.get(i) + "\n";
		}

		return allstatementsstring;
	}
	public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnOK.setOnAction(saveAction);
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}
	
	class cancelAddvraagListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			
			parent.close();
			System.out.println("cancel vraag button is clicked");
			
		}
	}
	class AddQuestionListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			
			String vraagstelling = questionField.getText();
			String correctStatement = statementField.getText();
			String feedback = feedbackField.getText();
			if(vraagid == -1) {
				controller.addVraag(vraagstelling, correctStatement,badstatements,categories,feedback);
			}else {
				
				controller.getVragen().get(vraagid).setVraagStelling(vraagstelling);
				controller.getVragen().get(vraagid).setCorrectStatement(correctStatement);
				controller.getVragen().get(vraagid).setFeedback(feedback);
				controller.getVragen().get(vraagid).setIncorrectStatements(badstatements);

				controller.notifyObserver();
			}
			parent.close();
		}
	}
	class AddCategorieListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			System.out.println("add category to vraag");
			Integer[] array = {0,1,2,3,4,5,6,7,9,10};
			String catnaam = "";
			Integer catScore;
			ChoiceDialog<String> dialog = new ChoiceDialog<>(controller.getCategorysnamen().get(0), controller.getCategorysnamen());
			dialog.setTitle("pick new category");
			dialog.setHeaderText("pick new category");
			dialog.setContentText("pick new category");
			//dialog.showAndWait();
			Optional<String> result = dialog.showAndWait();
			catnaam = result.get();
			
			ChoiceDialog<Integer> intdialog = new ChoiceDialog<>(1, array );
			intdialog.setTitle("pick new category");
			intdialog.setHeaderText("pick new category");
			intdialog.setContentText("pick new category");
			Optional<Integer> resultint = intdialog.showAndWait();
			catScore = resultint.get();
			
			categories.put(catnaam, catScore );
			catnaam = "";
			for (String entry : categories.keySet())
			{
			    catnaam = catnaam + entry +"   " +categories.get(entry) + "\n";
			}
			categorieArea.setText(catnaam);
			
		}
	}
	
	class AddStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			String allstatementsstring = "";
			TextInputDialog dialog = new TextInputDialog("");
			dialog.setTitle("new statement");
			dialog.setHeaderText("create new statement");
			dialog.setContentText("Please enter a possible statement:");
			
			for(int i = 0; i < badstatements.size(); i++) {
				allstatementsstring = allstatementsstring + badstatements.get(i) + "\n";
			}
			
			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				badstatements.add(result.get());
				allstatementsstring += result.get() + "\n";
				statementsArea.setText(allstatementsstring);
			}
			
			
		}
	}
	
	class RemoveStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			String allstatementsstring = "";
			if(badstatements.size() == 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("there are no statements to delete");
				alert.setContentText("please add statements firts");
				alert.showAndWait();
			}else {
				ChoiceDialog<String> dialog = new ChoiceDialog<>(badstatements.get(0), badstatements);
				dialog.setTitle("remove statement dialog");
				dialog.setHeaderText("what statment do you wana remove");
				dialog.setContentText("Choose your statement:");
				
				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    for(int i = 0;i< badstatements.size();i++) {
				    	if(badstatements.get(i).equals(result.get())) {
				    		badstatements.remove(i);
				    	}
				    }
				}
				for(int i = 0; i < badstatements.size(); i++) {
					allstatementsstring = allstatementsstring + badstatements.get(i) + "\n";
				}
				statementsArea.setText(allstatementsstring);
			}
		}
	}
}
