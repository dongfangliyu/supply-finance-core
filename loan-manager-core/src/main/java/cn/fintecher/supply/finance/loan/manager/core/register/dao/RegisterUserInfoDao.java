package cn.fintecher.supply.finance.loan.manager.core.register.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyPrimaryInfoResponse;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gonghebin
 * @date 2018/6/21 0021下午 3:08
 */
@Mapper
public interface RegisterUserInfoDao {

    boolean createRegisteCompanyUserInfo(RegisterUserInfoEntity registerUserInfoEntity);

    boolean updateRegisteCompanyUserInfo(RegisterUserInfoEntity registerUserInfoEntity);

    CompanyPrimaryInfoResponse searchRegisteCompanyPrimaryInfo(String registerCode);

    RegisterUserInfoEntity searchRegisteCompanyUserInfoByCode(String registerCode);

    RegisterUserInfoEntity searchRegisteCompanyByName(String name);

    RegisterUserInfoEntity searchRegisterUserInfoByPid(String pid);
}
