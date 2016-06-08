package com.bj58.chr.data.model.yccv;

import java.text.NumberFormat;

/**
 * 任务处理结果实体
 * 
 * @author sunlingao@58.com
 * @date 2016年3月15日
 */
public class TaskBo {

	/**
	 * 任务是否开启
	 */
	private boolean flag;

	/**
	 * 当前任务完成
	 */
	private int count;

	/**
	 * 任务总共数据
	 */
	private int total;
	
	private TaskBo(){}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPercent() {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		double percent = (float) getCount() / (float) getTotal();
		return numberFormat.format(percent * 100);
	}

	public void setValue(boolean flag, int count, int total) {
		this.flag = flag;
		this.count = count;
		this.total = total;
	}

	public final static TaskBo getInstance() {
		return Instance.instance;
	}

	private static class Instance {
		private final static TaskBo instance = new TaskBo();
	}

}
