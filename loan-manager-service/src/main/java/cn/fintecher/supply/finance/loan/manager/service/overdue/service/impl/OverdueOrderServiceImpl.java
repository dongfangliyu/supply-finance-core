package cn.fintecher.supply.finance.loan.manager.service.overdue.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRepaymentForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.response.OverdueOrderResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditOrderInfoService;
import cn.fintecher.supply.finance.loan.manager.service.overdue.core.OverdueOrderCore;
import cn.fintecher.supply.finance.loan.manager.service.overdue.core.OverdueRepaymentRecordCore;
import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueOrderService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
@Service("overdueOrderService")
public class OverdueOrderServiceImpl implements OverdueOrderService {

	@Autowired
    private OverdueOrderCore overdueOrderCore;
    
	@Autowired
	private OverdueRepaymentRecordCore repaymentRecordCore;
	
	@Autowired
	private AuditOrderInfoService auditOrderInfoService;
	
    @Override
	public Message insertOrder(OverdueOrderEntity overdueOrderEntity){
		return overdueOrderCore.insertOrder(overdueOrderEntity);
	}
	
	@Override
	public Message<List<OverdueOrderEntity>> selectByOrder(OverdueOrderEntity overdueOrderEntity) {
		return overdueOrderCore.selectByOrder(overdueOrderEntity);
	}
	
	@Override
	public Message updateOrder(OverdueOrderEntity overdueOrderEntity) {
		return overdueOrderCore.updateOrder(overdueOrderEntity);
	}
    
	@Override
	public Message<OverdueOrderEntity> queryOrderByPid(String pid) {
		return overdueOrderCore.queryOrderByPid(pid);
	}
	
    public Message searchOrderList(OverdueOrderForm overdueOrderForm){
    	try {
			Message<List<OverdueOrderResponse>> listMsg = overdueOrderCore.queryListByOrderForm(overdueOrderForm);
			List<OverdueOrderResponse> list = listMsg.getMessage();
			for (OverdueOrderResponse overdueOrderResponse : list) {
				Long unpaidPenaltyHistory = 0L;
				if (overdueOrderResponse.getSettle().equals(Constants.OVERDUE_SETTLE_YES)) {
					overdueOrderResponse.setUnpaidPenalty(0L);
					overdueOrderResponse.setOverdueDay(0);
				}else {
					
					OverdueRepaymentRecordEntity overdueRepaymentRecordEntity = new OverdueRepaymentRecordEntity();
					overdueRepaymentRecordEntity.setOrderId(overdueOrderResponse.getPid());
					overdueRepaymentRecordEntity.setStatus(Constants.DATA_STATUS_NOL);
					Message<List<OverdueRepaymentRecordEntity>> recordMsg = repaymentRecordCore.selectByRepaymentRecord(overdueRepaymentRecordEntity);
					Integer days = 0;
					if (ChkUtil.isEmpty(recordMsg.getMessage())) {
						days = DateUtil.diffDays(overdueOrderResponse.getLoanTime(),new Date())-overdueOrderResponse.getApprovalTerm();
					}else{
						days = DateUtil.diffDays(recordMsg.getMessage().get(0).getRepaymentTime(),new Date());
						unpaidPenaltyHistory += recordMsg.getMessage().get(0).getUnpaidPenalty();
					}
					Long unpaidPenalty = overdueOrderResponse.getUnpaidPrincipal()*days*overdueOrderResponse.getPenaltyFee()/10000+unpaidPenaltyHistory;
					overdueOrderResponse.setUnpaidPenalty(unpaidPenalty);
					overdueOrderResponse.setOverdueDay(DateUtil.diffDays(overdueOrderResponse.getLoanTime(),new Date())-overdueOrderResponse.getApprovalTerm());
				}
				
			}
			Message<Integer> countMsg = overdueOrderCore.queryCountByOrderForm(overdueOrderForm);
			PageResponse<List<OverdueOrderResponse>> response = new PageResponse<>();
			response.setTotal(countMsg.getMessage());
			response.setPageNo(overdueOrderForm.getPageNo());
			response.setPageSize(overdueOrderForm.getPageSize());
			response.setData(list);
			return new Message<>(MessageType.MSG_SUCCESS,"overdue",response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"overdue",ReturnMsg.FAILED_CURRENCY);
		}
    	
    };
	
