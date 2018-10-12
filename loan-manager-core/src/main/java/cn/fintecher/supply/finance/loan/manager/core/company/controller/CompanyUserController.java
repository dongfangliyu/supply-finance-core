package cn.fintecher.supply.finance.loan.manager.core.company.controller;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.core.company.service.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/3 0003下午 2:10
 */
@RestController
@RequestMapping("/company/userCore")
public class CompanyUserController {

    @Autowired
    private CompanyUserService companyUserService;

    @ResponseBody
    @RequestMapping(value ="/findCompanyUserByName", method = RequestMethod.GET)
    public CompanyUserEntity findCompanyUserByName(@RequestParam(value = "userName") String userName){
        return companyUserService.findCompanyUserByName(userName);
    }


    @ResponseBody
    @RequestMapping(value ="/findCompanyUserByNameAndEnterpriseType", method = RequestMethod.GET)
    public List<CompanyUserEntity> findCompanyUserByNameAndEnterpriseType(@RequestParam(value = "userName") String userName, @RequestParam(value = "enterpriseType") String enterpriseType){
        return companyUserService.findCompanyUserByNameAndEnterpriseType(userName,enterpriseType);
    }

}
