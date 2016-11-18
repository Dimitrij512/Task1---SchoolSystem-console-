package school.model;

public class Educator extends User {
  private Subject subject;

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }
}
