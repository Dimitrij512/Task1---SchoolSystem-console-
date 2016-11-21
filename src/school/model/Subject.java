package school.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private String name;
	private List<Integer> raiting;

	// constructor

	public Subject() {
		this.raiting = new ArrayList<Integer>();
	}

	public Subject(String name) {
		this.name = name;
		this.raiting = new ArrayList<Integer>();

	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getRaiting() {
		return raiting;
	}

	public void setRaiting(int raiting) {
		this.raiting.add(raiting);
	}

}
