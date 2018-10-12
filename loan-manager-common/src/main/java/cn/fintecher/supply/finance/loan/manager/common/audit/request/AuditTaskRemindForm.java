package cn.fintecher.supply.finance.loan.manager.common.audit.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 11:14
 */
@Data
public class AuditTaskRemindForm extends PageInfo implements Serializable {

    private Long pid;

    private Long orderInfoId;

    private Long advanceDay;

}
