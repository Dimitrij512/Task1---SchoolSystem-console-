package school.service;

import java.util.ArrayList;
import java.util.List;

import school.db.Db;
import school.model.Clas;
import school.model.LessonForSchedule;
import school.model.Student;
import school.model.Subject;

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
            System.out.println("Subjects : ");
            showRaiting(student);
            System.out.println("avarage raitin : " + student.getAvarageRang());
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

  public void showRaiting(Student student) {
    int summRaiting = 0;
    int countRaiting = 0;
    double avarageRaiting = 0;

    for (int i = 0; i < student.getLessons().size(); i++) {
      String nameSubject = student.getLessons().get(i).getName();
      System.out.print(nameSubject + " - ");
      System.out.print("raiting : ");
      List<Integer> raitingList = student.getLessons().get(i).getRaiting();
      for (int j = 0; j < raitingList.size(); j++) {
        int raiting = raitingList.get(j);
        if (raiting > 0) {
          summRaiting += raiting;
          countRaiting++;
        }

        System.out.print(raiting + ", ");

      }
      System.out.println();
    }
    if (countRaiting != 0) {
      avarageRaiting = (double) (summRaiting / countRaiting);
      student.setAvarageRang(avarageRaiting);
    }

  }

  public void addRaitingForStudent() {
    boolean start = true;
    while (start) {
      showStudentsInClass();
      System.out.println("Enter the student`s surname : ");
      String studentName = enterNumber();
      findSubjcetsStudents(studentName);
      System.out.println("If you want set raiting for other student : press 1");
      System.out.println("If you want go out from this service : press press other key !");
      int parametr = Integer.parseInt(enterNumber());
      if (parametr != 1) {
        start = false;
      }
    }
  }

  public void findSubjcetsStudents(String surname) {
    int column = 4;
    Student student;
    Subject subject;
    for (int i = 0; i < Db.student.size(); i++) {
      student = Db.student.get(i);
      String studentSurname = student.getSurname();
      if (surname.equals(studentSurname)) {

        System.out.println("List subjects of student : " + studentSurname);
        if (Db.currentSession.getDirector() != null) {
          for (int j = 0; j < student.getLessons().size(); j++) {
            subject = student.getLessons().get(j);
            System.out.print("| N: " + j + " " + subject.getName() + "| ");
            if (j % column == 0) {
              System.out.println();
            }
          }
        } else if (Db.currentSession.getEducator() != null) {
          String nameSubjectEducator = Db.currentSession.getEducator().getSubject().getName();
          for (int j = 0; j < student.getLessons().size(); j++) {
            String nameSubjectStudent = student.getLessons().get(j).getName();
            if (nameSubjectEducator.equals(nameSubjectStudent)) {
              System.out.print("N: " + j + " " + nameSubjectStudent + "||");
            }
          }
        }
        System.out.println();
        if (Db.currentSession.getDirector() != null) {
          boolean start1 = true;
          while (start1) {
            System.out.println("Eneter number of subject from list : ");
            int numberOfSubject = Integer.parseInt(enterNumber());
            System.out.println("subject : " + student.getLessons().get(numberOfSubject).getName());
            boolean start2 = true;
            while (start2) {
              System.out.println("enter raiting : ");
              int raiting = Integer.parseInt(enterNumber());
              student.getLessons().get(numberOfSubject).setRaiting(raiting);
              System.out.println("You has seted raitin for " + student.getName());
              System.out.print("subject : " + student.getLessons().get(numberOfSubject).getName() + " ");
              System.out.println("raiting : " + raiting);
              System.out.println("If you want set raitin for oter subjcets: press 1 ");
              System.out.println("If you want set raitin for this subject: press 2 ");
              System.out.println("If you want go out from this service : press othe key ");
              int parametr = Integer.parseInt(enterNumber());
              if (parametr == 1) {
                start2 = false;
              } else if (parametr == 2) {
                start1 = true;
                start2 = true;
              } else {
                start1 = false;
                start2 = false;
              }
            }
          }
        } else if (Db.currentSession.getEducator() != null) {

          int iterSubject = 0;
          Subject educatorSubject = Db.currentSession.getEducator().getSubject();
          String educatorSubjectName = Db.currentSession.getEducator().getSubject().getName();
          for (int j = 0; j < student.getLessons().size(); j++) {
            String subjectName = Db.subjects.get(i).getName();
            if (educatorSubjectName.equals(subjectName)) {
              iterSubject = j;
              break;
            }
          }
          boolean start3 = true;
          while (start3) {
            System.out.println("enter raiting : ");
            int raiting = Integer.parseInt(enterNumber());
            student.getLessons().get(iterSubject).setRaiting(raiting);
            System.out.println("You has seted raitin for " + student.getName());
            System.out.print("subject : " + student.getLessons().get(iterSubject).getName() + " ");
            System.out.println("raiting : " + raiting);
            System.out.println("If you want set raitin more: press 1 ");
            System.out.println("If you want go out from this service : press othe key ");
            int parametr = Integer.parseInt(enterNumber());
            if (parametr != 1) {
              start3 = false;
            }
          }
        }
      }
    }
  }
}
