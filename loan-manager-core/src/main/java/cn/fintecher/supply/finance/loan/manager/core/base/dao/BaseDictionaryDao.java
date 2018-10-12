package cn.fintecher.supply.finance.loan.manager.core.base.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 4:07
 */
@Mapper
public interface BaseDictionaryDao {

    List<BaseDictionaryEntity> getAllList();

    List<BaseDictionaryEntity> getListByCode(String code);

	BaseDictionaryEntity getEntityByPid(Long pid);
}
