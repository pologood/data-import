package com.bj58.chr.data.model.qdcv;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.bj58.chr.data.model.CoId;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:49:18
 * @see
 * @since
 */
public class CVModel extends CoId {

	/**
     * date:2016年4月15日
     * field:serialVersionUID
     */
    private static final long serialVersionUID = -605525843661255659L;
    @JsonProperty("resume_id")
    @NotNull
	private String resumeId;// "简历唯一标识，合库后的简历唯一标识"
	@JsonProperty("person_id")
	private int personId;// "用户ID，合库后的人员id，可为空，作为分片标识"
	@JsonProperty("create_time")
	private long createTime;// "时间戳"
	@JsonProperty("version_date")
	private long versionDate;// "版本时间"
	@JsonProperty("last_date")
	private long lastDate;// "最后时间"
	@JsonProperty("meta_info")
	private MetaInfo metaInfo;// 附加数据组
	@JsonProperty("resume_info")
	private ResumeInfo resumeInfo;
	@JsonProperty("deliver_info")
	private List<DeliverInfo> deliverInfo;
	
    public List<DeliverInfo> getDeliverInfo() {
        return deliverInfo;
    }
    public void setDeliverInfo(List<DeliverInfo> deliverInfo) {
        this.deliverInfo = deliverInfo;
    }
    public String getResumeId() {
        return resumeId;
    }
    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }
    public int getPersonId() {
        return personId;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public long getVersionDate() {
        return versionDate;
    }
    public void setVersionDate(long versionDate) {
        this.versionDate = versionDate;
    }
    public long getLastDate() {
        return lastDate;
    }
    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }
    public MetaInfo getMetaInfo() {
        return metaInfo;
    }
    public void setMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }
    public ResumeInfo getResumeInfo() {
        return resumeInfo;
    }
    public void setResumeInfo(ResumeInfo resumeInfo) {
        this.resumeInfo = resumeInfo;
    }
	
}