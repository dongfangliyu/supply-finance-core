package cn.fintecher.supply.finance.loan.manager.pm.bff.margin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.PayMarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.margin.service.MarginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lss
 * @date 2018/8/28
 */
@RestController
@RequestMapping("/margin")
@Api(tags = "仓单质押保证金")
public class marginController {
	
	@Autowired
    private MarginService marginService;
	
	/**
	 * 查询保证金列表
	 */
	@ApiOperation(value="查询保证金列表 ", notes="查询保证金列表")
    @RequestMapping(value ="/searchListMargin", method = RequestMethod.POST)
    public Message searchListMargin(@RequestBody MarginForm marginForm){
        Message message = marginService.searchListMargin(marginForm);
        return message;
    }
	
	/**
	 * 缴纳保证金
	 */
	@ApiOperation(value="缴纳保证金 ", notes="缴纳保证金")
    @RequestMapping(value ="/payMargin", method = RequestMethod.POST)
    public Message payMargin(@RequestBody PayMarginForm payMarginForm){
        Message message = marginService.payMargin(payMarginForm);
        return message;
    }
	
	/**
	 * 查看记录
	 */
	@ApiOperation(value="查看记录 ", notes="查看记录")
    @RequestMapping(value ="/searchMarginDetail", method = RequestMethod.POST)
    public Message searchMarginDetail(@RequestBody SearchMarginDetailForm searchMarginDetailForm){
        Message message = marginService.searchMarginDetail(searchMarginDetailForm);
        return message;
    }
	
	
	
	

}
