package cn.fintecher.supply.finance.loan.manager.common.audit.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 10:46
 */
@Data
public class AuditRemindForm extends PageInfo implements Serializable {

    private String supplierName;

    private Long enterpriseId;

    private String enterpriseName;

    private String startTime;

    private String endTime;

    private String shouldStartTime;

    private String shouldEndTime;

    private String state;

}
