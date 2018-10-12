package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:49
 */
public interface ProProductService {
    Message searchListProduct(ProProductForm proProductForm);

    Message submitProduct(ProProductEntity proProductEntity);

    Message searchProductDetail(String pid);

    Message deleteProduct(String pid);

    Message disableProduct(String pid);

    Message updateProduct(ProProductEntity proProductEntity);

    Message searchProductByCategory(String category);

    ProProductEntity searchByCompanyId(String companyId);
}
