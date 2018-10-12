package cn.fintecher.supply.finance.loan.manager.core.credit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.core.credit.service.EnterpriseDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:41 2018/6/21
 */

@RestController
@RequestMapping("/credit/dictionary")
public class EnterpriseDictionaryController {

    @Autowired
    private EnterpriseDictionaryService enterpriseDictionaryService;

    @ResponseBody
    @RequestMapping(value = "/searchAccountingStatementTime", method = RequestMethod.GET)
    public Message searchAccountingStatementTime(@RequestParam(value="year") String year){
        return enterpriseDictionaryService.searchAccountingStatementTime(year);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAllDictionary", method = RequestMethod.GET)
    public List<BaseDictionaryEntity> searchAllDictionary(@RequestParam(value = "code") String code){
      return  enterpriseDictionaryService.searchAllDictionary(code);
    }
}
