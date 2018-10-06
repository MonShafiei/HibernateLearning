package ir.university.hiber.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student extends Person {
	public static final String REF = "Student";
	private Set<CourseGroup> courseGroups;
	private Date dateOfPersent;
	public static final String  STUDENT_CODE = "code";
	public static final String  STUDENT_NAME = "name";
	public static final String  STUDENT_COURSES = "courseGroups";

	public Date getDateOfPersent() {
		return dateOfPersent;
	}

	public void setDateOfPersent(Date dateOfPersent) {
		this.dateOfPersent = dateOfPersent;
	}

	public Set<CourseGroup> getCourseGroupCollection() {
		return courseGroups;
	}

	public void setCourseGroupCollection(Set<CourseGroup> courseGroups) {
		this.courseGroups = courseGroups;
	}

	public Student() {

	}

	public Student(String name, Integer code) {
		super(name, code);
	}
	
	public void registerCourseGroups() {
		if(courseGroups==null) {
			courseGroups = new HashSet<>();
		}
	}

}
