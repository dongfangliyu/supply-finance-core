package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProFileEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/10 0010下午 5:08
 */
@Data
public class ProContractResponse implements Serializable{

    private ProContractEntity proContractEntity;

    private List<ProFileEntity> proFileEntityList;

}
