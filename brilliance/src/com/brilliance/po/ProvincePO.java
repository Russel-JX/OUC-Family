package com.brilliance.po;

public class ProvincePO {
	private Integer id;
	private String pid;
	private String pname;

	public ProvincePO() {

	}

	public ProvincePO(String pid, String pname) {
		this.pid = pid;
		this.pname = pname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

}
