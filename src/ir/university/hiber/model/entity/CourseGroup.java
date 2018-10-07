package ir.university.hiber.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseGroup {
	public static final String REF = "CourseGroup";
	
	private int capacity;
	private Integer number;	
	private Integer id;
	private Date presentTime;
	private Course course;
	private Set<Student> students;

	public static final String  PROP_COURSE = "course";
	public static final String  PROP_NUMBER = "number";
	public static final String  PROP_NAME = "name";

		

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}


	public int getCapacity() {
		return capacity;
	}

	public Integer getId() {
		return id;
	}

	

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPresentTime() {
		return presentTime;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setPresentTime(Date presentTime) {
		this.presentTime = presentTime;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	

	public CourseGroup() {
	}

	public CourseGroup( int number, int capacity,Course course) {
		
		this.capacity = capacity;
		this.number = number;
		this.course = course;
	}
	
	public CourseGroup( int capacity,Course course) {
		
		this.capacity = capacity;		
		this.course = course;
	}
	
	public void checkStudentList() {
		if(students==null) {
			students = new HashSet<>();
		}
	}

	@Override
	public String toString() {
		return "CourseGroup [ capacity=" + capacity + ", number=" + number + ", presentTime="
				+ presentTime + "]";
	}
	
}
