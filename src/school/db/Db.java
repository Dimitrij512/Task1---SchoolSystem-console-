package school.db;

import java.util.ArrayList;
import java.util.List;

import school.model.Audience;
import school.model.Clas;
import school.model.LessonForSchedule;
import school.model.Session;
import school.model.Subject;
import school.model.User;

public class Db {
  public static List<User> users = new ArrayList<User>();
  public static List<Subject> subjects = new ArrayList<Subject>();
  public static List<Audience> audiences = new ArrayList<Audience>();
  public static List<Clas> clases = new ArrayList<Clas>();
  public static List<ArrayList<LessonForSchedule>> schedule = new ArrayList<ArrayList<LessonForSchedule>>(5);
  public static Session currentSession;

}
