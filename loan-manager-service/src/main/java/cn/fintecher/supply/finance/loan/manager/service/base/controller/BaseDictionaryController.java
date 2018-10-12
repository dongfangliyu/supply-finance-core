package cn.fintecher.supply.finance.loan.manager.service.base.controller;

import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.DictionaryResponse;
import cn.fintecher.supply.finance.loan.manager.service.base.service.BaseDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 2:02
 */
@RestController
@RequestMapping("/base/dictionaryService")
public class BaseDictionaryController {

    @Autowired
    private BaseDictionaryService baseDictionaryService;

    /**
     * 字典查询所有接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/getAllList", method = RequestMethod.GET)
    public CommonResponse<DictionaryResponse> getAllList(){
        return baseDictionaryService.getAllList();
    }

    /**
     * 字典根据category查询接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/getDictionaryByPlanApplyId", method = RequestMethod.GET)
    public CommonResponse<DictionaryResponse> getDictionaryByPlanApplyId(@RequestParam(value = "category") String category){
        return baseDictionaryService.getDictionaryByPlanApplyId(category);
    }
}
