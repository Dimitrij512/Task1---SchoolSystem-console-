package school.service;

import java.util.List;
import java.util.Scanner;

import school.db.Db;
import school.model.LessonForSchedule;
import school.model.Session;
import school.model.Student;

public class ServiceStudent {

	final static int STUDENT_ROLE = 1;
	final static int EDUCATOR_ROLE = 2;
	final static int DIRECTOR_ROLE = 3;

	public String enterNumber() {
		Scanner sc = new Scanner(System.in);
		String number = sc.nextLine();
		return number;
	}

	public void hellowPages() {
		System.out.println("Welcome to our school !!! ");
	}

	public int existUser(String login, String password) {
		Session session = new Session();
		int result = 0;
		// for student
		for (int i = 0; i < Db.student.size(); i++) {
			String loginUser = Db.student.get(i).getLogin();
			String passwordUser = Db.student.get(i).getPassword();
			int role = Db.student.get(i).getRole();

			if (loginUser.equals(login) && passwordUser.equals(password)) {
				session.setStudent(Db.student.get(i));
				Db.currentSession = session;
				result = role;
				return result;
			}
		}
		// for educator
		System.out.println("Educator : ");
		for (int i = 0; i < Db.educator.size(); i++) {
			String loginUser = Db.educator.get(i).getLogin();
			String passwordUser = Db.educator.get(i).getPassword();
			int role = Db.educator.get(i).getRole();

			if (loginUser.equals(login) && passwordUser.equals(password)) {
				session.setEducator(Db.educator.get(i));
				Db.currentSession = session;
				result = role;
				return result;
			}
		}

		System.out.println("Director :");
		// for director
		for (int i = 0; i < Db.director.size(); i++) {
			String loginUser = Db.director.get(i).getLogin();
			String passwordUser = Db.director.get(i).getPassword();
			int role = Db.director.get(i).getRole();

			if (loginUser.equals(login) && passwordUser.equals(password)) {
				session.setDirector(Db.director.get(i));
				Db.currentSession = session;
				result = role;
				return result;
			}
		}
		return result;
	}

	public int autentification() {
		int result = 0;
		boolean exit = true;
		while (exit) {
			System.out.println("ENTER YOUR LOGIN : ");
			String login = enterNumber();
			System.out.println("ENTER YOUR PASSWORD : ");
			String password = enterNumber();
			int status = existUser(login, password);
			if (status > 0) {
				result = status;
				break;
			} else {
				System.out.println("ERROR AUTENTIFICATION !!!!");
				System.out.println("IF YOU WANT TRY AGAIN : PRESS 1");
				System.out.println("IF YOU WANT EXIT : PRESS OTHER KEY");
				int entered = Integer.parseInt(enterNumber());
				if (entered != 1) {
					System.exit(0);
				}
			}
		}
		return result;
	}

	public void showAllSchedule() {
		int countDay = 0;
		int day = 1;
		int week = 5;

		if (Db.currentSession.getDirector() != null || Db.currentSession.getEducator() != null) {
			for (int i = 0; i < Db.clases.size(); i++) {
				int numbOfClass = Db.clases.get(i).getNumb();
				System.out.println("-|--|---|--|--|---|-----|---|----|---");
				System.out.println("class : " + numbOfClass);
				printLessonForShudule(numbOfClass, countDay, day, week);
			}
		} else {
			int numbOfClass = Db.currentSession.getStudent().getClas();
			System.out.println("-|--|---|--|--|---|-----|---|----|---");
			System.out.println("class : " + numbOfClass);
			printLessonForShudule(numbOfClass, countDay, day, week);

		}
	}

	public void printLessonForShudule(int numbOfClass, int countDay, int day, int week) {

		for (int j = 0; j < Db.schedule.size(); j++) {
			countDay = j;
			for (int j2 = 0; j2 < Db.schedule.get(j).size(); j2++) {

				LessonForSchedule lessonForSchedule = Db.schedule.get(j).get(j2);
				int clasLesson = lessonForSchedule.getClas();
				int countLesson = lessonForSchedule.getNumbLesson();
				int numbAudience = lessonForSchedule.getAudience();
				String name = lessonForSchedule.getNameLesson();
				if (numbOfClass == clasLesson) {
					if (countDay == j) {

						System.out.println("--------------");
						System.out.println(day + " day : ");
						System.out.println("--------------");
						day++;
						if (day > week) {
							day = 1;
						}
					}
					System.out.println("N: " + countLesson + " " + numbAudience + " " + name);
					countDay++;
				}
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

}
