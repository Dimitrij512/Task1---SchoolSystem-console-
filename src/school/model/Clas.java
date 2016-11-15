package school.model;

import java.util.List;

public class Clas {
  private int numb;
  private List<User> students;

  public int getNumb() {
    return numb;
  }

  public void setNumb(int numb) {
    this.numb = numb;
  }

  public List<User> getStudents() {
    return students;
  }

  public void setStudents(List<User> students) {
    this.students = students;
  }

}
