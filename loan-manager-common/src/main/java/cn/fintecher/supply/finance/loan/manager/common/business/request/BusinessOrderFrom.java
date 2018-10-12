package cn.fintecher.supply.finance.loan.manager.common.business.request;


import java.io.Serializable;
import java.text.ParseException;

import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 供应商应收账款查询条件
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
public class BusinessOrderFrom extends PageInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*供应商id
	*/
	@ApiModelProperty(value="供应商id",hidden = true)
	private Long supplierId;
	
	
	/**
	 *核心企业名称
	 */
	@ApiModelProperty(value="核心企业名称")
	private String enterpriseName;
	
	
	/**
	 *账款起日
	 */
	@ApiModelProperty(value="账款起日")
	private String accountStartTime;
	/**
	 *账款止日
	 */
	@ApiModelProperty(value="账款止日")
	private String accountEndTime;
	
	/**
	 *账款凭证类型
	 */
	@ApiModelProperty(value="账款凭证类型")
	private String certificateCategory;
	
	
	/**
	 *确认状态   0未确认  1已确认
	 */
	@ApiModelProperty(value="确认状态   0未确认  1已确认")
	private String state;
	
	/**
	 *合同编号
	 */
	@ApiModelProperty(value="合同编号")
	private String contractNo;
	
	@ApiModelProperty(value="当前操作人用户账号",hidden = true)
	public String userName;
	
	@ApiModelProperty(value="数据状态，可用状态",hidden = true)
    public String status = Constants.DATA_STATUS_NOL;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAccountStartTime() {
		return accountStartTime;
	}

	public void setAccountStartTime(String accountStartTime) {
		if (null != accountStartTime && !("".equals(accountStartTime))){
			try {
				accountStartTime = DateUtil.TransformatStartTime(accountStartTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.accountStartTime = accountStartTime;
	}

	public String getAccountEndTime() {
		
		return accountEndTime;
	}

	public void setAccountEndTime(String accountEndTime) {
		if (null != accountEndTime && !("".equals(accountEndTime))){
			try {
				accountEndTime = DateUtil.TransformatEndTime(accountEndTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.accountEndTime = accountEndTime;
	}

	public String getCertificateCategory() {
		return certificateCategory;
	}

	public void setCertificateCategory(String certificateCategory) {
		this.certificateCategory = certificateCategory;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}