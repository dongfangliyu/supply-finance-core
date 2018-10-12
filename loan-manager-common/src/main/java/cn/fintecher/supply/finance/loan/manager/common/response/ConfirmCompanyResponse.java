package cn.fintecher.supply.finance.loan.manager.common.response;

import lombok.Data;

/**
 * @author gonghebin
 * @date 2018/6/25 0025上午 9:19
 */
@Data
public class ConfirmCompanyResponse {

    private boolean type;

    private Long attemptsNum;
}
