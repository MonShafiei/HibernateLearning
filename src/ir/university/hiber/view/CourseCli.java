package ir.university.hiber.view;

import java.util.Scanner;

import org.hibernate.Session;

import ir.university.hiber.core.HibernateUtil;
import ir.university.hiber.model.entity.Course;
import ir.university.hiber.model.entity.Student;
import ir.university.hiber.util.Print;

public class CourseCli implements CliInterface{
	Scanner scanner = new Scanner(System.in);

	@Override
	public void run() {
		try {
			Print.print(" 1: Create Course\n 2: Update Course\n 3: Delete Course\n "
					+ "4: Read All Course\n Another key to Back");
			String userChoice = scanner.nextLine();

			switch (userChoice) {
			case "1":

				break;
			case "2":
				updateCourse();
				break;
			case "3":

				break;
			case "4":

				break;
			case "5":

				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void updateCourse() {
		try {
			Print.print("Please enter course 's ID for change:");
			int id = Integer.parseInt(scanner.nextLine());
			Print.print("Please enter course 's name:");
			String name = scanner.nextLine();
			Print.print("Please enter course 's code:");
			int code = Integer.parseInt(scanner.nextLine());
			// update a new Student...
			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			Course course = session.get(Course.class, id);
			course.setCode(code);
			course.setName(name);
			session.update(course);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
