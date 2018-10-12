package cn.fintecher.supply.finance.loan.manager.pm.bff.systest.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;

/**
 * SysUserService 接口
 *
 */
@FeignClient(value="loan-manager-service")
public interface TestSysUserService {

    
     /**
      * 根据用户id查找用户
      * @param userId
      * @return
      */
	 @RequestMapping(value = "/testServiceUserInfo",method = RequestMethod.GET)
     CommonResponse<EmpResponse> getUserById(@RequestParam(value = "userId") String userId);

}