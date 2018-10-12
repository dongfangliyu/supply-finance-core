package cn.fintecher.supply.finance.loan.manager.service.base.service;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;

/**
 * @author gonghebin
 * @date 2018/6/28 0028下午 5:55
 */
public interface BaseAreaService {
    CommonResponse<AreaResponse> getEqptByPlanApplyId(int parentId);

    CommonResponse<AreaResponse> getAreaAll();
}
