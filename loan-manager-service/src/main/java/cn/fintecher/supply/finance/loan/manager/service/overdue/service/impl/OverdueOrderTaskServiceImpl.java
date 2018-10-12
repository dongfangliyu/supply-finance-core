package cn.fintecher.supply.finance.loan.manager.service.overdue.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.overdue.core.OverdueAuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.overdue.core.OverdueAuditTaskEntryCore;
import cn.fintecher.supply.finance.loan.manager.service.overdue.core.OverdueOrderCore;
import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueOrderTaskService;

/**
 * 逾期表定时任务
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:21
 */
@Service("overdueOrderTaskService")
public class OverdueOrderTaskServiceImpl implements OverdueOrderTaskService {

	@Autowired
    private OverdueOrderCore overdueOrderCore;
	
	@Autowired
	private OverdueAuditOrderInfoCore orderInfoCore;
	
	@Autowired
	private FCCompanyEnterpriseCore fccompanyEnterpriseCore;
	
	@Autowired
	private OverdueAuditTaskEntryCore entryCore;

	/**
	 * 所有金额统一乘以100存储
	 */
	@Override
	public void createOverdueOrder() {
		try {
			//获取所有的逾期订单
			List<AuditOrderInfoEntity> orderInfoList = getOverdueOrderInfo();
			for (AuditOrderInfoEntity orderInfo : orderInfoList) {
				OverdueOrderEntity order = formatOrderInfo(orderInfo);
				if (ChkUtil.isEmpty(order) || ChkUtil.isEmpty(order.getEnterpriseId()) || ChkUtil.isEmpty(order.getSupplierId())) {
					continue;
				}
				//查询插入核心企业及供应商联系人信息
				CompanyOperationEntity enterOperation = getOperation(order.getEnterpriseId());
				CompanyOperationEntity suppOperation = getOperation(order.getSupplierId());
				if (ChkUtil.isEmpty(enterOperation) || ChkUtil.isEmpty(suppOperation)) {
					continue;
				}
				order.setEnterpriseMan(enterOperation.getName());
				order.setEnterpriseMobile(enterOperation.getPhone());
				order.setSupplierMan(suppOperation.getName());
				order.setSupplierMobile(suppOperation.getPhone());
				
				order.setPenaltyFee(Constants.PENALTY_FEE);//罚息费率
				if (ChkUtil.isEmpty(orderInfo.getServiceFee())) {
					continue;
				}
				//平台服务费
				Long unpaidService = (long) (orderInfo.getServiceFee()*Double.parseDouble(orderInfo.getApprovalAmount())*orderInfo.getApprovalTerm()/360L);
				order.setServicePrice(unpaidService);
				//还款总额
				Message<Integer> paymentMsg = entryCore.queryPaymentCountByOrderInfoId(orderInfo.getPid()+"");
				Long paymentCount =paymentMsg.getMessage() == null?0:paymentMsg.getMessage()*100L;
				Long returnedPrincipal = 0L;
				Long unpaidPrincipal = (long) (Double.parseDouble(orderInfo.getCertificateAmount())*100L);
				Long returnedInterest = 0L;
				Long unpaidInterest = (long) (orderInfo.getInterestRate()*Double.parseDouble(orderInfo.getApprovalAmount())*orderInfo.getApprovalTerm()/360L);
				Long returnedService = 0L;
				order.setInterestPrice(unpaidInterest);
				if (paymentCount>unpaidService) {
					returnedService=unpaidService;
					unpaidService = 0L;
					paymentCount -= returnedService;
					if (paymentCount > unpaidInterest) {
						returnedInterest = unpaidInterest;
						unpaidInterest = 0L;
						paymentCount -= returnedInterest;
						unpaidPrincipal = unpaidPrincipal-paymentCount<0?0:unpaidPrincipal-paymentCount;
						returnedPrincipal = paymentCount;
					}else {
						returnedInterest = paymentCount ;
						unpaidInterest -= paymentCount;
					}
				}else {
					returnedService = paymentCount ;
					unpaidService -= paymentCount;
				}
				
				order.setUnpaidService(unpaidService);//未还平台服务费
				order.setReturnedService(returnedService);//已还平台服务费
				order.setReturnedPrincipal(returnedPrincipal);//已还本金
				order.setUnpaidPrincipal(unpaidPrincipal);//未还本金
				order.setReturnedInterest(returnedInterest);//已还利息
				order.setUnpaidInterest(unpaidInterest);//未还利息
				order.setReturnedPenalty(0L);//已付罚息
				order.setLoanCycle(getCycle(orderInfo.getLoanTime(), orderInfo.getShouldTime()));
				order.setSettle("0");//未结清状态
				overdueOrderCore.insertOrder(order);
				AuditOrderInfoEntity orderInfoEntity = new AuditOrderInfoEntity();
				orderInfoEntity.setPid(orderInfo.getPid());
				orderInfoEntity.setOverdueType("1");
				orderInfoCore.updateOrderInfo(orderInfoEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	/**
	 * 获取所有的逾期订单
	 * @return
	 */
	private List<AuditOrderInfoEntity> getOverdueOrderInfo(){
		Message<List<AuditOrderInfoEntity>> msg = orderInfoCore.getOverdueOrderInfo();
		return msg.getMessage();
	}
    
	/**
	 * orderInfo相同字段赋值order 
	 */
	private OverdueOrderEntity formatOrderInfo(AuditOrderInfoEntity orderInfo){
		OverdueOrderEntity order = new OverdueOrderEntity();
		order.setAccountNo(orderInfo.getAccountno());
		order.setApprovalAmount((long) (Double.parseDouble(orderInfo.getApprovalAmount())*100L));
		order.setApprovalTerm(orderInfo.getApprovalTerm());
		order.setCertificateAmount(orderInfo.getCertificateAmount()==null?null: String.valueOf((long)(Double.parseDouble(orderInfo.getCertificateAmount())*100L)));
		order.setContractNo(orderInfo.getContractNo());
		order.setEnterpriseId(orderInfo.getEnterpriseId());
		order.setEnterpriseName(orderInfo.getEnterpriseName());
		order.setInterestRate(orderInfo.getInterestRate());
		order.setLoanTime(orderInfo.getLoanTime());
		order.setOrderInfoId(orderInfo.getPid());
		order.setServiceFee(orderInfo.getServiceFee());
		order.setSupplierId(orderInfo.getSupplierId());
		order.setSupplierName(orderInfo.getSupplierName());
		order.setCreateAt(new Date());
		order.setUpdateAt(new Date());
		order.setStatus(Constants.DATA_STATUS_NOL);
		return order;
	}
	/**
	 * 查询企业联系人信息
	 */
	private CompanyOperationEntity getOperation(Long pid){
		//查询供应商联系人信息
		List<CompanyOperationEntity> suppOperationList = fccompanyEnterpriseCore.searchCompanyOperation(pid);
		if (suppOperationList.size() == 1) {
			return suppOperationList.get(0);
		}else {
			for (int i = 0; i < suppOperationList.size(); i++) {
				if ("agent".equals(suppOperationList.get(i).getType())) {
					return suppOperationList.get(i);
				}
				if (i==suppOperationList.size()-1) {
					return suppOperationList.get(0);
				}
			}
		}
		return new CompanyOperationEntity();
	}
	
	private String getCycle(Date startTime,Date endTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String start = sdf.format(startTime);
		String end = sdf.format(endTime);
		return start+"-"+end;
	}
  
}

