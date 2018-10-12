package cn.fintecher.supply.finance.loan.manager.common.audit.response;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/7/25 10:19
 */
@Data
public class AuditSuppReviewResponse implements Serializable {

    @ApiModelProperty(value = "融资合同信息")
    private AuditOrderInfoEntity auditOrderInfoEntity;

    @ApiModelProperty(value = "销售分账户信息")
    private BaseBankInfoEntity baseBankInfoEntity;

    @ApiModelProperty(value = "未结清金额")
    private String uncleared;

    @ApiModelProperty(value = "服务费")
    private String serviceMoney;

    @ApiModelProperty(value = "还款已复核")
    private String reviewed;

    @ApiModelProperty(value = "还款未复核")
    private String unReviewed;
}
