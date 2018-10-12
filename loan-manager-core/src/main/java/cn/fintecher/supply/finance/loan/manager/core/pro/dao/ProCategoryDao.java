package cn.fintecher.supply.finance.loan.manager.core.pro.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.ProCategoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:38
 */
@Mapper
public interface ProCategoryDao {

    List<ProCategoryEntity> searchCategoryListByParentId(String parentId);

    ProCategoryEntity searchCategoryByPid(String pid);

    ProCategoryEntity searchCategoryByCategory(String category);
}
