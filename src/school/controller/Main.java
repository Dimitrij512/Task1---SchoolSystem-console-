package school.controller;

import school.db.Db;
import school.dbInitial.DbInitial;
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
    while (true) {
      st.hellowPages();
      int role = st.autentification();

      if (role == STUDENT_ROLE) {
        String nameUser = Db.currentSession.getUser().getName();
        System.out.println("Dear " + nameUser);
        System.out.println("hello in our programm!!!!");
      }
      if (role == EDUCATOR_ROLE) {
        String nameEducator = Db.currentSession.getUser().getName();
        System.out.println("DEAR " + nameEducator);
      }
      if (role == DIRECTOR_ROLE) {

      }

    }
  }

}
