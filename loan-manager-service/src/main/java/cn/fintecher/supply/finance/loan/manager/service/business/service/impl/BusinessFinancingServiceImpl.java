package cn.fintecher.supply.finance.loan.manager.service.business.service.impl;

import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingForm;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessVoucherForm;
import cn.fintecher.supply.finance.loan.manager.common.business.response.BusinessFinancingResponse;
import cn.fintecher.supply.finance.loan.manager.common.business.response.BusinessOrderResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFileCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessOrderCore;
import org.springframework.stereotype.Service;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;

import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFinancingService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 16:59:17
 */
@Service("businessFinancingService")
public class BusinessFinancingServiceImpl implements BusinessFinancingService {

    @Autowired
    private BusinessOrderCore businessOrderCore;

    @Autowired
    private BusinessFileCore businessFileCore;

    public Message searchListOrder(BusinessFinancingRequest businessFinancingRequest) {
        //查询总数
        Integer count = null;
        Message msgCount = businessOrderCore.queryFinancingPageCount(businessFinancingRequest);
        if (MessageType.MSG_SUCCESS.equals(msgCount.getCode())) {
            count = Integer.parseInt(msgCount.getMessage().toString());
        } else {
            return new Message(MessageType.MSG_ERROR, "business_service", ReturnMsg.FAILED_CURRENCY);
        }
        //查询列表
        List<BusinessOrderEntity> list = null;
        Message msgList = businessOrderCore.queryFinancingPageList(businessFinancingRequest);
        if (MessageType.MSG_SUCCESS.equals(msgList.getCode())) {
            list = JSONUtil.toList(msgList.getMessage(), BusinessOrderEntity.class);
        } else {
            return new Message(MessageType.MSG_ERROR, "business", ReturnMsg.FAILED_CURRENCY);
        }
        PagedResponse response = new PagedResponse();
        response.setData(list);
        response.setTotal(count);
        response.setPageNo(businessFinancingRequest.getPageNo());
        response.setPageSize(businessFinancingRequest.getPageSize());

        return new Message(MessageType.MSG_SUCCESS, "business_service", response);
    }


    public Message selectOrderEnterDetail(Long id) {
        BusinessOrderResponse businessOrderResponse = new BusinessOrderResponse();
        Message<BusinessOrderEntity> message = businessOrderCore.queryOrderByPid(Long.toString(id));
        if (MessageType.MSG_SUCCESS.equals(message.getCode())) {
            businessOrderResponse.setBusinessOrder(message.getMessage());
        }
        Message<List<BusinessFileEntity>> listMessage = new Message<>();
        if (businessOrderResponse.getBusinessOrder() != null && businessOrderResponse.getBusinessOrder().getPid() > 0 && !ChkUtil.isEmpty(businessOrderResponse.getBusinessOrder().getOrderCode())) {
            listMessage = businessFileCore.queryEnterFileList(businessOrderResponse.getBusinessOrder().getOrderCode());
            if (MessageType.MSG_SUCCESS.equals(listMessage.getCode())) {
                businessOrderResponse.setFileList(listMessage.getMessage());
            }
        }
        return new Message(MessageType.MSG_SUCCESS, "business_service", businessOrderResponse);
    }


    public Message submitOrder(BusinessVoucherForm businessVoucherForm) {
        BusinessOrderEntity businessOrderEntity = new BusinessOrderEntity();
        Message<BusinessOrderEntity> message = businessOrderCore.queryOrderByPid(businessVoucherForm.getOrderId());
        if (MessageType.MSG_SUCCESS.equals(message.getCode())) {
            businessOrderEntity = message.getMessage();
            businessOrderEntity.setSuppCategory(businessVoucherForm.getSuppCategory());
            businessOrderEntity.setSuppCategoryValue(businessVoucherForm.getSuppCategoryValue());
            businessOrderEntity.setSuppCode(businessOrderEntity.getPid().toString());
            return businessOrderCore.updateOrder(businessOrderEntity);
        } else {
            return message;
        }
    }

