package cn.fintecher.supply.finance.loan.manager.service.pro.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:52
 */
@FeignClient(name = "loan-manager-core")
public interface FCProProductCore {

    @ResponseBody
    @RequestMapping(value ="/pro/productCore/searchProductListCount", method = RequestMethod.POST)
    int searchProductListCount(@RequestBody ProProductForm proProductForm);

    @ResponseBody
    @RequestMapping(value ="/pro/productCore/searchProductList", method = RequestMethod.POST)
    List<ProProductEntity> searchProductList(@RequestBody ProProductForm proProductForm);

    @ResponseBody
    @RequestMapping(value ="/pro/productCore/saveProProductEntity", method = RequestMethod.POST)
    void saveProProductEntity(@RequestBody ProProductEntity proProductEntity);

    @ResponseBody
    @RequestMapping(value ="/pro/productCore/searchProductDetail", method = RequestMethod.GET)
    ProProductEntity searchProductDetail(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value ="/pro/productCore/updateProProductEntity", method = RequestMethod.POST)
    void updateProProductEntity(@RequestBody ProProductEntity proProductEntity);

    @ResponseBody
    @RequestMapping(value ="/pro/productCore/searchCountOfProduct", method = RequestMethod.GET)
    long searchCountOfProduct();

    @ResponseBody
    @RequestMapping(value ="/pro/productCore/searchByCompanyId", method = RequestMethod.GET)
    ProProductEntity searchByCompanyId(@RequestParam(value = "companyId") String companyId);

    @ResponseBody
    @RequestMapping(value ="/pro/productCore/createProduct", method = RequestMethod.POST)
    Message<ProProductEntity> createProduct(@RequestBody ProProductEntity proProductEntity);
}
