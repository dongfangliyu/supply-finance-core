package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.feign;

import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.DictionaryResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 1:14
 */
@FeignClient(name = "loan-manager-service")
public interface FCBaseDictionaryService {

    /**
     * 字典查询所有接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/base/dictionaryService/getAllList", method = RequestMethod.GET)
    CommonResponse<DictionaryResponse> getAllList();

    /**
     * 字典根据category查询接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/base/dictionaryService/getDictionaryByPlanApplyId", method = RequestMethod.GET)
    CommonResponse<DictionaryResponse> getDictionaryByPlanApplyId(@RequestParam(value = "category") String category);
}
