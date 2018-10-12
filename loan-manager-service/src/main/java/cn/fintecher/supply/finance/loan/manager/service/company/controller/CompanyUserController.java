package cn.fintecher.supply.finance.loan.manager.service.company.controller;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author WuTianJuan
 * @Date Created in 9:55 2018/7/4
 */

@RestController
@RequestMapping("/company/user")
public class CompanyUserController {
    @Autowired
    private CompanyUserService companyUserService;

    @ResponseBody
    @RequestMapping(value ="/findCompanyUserByName", method = RequestMethod.GET)
    public CompanyUserEntity findCompanyUserByName(@RequestParam(value = "userName")String userName){
        return companyUserService.findCompanyUserByName(userName);
    }
}
