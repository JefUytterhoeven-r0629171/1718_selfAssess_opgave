package model;
//@author Jef Uytterhoeven 2018
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JaNeeVraag implements Vraag {
	int vraagid;
	String vraagstelling , correctstatement,feedback;
	String incorrectstatements;
	Category cat;
	Map<Category , Integer> categories = new HashMap<>();
	
	
	public JaNeeVraag(int vraagid, String vraagstelling, String correctstatement,
			String incorrectstatements, Map<Category , Integer> cat , String feedback) {
		super();
		this.vraagid = vraagid;
		this.vraagstelling = vraagstelling;
		this.correctstatement = correctstatement;
		this.feedback = feedback;
		this.incorrectstatements = incorrectstatements;
		this.categories = cat;
	}
	public String getCategoryNaam() {
		String catString = "";
		return catString;
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

	public String getIncorrectstatements() {
		return incorrectstatements;
	}

	public void setIncorrectstatements(String incorrectstatements) {
		this.incorrectstatements = incorrectstatements;
	}

	@Override
	public ArrayList<String> getAllAnswers() {
		ArrayList<String> antwoorden = new ArrayList<>();
		antwoorden.add("yes");
		antwoorden.add("no");
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
	
	public void setIncorrectStatements(ArrayList<String> statement) {
		
		
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
