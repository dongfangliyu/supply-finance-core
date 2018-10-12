package cn.fintecher.supply.finance.loan.manager.common.form.audit;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 17:39 2018/7/10
 */
@Data
public class AuditResultForm implements Serializable {

    private String id;

    private String type;
}
