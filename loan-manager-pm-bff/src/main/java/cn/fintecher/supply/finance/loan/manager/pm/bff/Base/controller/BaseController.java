package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.CompanyUserService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.common.redis.IRedisService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

/**
 * @Author WuTianJuan
 * @Date Created in 15:05 2018/6/26
 */
public class BaseController {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private CompanyUserService companyUserService;

    public Long getEnterpriseId(Principal principal) {
        String userName = principal.getName();
        CompanyUserEntity object = new CompanyUserEntity();
        if (!Strings.isNullOrEmpty(userName)) {
            object = redisService.getObject(userName, CompanyUserEntity.class);
            if (object == null) {
                object = companyUserService.findCompanyUserByName(userName);
               // redisService.setObject(userName,object);
                return object.getEnterpriseId();
            }
        }
        return object.getEnterpriseId();
    }
}