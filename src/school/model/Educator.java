package school.model;

public class Educator extends User {
	private Subject subject;
	private int audiance;

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getAudiance() {
		return audiance;
	}

	public void setAudiance(int audiance) {
		this.audiance = audiance;
	}
}
