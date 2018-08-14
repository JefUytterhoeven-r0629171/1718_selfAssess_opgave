package view.panels;

import java.awt.TextField;

import model.Observer;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PropertiesManager;

public class MessagePane extends GridPane implements Observer {
	private Button testButton;
	Controller controller;
	Text text = new Text();
	//private PropertiesManager prop;
	public MessagePane (){
		
		//prop = new PropertiesManager();
		controller= Controller.getInstance();
		controller.register(this);
		
	    setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		testButton = new Button("Evaluate");
		testButton.setOnAction(new EventHandler<ActionEvent>() { //TODO remove or generalize
			
			@Override
			public void handle(ActionEvent event) {
				Stage secundStage = new Stage();
				TestPane catergoryDtailsPane = new TestPane(secundStage , controller.getNewTestid());
				Group root = new Group();
				Scene scene = new Scene(root, 750, 300);
				root.getChildren().add(catergoryDtailsPane);
				secundStage.setScene(scene);
				secundStage.sizeToScene();
				secundStage.show();
				
			}
		});
		add(testButton, 0,0,1,1);
		if(controller.getPropertiesManager().getPropertieOf("testdone").equals("true")) {
			text.setText("you have already done this test");
		}else {
			text.setText("hello there please do the test");
		}
		
		
		
		
		add(text, 0,2,1,1);
		setHalignment(testButton, HPos.CENTER);
	}

	@Override
	public void update() {
		System.out.println("messagepane word geupdate");
		
		if(controller.getPropertiesManager().getPropertieOf("testdone").equals("true")) {
			text.setText("you have already done this test");
		}else {
			text.setText("hello there please do the test");
		}
	}

}
