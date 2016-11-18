package school.model;

public class Session {
  private User user;
  private Student student;
  private Educator educator;
  private Director director;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Educator getEducator() {
    return educator;
  }

  public void setEducator(Educator educator) {
    this.educator = educator;
  }

  public Director getDirector() {
    return director;
  }

  public void setDirector(Director director) {
    this.director = director;
  }

}
