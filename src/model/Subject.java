package model;
//@author Jef Uytterhoeven 2018
public interface Subject {

	public void register(Observer o); 
	public void unregister(Observer o);
    public void notifyObserver();

}
