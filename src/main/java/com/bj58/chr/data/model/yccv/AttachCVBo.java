package com.bj58.chr.data.model.yccv;

/**
 * 附件实体
 * @author sunlingao@58.com
 * @date 2016年3月10日
 */
public class AttachCVBo {

	private Long downloads;
	
	private String attachmentPath;
	
	private Long attachTime;
	
	private String isFileVerified;
	
	private Long fileVerifiedTime;
	
    private String isDel;
	
	private Long dTime;
	
    private String extend;
    
    public String getExtend() {
        return extend;
    }
    public void setExtend(String extend) {
        this.extend = extend;
    }
	
	public Long getDownloads() {
		return downloads;
	}
	public void setDownloads(Long downloads) {
		this.downloads = downloads;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public Long getAttachTime() {
		return attachTime;
	}
	public void setAttachTime(Long attachTime) {
		this.attachTime = attachTime;
	}
	public String getIsFileVerified() {
		return isFileVerified;
	}
	public void setIsFileVerified(String isFileVerified) {
		this.isFileVerified = isFileVerified;
	}
	public Long getFileVerifiedTime() {
		return fileVerifiedTime;
	}
	public void setFileVerifiedTime(Long fileVerifiedTime) {
		this.fileVerifiedTime = fileVerifiedTime;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public Long getdTime() {
		return dTime;
	}
	public void setdTime(Long dTime) {
		this.dTime = dTime;
	}
}
