package cn.fintecher.supply.finance.loan.manager.core.base.service;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;

/**
 * @author gonghebin
 * @date 2018/6/29 0029上午 9:08
 */
public interface BaseAreaService {

    AreaResponse getAreaByParentId(int parentId);

    String getAreaNameById(Long id);

    AreaResponse getAreaAll();
}
