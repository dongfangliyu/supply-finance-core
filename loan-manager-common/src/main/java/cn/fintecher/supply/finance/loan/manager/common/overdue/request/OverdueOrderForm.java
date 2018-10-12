package cn.fintecher.supply.finance.loan.manager.common.overdue.request;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 逾期查询条件
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 16:01:09
 */
@Data
public class OverdueOrderForm extends PageInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value="供应商企业名称")
	public String supplierName;
	
	@ApiModelProperty(value="逾期开始天数")
	public Integer overdueStartDay;
	
	@ApiModelProperty(value="逾期结束天数")
	public Integer overdueEndDay;
	
	@ApiModelProperty(value="结清状态 0未结清 1已结清")
	public String state;

	@ApiModelProperty(value="当前操作人用户id",hidden = true)
	public String userId;
	
	@ApiModelProperty(value="数据状态，可用状态",hidden = true)
    public String status = Constants.DATA_STATUS_NOL;
	
	

	

}