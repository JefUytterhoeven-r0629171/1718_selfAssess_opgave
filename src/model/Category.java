package model;
//@author Jef Uytterhoeven 2018
public class Category {
	int categoryid;
	String title, description;
	Category mainCategory;
	
	
	
	public Category(int categoryid, String title, String description) {
		super();
		this.categoryid = categoryid;
		this.title = title;
		this.description = description;
		mainCategory = null;
	}

	public Category(int categoryid, String title, String description, Category mainCategory) {
		super();
		this.categoryid = categoryid;
		this.title = title;
		this.description = description;
		this.mainCategory = mainCategory;
	}
	
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getMainCategory() {
		return mainCategory;
	}
	public void setMainCategory(Category mainCategory) {
		this.mainCategory = mainCategory;
	}
	
	

}
