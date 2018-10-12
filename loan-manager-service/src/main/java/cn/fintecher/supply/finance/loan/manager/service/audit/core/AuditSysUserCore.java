package cn.fintecher.supply.finance.loan.manager.service.audit.core;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@FeignClient(name = "loan-manager-core")
public interface AuditSysUserCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/sysUserRole/getRolesByUserId", method = RequestMethod.GET)
 	 public Message<List<SysRoleEntity>> getRolesByUserId(@RequestParam("userId")Long userId);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value="/sysUserRole/getUsersByRoleId",method = RequestMethod.GET)
    public Message<List<SysUserAdminEntity>> getUsersByRoleId(@RequestParam("roleId")Long roleId);
    
    
}
