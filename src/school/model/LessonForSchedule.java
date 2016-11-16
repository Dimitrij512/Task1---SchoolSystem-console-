package school.model;

public class LessonForSchedule {

  private int clas;
  private int numbLesson;
  private int audience;
  private String nameLesson;

  public LessonForSchedule(int numbLesson, int clas, int audience, String nameLesson) {

    this.numbLesson = numbLesson;
    this.clas = clas;
    this.audience = audience;
    this.nameLesson = nameLesson;
  }

  public int getClas() {
    return clas;
  }

  public void setClas(int clas) {
    this.clas = clas;
  }

  public int getNumbLesson() {
    return numbLesson;
  }

  public void setNumbLesson(int numbLesson) {
    this.numbLesson = numbLesson;
  }

  public int getAudience() {
    return audience;
  }

  public void setAudience(int audience) {
    this.audience = audience;
  }

  public String getNameLesson() {
    return nameLesson;
  }

  public void setNameLesson(String nameLesson) {
    this.nameLesson = nameLesson;
  }

}
