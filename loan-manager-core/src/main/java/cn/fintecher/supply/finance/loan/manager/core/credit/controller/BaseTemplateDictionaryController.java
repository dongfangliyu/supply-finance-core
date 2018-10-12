package cn.fintecher.supply.finance.loan.manager.core.credit.controller;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.core.credit.service.BaseTemplateDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Param:
 * @Return:
 * @Author WuTianJuan
 * @Date Created in 19:42 2018/7/2
 */

@RestController
@RequestMapping("base/dictionary")
public class BaseTemplateDictionaryController {

    @Autowired
    private BaseTemplateDictionaryService baseTemplateDictionaryService;

    @ResponseBody
    @RequestMapping(value = "/downloadDocTemplate", method = RequestMethod.GET)
    public BaseTemplateDictionaryEntity downloadDocTemplate(@RequestParam(value = "code") String code){
        return  baseTemplateDictionaryService.downloadDocTemplate(code);
    }
}
