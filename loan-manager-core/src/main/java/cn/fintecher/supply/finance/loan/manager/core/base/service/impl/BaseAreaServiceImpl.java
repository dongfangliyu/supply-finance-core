package cn.fintecher.supply.finance.loan.manager.core.base.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import cn.fintecher.supply.finance.loan.manager.core.base.dao.BaseAreaDao;
import cn.fintecher.supply.finance.loan.manager.core.base.service.BaseAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/6/29 0029上午 9:09
 */
@Service
public class BaseAreaServiceImpl implements BaseAreaService{

    @Autowired
    private BaseAreaDao baseAreaDao;

    @Override
    public AreaResponse getAreaByParentId(int parentId) {
        AreaResponse response = new AreaResponse();
        response.setList(baseAreaDao.getAreaByParentId(parentId));
        return response;
    }

    @Override
    public String getAreaNameById(Long id){
        return baseAreaDao.getAreaNameById(id);
    }

    @Override
    public AreaResponse getAreaAll() {
        AreaResponse response = new AreaResponse();
        response.setList(baseAreaDao.getAreaAll());
        return response;
    }
}
