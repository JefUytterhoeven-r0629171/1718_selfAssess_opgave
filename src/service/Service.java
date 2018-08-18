package service;
//@author Jef Uytterhoeven 2018
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db.CategoryStub;
import db.TestStub;
import db.VraagStub;
import model.*;

public class Service {
	CategoryStub allCategorys;
	VraagStub allvragen;
	TestStub alltests;
	private PropertiesManager prop;
	
	public Service() {
		prop = new PropertiesManager();
		allCategorys = CategoryStub.getInstance();
		allvragen = VraagStub.getInstance();
		alltests = TestStub.getInstance();
		
		// hardcodes vragen
		Map<Category , Integer> categoriesmap = new HashMap<>();
		categoriesmap.put(allCategorys.getCategorys().get(1), 2);
		categoriesmap.put(allCategorys.getCategorys().get(0), 9);
		//vraag 1
		ArrayList<String> incorrectstatements = new ArrayList<>();
		incorrectstatements.add("2/20");
		incorrectstatements.add("7/20");
		incorrectstatements.add("9/20");
		incorrectstatements.add("n/a");
		Vraag newvraag = new MeerkeuzeVraag(0,"hoeveel ga ik op ooo halen?" , "14/20","voor minder als een 10 ga ik niet", incorrectstatements, categoriesmap );
		allvragen.addVraag(newvraag);
		//vraag2
		categoriesmap = new HashMap<>();
		categoriesmap.put(allCategorys.getCategorys().get(3), 7);
		categoriesmap.put(allCategorys.getCategorys().get(0), 1);
		categoriesmap.put(allCategorys.getCategorys().get(4), 3);
		incorrectstatements = new ArrayList<>();
		incorrectstatements.add("cucumber");
		incorrectstatements.add("junit");
		incorrectstatements.add("selenium");
		newvraag = new MeerkeuzeVraag(1,"welke term heeft niets te maken met het testen van code ?" , "ucll","ucll is a school. i hope you know that", incorrectstatements,categoriesmap);
		allvragen.addVraag(newvraag);
		//vraag3
		categoriesmap = new HashMap<>();
		categoriesmap.put(allCategorys.getCategorys().get(2), 5);
		categoriesmap.put(allCategorys.getCategorys().get(4), 1);
		incorrectstatements = new ArrayList<>();
		incorrectstatements.add("facade");
		incorrectstatements.add("strategy");
		incorrectstatements.add("state");
		newvraag = new MeerkeuzeVraag(2,"wat is geen design principle" , "building a huge wall","building a huge wall is no design principle. \n het afschermen van je code noemt incapsulation", incorrectstatements, categoriesmap);
		allvragen.addVraag(newvraag);
		
	}
	
	public ArrayList<Category> getCategorys(){
		return allCategorys.getCategorys();
	}

	public void addCategory(String title, String desc, String cat) {
		allCategorys.addCategory(title,desc,cat);
	}

	public ArrayList<Vraag> getVragen() {
		return allvragen.getVragen();
	}

	public void addVraag(String vraagstelling, String correctStatement, ArrayList<String> badstatements, Map<String , Integer> cat,
			String feedback) {
		
		allvragen.addVraag(vraagstelling, correctStatement,badstatements,cat,feedback, allCategorys);
		
	}

	public int getNewTestId() {
		// TODO Auto-generated method stub
		Test test = new Test(alltests.getTests().size() , allvragen.getVragen(), allCategorys.getCategorys());
		alltests.addTest(test);
		return alltests.getTests().size();
	}

	public Test getTest(int testid) {
		// TODO Auto-generated method stub
		return alltests.getTests().get(testid - 1);
	}

	public void controlAnwser(String answer, int testid) {
		alltests.getTests().get(testid - 1).controlAnswer(answer);
		
	}

	public boolean isLaatsteVraag(int testid) {
		// TODO Auto-generated method stub
		return alltests.getTests().get(testid -1).isLaatsteVraag();
	}

	public ArrayList<String> getCategorysNamen() {
		ArrayList<String> catnamen = new ArrayList<>();
		
		for(int i = 0 ; i< this.getCategorys().size() ;i++) {
			catnamen.add(this.getCategorys().get(i).getTitle());
		}
		
		return catnamen;
	}

	public PropertiesManager getPropertiesManager() {
		// TODO Auto-generated method stub
		return prop;
	}

}
