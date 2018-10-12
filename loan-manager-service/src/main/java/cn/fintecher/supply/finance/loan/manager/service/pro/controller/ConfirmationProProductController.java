package cn.fintecher.supply.finance.loan.manager.service.pro.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.service.pro.service.ConfirmationProProductService;
import cn.fintecher.supply.finance.loan.manager.service.pro.service.ProProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 16:04
 */
@RestController
@RequestMapping("/confirmation/productService")
public class ConfirmationProProductController {

    @Autowired
    private ConfirmationProProductService confirmationProProductService;

    /**
     * 新增产品
     * @param proProductEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/submitProduct", method = RequestMethod.POST)
    public Message submitProduct(@RequestBody ProProductEntity proProductEntity){
        Message message = confirmationProProductService.submitProduct(proProductEntity);
        return message;
    }

}
