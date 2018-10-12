package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.CompanyEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.ConfirmationProProductService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.ProProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 16:04
 */
@RestController
@RequestMapping("/confirmation/proProduct")
@Api(tags = "保兑仓--预付类产品")
public class ConfirmationProProductController {

    @Autowired
    private ProProductService proProductService;

    @Autowired
    private ConfirmationProProductService confirmationProProductService;

    @Autowired
    private CompanyEnterpriseService companyEnterpriseService;

    /**
     * 查询保兑核心企业list
     * */

    @ApiOperation(value="查询保兑核心企业list ", notes="查询保兑核心企业list")
    @RequestMapping(value ="/searchListConfirmationCompany", method = RequestMethod.POST)
    public Message<List<CompanyEnterpriseEntity>> searchListCompany(){
        Message<List<CompanyEnterpriseEntity>> message = companyEnterpriseService.searchListConfirmationCompany();
        return message;
    }

    /**
     * 查询产品列表
     * @param proProductForm
     * @return
     */
    @ApiOperation(value="查询产品列表 ", notes="查询产品列表")
    @RequestMapping(value ="/searchListProduct", method = RequestMethod.POST)
    public Message searchListProduct(@RequestBody ProProductForm proProductForm){
        proProductForm.setBusinessCategory("confirming");
        Message message = proProductService.searchListProduct(proProductForm);
        return message;
    }


    /**
     * 新增产品
     * @param proProductEntity
     * @return
     */
    @ApiOperation(value="新增产品 ", notes="新增产品")
    @RequestMapping(value ="/submitProduct", method = RequestMethod.POST)
    public Message submitProduct(@RequestBody ProProductEntity proProductEntity){
        Message message = confirmationProProductService.submitProduct(proProductEntity);
        return message;
    }

    /**
     * 查询产品编辑
     * @param pid
     * @return
     */
    @ApiOperation(value="查询产品编辑 ", notes="查询产品编辑")
    @RequestMapping(value ="/searchProductDetail", method = RequestMethod.GET)
    public Message searchProductDetail(@RequestParam(value = "pid") String pid){
        Message message = proProductService.searchProductDetail(pid);
        return message;
    }


    /**
     * 保存编辑的产品
     * @param proProductEntity
     * @return
     */
    @ApiOperation(value="保存编辑的产品 ", notes="保存编辑的产品")
    @RequestMapping(value ="/updateProduct", method = RequestMethod.POST)
    public Message updateProduct(@RequestBody ProProductEntity proProductEntity){
        Message message = proProductService.updateProduct(proProductEntity);
        return message;
    }


    /**
     * 删除产品
     * @param pid
     * @return
     */
    @ApiOperation(value="删除产品 ", notes="删除产品")
    @RequestMapping(value ="/deleteProduct", method = RequestMethod.GET)
    public Message deleteProduct(@RequestParam(value = "pid") String pid){
        Message message = proProductService.deleteProduct(pid);
        return message;
    }

    /**
     * 禁用/启用产品
     * @param pid
     * @return
     */
    @ApiOperation(value="禁用/启用产品 ", notes="禁用/启用产品")
    @RequestMapping(value ="/disableProduct", method = RequestMethod.GET)
    public Message disableProduct(@RequestParam(value = "pid") String pid){
        Message message = proProductService.disableProduct(pid);
        return message;
    }

    /**
     * 根据category(产品类型)查询产品
     * @param category
     * @return
     */
    @ApiOperation(value="根据category(产品类型)查询产品 ", notes="根据category(产品类型)查询产品")
    @RequestMapping(value ="/searchProductByCategory", method = RequestMethod.GET)
    public Message searchProductByCategory(@RequestParam(value = "category") String category){
        Message message = proProductService.searchProductByCategory(category);
        return message;
    }

}
