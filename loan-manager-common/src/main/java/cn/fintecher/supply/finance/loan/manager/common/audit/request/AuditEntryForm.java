package cn.fintecher.supply.finance.loan.manager.common.audit.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 6:29
 */
@Data
public class AuditEntryForm extends PageInfo implements Serializable {

    private String repaymentTime;

    private Long repaymentPrice;

    private String account;

    private String accountType;

    private Long resourceId;

    private Long orderId;

}
