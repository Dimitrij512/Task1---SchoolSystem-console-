package school.service;

import java.util.ArrayList;

import school.db.Db;
import school.model.Clas;
import school.model.LessonForSchedule;
import school.model.Student;
import school.model.Subject;

public class ServiceEducator extends ServiceStudent {
	//
	// final static int STUDENT_ROLE = 1;
	// final static int EDUCATOR_ROLE = 2;
	// final static int DIRECTOR_ROLE = 3;

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
		if (Db.currentSession.getStudent() != null) {

			for (int i = 0; i < Db.student.size(); i++) {
				Student student = Db.student.get(i);
				if (student.getSurname().equals(Db.currentSession.getStudent().getSurname())) {
					System.out.println(student.getSurname() + " : ");
					for (int j = 0; j < student.getLessons().size(); j++) {
						Subject sub = new Subject();
						System.out.println(j + 1 + " - " + sub.getName() + ": ");
						for (int k = 0; k < sub.getRaiting().size(); k++) {
							int raiting = sub.getRaiting().get(k);
							System.out.print(raiting + ", ");
						}
						System.out.println();
					}
				}

			}
			return;
		}

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

		for (int i = 0; i < Db.student.size(); i++) {

			if (surname.equals(Db.student.get(i).getSurname())) {
				Student student = Db.student.get(i);
				String studentSurname = student.getSurname();
				System.out.println("List subjects of student : " + studentSurname);
				showSubject(student);
				System.out.println();
				if (Db.currentSession.getDirector() != null) {
					boolean start1 = true;
					while (start1) {
						System.out.println("Enter number of subject from list : ");
						int numberOfSubject = Integer.parseInt(enterNumber());
						System.out.println("subject : " + student.getLessons().get(numberOfSubject).getName());
						boolean start2 = true;
						if (Db.currentSession.getStudent() != null) {
							start2 = false;
						}
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
					// Subject educatorSubject =
					// Db.currentSession.getEducator().getSubject();
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
				break;
			}
		}

	}

	public void showSubject(Student student) {
		Subject subject;
		if (Db.currentSession.getDirector() != null) {

			for (int j = 0; j < student.getLessons().size(); j++) {
				subject = student.getLessons().get(j);
				System.out.println("N: " + j + " " + subject.getName() + "| ");
			}
		} else if (Db.currentSession.getEducator() != null || Db.currentSession.getStudent() != null) {
			// String nameSubject;
			if (Db.currentSession.getEducator() != null) {
				String nameSubjectEducator = Db.currentSession.getEducator().getSubject().getName();
				for (int j = 0; j < student.getLessons().size(); j++) {
					String nameSubjectStudent = student.getLessons().get(j).getName();
					if (nameSubjectEducator.equals(nameSubjectStudent)) {
						System.out.println("N: " + j + " " + nameSubjectStudent + "||");
					}
				}
			} else if (Db.currentSession.getStudent() != null) {
				for (int j = 0; j < student.getLessons().size(); j++) {
					String nameSubjectStudent = student.getLessons().get(j).getName();
					System.out.println(" N: " + j + " " + nameSubjectStudent);
				}
			}
		}

	}
}
