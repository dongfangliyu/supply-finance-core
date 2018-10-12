package cn.fintecher.supply.finance.loan.manager.pm.bff.company.controller;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CustomerEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.CompanyEnterpriseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.CompanyBlacklistService;

import java.util.List;

/**
 * 黑名单管理
 */
@Api(value = "黑名单管理", tags = "黑名单管理")
@RestController
@RequestMapping("/company/blacklist")
public class CompanyBlacklistController {

    @Autowired
    private CompanyBlacklistService companyBlacklistService;

    @Autowired
    private CompanyEnterpriseService companyEnterpriseService;


    @ApiOperation(value="查询客户管理列表 ", notes="查询客户管理列表")
    @RequestMapping(value ="/selectCustomerBlackList", method = RequestMethod.POST)
    public Message<PageResponse<List<CustomerEntity>>> selectCustomerBlackList(@RequestBody BlackListFrom blackListFrom){
        try {
            return  companyEnterpriseService.selectCustomerBlackList(blackListFrom);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"company",e.getMessage());
        }
    }

    @ApiOperation(value="移除黑名单 ", notes="移除黑名单")
    @RequestMapping(value ="/deleteBlackList", method = RequestMethod.GET)
    public Message deleteBlackList(@ApiParam(required = true, name = "id", value = "客户id") @RequestParam Long id,@ApiParam(required = true, name = "causationInfo", value = "移除原因") @RequestParam String causationInfo){
        try {
            return  companyEnterpriseService.deleteBlackList(id, causationInfo);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"company",e.getMessage());
        }
    }

    @ApiOperation(value="添加黑名单 ", notes="添加黑名单")
    @RequestMapping(value ="/submitBlackList", method = RequestMethod.GET)
    public Message submitBlackList(@ApiParam(required = true, name = "id", value = "客户id") @RequestParam Long id,@ApiParam(required = true, name = "causationInfo", value = "添加原因") @RequestParam String causationInfo){
        try {
            return  companyEnterpriseService.submitBlackList(id, causationInfo);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"company",e.getMessage());
        }
    }


    @ApiOperation(value="查询记录列表 ", notes="查询记录列表")
    @RequestMapping(value ="/selectBlackList", method = RequestMethod.POST)
    public Message<PageResponse<List<CompanyBlacklistEntity>>> selectBlackList(@RequestBody BlackListFrom blackListFrom){
        try {
            return  companyBlacklistService.selectBlackList(blackListFrom);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"company",e.getMessage());
        }
    }

   

}
