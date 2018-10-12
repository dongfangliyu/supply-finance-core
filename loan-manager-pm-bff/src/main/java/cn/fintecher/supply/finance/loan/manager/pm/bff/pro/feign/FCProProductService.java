package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:51
 */
@FeignClient(name = "loan-manager-service")
public interface FCProProductService {

    @ResponseBody
    @RequestMapping(value = "/pro/productService/searchListProduct", method = RequestMethod.POST)
    Message searchListProduct(@RequestBody ProProductForm proProductForm);

    @ResponseBody
    @RequestMapping(value = "/pro/productService/submitProduct", method = RequestMethod.POST)
    Message submitProduct(@RequestBody ProProductEntity proProductEntity);

    @ResponseBody
    @RequestMapping(value = "/pro/productService/searchProductDetail", method = RequestMethod.GET)
    Message searchProductDetail(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/pro/productService/deleteProduct", method = RequestMethod.GET)
    Message deleteProduct(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/pro/productService/disableProduct", method = RequestMethod.GET)
    Message disableProduct(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/pro/productService/updateProduct", method = RequestMethod.POST)
    Message updateProduct(@RequestBody ProProductEntity proProductEntity);

    @ResponseBody
    @RequestMapping(value = "/pro/productService/searchProductByCategory", method = RequestMethod.GET)
    Message searchProductByCategory(@RequestParam(value = "category") String category);

    @ResponseBody
    @RequestMapping(value = "/pro/productService/searchByCompanyId", method = RequestMethod.GET)
    ProProductEntity searchByCompanyId(@RequestParam(value = "companyId") String companyId);
}
