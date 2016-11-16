package school.service;

import java.util.Scanner;

import school.db.Db;
import school.model.Session;

public class ServiceStudent {
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
		for (int i = 0; i < Db.users.size(); i++) {
			String loginUser = Db.users.get(i).getLogin();
			String passwordUser = Db.users.get(i).getPassword();
			int role = Db.users.get(i).getRole();

			if (loginUser.equals(login) && passwordUser.equals(password)) {

				session.setUser(Db.users.get(i));
				Db.currentSession = session;
				result = role;
				System.out.println(loginUser + " " + password);
				System.out.println(role);
				break;
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

}
