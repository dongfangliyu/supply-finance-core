package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.impl;

import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign.FCBaseDictionaryService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.BaseTemplateDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WuTianJuan
 * @Date Created in 20:51 2018/7/2
 */
@Service
public class BaseTemplateDictionaryServiceImpl implements BaseTemplateDictionaryService {

    @Autowired
    private FCBaseDictionaryService fcBaseDictionaryService;

    public ResponseEntity<byte[]> downloadDocTemplate(@RequestParam(value = "code") String code){
        return fcBaseDictionaryService.downloadDocTemplate(code);
    }
}
