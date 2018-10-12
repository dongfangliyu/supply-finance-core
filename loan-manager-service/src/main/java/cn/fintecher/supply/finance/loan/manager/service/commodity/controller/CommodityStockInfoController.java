package cn.fintecher.supply.finance.loan.manager.service.commodity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockInfoSubmit;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockListResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.commodity.service.CommodityStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 仓单客户端入库管理接口
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/commodity/stockInfo")
public class CommodityStockInfoController  extends BaseCompanyUserController{

    @Autowired
    private CommodityStockInfoService commodityStockInfoService;
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/insertStockInfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"commodity",null);
        try {
            Message message =commodityStockInfoService.insertStockInfo(commodityStockInfoEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/updateStockInfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"commodity",null);
        try {
            Message message =commodityStockInfoService.updateStockInfo(commodityStockInfoEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectByStockInfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<List<CommodityStockInfoEntity>> selectByStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity){
        Message<List<CommodityStockInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
            Message<List<CommodityStockInfoEntity>> message =commodityStockInfoService.selectByStockInfo(commodityStockInfoEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/queryStockInfoByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<CommodityStockInfoEntity> queryStockInfoByPid(@RequestParam("pid") String pid){
        Message<CommodityStockInfoEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
            Message<CommodityStockInfoEntity> message =commodityStockInfoService.queryStockInfoByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }

	@ApiOperation(value="查询入库列表", notes="")
    @RequestMapping(value ="/searchStockList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message<PagedResponse<List<CommodityStockListResponse>>> searchStockList(@RequestBody CommodityStockForm commdityStockForm){
        Message<PagedResponse<List<CommodityStockListResponse>>> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
        	CompanyUserEntity user = getCompanyUser(commdityStockForm.getUserName());
            Message<PagedResponse<List<CommodityStockListResponse>>> message =commodityStockInfoService.searchStockList(commdityStockForm,user);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="提交申请", notes="")
    @RequestMapping(value ="/submitStock", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message<Object> submitStock(@RequestBody CommodityStockInfoSubmit commodityStockInfoSubmit){
        Message<Object> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
        	CompanyUserEntity user = getCompanyUser(commodityStockInfoSubmit.getUserName());
            Message<Object> message =commodityStockInfoService.submitStock(commodityStockInfoSubmit,user);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/searchStockDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message<CommodityStockInfoResponse> searchStockDetail(@RequestParam("pid") Long pid,@RequestParam("userName")  String userName){
        Message<CommodityStockInfoResponse> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
        	CompanyUserEntity user = getCompanyUser(userName);
            Message<CommodityStockInfoResponse> message =commodityStockInfoService.searchStockDetail(pid,user);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }



    @ResponseBody
    @RequestMapping(value = "/selectStockList", method = RequestMethod.POST)
    public Message selectStockList(@RequestBody CommodityStockForm commodityStockForm){
        return commodityStockInfoService.selectStockList(commodityStockForm);
    }

    @ResponseBody
    @RequestMapping(value = "/submitState", method = RequestMethod.GET)
    public Message submitState(@RequestParam("pid") Long pid, @RequestParam("time")String time){
        return commodityStockInfoService.submitState(pid, time);
    }

    @ResponseBody
    @RequestMapping(value = "/selectCommodityStockInfo", method = RequestMethod.GET)
    public Message selectCommodityStockInfo(@RequestParam("pid") Long pid){
        return commodityStockInfoService.selectCommodityStockInfo(pid);
    }


}
