package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Param:
 * @Return:
 * @Author WuTianJuan
 * @Date Created in 20:42 2018/7/2
 */

@FeignClient(name = "loan-manager-service")
public interface FCBaseDictionaryService {

    @ResponseBody
    @RequestMapping(value ="/base/dictionary/downloadDocTemplate", method = RequestMethod.GET)
    ResponseEntity<byte[]> downloadDocTemplate(@RequestParam(value = "code") String code);
}
