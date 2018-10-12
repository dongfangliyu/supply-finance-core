package cn.fintecher.supply.finance.loan.manager.pm.bff.overdue.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRepaymentForm;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.pm.bff.overdue.core.OverdueOrderCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.overdue.service.OverdueOrderService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.util.ExcelUtil;

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
	
    public Message searchOrderList(OverdueOrderForm overdueOrderForm){
    	return overdueOrderCore.searchOrderList(overdueOrderForm);
    };
	
    public Message searchOrderDetail(Long id){
    	return overdueOrderCore.searchOrderDetail(id);
    };
	
    public Message submitRepaymentRecord(OverdueOrderRepaymentForm overdueOrderRepaymentForm){
    	return overdueOrderCore.submitRepaymentRecord(overdueOrderRepaymentForm);
    };
	
    public Message searchRepaymenList(OverdueOrderRecordForm overdueOrderRecordForm){
    	return overdueOrderCore.searchRepaymenList(overdueOrderRecordForm);
    };
	
    public XSSFWorkbook downLoadOrder(OverdueOrderForm overdueOrderForm ){
    	try {
			Message msg = overdueOrderCore.downLoadOrder(overdueOrderForm);
			List<OverdueOrderEntity> list = JSONUtil.toList(msg.getMessage(), OverdueOrderEntity.class);
			if (ChkUtil.isEmpty(list)) {
				return null;
			}
			if (list != null && list.size() > 0){
				String sheetName = "企业逾期列表";//sheet名
				String []title = new String[]{"保理合同编号","商务合同编号","供应商","联系人","核心企业",
						"联系人","放款金额（元）","期限（天）","放款周期","利率(%/年）","利息（元）","平台服务费率(%/年）",
						"平台服务费（元）","逾期天数","罚息费率(%/日）","已还本金（元）","已付利息（元）","已付罚息（元）",
						"未还本金（元）","未付利息（元）","未付罚息（元）","状态","结清时间"};//标题
				 String [][]values = new String[list.size()][];
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        for(int i=0;i<list.size();i++){
			        	OverdueOrderEntity order = list.get(i);
			            values[i] = new String[title.length];
			            values[i][0] = order.getAccountNo()== null?"":order.getAccountNo();//保理合同编号
			            values[i][1] = order.getContractNo()== null?"":order.getContractNo();//商务合同编号
			            values[i][2] = order.getSupplierName()== null?"":order.getSupplierName();//供应商
			            values[i][3] = order.getSupplierMan()== null?"":order.getSupplierMan()+"-"+order.getSupplierMobile()== null?"":order.getSupplierMobile();//联系人
			            values[i][4] = order.getEnterpriseName()== null?"":order.getEnterpriseName();//核心企业
			            values[i][5] = order.getEnterpriseMan()== null?"":order.getEnterpriseMan()+"-"+order.getEnterpriseMobile()== null?"":order.getEnterpriseMobile();//联系人
			            values[i][6] = order.getApprovalAmount() == null?"":transformMoney(order.getApprovalAmount());//放款金额（元）
			            values[i][7] = order.getApprovalTerm() == null?"":order.getApprovalTerm()+"";//期限（天）
			            values[i][8] = order.getLoanCycle()== null?"":order.getLoanCycle();//放款周期
			            values[i][9] = order.getInterestRate()==null?"":(double)order.getInterestRate()/100+"";//利率(%/年）
			            values[i][10] = transformMoney(order.getInterestPrice());//利息（元）
			            values[i][11] = order.getServiceFee()==null?"":(double)order.getServiceFee()/100+"";//平台服务费率(%/年）
			            values[i][12] = transformMoney(order.getServicePrice());//平台服务费（元）
			            Integer days = DateUtil.diffDays(order.getLoanTime(),new Date())-order.getApprovalTerm()+1;
			            values[i][13] = days+"";//逾期天数
			            values[i][14] = order.getPenaltyFee()==null?"":(double)order.getPenaltyFee()/100+"";//罚息费率(%/日）
			            values[i][15] = transformMoney(order.getReturnedPrincipal());//已还本金（元）
			            values[i][16] = transformMoney(order.getReturnedInterest());//已付利息（元）
			            values[i][17] = transformMoney(order.getReturnedPenalty());//已付罚息（元）
			            values[i][18] = transformMoney(order.getUnpaidPrincipal());//未还本金（元）
			            values[i][19] = transformMoney(order.getUnpaidInterest());//未付利息（元）
			            Long unpaidPenalty = order.getUnpaidPrincipal()*days*order.getPenaltyFee()/10000-order.getReturnedPenalty();
			            values[i][20] = transformMoney(unpaidPenalty);//未付罚息（元）
			            if ("1".equals(order.getSettle())) {
			            	values[i][21] = "已结清";//状态
						}else if ("0".equals(order.getSettle())) {
							values[i][21] = "未结清";//状态
						}
			            values[i][22] =  order.getSettleTime()==null?"":sdf.format( order.getSettleTime());//结清时间
			            
			            
			        }
			        
			        return ExcelUtil.getXSSFWorkbook(sheetName, title, values, null);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    };
    
    /**
     * 乘以100的long类型，转化为正确金额的String类型
     * @return
     */
    private String transformMoney(Long money){
    	if (ChkUtil.isEmpty(money)) {
    		return "";
		}
    	double  du = money;
    	return du/100+"";
    }

}
