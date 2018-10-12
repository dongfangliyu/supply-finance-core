package cn.fintecher.supply.finance.loan.manager.common.confirming.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wuxiaoxing
 * @time 2018/9/6 14:20
 */
@Data
public class ConfirmingStockInfoListResponse implements Serializable {

    @ApiModelProperty(value = "记录id")
    private Long pid;

    @ApiModelProperty(value = "业务编号(YW+当日日期+243+流水号)")
    private String bussineNo;

    @ApiModelProperty(value = "产品id （pro_product)产品表")
    private Long proProductId;

    @ApiModelProperty(value = "企业id  来源 (企业信息表)")
    private Long companyId;

    @ApiModelProperty(value = "经销商id")
    private Long companyDealerId;

    @ApiModelProperty(value = "经销商融资确认状态   1.已经确认  0 .未确认")
    private String confirmDealerState;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "经销商融资确认时间")
    private Date confirmtDealerTime;

    @ApiModelProperty(value = "核心企业融资确认状态 1.已经确认  0 .未确认 2 拒绝")
    private String confirmCorecompanyState;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "核心企业融资确认时间")
    private Date confirmCorecompanyTime;

    @ApiModelProperty(value = "申请融资状态 0.未申请 1.申请中 2.初审通过 3.复审通过 4.终审通过 5.拒绝")
    private String confirmApplyState;

    @ApiModelProperty(value = "申请金额 ")
    private BigDecimal contractApplyAmount;

    @ApiModelProperty(value = "申请期限")
    private Long contractApplyTerm;

    @ApiModelProperty(value = "审批金额")
    private BigDecimal contractApplyPrice;

    @ApiModelProperty(value = "审批期限")
    private Long contractApprovalTerm;

    @ApiModelProperty(value = "商务合同编号")
    private String contractNo;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "默认未签约 0 ，经销商签约 1，核心企业签约 2 ，平台签约 3 ")
    private String contractState;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "经销商融资签约时间")
    private Date contractDealerTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "核心企业签约时间")
    private Date contractCorecompanyTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "平台签约时间")
    private Date contractPlatformTime;

    @ApiModelProperty(value = "敞口金额 （待还总额）")
    private BigDecimal confirmNotAmount;

    @ApiModelProperty(value= "文件owner_id")
    private String fileCode;

    @ApiModelProperty(value= "合同订单金额")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "产品编号")
    private String productNO;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "核心企业名称")
    private String companyName;

    @ApiModelProperty(value = "经销商名称")
    private String companyDealerName;

    @ApiModelProperty(value="初核状态0待领取 1待初审 2拒绝 3取消 4初审通过")
    private String applyFirstState;

    @ApiModelProperty(value="复核状态： 0待领取 1待初审 2拒绝 3取消  4复审通过")
    private String applyRehearState;

    @ApiModelProperty(value="终核状态： 0待领取 1待初审 2拒绝 3取消  4终审通过")
    private String applyFinalState;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value="初核时间")
    private Date applyFirstTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value="复核时间")
    private Date applyRehearTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value="终核时间")
    private Date applyFinalTime;

    @ApiModelProperty(value="任务id")
    private Long taskId;

}
