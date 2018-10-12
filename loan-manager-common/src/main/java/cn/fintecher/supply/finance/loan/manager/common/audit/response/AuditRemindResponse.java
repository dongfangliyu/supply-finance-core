package cn.fintecher.supply.finance.loan.manager.common.audit.response;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import lombok.Data;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 11:25
 */
@Data
public class AuditRemindResponse {

    private Long pid;

    private String enterpriseName;

    private String supplierName;

    private String certificateAmount;

    private String approvalAmount;

    private Integer approvalTerm;

    private Integer interestRate;

    private Integer serviceFee;

    private String interestRatePrice;

    private String serviceFeePrice;

    private String loanCycle;

    private double closedPrice;

    private double unclearPrice;

    private double unReviewPrice;

    private String review_agree_time;

    private String shouldTime;

    private BaseBankInfoEntity baseBankInfoEntity;

    private AuditTaskEntryEntity auditTaskEntryEntity;

}
