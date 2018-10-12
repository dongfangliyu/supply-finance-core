package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.feign;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gonghebin
 * @date 2018/6/28 0028下午 5:49
 */
@FeignClient(name = "loan-manager-service")
public interface FCBaseAreaService {

    /**
     * 区域查询接口
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/base/areaService/getEqptByPlanApplyId", method = RequestMethod.GET)
    CommonResponse<AreaResponse> getEqptByPlanApplyId(@RequestParam(value = "parentId") int parentId);

    @ResponseBody
    @RequestMapping(value ="/base/areaService/getAreaAll", method = RequestMethod.GET)
    CommonResponse<AreaResponse> getAreaAll();
}
