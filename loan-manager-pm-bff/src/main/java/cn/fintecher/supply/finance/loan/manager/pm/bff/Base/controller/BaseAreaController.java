package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.BaseAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonghebin
 * @date 2018/6/28 0028下午 5:38
 */
@RestController
@RequestMapping("/base/area")
@Api("区域查询接口")
public class BaseAreaController {

    @Autowired
    private BaseAreaService baseAreaService;

    @ApiOperation(value="区域查询接口 ", notes="区域查询接口")
    @RequestMapping(value ="/getEqptByPlanApplyId", method = RequestMethod.GET)
    public CommonResponse<AreaResponse> getEqptByPlanApplyId(@RequestParam(value = "parentId") int parentId){
        CommonResponse<AreaResponse> response = baseAreaService.getEqptByPlanApplyId(parentId);
        return response;
    }

    @ApiOperation(value="区域ALL接口 ", notes="区域ALL接口")
    @RequestMapping(value ="/getAreaAll", method = RequestMethod.GET)
    public CommonResponse<AreaResponse> getAreaAll(){
        CommonResponse<AreaResponse> response = baseAreaService.getAreaAll();
        return response;
    }

}
