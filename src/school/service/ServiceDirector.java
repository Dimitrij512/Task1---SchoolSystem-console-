package school.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import school.db.Db;
import school.model.LessonForSchedule;
import school.model.Subject;

public class ServiceDirector {

	private Random random = new Random();
	private final int MONDAY = 0;
	private final int TUESDAY = 1;
	private final int WEDNESDAY = 2;
	private final int THURSDAY = 3;
	private final int FRIDAY = 4;
	private ArrayList<LessonForSchedule> mondey = new ArrayList<LessonForSchedule>();
	private ArrayList<LessonForSchedule> tuesday = new ArrayList<LessonForSchedule>();
	private ArrayList<LessonForSchedule> wednesday = new ArrayList<LessonForSchedule>();
	private ArrayList<LessonForSchedule> thursday = new ArrayList<LessonForSchedule>();
	private ArrayList<LessonForSchedule> friday = new ArrayList<LessonForSchedule>();

	List<LessonForSchedule> listLessonForAllClassesDay;
	List<Subject> subjectsForSchedule;

	public void createSchedule(List<Subject> listSubjects, int clas) {
		subjectsForSchedule = listSubjects;

		int numbOfAllLessons = listSubjects.size();

		int startDayOfWeek = MONDAY;
		int countLessonInDay = 1;

		for (int i = 0; i < numbOfAllLessons; i++) {

			int iterAudiance = random.nextInt(Db.audiences.size());
			int numbAudiance = Db.audiences.get(iterAudiance).getNumber();
			int iterSubject = random.nextInt(subjectsForSchedule.size());
			String nameSubject = subjectsForSchedule.get(iterSubject).getName();

			LessonForSchedule lessons = new LessonForSchedule(countLessonInDay, clas, numbAudiance, nameSubject);
			if (startDayOfWeek > 4) {
				countLessonInDay++;
				startDayOfWeek = 0;
			}
			if (startDayOfWeek == MONDAY) {
				mondey.add(getGoodLesson(lessons, startDayOfWeek));
				startDayOfWeek++;
				continue;
			}
			if (startDayOfWeek == TUESDAY) {
				tuesday.add(getGoodLesson(lessons, startDayOfWeek));
				startDayOfWeek++;
				continue;
			}
			if (startDayOfWeek == WEDNESDAY) {
				wednesday.add(getGoodLesson(lessons, startDayOfWeek));
				startDayOfWeek++;
				continue;
			}
			if (startDayOfWeek == THURSDAY) {
				thursday.add(getGoodLesson(lessons, startDayOfWeek));
				startDayOfWeek++;
				continue;
			}
			if (startDayOfWeek == FRIDAY) {
				friday.add(getGoodLesson(lessons, startDayOfWeek));
				startDayOfWeek++;
				continue;
			}
		}

		Db.schedule.add(MONDAY, mondey);
		Db.schedule.add(TUESDAY, tuesday);
		Db.schedule.add(WEDNESDAY, wednesday);
		Db.schedule.add(THURSDAY, thursday);
		Db.schedule.add(FRIDAY, friday);

	}

	public LessonForSchedule getGoodLesson(LessonForSchedule less, int day) {
		int lessCount = less.getNumbLesson();
		int lessAudience = less.getAudience();
		String lessName = less.getNameLesson();

		if (Db.schedule.size() == 0) {
			return less;

		} else {

			listLessonForAllClassesDay = Db.schedule.get(day);

			boolean start = true;

			while (start) {
				int countEqualAudiance = 0;
				int countEqualLessName = 0;

				for (int i = 0; i < listLessonForAllClassesDay.size(); i++) {
					int lessCountList = listLessonForAllClassesDay.get(i).getNumbLesson();
					int lessAudianceList = listLessonForAllClassesDay.get(i).getAudience();
					String lessNameList = listLessonForAllClassesDay.get(i).getNameLesson();

					if (lessAudience == lessAudianceList) {
						countEqualAudiance++;

					}
					if (lessCount == lessCountList && lessName.equals(lessNameList)) {
						countEqualLessName++;

					}
				}
				if (countEqualAudiance > 0) {
					int iterAudiance = random.nextInt(Db.audiences.size());
					lessAudience = Db.audiences.get(iterAudiance).getNumber();
				}
				if (countEqualLessName > 0) {
					int iterNameLess = random.nextInt(subjectsForSchedule.size());
					lessName = subjectsForSchedule.get(iterNameLess).getName();
				}
				if (countEqualAudiance == 0 && countEqualLessName == 0) {
					start = false;
					less.setNameLesson(lessName);
					less.setAudience(lessAudience);
					deleteSubjectFromList(less.getNameLesson());
				}
			}
		}
		return less;
	}

	public void deleteSubjectFromList(String name) {
		Iterator<Subject> iter = subjectsForSchedule.iterator();
		while (iter.hasNext()) {
			Subject s = iter.next();
			if (name.equals(s.getName())) {
				iter.remove();
				break;
			}
		}
	}
}
