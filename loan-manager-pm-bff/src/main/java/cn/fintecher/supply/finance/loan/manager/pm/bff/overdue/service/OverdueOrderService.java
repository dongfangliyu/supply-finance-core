package cn.fintecher.supply.finance.loan.manager.pm.bff.overdue.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRepaymentForm;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
public interface OverdueOrderService {    
	
    public Message searchOrderList(OverdueOrderForm overdueOrderForm);
	
    public Message searchOrderDetail(Long id);
	
    public Message submitRepaymentRecord(OverdueOrderRepaymentForm overdueOrderRepaymentForm);
	
    public Message searchRepaymenList(OverdueOrderRecordForm overdueOrderRecordForm);
	
    public XSSFWorkbook downLoadOrder(OverdueOrderForm overdueOrderForm);
   

}
