package cn.fintecher.supply.finance.loan.manager.core.pro.dao;

import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/9 0009上午 11:37
 */
@Mapper
public interface ProProductDao {

    public int searchProductListCount(ProProductForm proProductForm);

    List<ProProductEntity> searchProductList(ProProductForm proProductForm);

    long saveProProductEntity(ProProductEntity proProductEntity);

    ProProductEntity searchProductDetail(String pid);

    void updateProProductEntity(ProProductEntity proProductEntity);

    long searchCountOfProduct();

    ProProductEntity searchByCompanyId(String companyId);
}
