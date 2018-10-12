package cn.fintecher.supply.finance.loan.manager.pm.bff.company.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CustomerEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.response.CustomerResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.CompanyEnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:14
 */
@RestController
@RequestMapping("/company/enterprise")
@Api(tags = "企业信息")
public class CompanyEnterpriseController {

    @Autowired
    private CompanyEnterpriseService companyEnterpriseService;

    /**
     * 查询企业信息
     * @return
     */
    @ApiOperation(value="查询企业信息 ", notes="查询企业信息")
    @RequestMapping(value ="/selectCompanyEnterprise", method = RequestMethod.GET)
    public Message selectRegisteCurrentStep(Principal principal){
        String name = principal.getName();
//        String name = "15146987562";
        Message message = companyEnterpriseService.selectCompanyEnterprise(name);
        return message;
    }



    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */


    @ApiOperation(value="查询客户管理列表 ", notes="查询客户管理列表")
    @RequestMapping(value ="/selectCustomerManagementList", method = RequestMethod.POST)
    public Message<PageResponse<List<CustomerEntity>>> selectCustomerManagementList(@RequestBody CustomerFrom customerFrom){
       try {
           return  companyEnterpriseService.selectCustomerManagementList(customerFrom);
       }catch (Exception e){
           return new Message(MessageType.MSG_ERROR,"company",e.getMessage());
       }
    }

    @ApiOperation(value="查询客户管理详情 ", notes="查询客户管理详情")
    @RequestMapping(value ="/selectCustomerManagementDetail", method = RequestMethod.GET)
    public Message<CustomerResponse> selectCustomerManagementDetail(@ApiParam(required = true, name = "id", value = "客户id")@RequestParam String id){
        try {
            return  companyEnterpriseService.selectCustomerManagementDetail(id);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"company",e.getMessage());
        }
    }


}
