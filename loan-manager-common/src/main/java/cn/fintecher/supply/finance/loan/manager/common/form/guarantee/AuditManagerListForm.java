package cn.fintecher.supply.finance.loan.manager.common.form.guarantee;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 14:50 2018/7/18
 */
@Data
public class AuditManagerListForm implements Serializable {
    /**
     * 審批id
     */
    private String id;

    /**
     * 審批結果
     */
    private String result;

    /**
     * 審批意見
     */
    private String content;

}
