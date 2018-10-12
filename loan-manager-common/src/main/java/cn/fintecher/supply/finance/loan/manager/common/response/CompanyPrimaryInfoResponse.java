package cn.fintecher.supply.finance.loan.manager.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gonghebin
 * @date 2018/6/22 0022上午 10:35
 */
@Data
public class CompanyPrimaryInfoResponse implements Serializable{

    @ApiModelProperty("企业名称")
    private  String name;

    @ApiModelProperty("营业执照注册号")
    private String enpsLicense;

    @ApiModelProperty("开户行")
    private String bankOpen;

    @ApiModelProperty("支行")
    private String bankBranch;

    @ApiModelProperty("法人归属地")
    private String legalAddress;

    @ApiModelProperty("银行卡号")
    private String bankCard;

    @ApiModelProperty("代理人OR法人")
    private String fillPersion;

    @ApiModelProperty("预计到账时间")
    private Date moneyTime;

    @ApiModelProperty("打款时间")
    private Date paymentTime;

    @ApiModelProperty("尝试次数")
    private Long attemptsNum;

    @ApiModelProperty("打款金额")
    private Long accountAmount;

    @ApiModelProperty("认证状态")
    private String state;


}
