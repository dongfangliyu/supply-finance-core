package cn.fintecher.supply.finance.loan.manager.pm.bff.margin.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.PayMarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;

/**
 * @author lss
 * @date 2018/8/28
 */
@FeignClient(name = "loan-manager-service")
public interface FCMarginService {
	
	@ResponseBody
    @RequestMapping(value = "/margin/marginService/searchListMargin", method = RequestMethod.POST)
    Message searchListMargin(@RequestBody MarginForm marginFormm);

	@ResponseBody
    @RequestMapping(value = "/margin/marginService/payMargin", method = RequestMethod.POST)
	Message payMargin(PayMarginForm payMarginForm);
	
	@ResponseBody
	@RequestMapping(value = "/margin/marginService/searchMarginDetail", method = RequestMethod.POST)
	Message searchMarginDetail(SearchMarginDetailForm searchMarginDetailForm);

}
