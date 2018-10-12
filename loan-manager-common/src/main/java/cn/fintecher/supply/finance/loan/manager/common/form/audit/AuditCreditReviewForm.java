package cn.fintecher.supply.finance.loan.manager.common.form.audit;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 19:56 2018/7/10
 */
@Data
public class AuditCreditReviewForm implements Serializable {
    /**
     * 审核id
     */
    private Long pid;

    /**
     *客户名称
     */
    private String name;
    /**
     *产品类型
     */
    private String productType;
    /**
     *合同号
     */
    private String contractNo;
    /**
     *授信状态
     */
    private String creditStatus;
    /**
     *循环标志
     */
    private String cycleSign;
    /**
     *授信额度
     */
    private String amount;
    /**
     *有效开始时间
     */
    private Date effectiveStartTime;
    /**
     *有效结束时间
     */
    private Date effectiveEndTime;

    private String ProductNo;
}
