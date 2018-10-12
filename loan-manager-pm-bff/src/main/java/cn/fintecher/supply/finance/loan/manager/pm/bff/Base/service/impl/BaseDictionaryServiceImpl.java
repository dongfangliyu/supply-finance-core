package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.DictionaryResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.feign.FCBaseDictionaryService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.BaseDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 1:13
 */
@Service
public class BaseDictionaryServiceImpl implements BaseDictionaryService {

    @Autowired
    private FCBaseDictionaryService fcBaseDictionaryService;

    @Override
    public CommonResponse<DictionaryResponse> getAllList() {
        return fcBaseDictionaryService.getAllList();
    }

    @Override
    public CommonResponse<DictionaryResponse> getDictionaryByPlanApplyId(String category) {
        return fcBaseDictionaryService.getDictionaryByPlanApplyId(category);
    }
}
