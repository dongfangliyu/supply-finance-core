package cn.fintecher.supply.finance.loan.manager.common.company.request;

import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * 链属列表查询条件
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@Data
public class CompanyChainFrom extends PageInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="公司名称")
    public String companyName;
    
    @ApiModelProperty(value="核心企业Id",hidden = true)
    public Long owerId;
    
    @ApiModelProperty(value="当前操作人用户账号",hidden = true)
    public String userName;

    @ApiModelProperty(value="添加时间开始")
    public String timeStart;

    @ApiModelProperty(value="添加时间结束")
    public String timeEnd;

    @ApiModelProperty(value="注册状态")
    public String state;
    
    @ApiModelProperty(value="数据状态，可用状态",hidden = true)
    public String status = Constants.DATA_STATUS_NOL;

    @ApiModelProperty(value="类型 0供应商  1经销商")
    public String type;

}
