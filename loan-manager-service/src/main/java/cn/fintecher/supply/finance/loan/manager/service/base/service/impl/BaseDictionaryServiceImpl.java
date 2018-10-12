package cn.fintecher.supply.finance.loan.manager.service.base.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.DictionaryResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseDictionaryCore;
import cn.fintecher.supply.finance.loan.manager.service.base.service.BaseDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 2:03
 */
@Service
public class BaseDictionaryServiceImpl implements BaseDictionaryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDictionaryServiceImpl.class);


    @Autowired
    private FCBaseDictionaryCore fcBaseDictionaryCore;

    @Override
    public CommonResponse<DictionaryResponse> getAllList() {
        CommonResponse<DictionaryResponse> response = new CommonResponse<DictionaryResponse>();
        DictionaryResponse dictionaryResponse = new DictionaryResponse();
        try {
            response.setSuccess(true);
            List<BaseDictionaryEntity> list = fcBaseDictionaryCore.getAllList();
            dictionaryResponse.setList(list);
            response.setData(dictionaryResponse);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));

        } catch (Exception e) {
            LOGGER.error("获取所有字典数据集合信息失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    @Override
    public CommonResponse<DictionaryResponse> getDictionaryByPlanApplyId(String category) {
        CommonResponse<DictionaryResponse> response = new CommonResponse<DictionaryResponse>();
        DictionaryResponse dictionaryResponse = new DictionaryResponse();
        try {
            response.setSuccess(true);
            List<BaseDictionaryEntity> list = fcBaseDictionaryCore.getListByCode(category);
            dictionaryResponse.setList(list);
            response.setData(dictionaryResponse);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));

        } catch (Exception e) {
            LOGGER.error("根据类型查找字典集合信息失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }
}
