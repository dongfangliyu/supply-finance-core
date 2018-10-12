package cn.fintecher.supply.finance.loan.manager.service.credit.controller;

import cn.fintecher.supply.finance.loan.manager.service.credit.service.BaseTemplateDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author WuTianJuan
 * @Date Created in 20:32 2018/7/2
 */

@RestController
@RequestMapping("/base/dictionary")
public class BaseTemplateDictionaryController {

    @Autowired
    private BaseTemplateDictionaryService baseDictionaryService;

    @ResponseBody
    @RequestMapping(value ="/downloadDocTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDocTemplate(@RequestParam(value = "code") String code){
        return  baseDictionaryService.downloadDocTemplate(code);
    }
}
