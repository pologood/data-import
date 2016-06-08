package com.bj58.chr.data.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

@Alias("Manager")
public class Manager extends com.bj58.chr.auth.model.Manager<Integer> {

	private static final long serialVersionUID = 7641344096262362079L;
	private String name; // 用户名字
	private String username;// 登录用户名
	private String password;// 登录密码
	private boolean root = false;
	private Role role;// 用户角色
	private String note;
	private Date createTime;

	@Size(min = 1, message = "*必填项")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return super.getId();
	}

	public void setId(Integer id) {
		super.setId(id);
	}

	@Override
	public boolean isFresh() {
		return this.getId() == null || this.getId() == 0;
	}

	@Override
	public Integer getPrimaryKey() {
		return this.getId();
	}

	@Override
	public void setPrimaryKey(Integer id) {
		this.setId(id);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
