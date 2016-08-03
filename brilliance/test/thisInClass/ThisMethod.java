package thisInClass;

public class ThisMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ToolService toolService = new ToolService();
		System.out.println(toolService.getValue("ABC"));
		
		Employee emp = new Employee();
		emp.setName("Russell");
		emp.setAge(23);
		emp.setHeight(170.00);
		System.out.println("原emp。"+emp);
		toolService.getParam(emp);
		System.out.println("后emp。"+emp);
	}
}
