package controller;
//@author Jef Uytterhoeven 2018
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.CategoryStub;
import model.*;
import service.Service;

public class Controller implements Subject {
	private volatile static Controller uniqueInstance;
	Service appservice ;
	private ArrayList<Observer> observers;
	//private PropertiesManager prop;
	
	public Controller() {
		super();
		observers = new ArrayList<Observer>();
		appservice = new Service();
	}
	
	public static Controller getInstance() {
		if(uniqueInstance == null) {
			synchronized(Controller.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Controller ();
					}
			}
		}
		return uniqueInstance;
	}
	
	public ArrayList<Category> getCategorys(){
		return appservice.getCategorys();
	}
	
	public void addCategory(String title, String desc , String cat) {
		appservice.addCategory(title,desc,cat);
		this.notifyObserver();
	}


	public ArrayList<Vraag> getVragen() {
		return appservice.getVragen();
		
	}
	public void addVraag(String vraagstelling, String correctStatement, ArrayList<String> badstatements, Map<String , Integer> cat,
			String feedback) {
		appservice.addVraag(vraagstelling, correctStatement,badstatements,cat,feedback);
		//prop.setPropertie("testdone", "false");
		this.notifyObserver();
	}

	
	@Override
	public void register(Observer o) {
		System.out.println("adding observer");
		observers.add(o);
	}

	@Override
	public void unregister(Observer o) {
		 int observerIndex = observers.indexOf(o);
		 observers.remove(observerIndex);
	}

	@Override
	public void notifyObserver() {
		 for(Observer observer : observers){
			 observer.update();             	 
		 }

		
	}

	public int getNewTestid() {
		// TODO Auto-generated method stub
		return appservice.getNewTestId();
	}

	public Test getTest(int testid) {
		// TODO Auto-generated method stub
		return appservice.getTest(testid);
	}

	public void controlAnswer(String answer, int testid) {
		appservice.controlAnwser(answer, testid);		
	}

	public boolean isLaatsteVraag(int testid) {
		return appservice.isLaatsteVraag(testid);
	}

	public ArrayList<String> getCategorysnamen() {
		// TODO Auto-generated method stub
		return appservice.getCategorysNamen();
	}

	public PropertiesManager getPropertiesManager() {
		// TODO Auto-generated method stub
		return appservice.getPropertiesManager();
	}


	

}
