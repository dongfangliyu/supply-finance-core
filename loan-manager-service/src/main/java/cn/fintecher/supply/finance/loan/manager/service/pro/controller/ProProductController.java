package cn.fintecher.supply.finance.loan.manager.service.pro.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.service.pro.service.ProProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:52
 */
@RestController
@RequestMapping("/pro/productService")
public class ProProductController {
    
    @Autowired
    private ProProductService proProductService;

    @ResponseBody
    @RequestMapping(value = "/searchListProduct", method = RequestMethod.POST)
    public Message searchListProduct(@RequestBody ProProductForm proProductForm){
        return proProductService.searchListProduct(proProductForm);
    }

    @ResponseBody
    @RequestMapping(value = "/submitProduct", method = RequestMethod.POST)
    public Message submitProduct(@RequestBody ProProductEntity proProductEntity){
        return proProductService.submitProduct(proProductEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/searchProductDetail", method = RequestMethod.GET)
    public Message searchProductDetail(@RequestParam(value = "pid") String pid){
        return proProductService.searchProductDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public Message deleteProduct(@RequestParam(value = "pid") String pid){
        return proProductService.deleteProduct(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/disableProduct", method = RequestMethod.GET)
    public Message disableProduct(@RequestParam(value = "pid") String pid){
        return proProductService.disableProduct(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public Message updateProduct(@RequestBody ProProductEntity proProductEntity){
        return proProductService.updateProduct(proProductEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/searchProductByCategory", method = RequestMethod.GET)
    public Message searchProductByCategory(@RequestParam(value = "category") String category){
        return proProductService.searchProductByCategory(category);
    }

    @ResponseBody
    @RequestMapping(value = "/searchByCompanyId", method = RequestMethod.GET)
    public ProProductEntity searchByCompanyId(@RequestParam(value = "companyId") String companyId) {
        return proProductService.searchByCompanyId(companyId);
    }
}
