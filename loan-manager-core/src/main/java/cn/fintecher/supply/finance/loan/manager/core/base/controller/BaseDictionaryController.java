package cn.fintecher.supply.finance.loan.manager.core.base.controller;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.core.base.service.BaseDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 4:06
 */
@RestController
@RequestMapping("/base/dictionaryCore")
public class BaseDictionaryController {

    @Autowired
    private BaseDictionaryService baseDictionaryService;

    @ResponseBody
    @RequestMapping(value ="/getAllList", method = RequestMethod.GET)
    public List<BaseDictionaryEntity> getAllList(){
        return baseDictionaryService.getAllList();
    }

    @ResponseBody
    @RequestMapping(value ="/getListByCode", method = RequestMethod.GET)
    public List<BaseDictionaryEntity> getListByCode(@RequestParam(value = "code") String code){
        return baseDictionaryService.getListByCode(code);
    }
    
    @ResponseBody
    @RequestMapping(value ="/getEntityByPid", method = RequestMethod.GET)
    public BaseDictionaryEntity getEntityByPid(@RequestParam(value = "pid") Long pid){
        return baseDictionaryService.getEntityByPid(pid);
    }

}
