package cn.fintecher.supply.finance.loan.manager.service.base.feign;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 2:03
 */
@FeignClient(name = "loan-manager-core")
public interface FCBaseDictionaryCore {

    @ResponseBody
    @RequestMapping(value ="/base/dictionaryCore/getAllList", method = RequestMethod.GET)
    List<BaseDictionaryEntity> getAllList();

    @ResponseBody
    @RequestMapping(value ="/base/dictionaryCore/getListByCode", method = RequestMethod.GET)
    List<BaseDictionaryEntity> getListByCode(@RequestParam(value = "code") String code);
    
    @ResponseBody
    @RequestMapping(value ="/base/dictionaryCore/getEntityByPid", method = RequestMethod.GET)
    BaseDictionaryEntity getEntityByPid(@RequestParam(value = "pid") Long pid);
}
