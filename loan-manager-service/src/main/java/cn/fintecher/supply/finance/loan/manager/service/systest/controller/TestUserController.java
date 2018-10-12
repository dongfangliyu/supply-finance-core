package cn.fintecher.supply.finance.loan.manager.service.systest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.supply.finance.loan.manager.common.sys.EmpResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;

@RestController
public class TestUserController {
	
	
	
	@RequestMapping(value = "/testServiceUserInfo",method = RequestMethod.GET)
    public CommonResponse<EmpResponse> getUserById(@RequestParam String userId){
		System.out.println("-----------------------");
		CommonResponse s= new CommonResponse();
		EmpResponse r= new EmpResponse();
		SysUserAdminEntity user = new SysUserAdminEntity();
		user.setCompId(123123);
		user.setUserId(555555);
		user.setDeptId(2323);
		user.setMobile("15823123141");
		r.setEmp(user);
		s.setData(r);
		return s;
    }
	
	
	
	
	
}
