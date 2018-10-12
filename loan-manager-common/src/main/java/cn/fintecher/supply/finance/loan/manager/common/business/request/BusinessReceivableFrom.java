package cn.fintecher.supply.finance.loan.manager.common.business.request;


import java.io.Serializable;

import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 应收账款查询条件
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Data
public class BusinessReceivableFrom extends PageInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*核心企业id
	*/
	@ApiModelProperty(value="核心企业id",hidden = true)
	private Long enterpriseId;
	
	
	/**
	 *供应商名称（供应商为注册时为链属名称）
	 */
	@ApiModelProperty(value="企业名称（供应商为注册时为链属名称）")
	private String supplierName;
	
	
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
	 *邀请状态   0未邀请  1已邀请
	 */
	@ApiModelProperty(value="邀请状态   0未邀请  1已邀请")
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
	
	
	

}