package cn.fintecher.supply.finance.loan.manager.core.pro.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.core.pro.service.ProProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:36
 */
@RestController
@RequestMapping("/pro/productCore")
public class ProProductController {

    @Autowired
    private ProProductService proProductService;

    @ResponseBody
    @RequestMapping(value ="/searchProductListCount", method = RequestMethod.POST)
    public int searchProductListCount(@RequestBody ProProductForm proProductForm){
        return proProductService.searchProductListCount(proProductForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchProductList", method = RequestMethod.POST)
    public List<ProProductEntity> searchProductList(@RequestBody ProProductForm proProductForm){
        return proProductService.searchProductList(proProductForm);
    }

    @ResponseBody
    @RequestMapping(value ="/saveProProductEntity", method = RequestMethod.POST)
    public void saveProProductEntity(@RequestBody ProProductEntity proProductEntity){
        proProductService.saveProProductEntity(proProductEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchProductDetail", method = RequestMethod.GET)
    public ProProductEntity searchProductDetail(@RequestParam(value = "pid") String pid){
        return proProductService.searchProductDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/updateProProductEntity", method = RequestMethod.POST)
    public void updateProProductEntity(@RequestBody ProProductEntity proProductEntity){
        proProductService.updateProProductEntity(proProductEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCountOfProduct", method = RequestMethod.GET)
    public long searchCountOfProduct(){
        return proProductService.searchCountOfProduct();
    }

    @ResponseBody
    @RequestMapping(value ="/searchByCompanyId", method = RequestMethod.GET)
    public ProProductEntity searchByCompanyId(@RequestParam(value = "companyId") String companyId) {
        return proProductService.searchByCompanyId(companyId);
    }


    @ResponseBody
    @RequestMapping(value ="/createProduct", method = RequestMethod.POST)
    public Message<ProProductEntity> createProduct(@RequestBody ProProductEntity proProductEntity){
        return  proProductService.createProduct(proProductEntity);
    }

}
