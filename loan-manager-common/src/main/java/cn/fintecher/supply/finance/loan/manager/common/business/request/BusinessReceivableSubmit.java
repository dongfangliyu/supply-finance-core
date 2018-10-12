package cn.fintecher.supply.finance.loan.manager.common.business.request;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;
/**
 * 应收账款查询条件
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Data
public class BusinessReceivableSubmit implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long supplierId;
	
	private String supplierName;
	
	private String contractNo;
	
	private String certificateAmount;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date accountStartTime;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date accountEndTime;
	
	private String certificateCategory;
	
	private String orderCode;
	
	private String type;

	@ApiModelProperty(hidden=true)
	private String userName;
	
	
	

}