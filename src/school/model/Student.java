package school.model;

import java.util.ArrayList;

public class Student extends User {

  private ArrayList<Subject> lessons;
  private double avarageRang;

  public ArrayList<Subject> getLessons() {
    return lessons;
  }

  public void setLessons(ArrayList<Subject> lessons) {
    this.lessons = lessons;
  }

  public double getAvarageRang() {
    return avarageRang;
  }

  public void setAvarageRang(double avarageRang) {
    this.avarageRang = avarageRang;
  }

}
