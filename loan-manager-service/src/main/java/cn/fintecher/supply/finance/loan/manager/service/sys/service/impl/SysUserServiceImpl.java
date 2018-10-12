package cn.fintecher.supply.finance.loan.manager.service.sys.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysUserRoleService;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * SysUserService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    @Value("${authorization.service.url}")
    private String authorizationServicePath;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public List<SysUserAdminEntity> queryUserByDeptId(Integer deptId) {
        return this.restTemplate.getForObject(managerCorePath+"/sysUser/queryUserByDeptId?deptId={deptId}",List.class,deptId);
    }

    @Override
    public boolean verPassWord(SysUserPasswordChange sysUserPasswordChange){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Integer userId = sysUserPasswordChange.getUserId();
        SysUserAdminEntity sysUserAdminEntity  = this.restTemplate.getForObject(managerCorePath+"/sysUser/findUserById?userId={userId}",SysUserAdminEntity.class,userId);
        if(sysUserAdminEntity!=null){
            if(encoder.matches(sysUserPasswordChange.getPassword(),sysUserAdminEntity.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public SysUserAdminEntity findUserByName(String userName) {
        return this.restTemplate.getForObject(managerCorePath+"/sysUser/userInfoByName/{userName}",SysUserAdminEntity.class,userName);
    }

    @Override
    public int updatePassword(SysUserPasswordChange sysUserPasswordChange) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",sysUserPasswordChange.getUsername());
        jsonObject.put("password",encoder.encode(sysUserPasswordChange.getNewPassword()));
        Message message = this.restTemplate.postForObject(authorizationServicePath+"/oauth/updateUser",jsonObject,Message.class);
        if(MessageType.MSG_SUCCESS.equals(message.getCode())){
            sysUserPasswordChange.setNewPassword(jsonObject.get("password").toString());
            return this.restTemplate.postForObject(managerCorePath+"/sysUser/updatePassWord",sysUserPasswordChange,Integer.class);
        }else{
            return 0;
        }

    }

    @Override
    public int findUserRoleCountByUserId(Integer userId) {
        return this.restTemplate.getForObject(managerCorePath+"/sysUser/findUserRoleCountByUserId?userId={userId}",Integer.class,userId);
    }

    @Override
    public SysUserAdminEntity queryByUserName(String username) {
        return this.restTemplate.getForObject(managerCorePath+"/sysUser/queryByUserName?username={username}",SysUserAdminEntity.class,username);
    }

    @Override
    public int save(SysUserAdminEntity user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",user.getUsername());
        jsonObject.put("password",encoder.encode(user.getPassword()));
        jsonObject.put("name",user.getRealname());
        Message message = this.restTemplate.postForObject(authorizationServicePath+"/oauth/createUser",jsonObject,Message.class);
        if(MessageType.MSG_SUCCESS.equals(message.getCode())){
            user.setPassword(jsonObject.get("password").toString());
            Integer userId = this.restTemplate.postForObject(managerCorePath+"/sysUser/saveAdminUser",user,Integer.class);
            if(userId!=null && userId>0){
                if(user.getRoleList()!=null && user.getRoleList().size()>0){
                    SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
                    sysRoleUserEntity.setUserId(userId);
                    List<Integer> roleList = new ArrayList<>();
                    for (SysRoleEntity sysRoleEntity : user.getRoleList()){
                        roleList.add(sysRoleEntity.getRoleId());
                    }
                    sysRoleUserEntity.setRoleIdList(roleList);
                    sysUserRoleService.saveOrUpdate(sysRoleUserEntity);
                }
                return userId;
            }else{
                return  0;
            }
        }else{
            return 0;
        }
    }

    @Override
    public void update(SysUserAdminEntity user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Integer userId = user.getUserId();
        SysUserAdminEntity sysUserAdminEntity = this.restTemplate.getForObject(managerCorePath+"/sysUser/findUserById?userId={userId}",SysUserAdminEntity.class,userId);
        if(sysUserAdminEntity != null){

            if(StringUtils.isBlank(user.getPassword())){
                user.setPassword(sysUserAdminEntity.getPassword());
            }else{
                if(sysUserAdminEntity.getPassword().equals(user.getPassword())){
                    user.setPassword(sysUserAdminEntity.getPassword());
                }else{
                    user.setPassword(encoder.encode(user.getPassword()));
                }
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("name",user.getRealname());
            Message message = this.restTemplate.postForObject(authorizationServicePath+"/oauth/updateUser",jsonObject,Message.class);
            if(MessageType.MSG_SUCCESS.equals(message.getCode())){

                if(!StringUtils.isBlank(user.getPassword())){
                    user.setPassword(user.getPassword());
                }
                this.restTemplate.postForObject(managerCorePath+"/sysUser/updateAdminUser",user,Void.class);
                if(user.getRoleList()!=null && user.getRoleList().size()>0){
                    SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
                    sysRoleUserEntity.setUserId(user.getUserId());
                    List<Integer> roleList = new ArrayList<>();
                    for (SysRoleEntity sysRoleEntity : user.getRoleList()){
                        roleList.add(sysRoleEntity.getRoleId());
                    }
                    sysRoleUserEntity.setRoleIdList(roleList);
                    sysUserRoleService.saveOrUpdate(sysRoleUserEntity);
                }
            }
        }
    }

    @Override
    public boolean checkIsExist(String username) {
        boolean result = this.restTemplate.getForObject(managerCorePath+"/sysUser/checkIsExist?username={username}",Boolean.class,username);
        return result;
    }


    @Override
    public SysUserAdminEntity findUserById(Integer userId) {
        SysUserAdminEntity sysUserAdminEntity = this.restTemplate.getForObject(managerCorePath+"/sysUser/findUserById?userId={userId}",SysUserAdminEntity.class,userId);
        return sysUserAdminEntity;
    }

    @Override
    public List<SysUserAdminEntity> getAllUsers() {
        List<SysUserAdminEntity> result= this.restTemplate.getForObject(managerCorePath+"/sysUser/getAllUsers",List.class);
        return result;
    }

    @Override
    public PagedResponse<EmpsResponse> findUserPage(UserSearchForm userSearchForm) {
        JSONObject json = new JSONObject();
        int total=0;
        List<SysUserAdminEntity> list=null;
        if (ChkUtil.isEmpty(userSearchForm.getRolename())) {
            list= this.restTemplate.postForObject(managerCorePath+"/sysUser/findUserPage",userSearchForm,List.class);
            total= this.restTemplate.postForObject(managerCorePath+"/sysUser/findUserPageCount",userSearchForm,Integer.class);
        }else{
            list= this.restTemplate.postForObject(managerCorePath+"/sysUser/findUserPageByRoleName",userSearchForm,List.class);
            total= this.restTemplate.postForObject(managerCorePath+"/sysUser/findUserPageCountByRoleName",userSearchForm,Integer.class);
        }
        List<SysUserAdminEntity> userList = JSONUtil.toList(list,SysUserAdminEntity.class);

        queryRoleByUserId(userList);

        json.put("emps", userList);
        PagedResponse<EmpsResponse> pagedResponse = new PagedResponse<EmpsResponse>();
        EmpsResponse empsResponse = new EmpsResponse();
        empsResponse.setEmps(userList);
        pagedResponse.setData(empsResponse);
        pagedResponse.setTotal(total);
        pagedResponse.setPageNo(userSearchForm.getPageNo());
        pagedResponse.setPageSize(userSearchForm.getPageSize());
        return pagedResponse;
    }

    private  void queryRoleByUserId(List<SysUserAdminEntity> list){
        for (SysUserAdminEntity user:list) {
            Integer userId = user.getUserId();
            List<SysRoleEntity> role =this.restTemplate.getForObject(managerCorePath+"/sysUser/queryRoles?userId={userId}",List.class,userId);
            user.setRoleList(role);
            if (user.getDeptId()!=null && user.getDeptId()!= 0) {
                Integer deptId =user.getDeptId();
                SysDeptEntity dept = this.restTemplate.getForObject(managerCorePath+"/sysDept/queryDeptById?deptId={deptId}",SysDeptEntity.class,deptId);
                if (dept!= null) {
                    user.setDeptName(dept.getName());
                }
            }

        }
    }

    @Override
    public List<SysUserAdminEntity> queryUserByRoleId(Integer roleId) {
        List<SysUserAdminEntity> list =  this.restTemplate.getForObject(managerCorePath+"/sysUser/queryUserByRoleId?roleId={roleId}",List.class,roleId);
        return list;
    }
}