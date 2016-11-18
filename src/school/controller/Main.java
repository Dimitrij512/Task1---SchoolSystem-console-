package school.controller;

import school.db.Db;
import school.dbInitial.DbInitial;
import school.service.ServiceDirector;
import school.service.ServiceEducator;
import school.service.ServiceStudent;

public class Main {
  final static int STUDENT_ROLE = 1;
  final static int EDUCATOR_ROLE = 2;
  final static int DIRECTOR_ROLE = 3;

  public static void main(String[] args) {

    DbInitial.initialUser();
    DbInitial.initialALLSubject();
    DbInitial.initialAudience();
    DbInitial.initialClases();
    ServiceStudent st = new ServiceStudent();
    ServiceEducator se = new ServiceEducator();
    ServiceDirector sd = new ServiceDirector();

    while (true) {
      st.hellowPages();
      int role = st.autentification();

      if (role == STUDENT_ROLE) {
        String nameUser = Db.currentSession.getStudent().getName();
        System.out.println("--------------------");
        System.out.println("Hello " + nameUser);
        System.out.println("hello in our programm!!!!");
        System.out.println("--------------------");
        boolean start = true;
        while (start) {
          System.out.println("--------------------");
          System.out.println("Show your schedule press : 1 ");
          System.out.println("--------------------");
          int parametr = Integer.parseInt(st.enterNumber());
          if (parametr == 1) {
            st.showAllSchedule();
          }
          System.out.println("If you want yet press : 1");
          System.out.println("If you log aut press press : other key  ");
          parametr = Integer.parseInt(sd.enterNumber());
          if (parametr != 1) {
            start = false;
          }
        }

      }
      if (role == EDUCATOR_ROLE) {
        String nameEducator = Db.currentSession.getEducator().getName();
        System.out.println("--------------------");
        System.out.println("Hello " + nameEducator);
        System.out.println("--------------------");
        boolean start = true;
        while (start) {
          System.out.println("--------------------");
          System.out.println("Shoose service : ");
          System.out.println("Show your schedule press : 1 ");
          System.out.println("Show list subject on week : 2 ");
          System.out.println("Show list students in clas : 3");
          System.out.println("add raiting for student : 4");
          System.out.println("--------------------");
          int parametr = Integer.parseInt(se.enterNumber());
          if (parametr == 1) {
            se.showAllSchedule();
          } else if (parametr == 2) {
            se.showSubjectOnWeek();
          } else if (parametr == 3) {
            se.showStudentsInClass();
          } else if (parametr == 4) {
            se.addRaitingForStudent();
          }
          System.out.println("If you want yet press : 1");
          System.out.println("If you log aut press press : other key  ");
          parametr = Integer.parseInt(sd.enterNumber());
          if (parametr != 1) {
            start = false;
          }
        }
      }
      if (role == DIRECTOR_ROLE) {
        String nameDirector = Db.currentSession.getDirector().getName();
        System.out.println("HELLO " + nameDirector);
        boolean start = true;
        int parametr;

        while (start) {
          System.out.println("--------------------");
          System.out.println("Shoose service : ");
          System.out.println("create shedule press : 2");
          System.out.println("show shedule press : 1");
          System.out.println("add raiting for student : 3");
          System.out.println("--------------------");
          parametr = Integer.parseInt(sd.enterNumber());
          if (parametr == 2) {
            sd.shooseSubjectForCreateShedule();
          } else if (parametr == 1) {
            sd.showAllSchedule();
          } else if (parametr == 3) {
            sd.addRaitingForStudent();
          }
          System.out.println("If you want yet press : 1");
          System.out.println("If you log aut press press : other key  ");
          parametr = Integer.parseInt(sd.enterNumber());
          if (parametr != 1) {
            start = false;
          }
        }
      }
    }
  }
}
