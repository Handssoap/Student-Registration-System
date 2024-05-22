package lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public abstract class Student implements Comparable<Object>{
	protected int id;
	protected String name;
	protected ArrayList<Course> courses;
	static protected int people;
	protected ArrayList<Student> students;
	protected int people2;
	
	public abstract void addCourse(Course course) throws Exception;
	public abstract void dropCourse(Course course);
	Student(){
		this.id = 0;
		this.name = "";
		this.courses = new ArrayList<>();
	}
	public void sort() {
		Collections.sort(this.students);
	}
	public int idset(int id) {
		return this.id = id;
	}
	
	public int CompareTo(Object o) {
		if(o instanceof RegisteredStudent) {
		RegisteredStudent other = (RegisteredStudent) o;
		if(Integer.compare(other.id, this.id) == 0) {
			return this.name.compareTo(other.name);
		}
	
		return (Integer.compare(this.id, other.id));
		} return 1;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(courses, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(courses, other.courses) && id == other.id && Objects.equals(name, other.name);
	}
	
	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public  ArrayList<Course> getCourses() {
		return courses;
	}
	
}
class RegisteredStudent extends Student implements Cloneable{
	RegisteredStudent(){
		this.id = 0;
		this.name = "";
		this.courses = new ArrayList<>();
	}
	public String setName(String name) {
		return this.name = name;
	}
	public RegisteredStudent(int id, String name)
	{	
		this.name = name;
		this.id = id;
		RegisteredStudent.people++;
		
	}
	public static int number() {
		return RegisteredStudent.people;
	}
	
	
	public int compareTo(Object o) {
		if(o instanceof RegisteredStudent) {
			RegisteredStudent other = (RegisteredStudent) o;
			if(Integer.compare(other.id, this.id) == 0) {
				return this.name.compareTo(other.name);
			}
		
			return (Integer.compare(this.id, other.id));
			} return 1;
	}

	
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public void addCourse(Course course) throws Exception {
		if(course.offered == true) {
			if(course.getPrerequisite() == null|| courses.contains(course.getPrerequisite())) {
				courses.add(course);
			} else {throw new PrerequisiteException("Not satisfied");}
		}else{throw new RegistrationException("Not offered");}
		}
	

	@Override
	public void dropCourse(Course course) {
		// TODO Auto-generated method stub
		courses.remove(course);
	}
	public void setid(int i) {
	this.id = i;
	}

}

class Course {
	protected String name;
	protected boolean offered;
	protected Course prerequisite;
	Course(){
		this.name = "";
		this.offered = false;
		this.prerequisite = null;
	}
	public Course(String name, boolean offered){
		this.name = name;
		this.offered = offered;
	}
	public Course(String name, Course course, boolean offered) {
		this.name = name;
		this.prerequisite = course;
		this.offered = offered;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Course getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(Course prerequisite) {
		this.prerequisite = prerequisite;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(name, other.name) && offered == other.offered
				&& Objects.equals(prerequisite, other.prerequisite);
	}
	
	}
class RegistrationException extends Exception{
	public RegistrationException(){
		super();
	}
	public RegistrationException(String message) {
		super(message);
	}
}
class PrerequisiteException extends Exception{
	public PrerequisiteException(){
		super();
	}
	public PrerequisiteException(String message) {
		super(message);
	}
}
