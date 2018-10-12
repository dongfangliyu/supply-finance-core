package cn.fintecher.supply.finance.loan.manager.service.base.feign;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gonghebin
 * @date 2018/6/28 0028下午 5:55
 */
@FeignClient(name = "loan-manager-core")
public interface FCBaseAreaCore {

    @ResponseBody
    @RequestMapping(value ="/base/areaCore/getAreaByParentId", method = RequestMethod.GET)
    AreaResponse getAreaByParentId(@RequestParam(value = "parentId") int parentId);

    @ResponseBody
    @RequestMapping(value ="/base/areaCore/getAreaNameById", method = RequestMethod.GET)
    String getAreaNameById(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value ="/base/areaCore/getAreaAll", method = RequestMethod.GET)
    AreaResponse getAreaAll();
}
