package com.luoyu.qiudai.register.model;



/**
 * 校区db实体类
 * @author chuanrong
 *
 */
public class Campus {
	/**
	 * 校园id
	 */
	private int campusId;
	
	/**
	 * 校园名称
	 */
	private String campusName;
	
	/**
	 * 所属高校Id
	 */
	private int universityId;

	public int getCampusId() {
		return campusId;
	}

	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}
	
	

}
