package cn.fintecher.supply.finance.loan.manager.service.margin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.PayMarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.service.margin.service.MarginService;

/**
 * @author lss
 * @date 2018/8/28
 */
@RestController
@RequestMapping("/margin/marginService")
public class MarginController {
	
	@Autowired
    private MarginService marginService;

    @ResponseBody
    @RequestMapping(value = "/searchListMargin", method = RequestMethod.POST)
    public Message searchListMargin(@RequestBody MarginForm marginForm){
        return marginService.searchListMargin(marginForm);
    }
    
    @ResponseBody
    @RequestMapping(value = "/payMargin", method = RequestMethod.POST)
    public Message payMargin(@RequestBody PayMarginForm payMarginForm ){
    	return marginService.payMargin(payMarginForm);
    }
    
    @ResponseBody
    @RequestMapping(value = "/searchMarginDetail", method = RequestMethod.POST)
    public Message searchMarginDetail(@RequestBody SearchMarginDetailForm searchMarginDetailForm ){
    	return marginService.searchMarginDetail(searchMarginDetailForm);
    }

}
