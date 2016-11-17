package school.service;

import java.util.ArrayList;

import school.db.Db;
import school.model.LessonForSchedule;

public class ServiceEducator extends ServiceStudent {

  final static int STUDENT_ROLE = 1;
  final static int EDUCATOR_ROLE = 2;
  final static int DIRECTOR_ROLE = 3;

  public void showSubjectOnWeek() {
    System.out.println("List subject on week : ");
    for (int i = 0; i < Db.schedule.size(); i++) {
      ArrayList<LessonForSchedule> listShedule = Db.schedule.get(i);
      for (int j = 0; j < listShedule.size(); j++) {
        if (listShedule.get(j).getNameLesson().equals(Db.currentSession.getUser().getLesson())) {
          System.out.print("N: " + listShedule.get(j).getNumbLesson());
          System.out.print(" " + listShedule.get(j).getAudience());
          System.out.println(" " + listShedule.get(j).getNameLesson());

        }
      }
    }
  }

  public void addRaiting() {

  }
}
