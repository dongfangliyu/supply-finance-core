package cn.fintecher.supply.finance.loan.manager.core.base.service;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 4:07
 */
public interface BaseDictionaryService {
    List<BaseDictionaryEntity> getAllList();

    List<BaseDictionaryEntity> getListByCode(String code);

	BaseDictionaryEntity getEntityByPid(Long pid);
}
