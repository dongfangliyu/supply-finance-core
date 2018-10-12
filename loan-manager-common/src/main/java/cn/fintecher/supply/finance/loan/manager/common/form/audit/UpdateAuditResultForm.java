package cn.fintecher.supply.finance.loan.manager.common.form.audit;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 15:21 2018/7/11
 */
@Data
public class UpdateAuditResultForm implements Serializable {
    private String id;

    private String content;
}
