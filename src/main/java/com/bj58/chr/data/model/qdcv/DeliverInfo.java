package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:53:07
 * @see
 * @since
 */
public class DeliverInfo {
	@JsonProperty("disp_time")
	private long dispTime;// : "简历投递时间，时间戳",
	@JsonProperty("refresh_time")
	private long refreshTime;// : "简历最后刷新时间，时间戳",
	@JsonProperty("update_time")
	private long updateTime;// : "简历最后更新时间，时间戳",
	@JsonProperty("appoint_time")
	private long appointTime;// : "指定时间，永远按照最后一份工作的结束时间的规则来获取;//
	@JsonProperty("recruit_path")
	private String recruitPath;//// "招收方式：deliver:投递，download：下载；monitor：监测；collect：收藏；",
	@JsonProperty("position_info")
	private PositionInfo positionInfo;
	@JsonProperty("job_intension")
	private JobIntension jobIntension;
	/**信息来源 渠道id 1:智联招聘 2:前程无忧 3:猎聘网 4:中华英才 5:58同城 6:赶集网 7:拉钩网 8:周伯通 9:若邻网 */
	private String sid;
	/**渠道简历id*/
    private String srid;
	
    public long getDispTime() {
        return dispTime;
    }
    public void setDispTime(long dispTime) {
        this.dispTime = dispTime;
    }
    public long getRefreshTime() {
        return refreshTime;
    }
    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }
    public long getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
    public long getAppointTime() {
        return appointTime;
    }
    public void setAppointTime(long appointTime) {
        this.appointTime = appointTime;
    }
    public String getRecruitPath() {
        return recruitPath;
    }
    public void setRecruitPath(String recruitPath) {
        this.recruitPath = recruitPath;
    }
    public PositionInfo getPositionInfo() {
        return positionInfo;
    }
    public void setPositionInfo(PositionInfo positionInfo) {
        this.positionInfo = positionInfo;
    }
    public JobIntension getJobIntension() {
        return jobIntension;
    }
    public void setJobIntension(JobIntension jobIntension) {
        this.jobIntension = jobIntension;
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getSrid() {
        return srid;
    }
    public void setSrid(String srid) {
        this.srid = srid;
    }

}
