package cn.fintecher.supply.finance.loan.manager.core.pro.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.core.pro.dao.ProCategoryDao;
import cn.fintecher.supply.finance.loan.manager.core.pro.dao.ProProductDao;
import cn.fintecher.supply.finance.loan.manager.core.pro.service.ProProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:37
 */
@Service
public class ProProductServiceImpl implements ProProductService {

    @Autowired
    private ProProductDao proProductDao;

    @Autowired
    private ProCategoryDao proCategoryDao;

    @Override
    public int searchProductListCount(ProProductForm proProductForm) {
        return proProductDao.searchProductListCount(proProductForm);
    }

    @Override
    public List<ProProductEntity> searchProductList(ProProductForm proProductForm) {
        return proProductDao.searchProductList(proProductForm);
    }

    @Override
    public void saveProProductEntity(ProProductEntity proProductEntity) {
        proProductDao.saveProProductEntity(proProductEntity);
    }

    @Override
    public ProProductEntity searchProductDetail(String pid) {
        return proProductDao.searchProductDetail(pid);
    }

    @Override
    public void updateProProductEntity(ProProductEntity proProductEntity) {
        proProductDao.updateProProductEntity(proProductEntity);
    }

    @Override
    public long searchCountOfProduct() {
        return proProductDao.searchCountOfProduct();
    }

    public  ProProductEntity searchByCompanyId(String companyId) {
        return proProductDao.searchByCompanyId(companyId);
    }

    @Override
    public Message<ProProductEntity> createProduct(ProProductEntity proProductEntity) {
        proProductDao.saveProProductEntity(proProductEntity);
        return new Message<ProProductEntity>(MessageType.MSG_SUCCESS,"product_core",proProductEntity);
    }
}
