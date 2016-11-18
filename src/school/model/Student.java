package school.model;

import java.util.List;

public class Student extends User {
  private int clas;
  private List<Subject> lessons;
  private double avarageRang;

  public List<Subject> getLessons() {
    return lessons;
  }

  public void setLessons(List<Subject> lessons) {
    this.lessons = lessons;
  }

  public double getAvarageRang() {
    return avarageRang;
  }

  public void setAvarageRang(double avarageRang) {
    this.avarageRang = avarageRang;
  }

  public int getClas() {
    return clas;
  }

  public void setClas(int clas) {
    this.clas = clas;
  }

}
