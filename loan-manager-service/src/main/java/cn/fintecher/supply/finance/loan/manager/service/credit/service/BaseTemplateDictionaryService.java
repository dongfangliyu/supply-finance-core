package cn.fintecher.supply.finance.loan.manager.service.credit.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WuTianJuan
 * @Date Created in 20:31 2018/7/2
 */
public interface BaseTemplateDictionaryService {
     ResponseEntity<byte[]> downloadDocTemplate(@RequestParam(value = "code") String code);
}
