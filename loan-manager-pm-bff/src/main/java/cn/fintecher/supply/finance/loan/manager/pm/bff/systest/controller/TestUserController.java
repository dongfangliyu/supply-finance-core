package cn.fintecher.supply.finance.loan.manager.pm.bff.systest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.supply.finance.loan.manager.common.sys.EmpResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.systest.service.TestSysUserService;

@RestController
public class TestUserController {
	
	@Autowired
	TestSysUserService testSysUserService;
	
	@RequestMapping(value = "/testFindUserInfo",method = RequestMethod.GET)
    public CommonResponse<EmpResponse> testFindUserInfo(@RequestParam String userId){
		System.out.println("ssssssssssssssssssssssssssss");
        return testSysUserService.getUserById(userId);
    }
	
	
	
	
	
}
