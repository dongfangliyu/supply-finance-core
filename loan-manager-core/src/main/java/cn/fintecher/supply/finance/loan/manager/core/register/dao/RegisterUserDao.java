package cn.fintecher.supply.finance.loan.manager.core.register.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 6:12
 */
@Mapper
public interface RegisterUserDao {

    RegisterUserEntity selectRegisteCurrentStep(String registerCode);

    boolean createRegisteCompanyUser(RegisterUserEntity registerUserEntity);

    boolean updateRegisteCompanyUser(RegisterUserEntity registerUserEntity);

    List<RegisterUserEntity> searchRegisterUserByUserName(String userName);

    RegisterUserEntity searchRegisteCompanyUserByCode(String registerCode);

    RegisterUserEntity searchRegisterUserByUserNameLast(String userName);
}
