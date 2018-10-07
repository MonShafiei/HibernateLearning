package ir.university.hiber.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import ir.university.hiber.core.HibernateUtil;
import ir.university.hiber.model.entity.Course;
import ir.university.hiber.model.entity.CourseGroup;
import ir.university.hiber.model.entity.Person;
import ir.university.hiber.model.entity.Student;
import ir.university.hiber.resourse.KeyWord;
import ir.university.hiber.util.Print;

public class StudentCli implements CliInterface {
	Scanner scanner = new Scanner(System.in);

	@Override
	public void run() {
		
			Print.print(" 1: Create Student\n 2: Update Student\n 3: Delete Student\n "
					+ "4: Read All Student\n 5: Take a course\n Another key to Back");
			String userChoice = scanner.nextLine();

			switch (userChoice) {
			case "1":
				createStudent();
				break;
			case "2":
				updateStudent();
				break;
			case "3":
				deleteStudent();
				break;
			case "4":
				ReadAllStudent();
				break;
			case "5":				
				take_Course();
				break;
			}

		
	}
	
	private void take_Course() {
		Print.print("Please enter course 's code :");
		int codeCourse = Integer.parseInt(scanner.nextLine());		
		Print.print("Please enter courseGroup 's number :");
		 int numberCourseGroup = Integer.parseInt(scanner.nextLine());
		Print.print("Please enter student 's code :");
		 int codeStudent = Integer.parseInt(scanner.nextLine());
		 Registeration register = new Registeration(codeCourse,numberCourseGroup,codeStudent);
			
		 try {
			register.run();
		} catch (Exception e) {
			System.out.println();
			System.out.println("***   " + e.getMessage()+"   ***");
			System.out.println();
			//e.printStackTrace();
		}
	}

	private void createStudent() {
		try {
			Print.print("Please enter student 's name:");
			String name = scanner.nextLine();
			Print.print("Please enter student 's code:");
			int code = Integer.parseInt(scanner.nextLine());
			Student student = new Student(name, code);
			// create a new Student...
			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateStudent() {
		try {
			Print.print("Please enter student 's ID for change:");
			int id = Integer.parseInt(scanner.nextLine());
			Print.print("Please enter student 's name:");
			String name = scanner.nextLine();
			Print.print("Please enter student 's code:");
			int code = Integer.parseInt(scanner.nextLine());
			// update a new Student...
			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			Student student = session.get(Student.class, id);
			student.setCode(code);
			student.setName(name);
			session.update(student);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ReadAllStudent() {
		try {
			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Person> result = (List<Person>) session.createQuery(KeyWord.FROM + Student.REF).list();
			for (Person person : result) {
				Print.print(person.toString());
			}
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteStudent() {
		try {
			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			Print.print("Please enter student 's name that you want delete it:");
			String name = scanner.nextLine();
			Student student = (Student) session
					.createQuery(KeyWord.FROM + Student.REF + KeyWord.WHERE + Student.STUDENT_NAME + " = :b")
					.setParameter("b", name).uniqueResult();
			session.delete(student);
			Print.print(" *** " + student.getName() + " deleted *** ");
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void takeCourse() {
		try {
			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			Print.print("Please enter student 's name that you want take a course for that:");
			String nameStudent = scanner.nextLine();
			Student student = (Student) session
					.createQuery(KeyWord.FROM + Student.REF + KeyWord.WHERE + Student.STUDENT_NAME + " = :b")
					.setParameter("b", nameStudent).uniqueResult();
			Print.print("Please enter courseGroup 's name that you want take a it for student:");
			String nameCourseGroup = scanner.nextLine();
			CourseGroup courseGroup = (CourseGroup) session
					.createQuery(KeyWord.FROM + CourseGroup.REF + KeyWord.WHERE + CourseGroup.PROP_NAME + " = :v")
					.setParameter("v", nameCourseGroup).uniqueResult();
			boolean hasCourseGroup = false;
			student.registerCourseGroups();
			for (CourseGroup item : student.getCourseGroups()) {
//				if (item.getName().equals(courseGroup.getName())) {
//					hasCourseGroup = true;
//				}
			}
			if (!hasCourseGroup) {
				student.getCourseGroups().add(courseGroup);
				session.update(student);
				session.getTransaction().commit();
			}
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
