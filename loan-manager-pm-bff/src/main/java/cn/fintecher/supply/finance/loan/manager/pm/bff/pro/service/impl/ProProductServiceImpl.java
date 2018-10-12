package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.feign.FCProProductService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.ProProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:50
 */
@Service
public class ProProductServiceImpl implements ProProductService {

    @Autowired
    private FCProProductService fcProProductService;


    @Override
    public Message searchListProduct(ProProductForm proProductForm) {
        return fcProProductService.searchListProduct(proProductForm);
    }

    @Override
    public Message submitProduct(ProProductEntity proProductEntity) {
        return fcProProductService.submitProduct(proProductEntity);
    }

    @Override
    public Message searchProductDetail(String pid) {
        return fcProProductService.searchProductDetail(pid);
    }

    @Override
    public Message deleteProduct(String pid) {
        return fcProProductService.deleteProduct(pid);
    }

    @Override
    public Message disableProduct(String pid) {
        return fcProProductService.disableProduct(pid);
    }

    @Override
    public Message updateProduct(ProProductEntity proProductEntity) {
        return fcProProductService.updateProduct(proProductEntity);
    }

    @Override
    public Message searchProductByCategory(String category) {
        return fcProProductService.searchProductByCategory(category);
    }

    @Override
    public ProProductEntity searchByCompanyId(String companyId) {
        return fcProProductService.searchByCompanyId(companyId);
    }
}
