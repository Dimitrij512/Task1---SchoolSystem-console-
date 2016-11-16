package school.controller;

import school.db.Db;
import school.dbInitial.DbInitial;
import school.service.ServiceDirector;
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

		ServiceDirector sd = new ServiceDirector();

		// test code
		// List<Subject> test = new ArrayList<Subject>();
		// test.add(Db.subjects.get(5));
		// test.add(Db.subjects.get(1));
		// test.add(Db.subjects.get(2));
		// test.add(Db.subjects.get(3));
		// test.add(Db.subjects.get(4));
		// test.add(Db.subjects.get(3));
		// test.add(Db.subjects.get(2));
		// test.add(Db.subjects.get(1));
		// test.add(Db.subjects.get(3));
		// test.add(Db.subjects.get(2));
		// test.add(Db.subjects.get(3));
		// test.add(Db.subjects.get(5));
		// test.add(Db.subjects.get(2));
		// test.add(Db.subjects.get(2));
		// test.add(Db.subjects.get(4));
		// test.add(Db.subjects.get(1));
		//
		// System.out.println("test code : ");
		// sd.createSchedule(test, 1);
		//
		// for (int i = 0; i < Db.schedule.size(); i++) {
		// ArrayList<LessonForSchedule> list = Db.schedule.get(i);
		// System.out.println("Size : " + Db.schedule.get(i).size());
		// for (int j = 0; j < list.size(); j++) {
		// // LessonForSchedule lfs = list.get(j);
		// System.out.print("N: " + list.get(j).getNumbLesson() + " " +
		// list.get(j).getAudience() + " " + list.get(j).getNameLesson() + " ");
		// System.out.println();
		// }
		//
		// }

		//

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
				String nameDirector = Db.currentSession.getUser().getName();
				System.out.println("DEAR " + nameDirector);
				boolean start = true;
				int parametr;

				while (start) {
					System.out.println("Shoose service : ");
					System.out.println("create shedule press : 2");
					System.out.println("show shedule press : 1");
					parametr = Integer.parseInt(sd.enterNumber());
					if (parametr == 2) {
						sd.shooseSubjectForCreateShedule();
					} else if (parametr == 1) {
						sd.showAllSсhedule();
					}
					System.out.println("If you want yet press : 1");
					parametr = Integer.parseInt(sd.enterNumber());
					if (parametr != 1) {
						start = false;
					}
				}
			}
		}
	}
}
