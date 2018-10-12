package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 19:50 2018/7/10
 */
@Data
public class AuditCreditInfoEntity implements Serializable {

    /**
     *pid
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
     * 产品类型对应的中文
     */
    private String productCode;
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
    /**
     *授信类型  0间接授信  1直接授信（暂无）
     */
    private String type;
    /**
     *审核id
     */
    private Long auditCompanyId;

    /**
     *产品代号
     */
    private String productNo;
}
