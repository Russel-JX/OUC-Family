package thread.reorder;

public class NormalReorder {

	public static void main(String[] args) {
		int a = 1;
		int c = a*a*a;
		System.out.println(c);
		operate(a);
		int b = 0;
		

	}
	public static void operate(int param){
		int temp = 0;
		for(int i=0;i<10000;i++){
			temp = param*i;
		}
	}

}
