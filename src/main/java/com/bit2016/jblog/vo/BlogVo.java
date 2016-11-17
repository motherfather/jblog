package com.bit2016.jblog.vo;

public class BlogVo {
	private Long usersNo;
	private String title;
	private String logo;
	
	public Long getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(Long usersNo) {
		this.usersNo = usersNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Override
	public String toString() {
		return "BlogVo [usersNo=" + usersNo + ", title=" + title + ", logo=" + logo + "]";
	}
}
