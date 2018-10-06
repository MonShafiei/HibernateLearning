package ir.university.hiber.view;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.Session;

import ir.university.hiber.core.HibernateUtil;
import ir.university.hiber.model.entity.Course;
import ir.university.hiber.model.entity.CourseGroup;
import ir.university.hiber.model.entity.Student;
import ir.university.hiber.util.Print;

public class CourseGroupCli implements CliInterface{
	Scanner scanner = new Scanner(System.in);

	@Override
	public void run() {
		try {
			Print.print(" 1: Create CourseGroup\n 2: Update CourseGroup\n 3: Delete CourseGroup\n "
					+ "4: Read All CourseGroup\n  Another key to Back");
			String userChoice = scanner.nextLine();

			switch (userChoice) {
			case "1":
				createCourseGroup();
				break;
			case "2":

				break;
			case "3":

				break;
			case "4":

				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createCourseGroup() {
		try {
			Print.print("Please enter CourseGroup 's number:");
			Integer number = Integer.parseInt(scanner.nextLine());
			Print.print("Please enter CourseGroup 's capacity:");
			int capacity = Integer.parseInt(scanner.nextLine());
			Print.print("Please enter Course 's ID to attach to it:");
			int courseID = Integer.parseInt(scanner.nextLine());			
			// create a new Student...
			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			Course course = session.get(Course.class, courseID);
//			CourseGroup courseGroup = new CourseGroup(name, 0,capacity,course);
			CourseGroup courseGroup = new CourseGroup(number,capacity,course);
			session.save(courseGroup);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
