package cn.fintecher.supply.finance.loan.manager.common.form.audit;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 2:34
 */
@Data
public class AuditFinanceForm extends PageInfo implements Serializable {

    private String enterpriseName;

    private String supplierName;

    private String accountNo;

    private String startTime;

    private String endTime;

    private String state;

}
