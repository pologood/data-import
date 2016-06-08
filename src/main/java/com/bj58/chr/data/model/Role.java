package com.bj58.chr.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

import com.bj58.chr.common.web.IntPersistable;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

@Alias("Role")
public class Role extends IntPersistable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6375769300747261175L;

	private String name;
	private String note;
	private String menus;
	private Date createTime;

	@Size(min = 1, message = "必须填写角色名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public void setMenus(String[] menus) {
		this.menus = Joiner.on(",").join(menus);
	}

	public List<String> getMenusArray() {
		return StringUtils.isEmpty(menus) ? new ArrayList<String>() : Splitter.on(",").splitToList(menus);
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", note=" + note + ", menus=" + menus + ", createTime=" + createTime
				+ ", getId()=" + getId() + "]";
	}

	public boolean isEmpty() {
		return getId() == 0;
	}

}
