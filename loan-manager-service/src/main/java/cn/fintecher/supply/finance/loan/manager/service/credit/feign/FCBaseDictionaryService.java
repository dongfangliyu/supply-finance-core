package cn.fintecher.supply.finance.loan.manager.service.credit.feign;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author WuTianJuan
 * @Date Created in 20:26 2018/7/2
 */

@FeignClient(name = "loan-manager-core")
public interface FCBaseDictionaryService {

    @ResponseBody
    @RequestMapping(value ="/base/dictionary/downloadDocTemplate", method = RequestMethod.GET)
    BaseTemplateDictionaryEntity downloadDocTemplate(@RequestParam(value = "code") String code);
}
