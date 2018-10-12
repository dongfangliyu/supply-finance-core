package cn.fintecher.supply.finance.loan.manager.service.pro.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;
import cn.fintecher.supply.finance.loan.manager.service.pro.service.ConfirmationProProductService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 17:37
 */
@Service
public class ConfirmationProProductServiceImpl implements ConfirmationProProductService {

    @Autowired
    private FCProProductCore fcProProductCore;

    @Override
    public Message submitProduct(ProProductEntity proProductEntity) {
        Message message = new Message();
        if (null == proProductEntity){
            message.setCode(MsgCodeConstant.ERR_SUBMIT_PRO_PRODUCT);
            message.setMessage("数据异常");
            return message;
        }
        if (null == proProductEntity.getRebateRatio()){
            proProductEntity.setServiceFee(proProductEntity.getPlatformServiceFee());
        }else {
            proProductEntity.setServiceFee(proProductEntity.getPlatformServiceFee() + proProductEntity.getRebateRatio());
        }
        proProductEntity.setStatus("NOL");
        proProductEntity.setCreateAt(new Date());

        Message<ProProductEntity> result= fcProProductCore.createProduct(proProductEntity);

        if(MessageType.MSG_SUCCESS.equals(result.getCode())){
            proProductEntity =result.getMessage();

            long productId = proProductEntity.getPid();
            String productNo = "BDC243";
            if(productId>0&&productId<10){
                productNo +="000"+productId;
            }else if(productId>=10&&productId<100){
                productNo +="00"+productId;
            }else if(productId>=100&&productId<1000) {
                productNo +="0"+productId;
            }else{
                productNo +=productId;
            }
            proProductEntity.setPid(productId);
            proProductEntity.setProductNo(productNo);
            fcProProductCore.updateProProductEntity(proProductEntity);
            message.setMessage(MsgCodeConstant.ERR_MSG_SUCCESS);
            return message;
        }else{
            message.setCode(MsgCodeConstant.ERR_SUBMIT_PRO_PRODUCT);
            message.setMessage("数据异常");
            return message;
        }
    }
}
