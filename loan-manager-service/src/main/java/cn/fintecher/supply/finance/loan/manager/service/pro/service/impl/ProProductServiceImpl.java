package cn.fintecher.supply.finance.loan.manager.service.pro.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;
import cn.fintecher.supply.finance.loan.manager.service.pro.service.ProProductService;
import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/9 0009下午 1:30
 */
@Service
public class ProProductServiceImpl implements ProProductService {

    @Autowired
    private FCProProductCore fcProProductCore;

    @Autowired
    private IRedisService redisService;

    @Override
    public Message searchListProduct(ProProductForm proProductForm) {
        if(StringUtils.isNotBlank(proProductForm.getTimeStart())){
            proProductForm.setTimeStart(proProductForm.getTimeStart() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(proProductForm.getTimeEnd())){
            proProductForm.setTimeEnd(proProductForm.getTimeEnd() + " 23:59:59");
        }
        Message message = new Message();
        PageResponse<Object> response = new PageResponse<>();
        int total = 0;
        if(proProductForm.getPageNo() != 0){
            total = fcProProductCore.searchProductListCount(proProductForm);
        }
        List<ProProductEntity> productList = fcProProductCore.searchProductList(proProductForm);
        if (null != productList){
            response.setTotal(total);
            response.setData(productList);
            response.setPageNo(proProductForm.getPageNo());
            response.setPageSize(proProductForm.getPageSize());
            message.setMessage(response);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_PRO_LIST);
        }
        return message;
    }

    @Override
    public Message submitProduct(ProProductEntity proProductEntity) {
        Message message = new Message();
        if (null == proProductEntity){
            message.setCode(MsgCodeConstant.ERR_SUBMIT_PRO_PRODUCT);
            message.setMessage("数据异常");
            return message;
        }
//        if (null != proProductEntity.getCategory()){
//            ProProductForm proProductForm = new ProProductForm();
//            proProductForm.setCategory(proProductEntity.getCategory());
//            List<ProProductEntity> productList = fcProProductCore.searchProductList(proProductForm);
//            if (productList.size() != 0){
//                message.setCode(MsgCodeConstant.ERR_SUBMIT_PRO_PRODUCT);
//                message.setMessage("该类型的产品已存在!");
//                return message;
//            }
//        }

        String productNo = redisService.get("countProduct");
        if (Strings.isNullOrEmpty(productNo)){
            long aLong = fcProProductCore.searchCountOfProduct();
            long newCount = aLong + 1l;
            productNo = String.valueOf(newCount);
            redisService.set("countProduct",productNo);
        }else {
            long aLong = Long.valueOf(productNo);
            long newLong = aLong + 1l;
            productNo = String.valueOf(newLong);
            redisService.set("countProduct",productNo);
        }

        if (productNo.length() == 1){
            productNo = "CP00000" + productNo;
        } else if(productNo.length() == 2){
            productNo = "CP0000" + productNo;
        } else if(productNo.length() == 3){
            productNo = "CP000" + productNo;
        } else if(productNo.length() == 4){
            productNo = "CP00" + productNo;
        } else if(productNo.length() == 5) {
            productNo = "CP0" + productNo;
        } else if(productNo.length() == 6) {
            productNo = "CP" + productNo;
        }

        if (null == proProductEntity.getRebateRatio()){
            proProductEntity.setServiceFee(proProductEntity.getPlatformServiceFee());
        }else {
            proProductEntity.setServiceFee(proProductEntity.getPlatformServiceFee() + proProductEntity.getRebateRatio());
        }
        proProductEntity.setProductNo(productNo);
        proProductEntity.setStatus("NOL");
        proProductEntity.setCreateAt(new Date());
        fcProProductCore.saveProProductEntity(proProductEntity);
        message.setMessage(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message searchProductDetail(String pid) {
        Message message = new Message();
        ProProductEntity proProductEntity = fcProProductCore.searchProductDetail(pid);
        if (null == proProductEntity){
            message.setCode(MsgCodeConstant.ERR_SEARCH_PRODUCT_DETAIL);
            return message;
        }
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(proProductEntity);
        return message;
    }

    @Override
    public Message deleteProduct(String pid) {
        Message message = new Message();
        ProProductEntity proProductEntity = fcProProductCore.searchProductDetail(pid);
        if (null == proProductEntity){
            message.setCode(MsgCodeConstant.ERR_DELETE_PRODUCT);
            return message;
        }
        proProductEntity.setStatus("DEL");
        proProductEntity.setUpdateAt(new Date());
        fcProProductCore.updateProProductEntity(proProductEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message disableProduct(String pid) {
        Message message = new Message();
        ProProductEntity proProductEntity = fcProProductCore.searchProductDetail(pid);
        if (null == proProductEntity){
            message.setCode(MsgCodeConstant.ERR_DELETE_PRODUCT);
            return message;
        }
        if ("1".equals(proProductEntity.getState())){
            proProductEntity.setState("0");
        }else{
            proProductEntity.setState("1");
        }
        proProductEntity.setUpdateAt(new Date());
        fcProProductCore.updateProProductEntity(proProductEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message updateProduct(ProProductEntity proProductEntity) {
        Message message = new Message();

        if (null == proProductEntity){
            message.setCode(MsgCodeConstant.ERR_SUBMIT_PRO_PRODUCT);
            message.setMessage("数据异常");
            return message;
        }

//        if (null != proProductEntity.getCategory()){
//            ProProductForm proProductForm = new ProProductForm();
//            proProductForm.setCategory(proProductEntity.getCategory());
//            proProductForm.setState("1");
//            List<ProProductEntity> productList = fcProProductCore.searchProductList(proProductForm);
//            if (productList.size() > 1){
//                message.setMessage("该类型的产品已存在!");
//                message.setCode(MsgCodeConstant.ERR_SUBMIT_PRO_PRODUCT);
//                return message;
//            }
//        }

        if (null == proProductEntity.getRebateRatio()){
            proProductEntity.setServiceFee(proProductEntity.getPlatformServiceFee());
        }else {
            proProductEntity.setServiceFee(proProductEntity.getPlatformServiceFee() + proProductEntity.getRebateRatio());
        }
        proProductEntity.setUpdateAt(new Date());
        fcProProductCore.updateProProductEntity(proProductEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message searchProductByCategory(String category) {
        Message<Object> message = new Message<>();
        ProProductForm proProductForm = new ProProductForm();
        proProductForm.setCategory(category);
        proProductForm.setState("1");
        List<ProProductEntity> list = fcProProductCore.searchProductList(proProductForm);
        message.setMessage(list);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public ProProductEntity searchByCompanyId(String companyId) {
        return fcProProductCore.searchByCompanyId(companyId);
    }
}
