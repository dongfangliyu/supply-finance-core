package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.feign.FCBaseAreaService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.BaseAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/6/28 0028下午 5:47
 */
@Service
public class BaseAreaServiceImpl implements BaseAreaService{

    @Autowired
    private FCBaseAreaService fcBaseAreaService;

    @Override
    public CommonResponse<AreaResponse> getEqptByPlanApplyId(int parentId) {
        return fcBaseAreaService.getEqptByPlanApplyId(parentId);
    }

    @Override
    public CommonResponse<AreaResponse> getAreaAll() {
        return fcBaseAreaService.getAreaAll();
    }
}
