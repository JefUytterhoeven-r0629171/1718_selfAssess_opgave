package view.panels;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Category;
import model.Observer;
import model.Vraag;
import view.panels.CategoryOverviewPane.AddCategoryListener;

public class QuestionOverviewPane extends GridPane implements Observer {
	private TableView<Vraag> table;
	private Button btnNew;
	private Controller controller;
	public QuestionOverviewPane() {
		
		controller= Controller.getInstance();
		controller.register(this);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Questions:"), 0, 0, 1, 1);
		
		table = new TableView<>();
		
	        table.setRowFactory(tv -> {
	            TableRow<Vraag> row = new TableRow<>();
	            row.setOnMouseClicked(event -> {
	                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
	                	Vraag rowData = row.getItem();
	                    System.out.println("Double click on: "+rowData.getVraagStelling());
	                    
	        			Stage secundStage = new Stage();
	        			QuestionDetailPane catergoryDtailsPane = new QuestionDetailPane(secundStage, rowData.getVraagId());
	        			Group root = new Group();
	        			Scene scene = new Scene(root, 320, 300);
	        			root.getChildren().add(catergoryDtailsPane);
	        			secundStage.setScene(scene);
	        			secundStage.sizeToScene();
	        			secundStage.show();
	                }
	            });
	            return row ;
	        });
		
		
		table.setPrefWidth(REMAINING);
        TableColumn nameCol = new TableColumn<>("vraag");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("VraagStelling"));
        table.getColumns().add(nameCol);
        TableColumn descriptionCol = new TableColumn<>("Category");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("CategoryNaam"));
        table.getColumns().add(descriptionCol);
        
        // add all category's in de categorystub
        for(int i = 0 ; i< controller.getVragen().size() ; i++) {
        	table.getItems().add(controller.getVragen().get(i));
            
        }
        
		this.add(table, 0, 1, 2, 6);
		
		btnNew = new Button("New");
		btnNew.setOnAction(new AddVraagListener());
		this.add(btnNew, 0, 11, 1, 1);
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

	class AddVraagListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			Stage secundStage = new Stage();
			QuestionDetailPane catergoryDtailsPane = new QuestionDetailPane(secundStage);
			Group root = new Group();
			Scene scene = new Scene(root, 320, 300);
			root.getChildren().add(catergoryDtailsPane);
			secundStage.setScene(scene);
			secundStage.sizeToScene();
			secundStage.show();
			
			System.out.println("new vraag window please");
			
		}
	}

	
	@Override
	public void update() {
        // add all category's in de categorystub
		table.getItems().clear();
		
        for(int i = table.getItems().size() ; i< controller.getVragen().size() ; i++) {
        	table.getItems().add(controller.getVragen().get(i));
            
        }
        
        table.refresh();
        
        System.out.println("questionoverview update methode called");
	}

}
