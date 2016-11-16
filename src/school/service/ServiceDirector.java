package school.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import school.db.Db;
import school.model.LessonForSchedule;
import school.model.Subject;

public class ServiceDirector extends ServiceStudent {

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
	List<Subject> listSubjects = new ArrayList<Subject>();

	public void createSchedule(int clas) {
		System.out.println("List subject size : " + listSubjects.size());

		subjectsForSchedule = listSubjects;

		int numbOfAllLessons = listSubjects.size();

		int startDayOfWeek = MONDAY;
		int countLessonInDay = 0;

		for (int i = 0; i < numbOfAllLessons; i++) {

			int iterAudiance = random.nextInt(Db.audiences.size());
			int numbAudiance = Db.audiences.get(iterAudiance).getNumber();
			int iterSubject = random.nextInt(subjectsForSchedule.size());
			String nameSubject = subjectsForSchedule.get(iterSubject).getName();

			LessonForSchedule lessons = new LessonForSchedule(countLessonInDay, clas, numbAudiance, nameSubject);
			if (startDayOfWeek >= 4) {
				countLessonInDay += 1;
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

	public void shooseSubjectForCreateShedule() {
		listSubjects = new ArrayList<Subject>();
		System.out.println("Make shoose subjects for make shedule : ");
		System.out.println("Fist shoose the numb of clas : ");
		System.out.println("List clases : ");
		viewAllClases();
		int clas = Integer.parseInt(enterNumber());
		System.out.println("List subjects : ");
		viewAllSubjects();
		boolean start = true;
		while (start) {
			System.out.println("Enter number subject : ");
			int numbOfSubject = Integer.parseInt(enterNumber());
			System.out.println("Enter count subject : ");
			int countSubjects = Integer.parseInt(enterNumber());
			addSubjectsorShedule(numbOfSubject, countSubjects);
			System.out.println("if you want add more subjects press : 1");
			System.out.println("if it all press : same key ");
			int parametr = Integer.parseInt(enterNumber());
			if (parametr != 1) {
				start = false;
			}
		}
		createSchedule(clas);
	}

	public void viewAllSubjects() {
		for (int i = 0; i < Db.subjects.size(); i++) {
			String name = Db.subjects.get(i).getName();
			System.out.println("N: " + i + " " + name);

		}
	}

	public void viewAllClases() {
		for (int i = 0; i < Db.clases.size(); i++) {
			int name = Db.clases.get(i).getNumb();
			System.out.println("- " + name);
		}
	}

	public void addSubjectsorShedule(int numbSubject, int countSubject) {
		for (int i = 0; i < countSubject; i++) {
			for (int j = 0; j < Db.subjects.size(); j++) {
				Subject sub = Db.subjects.get(j);
				if (numbSubject == j) {
					listSubjects.add(sub);
				}
			}
		}
	}

	public void showAllSсhedule() {
		for (int i = 0; i < Db.clases.size(); i++) {
			int numbOfClass = Db.clases.get(i).getNumb();
			System.out.println("-|--|---|--|--|---|-----|---|----|---");
			System.out.println("class : " + numbOfClass);
			for (int j = 0; j < Db.schedule.size(); j++) {
				System.out.println("--------------");
				System.out.println("day : " + j);
				System.out.println("--------------");
				for (int j2 = 0; j2 < Db.schedule.get(j).size(); j2++) {
					LessonForSchedule lessonForSchedule = Db.schedule.get(j).get(j2);
					int clasLesson = lessonForSchedule.getClas();
					int countLesson = lessonForSchedule.getNumbLesson();
					int numbAudience = lessonForSchedule.getAudience();
					String name = lessonForSchedule.getNameLesson();
					if (numbOfClass == clasLesson) {
						System.out.println("№: " + countLesson + " " + numbAudience + " " + name);
					}
				}
			}
		}
	}
}
