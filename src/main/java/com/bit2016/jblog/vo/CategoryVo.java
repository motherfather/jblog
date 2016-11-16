package com.bit2016.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String description;
	private String regDate;
	private Long usersNO;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getUsersNO() {
		return usersNO;
	}
	public void setUsersNO(Long usersNO) {
		this.usersNO = usersNO;
	}
	@Override
	public String toString() {
		return "CategoryDao [no=" + no + ", name=" + name + ", description=" + description + ", regDate=" + regDate
				+ ", usersNO=" + usersNO + "]";
	}
}
