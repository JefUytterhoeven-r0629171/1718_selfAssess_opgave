package model;
//@author Jef Uytterhoeven 2018
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {
	int currentQuestionid = 0 , testid;
	ArrayList<Vraag> vragen = new ArrayList<>();
	ArrayList<Vraag> foutevragen = new ArrayList<>();
	Map<String , Integer> score = new HashMap<>();
	Map<String , Integer> totalscoren = new HashMap<>();
	
	
	public Test(int testid, ArrayList<Vraag> vragen2, ArrayList<Category> categorys) {
		this.testid = testid;
		this.vragen = vragen2;
		for(int i = 0; i< categorys.size(); i++) {
			score.put(categorys.get(i).title, 0);
			totalscoren.put(categorys.get(i).title, 0);
		}
	}
	public int getCurrentQuestionid() {
		return currentQuestionid;
	}
	public void setCurrentQuestionid(int currentQuestionid) {
		this.currentQuestionid = currentQuestionid;
	}
	public ArrayList<Vraag> getVragen() {
		return vragen;
	}
	public void setVragen(ArrayList<Vraag> vragen) {
		this.vragen = vragen;
	}
	public Map<String, Integer> getScore() {
		return score;
	}
	public void setScore(Map<String, Integer> score) {
		this.score = score;
	}
	public int getTestid() {
		return testid;
	}
	public Vraag getCurrentQuestion() {
		// TODO Auto-generated method stub
		System.out.println(currentQuestionid);
		return vragen.get(currentQuestionid);
	}
	public void controlAnswer(String answer) {
		Vraag vraag = vragen.get(currentQuestionid);
		Map<Category, Integer> cat = vraag.getCategories();
		
		if(vraag.getCorrectStatement().equals(answer)) {	
			for (Category entry : cat.keySet())
			{
				score.put(entry.getTitle(), score.get(entry.getTitle()) + cat.get(entry));
				totalscoren.put(entry.getTitle(), totalscoren.get(entry.getTitle()) + cat.get(entry));
			}
		}else {
			for (Category entry : cat.keySet())
			{
				totalscoren.put(entry.getTitle(), totalscoren.get(entry.getTitle()) + cat.get(entry));
			}
			foutevragen.add(vragen.get(currentQuestionid));
		}
		
		
		/* code for when it was only 1 category
		if(vragen.get(currentQuestionid).getCorrectStatement().equals(answer)) {
			score.put(cat.getTitle(), score.get(cat.getTitle()) + 1);
			totalscoren.put(cat.getTitle(), totalscoren.get(cat.getTitle()) + 1);
			System.out.println("new score for category " + cat.getTitle()+ " is " + score.get(cat.getTitle()));
		}else {
			totalscoren.put(cat.getTitle(), totalscoren.get(cat.getTitle()) + 1);
			foutevragen.add(vragen.get(currentQuestionid));
		}
		*/
		currentQuestionid++;
		
	}
	public boolean isLaatsteVraag() {
		System.out.println("vragen = " + vragen.size() + " questionid = " + currentQuestionid);
		if(currentQuestionid  == vragen.size() ) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getScoreToString() {
		String scorestring = "";
		int totalscore = 0;
		 for (String key : score.keySet()) {
			 	scorestring = scorestring+ "category :  " + key + " you scored : " + score.get(key)+" out of " + totalscoren.get(key) + "\n" ;
		        System.out.println(key + "=" + score.get(key));
		        totalscore = totalscore + score.get(key) ;
		    }
		
		return "total score " + totalscore + " \n" +scorestring;
	}
	public String getMistakeString() {
		String mistakeString = "";
		
		for(int i =0 ; i< foutevragen.size(); i++) {
			mistakeString = mistakeString + foutevragen.get(i).getVraagStelling() + " : " +foutevragen.get(i).getFeedback() + "\n \n ";
		}
		
		return mistakeString;
	}
	public boolean isPerfectScore() {
		if(foutevragen.size() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	

}
