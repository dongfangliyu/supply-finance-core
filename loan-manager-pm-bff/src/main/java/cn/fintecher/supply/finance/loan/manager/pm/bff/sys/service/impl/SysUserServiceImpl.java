package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * SysUserService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Value("${loan.manager.service.url}")
    private String userServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public CommonResponse verPassWord(String passWord, String userName) {
        CommonResponse response = this.restTemplate.getForObject(userServicePath+"/sysUser/verPassWord?passWord={passWord}&userName={userName}",CommonResponse.class,passWord,userName);
        return response;
    }

    @Override
    public Message getUserInfoByName(String userName) {
        Message message = this.restTemplate.getForObject(userServicePath+"/sysUser/findUserByName/{userName}",Message.class,userName);
        return message;
    }

    @Override
    public CommonResponse updatePassword(SysUserPasswordChange sysUserPasswordChange) {

        CommonResponse response = this.restTemplate.postForObject(userServicePath+"/sysUser/updatePassWord",sysUserPasswordChange,CommonResponse.class);
        return response;
    }

    @Override
    public PagedResponse<EmpsResponse> getUserInfo(UserSearchForm userSearchForm) {
        PagedResponse<EmpsResponse> response = this.restTemplate.postForObject(userServicePath+"/sysUser/getUserInfo",userSearchForm,PagedResponse.class);
        return response;
    }

    @Override
    public CommonResponse<EmpResponse> getUserById(Integer userId) {
        CommonResponse<EmpResponse> response = this.restTemplate.getForObject(userServicePath+"/sysUser/getUserById?userId={userId}",CommonResponse.class,userId);
        return response;
    }


    @Override
    public CommonResponse<EmpsResponse> getAllUsers() {
        CommonResponse<EmpsResponse> response = this.restTemplate.getForObject(userServicePath+"/sysUser/getAllUsers",CommonResponse.class);
        return response;
    }

    @Override
    public CommonResponse<UserIdResponse> addOrUpdateUser(SysUserAdminEntity user) {
        CommonResponse<UserIdResponse> response = this.restTemplate.postForObject(userServicePath+"/sysUser/addOrUpdateUser",user,CommonResponse.class);
        return response;
    }

    public String getById(String id) {
        String result = this.restTemplate.getForObject(userServicePath+"/sysUser/test?id={id}",String.class,id);
        return result;
    }

    @Override
    public CommonResponse<Object> checkIsExist(String username) {
        CommonResponse<Object> result = this.restTemplate.getForObject(userServicePath+"/sysUser/checkIsExist?username={username}",CommonResponse.class,username);
        return result;
    }


}