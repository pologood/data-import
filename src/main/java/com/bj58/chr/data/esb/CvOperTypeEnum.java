package com.bj58.chr.data.esb;

/**
 * 简历消息枚举
 * @author sunlingao@58.com
 * @date 2016年4月8日
 */
public enum CvOperTypeEnum {
	
	CREATE(1,"创建简历"),
	REFRESH(2,"刷新简历"),
	UPDATEBASEINFO(3,"更新简历基本信息"),
	UPDATECONTENT(4,"更新简历内容"),
	DELETE(5,"删除简历"),
	UPDATEFILTERWORDS(6,"修改过滤词");
	
	private int id;
	
	private String name;
	
	
	CvOperTypeEnum(int id,String name){
		this.id=id;
		this.name=name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static CvOperTypeEnum getEnumById(int id){
		for(CvOperTypeEnum e:CvOperTypeEnum.values()){
			if(e.getId()==id){
				return e;
			}
		}
		return null;
	}
}
