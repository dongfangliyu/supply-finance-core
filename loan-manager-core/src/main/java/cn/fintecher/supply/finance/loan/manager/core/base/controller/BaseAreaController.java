package cn.fintecher.supply.finance.loan.manager.core.base.controller;

import cn.fintecher.supply.finance.loan.manager.common.response.AreaResponse;
import cn.fintecher.supply.finance.loan.manager.core.base.service.BaseAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/6/29 0029上午 9:08
 */
@RestController
@RequestMapping("/base/areaCore")
public class BaseAreaController {

    @Autowired
    private BaseAreaService baseAreaService;

    @ResponseBody
    @RequestMapping(value ="/getAreaByParentId", method = RequestMethod.GET)
    public AreaResponse getAreaByParentId(@RequestParam(value = "parentId") int parentId){
        return baseAreaService.getAreaByParentId(parentId);
    }

    @ResponseBody
    @RequestMapping(value ="/getAreaAll", method = RequestMethod.GET)
    public AreaResponse getAreaAll(){
        return baseAreaService.getAreaAll();
    }



    @ResponseBody
    @RequestMapping(value ="/getAreaNameById", method = RequestMethod.GET)
    public String getAreaNameById(@RequestParam(value="id") Long id){
        return baseAreaService.getAreaNameById(id);
    }

}
