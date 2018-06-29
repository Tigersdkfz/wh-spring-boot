package com.sfa.model.sys;

/**
 * @author sfa
 *
 */
public class UserEntity {
	/**
	 * id
	 */
	private String id;
	/**
	 * 登录账号
	 */
	private String usercode;
	/**
	 * 用户名称
	 */
	private String username;
	/**
	 * 密码
	 */
	private String userpassword;
	/**
	 * 密码
	 */
	private String roleid;
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 组织机构
	 */
	private String orgcode;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userpassword;
	}

	public void setUserPassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserCode() {
		return usercode;
	}

	public void setUserCode(String usercode) {
		this.usercode = usercode;
	}
	
	public String getOrgCode() {
		return orgcode;
	}

	public void setOrgCode(String orgcode) {
		this.orgcode = orgcode;
	}
	
	public String getRoleId() {
		return roleid;
	}

	public void setRoleId(String roleid) {
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", usercode=" + usercode + ", username=" + username + ", userpassword=" + userpassword
				+ ", email=" + email + ", orgcode=" + orgcode + ", roleid=" + roleid + "]";
	}

}
