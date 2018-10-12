package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeSigningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * 仓单签约模块
 */
@Api(value = "签约管理模块", tags = "签约管理模块")
@RestController
@RequestMapping("/pledge/signing")
public class PledgeSigningController {

    @Autowired
    private PledgeSigningService pledgeSigningService;

    @ApiOperation(value="签约后端列表", notes="签约前端列表")
    @RequestMapping(value ="/selectAdminSigningList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectAdminSigningList(@RequestBody PledgeStockFrom pledgeStockFrom){
        try{
            return pledgeSigningService.selectAdminSigningList(pledgeStockFrom);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"pledgeStockInfo",e.getMessage());
        }
    }

    @ApiOperation(value="签约前端列表", notes="签约前端列表")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/selectWebSigningList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectWebSigningList(@RequestBody PledgeStockFrom pledgeStockFrom, Principal principa){
        try{
            pledgeStockFrom.setOwnerId(getUserName(principa));
            return pledgeSigningService.selectWebSigningList(pledgeStockFrom);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"pledgeStockInfo",e.getMessage());
        }
    }

    @ApiOperation(value="签约后端详情", notes="签约前端详情")
    @RequestMapping(value ="/selectAdminSigningDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectAdminSigningDetail(@ApiParam(required = true, name = "id", value = "id")@RequestParam Long id, Principal principa){
        try{
            return pledgeSigningService.selectAdminSigningDetail(id);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"pledgeStockInfo",e.getMessage());
        }
    }


    @ApiOperation(value="签约前端详情", notes="签约前端详情")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/selectWebSigningDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectWebSigningDetail(@ApiParam(required = true, name = "id", value = "id")@RequestParam Long id, Principal principa){
        try{
            return pledgeSigningService.selectWebSigningDetail(id);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"pledgeStockInfo",e.getMessage());
        }
    }


    @ApiOperation(value="前端提交签约", notes="前端提交签约")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitWebSigning", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitWebSigning(@ApiParam(required = true, name = "id", value = "id")@RequestParam Long id, Principal principa){
        try{
            return pledgeSigningService.submitWebSigning(id);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"pledgeStockInfo",e.getMessage());
        }
    }

    @ApiOperation(value="后端提交签约", notes="后端提交签约")
    @RequestMapping(value ="/submitAdminSigning", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitAdminSigning(@ApiParam(required = true, name = "id", value = "id")@RequestParam Long id){
        try{
            return pledgeSigningService.submitAdminSigning(id);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"pledgeStockInfo",e.getMessage());
        }
    }

    @ApiOperation(value="查看纸质合同", notes="查看纸质合同")
    @RequestMapping(value ="/selectPaperContract", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectPaperContract(@ApiParam(required = true, name = "id", value = "id")@RequestParam Long id){
        try{
            return pledgeSigningService.selectPaperContract(id);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"pledgeStockInfo",e.getMessage());
        }
    }

    @ApiOperation(value="查看是否上传纸质合同", notes="查看是否上传纸质合同")
    @RequestMapping(value ="/selectContractMoreType", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectContractMoreType(@ApiParam(required = true, name = "id", value = "id")@RequestParam Long id){
        try{
            return pledgeSigningService.selectContractMoreType(id);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"pledgeStockInfo",e.getMessage());
        }
    }



    private String getUserName(Principal principa){
        return principa.getName();
    }
}
