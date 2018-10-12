package cn.fintecher.supply.finance.loan.manager.core.pro.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:37
 */
public interface ProProductService {
    int searchProductListCount(ProProductForm proProductForm);

    List<ProProductEntity> searchProductList(ProProductForm proProductForm);

    void saveProProductEntity(ProProductEntity proProductEntity);

    ProProductEntity searchProductDetail(String pid);

    void updateProProductEntity(ProProductEntity proProductEntity);

    long searchCountOfProduct();

    ProProductEntity searchByCompanyId(String companyId);

    Message<ProProductEntity> createProduct(ProProductEntity proProductEntity);
}
