package db;
//@author Jef Uytterhoeven 2018
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Category;
import model.JaNeeVraag;
import model.MeerkeuzeVraag;
import model.Vraag;

public class VraagStub {
	
	ArrayList<Vraag> vragen;
	
	
	
	public VraagStub() {
		super();
		vragen = new ArrayList<Vraag>();
	}
	private volatile static VraagStub uniqueInstance;
	
	public static VraagStub getInstance() {
		if(uniqueInstance == null) {
			synchronized(CategoryStub.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new VraagStub ();
					}
			}
		}
		return uniqueInstance;
	}
	
	public void addVraag(Vraag vraag) {
		this.vragen.add(vraag);
	}
	public void deleteVraag(int index) {
		vragen.remove(index);
	}
	public ArrayList<Vraag> getVragen(){
		return vragen;
	}

	public void addVraag(String vraagstelling, String correctStatement, ArrayList<String> badstatements, Map<String , Integer> cat,
			String feedback, CategoryStub allcats) {
		Vraag newvraag;
		if(cat != null) {
			Map<Category , Integer> categories = new HashMap<>();
			for (String entry : cat.keySet()) {
				for(int i =0; i< allcats.getCategorys().size(); i++) {
					if(entry.equals(allcats.getCategorys().get(i).getTitle())) {
						categories.put(allcats.getCategorys().get(i), cat.get(entry));
						System.out.println("er word een categorie toegevoegd aan de nieuwe vraag \n naam van de categorie is " + allcats.getCategorys().get(i).getTitle() +" het aantal punten in deze cat zijn " + cat.get(entry)  );
					}
				}
			}
			
			if(correctStatement.equals("yes") && badstatements.size() == 1 ) {
				newvraag = new JaNeeVraag(vragen.size(), vraagstelling, "yes" , "no" ,categories , feedback);
				
			}else {
				newvraag = new MeerkeuzeVraag(vragen.size(), vraagstelling, correctStatement, feedback, badstatements, categories);
			}
			
			vragen.add(newvraag);
			System.out.println("er zijn nu " + vragen.size() + " vragen in de database");
		}
		
	}
	

}
