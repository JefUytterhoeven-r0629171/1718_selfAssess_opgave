package view.panels;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Category;

public class CategoryDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextField titleField, descriptionField;
	private ComboBox categoryField;
	private Controller controller;
	private Stage parent;
	private int catid;
	public CategoryDetailPane(Stage secundStage) {
		this.parent = secundStage;
		controller= Controller.getInstance();
		this.buildScreen(-1);
	}
	public CategoryDetailPane(Stage secundStage, int catid) {
		this.parent = secundStage;
		controller= Controller.getInstance();
		this.buildScreen(catid);
	}
	

	private void buildScreen(int catid) {
		this.catid = catid;
		this.setPrefHeight(150);
		this.setPrefWidth(300);
		
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);


		titleField = new TextField();



		descriptionField = new TextField();
		
		categoryField = new ComboBox<>();
		
		// add all category's in de categorystub
        for(int i = 0 ; i< controller.getCategorys().size() ; i++) {
        	categoryField.getItems().add(controller.getCategorys().get(i).getTitle());
        }
		

		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(new cancelAddCategoryListener());
		

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		btnOK.setOnAction(new AddCategoryListener());
		
		if(catid != -1) {
			titleField.setText(controller.getCategorys().get(catid).getTitle());
			descriptionField.setText(controller.getCategorys().get(catid).getDescription());
			//categoryField.setValue(controller.getCategorys().get(catid).getMainCategory().getTitle());
		}
		
		
		this.add(new Label("Title:"), 0, 0, 1, 1);
		this.add(titleField, 1, 0, 1, 1);
		this.add(new Label("Description:"), 0, 1, 1, 1);
		this.add(descriptionField, 1, 1, 1, 1);
		this.add(new Label("Main Category:"), 0, 2, 1, 1);
		this.add(categoryField, 1, 2, 1, 1);
		this.add(btnCancel, 0, 3, 1, 1);
		this.add(btnOK, 1, 3, 1, 1);
	}
	public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnOK.setOnAction(saveAction);
		
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}
	
	class cancelAddCategoryListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			
			parent.close();
			System.out.println("cancel category button is clicked");
			
		}
	}
	class AddCategoryListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			String title = titleField.getText();
			String desc = descriptionField.getText();
			String cat = (String) categoryField.getValue();
			

			if(catid == -1) {
				controller.addCategory(title, desc, cat);
			}else {
				Category categ = null;
				for(int i = 0 ; i< controller.getCategorys().size();i++) {
					if(controller.getCategorys().get(i).getTitle().equals(cat)) {
						categ = controller.getCategorys().get(i);
					}
				}
				
				controller.getCategorys().get(catid).setTitle(title);
				controller.getCategorys().get(catid).setDescription(desc);
				if(cat == null) {
					
				}else {
					controller.getCategorys().get(catid).setMainCategory(categ);
				}
				controller.notifyObserver();

			}
			parent.close();
			System.out.println("save category button is clicked");
			
		}
	}

}
