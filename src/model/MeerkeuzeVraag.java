package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MeerkeuzeVraag implements Vraag {
	int vraagid;
	String vraagstelling , correctstatement,feedback;
	ArrayList<String> incorrectstatements;
	Category cat;
	Map<Category , Integer> categories = new HashMap<>();

	
	
	public MeerkeuzeVraag(int vraagid, String vraagstelling, String correctstatement, String feedback,
			ArrayList<String> incorrectstatements, Map<Category , Integer> cat) {
		super();
		this.vraagid = vraagid;
		this.vraagstelling = vraagstelling;
		this.correctstatement = correctstatement;
		this.feedback = feedback;
		this.incorrectstatements = incorrectstatements;
		this.categories = cat;
	}
	

	@Override
	public Map<Category, Integer> getCategories() {
		return categories;
	}


	@Override
	public void setCategories(Map<Category, Integer> categories) {
		this.categories = categories;
	}



	@Override
	public int getVraagId() {
		// TODO Auto-generated method stub
		return vraagid;
	}

	@Override
	public void setVraagId(int vraagid) {
		this.vraagid = vraagid;	
	}

	@Override
	public String getVraagStelling() {
		
		return vraagstelling;
	}

	@Override
	public void setVraagStelling(String vraagstelling) {
		this.vraagstelling = vraagstelling;
	}

	@Override
	public String getCorrectStatement() {
	
		return correctstatement;
	}

	@Override
	public void setCorrectStatement(String statement) {
		this.correctstatement = statement;
		
	}

	@Override
	public Category getCategory() {
		
		return cat;
	}
	
	public String getCategoryNaam() {
		String catString = "";
		for (Category entry : categories.keySet())
		{
			catString = catString + entry.getTitle() +",";
		}
		return catString;
	}


	public void setIncorrectStatements(ArrayList<String> statement) {
		this.incorrectstatements = statement;
		
	}


	public ArrayList<String> getIncorrectStatement() {
		return incorrectstatements;
	}
	public void addInCorrectStatement(String statement) {
		incorrectstatements.add(statement);
	}

	@Override
	public ArrayList<String> getAllAnswers() {
		ArrayList<String> antwoorden = new ArrayList<>();
		
		antwoorden.addAll(incorrectstatements);
		antwoorden.add(correctstatement);
		
		Collections.shuffle(antwoorden);
		
		return antwoorden;
	}


	@Override
	public void setFeedback(String feedback) {
		this.feedback = feedback;		
	}


	@Override
	public String getFeedback() {
		// TODO Auto-generated method stub
		return feedback;
	}
	
	@Override
	public void setCategory(Category categ) {
		this.cat = categ;
		
	}


	@Override
	public Map<String, Integer> getCategoriesString() {
		Map<String , Integer> categoriesstring = new HashMap<>();	
		for (Category entry : categories.keySet())
		{
			categoriesstring.put(entry.title, categories.get(entry));
		}	
		return categoriesstring;
	}

}
