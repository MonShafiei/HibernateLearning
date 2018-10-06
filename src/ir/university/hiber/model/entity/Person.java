package ir.university.hiber.model.entity;

public class Person {

	private Integer id;
	private String name;
	private Integer code;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Person(String name, Integer code) {		
		this.name = name;
		this.code = code;
	}

	public Person() {

	}

	@Override
	public String toString() {
		return "Person [ name=" + name + ", code=" + code + "]";
	}
	
	

}
