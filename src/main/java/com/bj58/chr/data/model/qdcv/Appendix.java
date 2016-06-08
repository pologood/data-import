package com.bj58.chr.data.model.qdcv;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:28:40
 * @see
 * @since
 */
public class Appendix {

	private String file; // "附件路径",
	private int type; // "附件类型",
	private long size; // "附件大小",
	private String cmmt; // "附件说明"

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getCmmt() {
		return cmmt;
	}

	public void setCmmt(String cmmt) {
		this.cmmt = cmmt;
	}

}
