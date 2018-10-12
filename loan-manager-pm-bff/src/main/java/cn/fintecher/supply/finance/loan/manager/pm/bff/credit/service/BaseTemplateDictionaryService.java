package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WuTianJuan
 * @Date Created in 20:50 2018/7/2
 */

public interface BaseTemplateDictionaryService {

     ResponseEntity<byte[]> downloadDocTemplate(@RequestParam(value = "code") String code);

}
