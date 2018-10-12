package cn.fintecher.supply.finance.loan.manager.core.base.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseAreaEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/29 0029上午 9:12
 */
@Mapper
public interface BaseAreaDao {

    List<BaseAreaEntity> getAreaByParentId(int parentId);

    String getAreaNameById(Long id);

    List<BaseAreaEntity> getAreaAll();
}
