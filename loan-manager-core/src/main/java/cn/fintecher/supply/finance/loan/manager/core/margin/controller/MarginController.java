package cn.fintecher.supply.finance.loan.manager.core.margin.controller;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;
import cn.fintecher.supply.finance.loan.manager.core.margin.service.MarginService;

/**
 * @author lss
 * @date 2018/8/28
 */
@RestController
@RequestMapping("/margin/marginCore")
public class MarginController {
	
	@Autowired
    private MarginService marginService;

    @ResponseBody
    @RequestMapping(value ="/searchMarginListCount", method = RequestMethod.POST)
    public int searchProductListCount(@RequestBody MarginForm marginForm){
        return marginService.searchMarginListCount(marginForm);
    }
    
    @ResponseBody
    @RequestMapping(value ="/searchMarginList", method = RequestMethod.POST)
    public List<ConfirmingStockInfoResponse> searchMarginList(@RequestBody MarginForm marginForm){
        return marginService.searchProductList(marginForm);
    }
    
    @ResponseBody
    @RequestMapping(value ="/payMargin", method = RequestMethod.POST)
    public void payMargin(@RequestBody AuditTaskEntryEntity auditTaskEntryEntity){
    	marginService.saveAuditTaskEntryEntity(auditTaskEntryEntity);
    }

}
