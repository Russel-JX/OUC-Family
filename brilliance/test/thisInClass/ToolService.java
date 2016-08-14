package thisInClass;

public class ToolService {
	
	
	
	public String getValue(String code){
		return this.getValue2(code);
	}
	
	public String getValue2(String code){
		return code+"23";
	}
	
	public Employee getParam(Employee emp){
		return this.getParam2(emp);
	}
	
	public Employee getParam2(Employee emp){
		emp.setHeight(emp.getHeight()+100);
		return emp;
	}

}