    @Override
    public Message submitApply(BusinessFinancingForm businessFinancingForm) {
        BusinessOrderEntity businessOrderEntity = new BusinessOrderEntity();
        Message<BusinessOrderEntity> message = businessOrderCore.queryOrderByPid(businessFinancingForm.getOrderId());
        if (MessageType.MSG_SUCCESS.equals(message.getCode())) {
            businessOrderEntity = message.getMessage();
            String certificateAmount = businessOrderEntity.getCertificateAmount();
            if(!ChkUtil.isEmpty(certificateAmount)){
                BigDecimal certificateAmountBig = new BigDecimal(certificateAmount);
                if(!ChkUtil.isEmpty(businessFinancingForm.getApplicationAmount())){
                    BigDecimal applicationAmount = new BigDecimal(businessFinancingForm.getApplicationAmount());
                    if(applicationAmount.compareTo(certificateAmountBig.multiply(new BigDecimal("0.8")))>0){
                        return new Message(MessageType.MSG_ERROR,"business_service","最高申请金额为应收账款金额的80%！");
                    }else{
                        businessOrderEntity.setInterest(businessFinancingForm.getInterest());
                        businessOrderEntity.setApplicationAmount(businessFinancingForm.getApplicationAmount());
                        businessOrderEntity.setApplicationTerm(businessFinancingForm.getApplicationTerm());
                        businessOrderEntity.setFinancingStatus("1");
                        businessOrderEntity.setSubmitTime(new Date());
                        businessOrderEntity.setProcessStatus(Constants.APPROVAL_FIRST_STAY);
                        return businessOrderCore.updateOrder(businessOrderEntity);
                    }
                }else{
                    return new Message(MessageType.MSG_ERROR,"business_service","申请金额必填！");
                }

            }else{
                return new Message(MessageType.MSG_ERROR,"business_service","账款凭证金额为空！");
            }
        } else {
            return message;
        }
    }

    public Message selectOrderSuppDetail(Long id) {
        BusinessOrderResponse businessOrderResponse = new BusinessOrderResponse();
        Message<BusinessOrderEntity> message = businessOrderCore.queryOrderByPid(Long.toString(id));
        if (MessageType.MSG_SUCCESS.equals(message.getCode())) {
            businessOrderResponse.setBusinessOrder(message.getMessage());
        }
        Message<List<BusinessFileEntity>> listMessage = new Message<>();
        if (businessOrderResponse.getBusinessOrder() != null && businessOrderResponse.getBusinessOrder().getPid() > 0 ) {
            listMessage = businessFileCore.querySuppFileList(businessOrderResponse.getBusinessOrder().getPid().toString());
            if (MessageType.MSG_SUCCESS.equals(listMessage.getCode())) {
                businessOrderResponse.setFileList(listMessage.getMessage());
            }
        }
        return new Message(MessageType.MSG_SUCCESS, "business_service", businessOrderResponse);
    }

    public Message selectOrderDetail(Long id) {
        BusinessFinancingResponse businessFinancingResponse = new BusinessFinancingResponse();
        Message<BusinessOrderEntity> message = businessOrderCore.queryOrderByPid(Long.toString(id));
        if (MessageType.MSG_SUCCESS.equals(message.getCode())) {
            BusinessOrderEntity businessOrderEntity =message.getMessage();
            Integer a = businessOrderEntity.getServiceFee();
            String b = businessOrderEntity.getApplicationAmount();
            Integer C = businessOrderEntity.getApplicationTerm();
            String serviceMoney = new BigDecimal(a)
                    .multiply(new BigDecimal(b))
                    .divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(C))
                    .setScale(2,BigDecimal.ROUND_HALF_UP).toString();
            businessOrderEntity.setServiceMoney(serviceMoney);
            businessFinancingResponse.setBusinessOrder(businessOrderEntity);
        }
        Message<List<BusinessFileEntity>> listSuppMessage = new Message<>();
        if (businessFinancingResponse.getBusinessOrder() != null && businessFinancingResponse.getBusinessOrder().getPid() > 0) {
            listSuppMessage = businessFileCore.querySuppFileList(businessFinancingResponse.getBusinessOrder().getPid().toString());
            if (MessageType.MSG_SUCCESS.equals(listSuppMessage.getCode())) {
                businessFinancingResponse.setSuppfileList(listSuppMessage.getMessage());
            }
        }

        Message<List<BusinessFileEntity>> listEnterMessage = new Message<>();
        if (businessFinancingResponse.getBusinessOrder() != null && businessFinancingResponse.getBusinessOrder().getPid() > 0 && !ChkUtil.isEmpty(businessFinancingResponse.getBusinessOrder().getOrderCode())) {
            listEnterMessage = businessFileCore.queryEnterFileList(businessFinancingResponse.getBusinessOrder().getOrderCode());
            if (MessageType.MSG_SUCCESS.equals(listEnterMessage.getCode())) {
                businessFinancingResponse.setEnterfileList(listEnterMessage.getMessage());
            }
        }

        return new Message(MessageType.MSG_SUCCESS, "business_service", businessFinancingResponse);
    }

}
