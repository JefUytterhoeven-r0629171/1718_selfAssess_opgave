package db;

import java.util.ArrayList;

import model.Category;

public class CategoryStub {
	private volatile static CategoryStub uniqueInstance;
	
	ArrayList<Category>  categorys;

	public CategoryStub() {
		 categorys = new ArrayList<Category>();
		 
		 Category basic = new Category(0 , "ooo", "deze category gaat over het vak ooo");
		 categorys.add(basic);
		 basic = new Category(1 , "testing", "deze category gaat over het vak testing");
		 categorys.add(basic);
		 basic = new Category(2 , "web4", "deze category gaat over het vak web4");
		 categorys.add(basic);
		 basic = new Category(3 , "ip", "deze category gaat over het vak ip");
		 categorys.add(basic);
		 basic = new Category(4 , "osa", "deze category gaat over het vak osa");
		 categorys.add(basic);
	}
	
	
	public static CategoryStub getInstance() {
		if(uniqueInstance == null) {
			synchronized(CategoryStub.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new CategoryStub ();
					}
			}
		}
		return uniqueInstance;
	}
	public void addCategory(Category category) {
		 categorys.add(category);
	}
	
	public void deleteCategory(int index) {
		 categorys.remove(index);
	}


	public ArrayList<Category> getCategorys() {
		return  categorys;
	}


	public void addCategory(String title, String desc, String cat) {
		Category newcat = new Category(categorys.size(), title, desc);
		
		if(cat != null) {
			for(int i =0; i< categorys.size(); i++) {
				if(cat.equals(categorys.get(i).getTitle())) {
					newcat.setMainCategory(categorys.get(i));
				}
			}
		}
		categorys.add(newcat);
		
		System.out.println("there are " + categorys.size() + "categories in the database");
		
	}	
	

}
