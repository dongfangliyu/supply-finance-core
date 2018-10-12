package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

/**
 * SysUserService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysUserService {

     CommonResponse verPassWord(String passWord, String userName);

     /**
      * 根据用户名获取用户信息
      */
     Message getUserInfoByName(String userName);

     /**
      * 修改登录用户密码
      */
     CommonResponse updatePassword(SysUserPasswordChange sysUserPasswordChange);

     /**
      * 用户分页列表
      */
     PagedResponse<EmpsResponse> getUserInfo(UserSearchForm userSearchForm);

     CommonResponse<UserIdResponse> addOrUpdateUser(SysUserAdminEntity user);

     CommonResponse<Object> checkIsExist(String username);

     /**
      * 查询所有的用户信息
      * @return
      */
     CommonResponse<EmpsResponse> getAllUsers();

     /**
      * 根据用户id查找用户
      * @param userId
      * @return
      */
     CommonResponse<EmpResponse> getUserById(Integer userId);

}