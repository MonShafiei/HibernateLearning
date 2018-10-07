package ir.university.hiber.view;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;


import ir.university.hiber.core.HibernateUtil;
import ir.university.hiber.model.entity.Course;
import ir.university.hiber.model.entity.CourseGroup;
import ir.university.hiber.model.entity.Student;
import ir.university.hiber.resourse.KeyWord;
import ir.university.hiber.util.Print;
import net.bytebuddy.asm.Advice.Return;

public class Registeration implements CliInterface {
	Scanner scanner = new Scanner(System.in);
	Integer codeCourse;
	Integer numberCourseGroup;
	Integer codeStudent;

	public Registeration(Integer codeCourse, Integer numberCourseGroup, Integer codeStudent) {
		super();
		this.codeCourse = codeCourse;
		this.numberCourseGroup = numberCourseGroup;
		this.codeStudent = codeStudent;
	}

	@Override
	public void run() throws Exception {
		CourseGroup courseGroup = null;

		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();

		Course course = findCourse(codeCourse);
		if (course == null) {
			throw new Exception("Threre is not course with this code");
		}

		courseGroup = findCourseGroup(numberCourseGroup, course);
		if (courseGroup == null) {
			throw new Exception(
					"Threre are not avaliable CourseGroup with this Course\n or Threre are not match CourseGroup.code with Course.code");
		}

		Student student = getStudentByCode(codeStudent);
		if (student == null) {
			throw new Exception("Threre is not Student with this code");
		}
		student.registerCourseGroups();

		if (courseGroup.getCapacity() <= courseGroup.getStudents().size()) {
			throw new Exception("Capacity is overload");
		}

		for (CourseGroup itemOfStudent : student.getCourseGroups()) {
			if (itemOfStudent.getCourse().getCode() == course.getCode()) {
				throw new Exception("This course is taken");
			}
		}
		// checkDuplicateCourse();

		student.getCourseGroups().add(courseGroup);
		session.update(student);
		session.getTransaction().commit();

		session.close();
	}

	private Student getStudentByCode(Integer studentCode) {
		Student student = (Student) HibernateUtil.getCurrentSession()
				.createQuery(KeyWord.FROM + Student.REF + KeyWord.WHERE + Student.STUDENT_CODE + " = :b")
				.setParameter("b", studentCode).uniqueResult();
		return student;
	}

	private Course findCourse(Integer codeCourse) {
		return (Course) HibernateUtil.getCurrentSession()
				.createQuery(KeyWord.FROM + Course.REF + KeyWord.WHERE + Course.PROP_CODE + " = :v")
				.setParameter("v", codeCourse).uniqueResult();
	}

	private CourseGroup findCourseGroup(Integer numberCourseGroup, Course course) {
		return (CourseGroup) HibernateUtil.getCurrentSession()
				.createQuery(KeyWord.FROM + CourseGroup.REF + KeyWord.WHERE + CourseGroup.PROP_NUMBER + " = :num"
						+ " and " + CourseGroup.PROP_COURSE + " = :course")
				.setParameter("num", numberCourseGroup).setParameter("course", course).uniqueResult();
	}

	// private boolean checkDuplicateCourse(Student studentInput, CourseGroup
	// courseGroupInput) {
	// CourseGroup courseGroup = (CourseGroup) HibernateUtil.getCurrentSession()
	// .createQuery(KeyWord.FROM + Student.REF + KeyWord.WHERE +
	// Student.STUDENT_COURSES + " not in:num")
	// .setParameter("num", CourseGroup.PROP_COURSE).uniqueResult();
	// if (courseGroup == null) {
	// return true;
	// }
	// return false;
	// }

}
