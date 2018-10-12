package cn.fintecher.supply.finance.loan.manager.service.sys.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.common.util.group.AddGroup;
import cn.fintecher.supply.finance.loan.manager.common.util.group.UpdateGroup;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysRoleService;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author hhh
 * @version 1.0.0
 * @date 2016-5-17 17:36:28
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseSysUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 角色和用户绑定
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping(value ="/relRoleAndUser", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse relRoleAndUser(@RequestBody SysRoleUserEntity sysRoleUserEntity){
        CommonResponse response = new CommonResponse();
        try {
            sysUserRoleService.saveOrUpdate(sysRoleUserEntity);
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
        } catch (RuntimeException e) {
            LOGGER.error("角色和用户绑定失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }

    /**
     * 查询所有可用角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/queryAllRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<RolesResponse> queryAllRoles() {
        CommonResponse<RolesResponse> response = new CommonResponse<>();

        try {
            RolesResponse rolesResponse = new RolesResponse();
            rolesResponse.setRoles(sysRoleService.queryAllRoles());
            response.setData(rolesResponse);
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
        } catch (RuntimeException e) {
            LOGGER.error("获取角色分页列表失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }

    /**
     * 根据角色ID，查询角色信息
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleById/{roleId}", method = RequestMethod.GET)
    public CommonResponse<RoleResponse> getRoleById(@PathVariable("roleId") Integer roleId) {

        CommonResponse<RoleResponse> response = new CommonResponse<>();

        try {
            if (ChkUtil.isEmpty(roleId)) {
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("角色id不能为空！");
                return response;
            }
            SysRoleEntity role = sysRoleService.queryObject(roleId);
            if (role == null) {
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("角色不存在！");
                return response;
            }

            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setRole(role);
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(roleResponse);
        } catch (RuntimeException e) {
            LOGGER.error("根据角色ID，查询角色信息失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }

    /**
     * 角色分页列表
     */
    @ResponseBody
    @RequestMapping(value = "/getAllRoles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResponse<RolesResponse> getAllRoles(@RequestBody RoleSearchForm roleSearchForm) {
        PagedResponse<RolesResponse> pagedResponse = new PagedResponse<>();
        try {
            if (("").equals(roleSearchForm.getStatus())) {
                roleSearchForm.setStatus(null);
            }
            pagedResponse = sysRoleService.findRolePage(roleSearchForm);
            pagedResponse.setSuccess(true);
            pagedResponse.setCode(Constants.STATUS_SUCCESS);
            pagedResponse.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
        } catch (RuntimeException e) {
            LOGGER.error("获取角色分页列表失败：{}", e);
            pagedResponse.setCode(Constants.SYSTEM_ERROR);
            pagedResponse.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return pagedResponse;
    }

    /**
     * 角色信息添加/修改／停用
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping(value = "/addOrUpdateRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addOrUpdateRole(@RequestBody SysRoleEntity role) {
        CommonResponse response = new CommonResponse();
        try {
            SysUserAdminEntity sysUserAdminEntity = getSysUser(role.getOperateByName());
            if (ChkUtil.isEmpty(role.getRoleId())) {   //新增
                ValidatorUtils.validateEntity(role, AddGroup.class);
                try {
                    role.setStatus("1");
                    role.setRoleId(null);
                    role.setCreateTime(DateUtil.getCurrentTime());
                    role.setCreateUserId(sysUserAdminEntity.getUserId());
                    role.setUpdateTime(null);
                    role.setUpdateUserId(null);
                    Message message = sysRoleService.save(role);
                    if(MessageType.MSG_SUCCESS.equals(message.getCode())){
                        response.setSuccess(true);
                        response.setCode(Constants.STATUS_SUCCESS);
                        response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
                        return response;
                    }else{
                        response.setSuccess(false);
                        response.setCode(Constants.STATUS_NO_SUCCESS);
                        response.setMsg(message.getMessage().toString());
                        return response;
                    }

                } catch (RuntimeException e) {
                    LOGGER.error("新增角色信息失败：{}", e);
                    response.setSuccess(false);
                    response.setCode(Constants.STATUS_NO_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                    return response;
                }
            } else {   //修改
                ValidatorUtils.validateEntity(role, UpdateGroup.class);
                try {
                    if (null != role.getStatus() && "2".equals(role.getStatus())) {
                        if (sysRoleService.queryUserByRoleId(role.getRoleId())) {
                            response.setCode(Constants.SYSTEM_ERROR);
                            response.setMsg("该角色存在正在使用的用户，暂不能停用");
                            return response;
                        }
                    } else if ("0".equals(role.getStatus())) {
                        if (sysRoleService.queryUserByRoleId(role.getRoleId())) {
                            response.setCode(Constants.SYSTEM_ERROR);
                            response.setMsg("该角色存在正在使用的用户，暂不能删除");
                            return response;
                        }
                    } else {
                        role.setStatus("1");
                    }

                    role.setUpdateTime(DateUtil.getCurrentTime());
                    role.setUpdateUserId(sysUserAdminEntity.getUserId());
                    role.setCreateTime(null);
                    role.setCreateUserId(null);
                    Message message  = sysRoleService.update(role);

                    if(MessageType.MSG_SUCCESS.equals(message.getCode())){
                        response.setSuccess(true);
                        response.setCode(Constants.STATUS_SUCCESS);
                        response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
                        return response;
                    }else{
                        response.setSuccess(false);
                        response.setCode(Constants.STATUS_NO_SUCCESS);
                        response.setMsg(message.getMessage().toString());
                        return response;
                    }
                } catch (RuntimeException e) {
                    LOGGER.error("修改角色信息失败：{}", e);
                    response.setSuccess(false);
                    response.setCode(Constants.STATUS_NO_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                    return response;
                }
            }

        }catch (Exception e){
            LOGGER.error("修改角色信息失败：{}", e);
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg(e.getMessage());
            return response;
        }

    }

}

