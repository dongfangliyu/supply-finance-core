package cn.fintecher.supply.finance.loan.manager.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/5 0005下午 7:55
 */
@Data
public class AuditCreditResponse implements Serializable {

    private Long certifiedNumber;

    private Long creditNumber;

}
