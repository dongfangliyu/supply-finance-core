package cn.fintecher.supply.finance.loan.manager.service.base.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseAreaCore;
import cn.fintecher.supply.finance.loan.manager.service.base.service.BaseAreaService;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/6/28 0028下午 5:56
 */
@Service
public class BaseAreaServiceImpl implements BaseAreaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAreaServiceImpl.class);

    @Autowired
    private FCBaseAreaCore fcBaseAreaCore;

    @Override
    public CommonResponse<AreaResponse> getEqptByPlanApplyId(int parentId) {

        CommonResponse<AreaResponse> response = new CommonResponse<AreaResponse>();
        try {
            response.setSuccess(true);
            AreaResponse areaResponse = fcBaseAreaCore.getAreaByParentId(parentId);
            response.setData(areaResponse);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
        } catch (Exception e) {
            LOGGER.error("根据父Id查询子集合失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    @Override
    public CommonResponse<AreaResponse> getAreaAll() {
        CommonResponse<AreaResponse> response = new CommonResponse<AreaResponse>();
        try {
            response.setSuccess(true);
            AreaResponse areaResponse = fcBaseAreaCore.getAreaAll();
            response.setData(areaResponse);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
        } catch (Exception e) {
            LOGGER.error("根据父Id查询子集合失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }
}
