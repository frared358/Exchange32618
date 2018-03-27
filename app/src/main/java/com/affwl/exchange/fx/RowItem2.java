package com.affwl.exchange.fx;

public class RowItem2 {

	private String member_name1;
	private int profile_pic_id1;
	private String status1;
	private String contactType1;

	public RowItem2(String member_name1, int profile_pic_id1, String status1, String contactType1) {

		this.member_name1 = member_name1;
		this.profile_pic_id1 = profile_pic_id1;
		this.status1 = status1;
		this.contactType1 = contactType1;
	}

	public String getMember_name1() {
		return member_name1;
	}

	public void setMember_name1(String member_name1) {
		this.member_name1 = member_name1;
	}

	public int getProfile_pic_id1() {
		return profile_pic_id1;
	}

	public void setProfile_pic_id1(int profile_pic_id1) {
		this.profile_pic_id1 = profile_pic_id1;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status) {
		this.status1 = status1;
	}

	public String getContactType1() {
		return contactType1;
	}

	public void setContactType1(String contactType1) {
		this.contactType1 = contactType1;
	}

}