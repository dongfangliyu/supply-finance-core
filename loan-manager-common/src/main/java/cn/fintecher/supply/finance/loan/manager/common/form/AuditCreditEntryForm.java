package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 15:46 2018/7/8
 */
@Data
public class AuditCreditEntryForm extends PageInfo implements Serializable {
    private String name;

    private Date submitStartTime;

    private Date  submitEndTime;

    private Date surveyStartTime;

    private Date  surveyEndTime;

    private String state;
}
