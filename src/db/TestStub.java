package db;

import java.util.ArrayList;

import model.Category;
import model.Test;

public class TestStub {
	private volatile static TestStub uniqueInstance;
	
	ArrayList<Test>  tests;
	
	public TestStub() {
		tests = new ArrayList<Test>();
		 
	}
	
	public static TestStub getInstance() {
		if(uniqueInstance == null) {
			synchronized(TestStub.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new TestStub ();
					}
			}
		}
		return uniqueInstance;
	}

	public ArrayList<Test> getTests() {
		return tests;
	}

	public void setTests(ArrayList<Test> tests) {
		this.tests = tests;
	}
	
	public void addTest(Test test) {
		tests.add(test);
	}
	public Test getTest(int testid) {
		return tests.get(testid);
	}
}
