package cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: ConfirmingStockList
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/28 0028下午 16:16
 */
@Data
public class ConfirmingStockList {


	/**
	 * 记录id
	 */
	@ApiModelProperty(value = "记录id")
	private String pid;
	
   /**
    * 业务编号(YW+当日日期+243+流水号)
    */
   @ApiModelProperty(value = "业务编号(YW+当日日期+243+流水号)")
   private String bussineNo;
   
   /**
    * 产品编号
    */
   @ApiModelProperty(value = "产品编号")
   private String productNo;

   /**
    * 企业  来源 (企业信息表)
    */
   @ApiModelProperty(value = "企业名称  来源 (企业信息表)")
   private String companyName;


   /**
    * 审批金额
    */
   @ApiModelProperty(value = "审批金额")
   private BigDecimal contractApplyPrice;

   /**
    * 审批期限
    */
   @ApiModelProperty(value = "审批期限")
   private Long contractApprovalTerm;

   /**
    * 商务合同编号
    */
   @ApiModelProperty(value = "商务合同编号")
   private String contractNo;


   /**
    * 经销商融资签约时间
    */
   @ApiModelProperty(value = "经销商融资签约时间")
   private Date contractDealerTime;

   @ApiModelProperty(value = "签约状态")
   private String contractState;

   @ApiModelProperty(value = "经销商名称")
   private String dcName;

   @ApiModelProperty(value = "产品名称")
   private String proName;

}
