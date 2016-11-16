package school.model;

import java.util.List;

public class Subject {
	private String name;
	private List<Integer> raiting;

	// constructor

	public Subject() {
	}

	public Subject(String name) {
		this.name = name;
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
