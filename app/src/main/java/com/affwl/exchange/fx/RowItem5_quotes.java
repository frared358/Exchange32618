package com.affwl.exchange.fx;

public class RowItem5_quotes {

	private String member_name5;
	private int profile_pic_id5;
	private String status5;
	int intCheck;


	public RowItem5_quotes(String member_name5, int profile_pic_id5, String status5) {

		this.member_name5 = member_name5;
		this.profile_pic_id5 = profile_pic_id5;
		this.status5 = status5;

	}

	public RowItem5_quotes(String member_name5, int profile_pic_id5, String status5,int intCheck) {

		this.member_name5 = member_name5;
		this.profile_pic_id5 = profile_pic_id5;
		this.status5 = status5;
		this.intCheck=intCheck;

	}


	public int getIntCheck() {
		return intCheck;
	}

	public void setIntCheck(int intCheck) {
		this.intCheck = intCheck;
	}

	public String getMember_name5() {
		return member_name5;
	}

	public void setMember_name5(String member_name1) {
		this.member_name5 = member_name5;
	}

	public int getProfile_pic_id5() {
		return profile_pic_id5;
	}

	public void setProfile_pic_id5(int profile_pic_id5) {
		this.profile_pic_id5 = profile_pic_id5;
	}

	public String getStatus5() {
		return status5;
	}

	public void setStatus5(String status5) {
		this.status5 = status5;
	}


}