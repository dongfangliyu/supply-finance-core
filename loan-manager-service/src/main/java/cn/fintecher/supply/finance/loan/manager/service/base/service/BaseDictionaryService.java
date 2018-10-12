package cn.fintecher.supply.finance.loan.manager.service.base.service;

import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.DictionaryResponse;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 2:03
 */
public interface BaseDictionaryService {
    CommonResponse<DictionaryResponse> getAllList();

    CommonResponse<DictionaryResponse> getDictionaryByPlanApplyId(String category);
}
