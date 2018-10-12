package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 10:04
 */
@Data
public class AuditRegisterForm extends PageInfo implements Serializable {

    private String name;

    private String submitStartTime;

    private String submitEndTime;

    private String successStartTime;

    private String successEndTime;

    private String state;

    private String enterpriseType;
}
