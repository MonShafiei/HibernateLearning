package ir.university.hiber.model.entity;

import java.util.HashSet;
import java.util.Set;

public class Course {
	public static final String REF = "Course";
	private Integer id;
	private int code;
	private String name;
	private Set<CourseGroup> groups;
	public static final String  PROP_CODE = " code ";
	public static final String  COURSE_NAME = " name ";

	public Set<CourseGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<CourseGroup> groups) {
		this.groups = groups;
	}

	public Integer getId() {
		return id;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Course() {

	}
	
	public void checkGroups() {
		if(groups==null) {
			groups = new HashSet<>();
		}
	}

}
