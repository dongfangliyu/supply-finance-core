package cn.fintecher.supply.finance.loan.manager.common.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 11:20 2018/7/7
 */
@Data
public class AuditSubmitCreditForm implements Serializable {

    private long id;

    private String result;

    private String remark;

    private String type;
}
