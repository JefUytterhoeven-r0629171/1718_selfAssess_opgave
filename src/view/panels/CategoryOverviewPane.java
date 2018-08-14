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
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Category;
import model.Observer;


public class CategoryOverviewPane extends GridPane implements Observer {
	private TableView<Category> table;
	private Button btnNew;
	private Controller controller;
	public CategoryOverviewPane() {
		
		controller= Controller.getInstance();
		controller.register(this);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Categories:"), 0, 0, 1, 1);
		
		   table = new TableView<>();
	        table.setRowFactory(tv -> {
	            TableRow<Category> row = new TableRow<>();
	            row.setOnMouseClicked(event -> {
	                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
	                	Category rowData = row.getItem();
	                    System.out.println("Double click on: "+rowData.getTitle());
	                    
	        			Stage secundStage = new Stage();
	        			CategoryDetailPane catergoryDtailsPane = new CategoryDetailPane(secundStage,rowData.getCategoryid());
	        			Group root = new Group();
	        			Scene scene = new Scene(root, 300, 150);
	        			root.getChildren().add(catergoryDtailsPane);
	        			secundStage.setScene(scene);
	        			secundStage.sizeToScene();
	        			secundStage.show();
	                }
	            });
	            return row ;
	        });
		
		
		table.setPrefWidth(REMAINING);
		
		
		
        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("title"));
        table.getColumns().add(nameCol);
  
        TableColumn descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        table.getColumns().add(descriptionCol);
        
       
        // add all category's in de categorystub
        for(int i = 0 ; i< controller.getCategorys().size() ; i++) {
        	table.getItems().add(controller.getCategorys().get(i));
        }
        
		this.add(table, 0, 1, 2, 6);
		
		btnNew = new Button("New");
		btnNew.setOnAction(new AddCategoryListener());
		
		this.add(btnNew, 0, 11, 1, 1);
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}
		
	class AddCategoryListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			
			
			Stage secundStage = new Stage();
			CategoryDetailPane catergoryDtailsPane = new CategoryDetailPane(secundStage);
			Group root = new Group();
			Scene scene = new Scene(root, 300, 150);
			root.getChildren().add(catergoryDtailsPane);
			secundStage.setScene(scene);
			secundStage.sizeToScene();
			secundStage.show();
			
			System.out.println("new category window please");
			
		}
	}


	@Override
	public void update() {
		table.getItems().clear();
		
	       for(int i = table.getItems().size() ; i< controller.getCategorys().size() ; i++) {
	        	table.getItems().add(controller.getCategorys().get(i));
	        }
	      
	       table.refresh();
	       System.out.println("categorie observer update methode called\n\n");
		
	}

}
