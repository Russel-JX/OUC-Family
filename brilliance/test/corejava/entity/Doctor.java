package corejava.entity;

public class Doctor extends Hospital{
	
	private String docName;
	private int docStar;
	private int gender;
	private int age;
	
	public Doctor(String docName, int docStar, int gender, int age) {
		super();
		this.docName = docName;
		this.docStar = docStar;
		this.gender = gender;
		this.age = age;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public int getDocStar() {
		return docStar;
	}
	public void setDocStar(int docStar) {
		this.docStar = docStar;
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
