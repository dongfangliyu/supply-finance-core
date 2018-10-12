package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.feign;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Param:
 * @Return:
 * @Author WuTianJuan
 * @Date Created in 10:04 2018/7/4
 */
@FeignClient(name = "loan-manager-service")
public interface FCompanyUserService {

    @ResponseBody
    @RequestMapping(value ="/company/user/findCompanyUserByName", method = RequestMethod.GET)
    CompanyUserEntity findCompanyUserByName(@RequestParam(value="userName") String userName);
}
