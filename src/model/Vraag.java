package model;

import java.util.ArrayList;
import java.util.Map;

public interface Vraag {
	public int getVraagId();
	public void setVraagId(int vraagid);
	public String getVraagStelling();
	public void setVraagStelling(String vraagstelling);
	public String getCorrectStatement();
	public void setCorrectStatement(String statement);
	public Category getCategory();
	public ArrayList<String> getAllAnswers();
	public void setFeedback(String feedback);
	public String getFeedback();
	public void setCategory(Category categ);
	public void setIncorrectStatements(ArrayList<String> badstatements);
	Map<Category, Integer> getCategories();
	void setCategories(Map<Category, Integer> categories);
	public Map<String, Integer> getCategoriesString();
}
