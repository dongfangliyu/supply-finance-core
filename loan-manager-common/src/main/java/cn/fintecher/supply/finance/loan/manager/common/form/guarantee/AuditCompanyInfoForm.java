package cn.fintecher.supply.finance.loan.manager.common.form.guarantee;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 16:56 2018/7/21
 */
@Data
public class AuditCompanyInfoForm implements Serializable {

    private String supplyId;

    private String enterpriseId;

    private String type;
}
