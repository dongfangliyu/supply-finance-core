package cn.fintecher.supply.finance.loan.manager.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: ConfirmingStockInfoEntity
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/28 0028下午 15:54
 */
@Data
public class ConfirmingStockInfoEntity implements Serializable {

   /**
    * 记录id
    */
   @ApiModelProperty(value = "记录id")
   private Long pid;

   /**
    * 业务编号(YW+当日日期+243+流水号)
    */
   @ApiModelProperty(value = "业务编号(YW+当日日期+243+流水号)")
   private String bussineNo;

   /**
    * 产品id （pro_product)产品表
    */
   @ApiModelProperty(value = "产品id （pro_product)产品表")
   private Long proRoductId;

   /**
    * 企业id  来源 (企业信息表)
    */
   @ApiModelProperty(value = "企业id  来源 (企业信息表)")
   private Long companyId;

   /**
    * 经销商id
    */
   @ApiModelProperty(value = "经销商id")
   private Long companyDealerId;

   /**
    * 经销商融资确认状态   1.已经确认  0 .未确认
    */
   @ApiModelProperty(value = "经销商融资确认状态   1.已经确认  0 .未确认")
   private String confirmDealerState;

   /**
    * 经销商融资确认时间
    */
   @ApiModelProperty(value = "经销商融资确认时间")
   private Date confirmtDealerTime;

   /**
    * 核心企业融资确认状态 1.已经确认  0 .未确认
    */
   @ApiModelProperty(value = "核心企业融资确认状态 1.已经确认  0 .未确认")
   private String confirmCorecompanyState;

   /**
    * 核心企业融资确认时间
    */
   @ApiModelProperty(value = "核心企业融资确认时间")
   private Date confirmCorecompanyTime;

   /**
    * 申请融资状态  1.已申请通过  0 .未申请 2.申请中 3.拒绝
    */
   @ApiModelProperty(value = "申请融资状态  1.已申请通过  0 .未申请 2.申请中 3.拒绝")
   private String confirmApplyState;

   /**
    * 申请金额
    */
   @ApiModelProperty(value = "申请金额 ")
   private BigDecimal contractApplyAmount;

   /**
    * 申请期限
    */
   @ApiModelProperty(value = "申请期限")
   private Long contractApplyTerm;

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
    * 合同类型
    */
   @ApiModelProperty(value = "合同类型")
   private String contractType;

   /**
    * 默认未签约 0 ，经销商签约 1，核心企业签约 2 ，平台签约 3
    */
   @ApiModelProperty(value = "默认未签约 0 ，经销商签约 1，核心企业签约 2 ，平台签约 3 ")
   private String contractState;

   /**
    * 经销商融资签约时间
    */
   @ApiModelProperty(value = "经销商融资签约时间")
   private Date contractDealerTime;

   /**
    * 核心企业签约时间
    */
   @ApiModelProperty(value = "核心企业签约时间")
   private Date contractCorecompanyTime;

   /**
    * 平台签约时间
    */
   @ApiModelProperty(value = "平台签约时间")
   private Date contractPlatformTime;

   /**
    * 敞口金额 （待还总额）
    */
   @ApiModelProperty(value = "敞口金额 （待还总额）")
   private BigDecimal confirmNotAmount;
}
