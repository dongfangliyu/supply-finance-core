package cn.fintecher.supply.finance.loan.manager.common.audit.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/7/19 18:07
 */
@Data
public class AuditRepayBankInfoForm implements Serializable {

    @ApiModelProperty(value="审批id")
    private Long id;

    @ApiModelProperty(value="开户行账号id")
    private Long bankId;

    @ApiModelProperty(value="开户行")
    private String bankOpen;

    @ApiModelProperty(value="支行")
    private String bankBranch;

    @ApiModelProperty(value="账户类型（0 核心企业  1 供应商  2签约审核）")
    private String accountType;

    @ApiModelProperty(value="户名")
    private String accountName;

    @ApiModelProperty(value="账户用途（放款账户为1；还款账户为2）")
    private String accountUse;

    @ApiModelProperty(value="银行卡号")
    private String bankCard;

    @ApiModelProperty(value="当前操作人",hidden = true)
    private String currentUserName;

}
