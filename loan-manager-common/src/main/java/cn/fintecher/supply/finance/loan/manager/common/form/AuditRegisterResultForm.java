package cn.fintecher.supply.finance.loan.manager.common.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/5 0005下午 5:19
 */
@Data
public class AuditRegisterResultForm implements Serializable {

    private String pid;

    private String result;

    private String remark;

}
