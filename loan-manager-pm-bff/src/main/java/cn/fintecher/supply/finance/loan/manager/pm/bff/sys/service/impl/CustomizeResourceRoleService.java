package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.impl;


import cn.fintecher.authorization.common.dto.functionDetails.FunctionDetailInfo;
import cn.fintecher.authorization.common.dto.roledetails.RoleDetailInfo;
import cn.fintecher.authorization.conf.provider.function.ResourceDefaultUserFunction;
import cn.fintecher.authorization.conf.provider.function.ResourceFunctionService;
import cn.fintecher.authorization.conf.provider.function.ResourceUserFunction;
import cn.fintecher.authorization.conf.provider.function.ResourceUserFunctionException;
import cn.fintecher.authorization.conf.provider.role.ResourceDefaultUserRole;
import cn.fintecher.authorization.conf.provider.role.ResourceRoleService;
import cn.fintecher.authorization.conf.provider.role.ResourceUserRole;
import cn.fintecher.authorization.conf.provider.role.ResourceUserRoleException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomizeResourceRoleService implements ResourceRoleService,ResourceFunctionService {

    public ResourceUserRole loadRoleByUsername(String userName) throws ResourceUserRoleException {

        ResourceUserRole userRole = new ResourceDefaultUserRole();

        List<RoleDetailInfo> roles = new ArrayList<RoleDetailInfo>();


      /*  List<String> roleCodes = sysUserRoleService.findRolesByUsername(userName);
        if(roleCodes!=null && roleCodes.size()>0){
        	for (String roleCode : roleCodes) {
        		RoleDetailInfo roleInfo = new RoleDetailInfo();
                roleInfo.setRole(roleCode);
                roles.add(roleInfo);
			}
        }

        userRole.setRoles(roles);*/

		userRole.setRoles(roles);
        return userRole;
    }

	public ResourceUserFunction loadFunctionByUsername(String userName)
			throws ResourceUserFunctionException {
		ResourceUserFunction userFunction = new ResourceDefaultUserFunction();
		List<FunctionDetailInfo> functions = new ArrayList<FunctionDetailInfo>();
		
		/* List<String> functionCodes = sysUserRoleService.findFunctionByUsername(userName);
	        if(functionCodes!=null && functionCodes.size()>0){
	        	for (String functionCode : functionCodes) {
	        		if(functionCode!=null && !"".equals(functionCode) && functionCode.split(",").length>0){
	        			for (String function:functionCode.split(",")) {
	        				FunctionDetailInfo functionDetailInfo = new FunctionDetailInfo();
			        		functionDetailInfo.setFunction(function);
			        		functions.add(functionDetailInfo);
						}
	        		}
	        		
				}
	        }
        userFunction.setFunctions(functions);*/
		userFunction.setFunctions(functions);
		return userFunction;
	}

}
