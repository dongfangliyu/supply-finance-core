package cn.fintecher.supply.finance.loan.manager.service.margin.feign;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;

/**
 * @author lss
 * @date 2018/8/28
 */
@FeignClient(name = "loan-manager-core")
public interface FCMarginCore {

	@ResponseBody
    @RequestMapping(value ="/margin/marginCore/searchMarginListCount", method = RequestMethod.POST)
	int searchMarginListCount(MarginForm marginForm);
	
	@ResponseBody
    @RequestMapping(value ="/margin/marginCore/searchMarginList", method = RequestMethod.POST)
	List<ConfirmingStockInfoResponse> searchMarginList(MarginForm marginForm);
	
	@ResponseBody
    @RequestMapping(value ="/margin/marginCore/payMargin", method = RequestMethod.GET)
	void saveAuditTaskEntryEntity(AuditTaskEntryEntity auditTaskEntryEntity);

}
