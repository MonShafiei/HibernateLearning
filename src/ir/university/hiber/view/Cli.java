package ir.university.hiber.view;

import java.util.Scanner;
import ir.university.hiber.core.HibernateUtil;
import ir.university.hiber.util.Print;

public class Cli implements CliInterface{
	Scanner scanner = new Scanner(System.in);

	@Override
	public void run() {
		try {
			HibernateUtil.setUp();

			Print.print("Please select your request :");
			String userChoice = "";

			while (!userChoice.equals("0")) {
				Print.print(" 1: Student\n 2: Teacher\n 3: Course\n " + "4: CourseGroup\n 0: Exit\n");

				userChoice = scanner.nextLine();

				switch (userChoice) {
				case "1":
					CliInterface studentCli = new StudentCli();
					studentCli.run();
					break;
				case "2":

					break;
				case "3":
					CliInterface courseCli = new CourseCli();
					courseCli.run();
					break;
				case "4":
					CliInterface courseGroupCli = new CourseGroupCli();
					courseGroupCli.run();
					break;
				case "5":

					break;
				case "6":

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			HibernateUtil.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	
	

}
