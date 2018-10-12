package cn.fintecher.supply.finance.loan.manager.common.pledge.request;


import java.io.Serializable;
import java.text.ParseException;

import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
/**
 * 仓单质押审核查询条件
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 16:01:09
 */
public class PledgeStockTrialFrom extends PageInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/**
	*客户名称
	*/
	@ApiModelProperty(value="客户名称")
	private String customerName;
	
	
	/**
	 *供应商名称
	 */
	@ApiModelProperty(value="供应商名称")
	private String supplierName;
	
	
	/**
	 *仓单编号
	 */
	@ApiModelProperty(value="仓单编号")
	private String applyNumber;
	
	/**
	 *审核开始时间
	 */
	@ApiModelProperty(value="审核开始时间")
	private String startTime;
	
	/**
	 *审核结束时间
	 */
	@ApiModelProperty(value="审核结束时间")
	private String endTime;
	
	
	/**
	 *审核状态 0待领取 1待审核 2拒绝 3取消 4通过 
	 */
	@ApiModelProperty(value="审核状态 0待领取 1待审核 2拒绝 3取消 4通过 ")
	private String state;
	
	
	@ApiModelProperty(value="当前操作人用户id",hidden = true)
	public String userId;
	
	@ApiModelProperty(value="数据状态，可用状态",hidden = true)
    public String status = Constants.DATA_STATUS_NOL;
	
	@ApiModelProperty(value="数据状态，可用状态",hidden = true)
	public String taskStatus;
	
	@ApiModelProperty(value="审批节点",hidden = true)
	public String node;

	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if (null != startTime && !("".equals(startTime))){
			try {
				startTime = DateUtil.TransformatStartTime(startTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.startTime = startTime;
	}

	public String getEndTime() {
		
		return endTime;
	}

	public void setEndTime(String endTime) {
		if (null != endTime && !("".equals(endTime))){
			try {
				endTime = DateUtil.TransformatEndTime(endTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.endTime = endTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}
	
}