package corejava.Final;

public class IntTest {
	public String name;
	protected int age = 30;
	private boolean gender;

	public static void main(String[] args) {
		int i = 8;
		int j = 10;
		i = i+j;
		String a = "abc";
		String d = new String("abc");
		
		String c = "abc";
		String b = "def";
		a = a+b;
	}
	
	public IntTest(String name,int age,boolean gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

}
