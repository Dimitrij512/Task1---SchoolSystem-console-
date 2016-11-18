package school.service;

import java.util.ArrayList;

import school.db.Db;
import school.model.Clas;
import school.model.LessonForSchedule;
import school.model.Student;

public class ServiceEducator extends ServiceStudent {

  final static int STUDENT_ROLE = 1;
  final static int EDUCATOR_ROLE = 2;
  final static int DIRECTOR_ROLE = 3;

  public void showSubjectOnWeek() {
    System.out.println("List subject on week : ");
    for (int i = 0; i < Db.schedule.size(); i++) {
      ArrayList<LessonForSchedule> listShedule = Db.schedule.get(i);
      for (int j = 0; j < listShedule.size(); j++) {
        if (Db.currentSession.getEducator() != null) {
          if (listShedule.get(j).getNameLesson().equals(Db.currentSession.getEducator().getSubject().getName())) {
            System.out.print("N: " + listShedule.get(j).getNumbLesson());
            System.out.print(" " + listShedule.get(j).getAudience());
            System.out.println(" " + listShedule.get(j).getNameLesson());
          }
        }
      }
    }
  }

  public void showStudentsInClass() {
    boolean start = true;
    while (start) {
      System.out.println("Enter the class number : ");
      int clasNumber = Integer.parseInt(enterNumber());

      for (int i = 0; i < Db.clases.size(); i++) {
        Clas clas = Db.clases.get(i);
        if (clas.getNumb() == clasNumber) {
          System.out.println("Class : " + clas.getNumb());
          System.out.println("List students :");
          for (int j = 0; j < clas.getStudents().size(); j++) {
            Student student = clas.getStudents().get(j);
            System.out.println("----------------------------");
            System.out.println(student.getName() + " " + student.getSurname());
            System.out.println("Adress: " + student.getAdress());
            System.out.println("----------------------------");
          }
        }
      }
      System.out.println("If you want see information abaut students in other classes press :  1");
      System.out.println("If you want go out from this service press othe key !!!");
      int parametr = Integer.parseInt(enterNumber());
      if (parametr != 1) {
        start = false;
      }
    }
  }
}
