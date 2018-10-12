package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service;

import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.DictionaryResponse;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 1:13
 */
public interface BaseDictionaryService {
    CommonResponse<DictionaryResponse> getAllList();

    CommonResponse<DictionaryResponse> getDictionaryByPlanApplyId(String category);
}
