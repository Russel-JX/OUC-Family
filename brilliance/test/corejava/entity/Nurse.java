package corejava.entity;

public class Nurse extends Hospital{
	
	private String nurName;
	private int nurStar;
	private int gender;
	private int age;
	
	public Nurse(String nurName, int nurStar, int gender, int age) {
		super();
		this.nurName = nurName;
		this.nurStar = nurStar;
		this.gender = gender;
		this.age = age;
	}
	public String getNurName() {
		return nurName;
	}
	public void setNurName(String nurName) {
		this.nurName = nurName;
	}
	public int getNurStar() {
		return nurStar;
	}
	public void setNurStar(int nurStar) {
		this.nurStar = nurStar;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
