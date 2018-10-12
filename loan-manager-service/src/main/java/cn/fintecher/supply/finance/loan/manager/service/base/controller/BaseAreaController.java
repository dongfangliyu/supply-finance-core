package cn.fintecher.supply.finance.loan.manager.service.base.controller;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.service.base.service.BaseAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/6/28 0028下午 5:55
 */
@RestController
@RequestMapping("/base/areaService")
public class BaseAreaController {

    @Autowired
    private BaseAreaService baseAreaService;


    /**
     * 区域查询接口
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/getEqptByPlanApplyId", method = RequestMethod.GET)
    CommonResponse<AreaResponse> getEqptByPlanApplyId(@RequestParam(value = "parentId") int parentId){
        return baseAreaService.getEqptByPlanApplyId(parentId);
    }

    @ResponseBody
    @RequestMapping(value ="/getAreaAll", method = RequestMethod.GET)
    public CommonResponse<AreaResponse> getAreaAll(){
        return baseAreaService.getAreaAll();
    }


}
