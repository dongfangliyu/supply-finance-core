package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 16:56
 */
@FeignClient(name = "loan-manager-service")
public interface FCConfirmationProProductService {

    @ResponseBody
    @RequestMapping(value = "/confirmation/productService/submitProduct", method = RequestMethod.POST)
    Message submitProduct(@RequestBody ProProductEntity proProductEntity);

}
