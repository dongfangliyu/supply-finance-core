package cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockInfoSubmit;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.service.CommodityStockInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 仓单入库管理接口
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Api(value = "仓单入库管理接口", tags = "入库管理接口")
@RestController
@RequestMapping("/commodity/stockInfo")
public class CommodityStockInfoController {

    @Autowired
    private CommodityStockInfoService commodityStockInfoService;
    

	@ApiOperation(value="客户端查看入库列表", notes="客户端查看入库列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchStockList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PagedResponse<List<CommodityStockListResponse>>> searchStockList(@RequestBody CommodityStockForm commdityStockForm,Principal principa){
		Message<PagedResponse<List<CommodityStockListResponse>>> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        
		try {
			commdityStockForm.setUserName(principa.getName());
        	Message<PagedResponse<List<CommodityStockListResponse>>> message =commodityStockInfoService.searchStockList(commdityStockForm);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="客户端提交入库信息", notes="客户端提交入库信息")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitStock", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<Object> submitStock(@Valid @RequestBody CommodityStockInfoSubmit commodityStockInfoSubmit,Principal principa){
		Message<Object> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
        	commodityStockInfoSubmit.setUserName(principa.getName());
            Message<Object> message =commodityStockInfoService.submitStock(commodityStockInfoSubmit);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="客户端查看入库详情", notes="客户端查看入库详情")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchStockDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<CommodityStockInfoResponse> searchStockDetail(@ApiParam(required = true, name = "id", value = "记录id")@RequestParam("id") Long id,Principal principa){
        Message<CommodityStockInfoResponse> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
            Message<CommodityStockInfoResponse> message =commodityStockInfoService.searchStockDetail(id,principa.getName());
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }


    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */


    @ApiOperation(value="管理端入库列表", notes="管理端入库列表")
    @RequestMapping(value ="/selectStockList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PagedResponse<List<CommodityStockListResponse>>> selectStockList(@RequestBody CommodityStockForm commdityStockForm){
        try {
            return commodityStockInfoService.selectStockList(commdityStockForm);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }

    @ApiOperation(value="管理端提交入库", notes="管理端提交入库")
    @RequestMapping(value ="/submitState", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitState(@ApiParam(required = true, name = "id", value = "记录id")@RequestParam("id") Long id,@ApiParam(required = true,name="time",value = "入库时间")@RequestParam("time")String time){
        try {
            return commodityStockInfoService.submitState(id,time);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }

    @ApiOperation(value="管理端入库查看详情", notes="管理端入库查看详情")
    @RequestMapping(value ="/selectCommodityStockInfo", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectCommodityStockInfo(@ApiParam(required = true, name = "pid", value = "记录id")@RequestParam("pid") Long pid){
        try {
            return commodityStockInfoService.selectCommodityStockInfo(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }



}
