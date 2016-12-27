package corejava.entity;

/**
* @ClassName: Hosipital
* @Package corejava.entity
* @Description: 医院实体类
* @author Russell Xun Jiang
* @date 2016年12月27日 上午11:23:52
*/
public class Hospital {
	
	private String name;//医院名称
	private String address;//医院地址
	private int star;//星级
	
	
	
	public Hospital() {
		super();
	}
	public Hospital(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public Hospital(String name, String address, int star) {
		super();
		this.name = name;
		this.address = address;
		this.star = star;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	
	

}