    /**
     * 查看详情
     */
    public Message searchOrderDetail(Long pid){
    	Message msg = null;
    	try {
			msg = this.queryOrderByPid(pid+"");
			OverdueOrderResponse overdueOrderResponse = JSONUtil.toBean(msg.getMessage(), OverdueOrderResponse.class);
			Long unpaidPenaltyHistory = 0L;
			if (overdueOrderResponse.getSettle().equals(Constants.OVERDUE_SETTLE_YES)) {
				overdueOrderResponse.setUnpaidPenalty(0L);
				overdueOrderResponse.setOverdueDay(0);
			}else {
				OverdueRepaymentRecordEntity overdueRepaymentRecordEntity = new OverdueRepaymentRecordEntity();
				overdueRepaymentRecordEntity.setOrderId(overdueOrderResponse.getPid());
				overdueRepaymentRecordEntity.setStatus(Constants.DATA_STATUS_NOL);
				Message<List<OverdueRepaymentRecordEntity>> recordMsg = repaymentRecordCore.selectByRepaymentRecord(overdueRepaymentRecordEntity);
				Integer days = 0;
				if (ChkUtil.isEmpty(recordMsg.getMessage())) {
					days = DateUtil.diffDays(overdueOrderResponse.getLoanTime(),new Date())-overdueOrderResponse.getApprovalTerm();
				}else{
					days = DateUtil.diffDays(recordMsg.getMessage().get(0).getRepaymentTime(),new Date());
					unpaidPenaltyHistory += recordMsg.getMessage().get(0).getUnpaidPenalty();
				}
				Long unpaidPrincipal = overdueOrderResponse.getUnpaidPrincipal();//未还本金
				Long unpaidInterest = overdueOrderResponse.getUnpaidInterest();//未还利息
				Long unpaidService = overdueOrderResponse.getUnpaidService();//未还服务费
				Long unpaidPenalty = overdueOrderResponse.getUnpaidPrincipal()*days*overdueOrderResponse.getPenaltyFee()/10000+unpaidPenaltyHistory;
				overdueOrderResponse.setMoneyCount(unpaidPrincipal+unpaidInterest+unpaidService+unpaidPenalty); //未还款总额
				overdueOrderResponse.setUnpaidPenalty(unpaidPenalty);
				overdueOrderResponse.setOverdueDay(DateUtil.diffDays(overdueOrderResponse.getLoanTime(),new Date())-overdueOrderResponse.getApprovalTerm());
			}
			
			msg.setMessage(overdueOrderResponse);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"overdue",ReturnMsg.FAILED_CURRENCY);
		}
    	return msg;
    };
	/**
	 * 逾期还款
	 */
    public Message submitRepaymentRecord(OverdueOrderRepaymentForm overdueOrderRepaymentForm){
    	try {
			Message<OverdueOrderEntity> msg = this.queryOrderByPid(overdueOrderRepaymentForm.getId()+"");
			OverdueOrderEntity orderEntity = msg.getMessage();
			if (orderEntity.getSettle().equals(Constants.OVERDUE_SETTLE_YES)) {
				return new Message<>(MessageType.MSG_ERROR,"overdue","已结清订单不可重复还款");
			}
			//本次还款总额
			Long paymentCount = (long) (Double.parseDouble(overdueOrderRepaymentForm.getRepaymentPrice())*100);
			if (paymentCount<=0) {
				return new Message<>(MessageType.MSG_ERROR,"overdue","还款金额不正确");
			}
			Long approvalAmount = orderEntity.getApprovalAmount();//放款金额
			Long unpaidPrincipal = orderEntity.getUnpaidPrincipal();//未还本金
			Long returnedPrincipal = orderEntity.getReturnedPrincipal();//已还本金
			Long unpaidInterest = orderEntity.getUnpaidInterest();//未还利息
			Long returnedInterest = orderEntity.getReturnedInterest();//已还利息
			Long unpaidService = orderEntity.getUnpaidService();//未还服务费
			Long returnedService = orderEntity.getReturnedService();//已还服务费
			Long returnedPenalty = orderEntity.getReturnedPenalty();//已还罚息
			//未还罚息
			Date newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(overdueOrderRepaymentForm.getRepaymentTime());
			
			OverdueRepaymentRecordEntity overdueRepaymentRecordEntity = new OverdueRepaymentRecordEntity();
			overdueRepaymentRecordEntity.setOrderId(overdueOrderRepaymentForm.getId());
			overdueRepaymentRecordEntity.setStatus(Constants.DATA_STATUS_NOL);
			Message<List<OverdueRepaymentRecordEntity>> recordMsg = repaymentRecordCore.selectByRepaymentRecord(overdueRepaymentRecordEntity);
			Integer days = 0;
			Long unpaidPenaltyHistory = 0L;
			if (ChkUtil.isEmpty(recordMsg.getMessage())) {
				days = DateUtil.diffDays(orderEntity.getLoanTime(),newDate)-orderEntity.getApprovalTerm();
			}else{
				days = DateUtil.diffDays(recordMsg.getMessage().get(0).getRepaymentTime(),newDate);
				unpaidPenaltyHistory += recordMsg.getMessage().get(0).getUnpaidPenalty();
			}
			if (days < 0) {
				return  new Message<>(MessageType.MSG_ERROR,"overdue","还款时间错误，请验证后重新提交！");
			}
			Long unpaidPenalty = unpaidPrincipal*days*orderEntity.getPenaltyFee()/10000+unpaidPenaltyHistory;
			if (paymentCount>(unpaidPrincipal+unpaidInterest+unpaidService+unpaidPenalty)) {
				return new Message<>(MessageType.MSG_ERROR,"overdue","还款金额大于应还金额，还款失败");
			}
			if (paymentCount>unpaidPenalty) {
				returnedPenalty +=unpaidPenalty;
				paymentCount -= unpaidPenalty;
				unpaidPenalty = 0L;
				if (paymentCount>unpaidService) {
					returnedService += unpaidService;
					paymentCount -= unpaidService;
					unpaidService = 0L;
					if (paymentCount > unpaidInterest) {
						returnedInterest += unpaidInterest;
						paymentCount -= unpaidInterest;
						unpaidInterest = 0L;
						unpaidPrincipal = unpaidPrincipal-paymentCount<0?0:unpaidPrincipal-paymentCount;
						returnedPrincipal += paymentCount;
					}else {
						returnedInterest += paymentCount ;
						unpaidInterest -= paymentCount;
					}
				}else {
					returnedService += paymentCount ;
					unpaidService -= paymentCount;
				}
			}else{
				returnedPenalty += paymentCount ;
				unpaidPenalty -= paymentCount;
			}
			//修改orderInfo表 
			AuditOrderInfoEntity orderInfo = new AuditOrderInfoEntity();
			orderInfo.setPid(orderEntity.getOrderInfoId());
			orderInfo.setState("81");
			OverdueOrderEntity overdueOrderEntity = new OverdueOrderEntity(); 
			if (unpaidPrincipal == 0) { //如果未还本金为0 则改为已结清状态
				orderInfo.setState("90");
				overdueOrderEntity.setSettle("1");
				overdueOrderEntity.setSettleTime(newDate);
			}
			auditOrderInfoService.updateOrderInfo(orderInfo);
			//修改逾期表
			overdueOrderEntity.setPid(orderEntity.getPid());
			overdueOrderEntity.setUnpaidPrincipal(unpaidPrincipal);//未还本金
			overdueOrderEntity.setReturnedPrincipal(returnedPrincipal);//已还本金
			overdueOrderEntity.setUnpaidInterest(unpaidInterest);//未还利息
			overdueOrderEntity.setReturnedInterest(returnedInterest);//已还利息
			overdueOrderEntity.setUnpaidService(unpaidService);//未还服务费
			overdueOrderEntity.setReturnedService(returnedService);//已还服务费
			overdueOrderEntity.setReturnedPenalty(returnedPenalty);//已还罚息
			overdueOrderEntity.setUpdateAt(new Date());
			overdueOrderEntity.setUpdateBy(overdueOrderRepaymentForm.getUserId());
			this.updateOrder(overdueOrderEntity);
			
			//新增逾期还款记录
			OverdueRepaymentRecordEntity repaymentRecordEntity = new OverdueRepaymentRecordEntity();
			repaymentRecordEntity.setUnpaidPrincipal(unpaidPrincipal);//未还本金
			repaymentRecordEntity.setReturnedPrincipal(returnedPrincipal);//已还本金
			repaymentRecordEntity.setUnpaidInterest(unpaidInterest);//未还利息
			repaymentRecordEntity.setReturnedInterest(returnedInterest);//已还利息
			repaymentRecordEntity.setUnpaidService(unpaidService);//未还服务费
			repaymentRecordEntity.setReturnedService(returnedService);//已还服务费
			repaymentRecordEntity.setReturnedPenalty(returnedPenalty);//已还罚息
			repaymentRecordEntity.setUnpaidPenalty(unpaidPenalty);//未还罚息
			repaymentRecordEntity.setRepaymentTime(newDate);
			repaymentRecordEntity.setOrderId(orderEntity.getPid());
			repaymentRecordEntity.setRemark("逾期第"+(DateUtil.diffDays(orderEntity.getLoanTime(),newDate)-orderEntity.getApprovalTerm())+"天");
			repaymentRecordEntity.setOverdueDay(DateUtil.diffDays(orderEntity.getLoanTime(),newDate)-orderEntity.getApprovalTerm());
			repaymentRecordEntity.setPenaltyFee(orderEntity.getPenaltyFee());
			repaymentRecordEntity.setStatus(Constants.DATA_STATUS_NOL);
			repaymentRecordEntity.setCreateBy(overdueOrderRepaymentForm.getUserId());
			repaymentRecordEntity.setUpdateBy(overdueOrderRepaymentForm.getUserId());
			repaymentRecordEntity.setCreateAt(new Date());
			repaymentRecordEntity.setUpdateAt(new Date());
			repaymentRecordCore.insertRepaymentRecord(repaymentRecordEntity);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"overdue",ReturnMsg.FAILED_CURRENCY);
		}
    	return new Message<>(MessageType.MSG_SUCCESS,"overdue",ReturnMsg.SSUCCESS_CURRENCY);
    };
	/**
	 *查询逾期还款记录 
	 */
    public Message searchRepaymenList(OverdueOrderRecordForm overdueOrderRecordForm){
    	try {
			Message<List<OverdueRepaymentRecordEntity>> listMsg = repaymentRecordCore.queryListByRecordForm(overdueOrderRecordForm);
			Message<Integer> countMsg = repaymentRecordCore.queryCountByRecordForm(overdueOrderRecordForm);
			PageResponse<List<OverdueRepaymentRecordEntity>> response = new PageResponse<>();
			response.setTotal(countMsg.getMessage());
			response.setPageNo(overdueOrderRecordForm.getPageNo());
			response.setPageSize(overdueOrderRecordForm.getPageSize());
			response.setData(listMsg.getMessage());
			return new Message<>(MessageType.MSG_SUCCESS,"overdue",response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"overdue",ReturnMsg.FAILED_CURRENCY);
		}
    };
	
    public Message downLoadOrder(OverdueOrderForm overdueOrderForm){
    
    	return overdueOrderCore.queryDownListByOrderForm(overdueOrderForm);
    };

}

