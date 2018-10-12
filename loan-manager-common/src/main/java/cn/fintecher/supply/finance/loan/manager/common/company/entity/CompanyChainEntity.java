package cn.fintecher.supply.finance.loan.manager.common.company.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * 链属名单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@Data
public class CompanyChainEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	@ApiModelProperty(value="")
	private Long pid;

	/**
	*核心企业id
	*/
	@ApiModelProperty(value="核心企业id")
	private Long owerId;

	/**
	*公司名称
	*/
	@ApiModelProperty(value="公司名称")
	private String companyName;
	
	/**
	*联系人
	*/
	@ApiModelProperty(value="联系人")
	private String linkMan;
	
	/**
	*手机号
	*/
	@ApiModelProperty(value="手机号")
	private String mobile;
	
	/**
	*邮箱
	*/
	@ApiModelProperty(value="邮箱")
	private String email;
	
	/**
	*注册状态 0未注册 1已注册
	*/
	@ApiModelProperty(value="注册状态 0未注册 1已注册")
	private String state;
	
	/**
	*类型  0供应商  1经销商
	*/
	@ApiModelProperty(value="类型  0供应商  1经销商")
	private String type;
	
	/**
	*创建人账号
	*/
	@ApiModelProperty(value="创建人账号")
	private String createBy;
	
	/**
	*创建时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="创建时间")
	private Date createAt;
	
	/**
	*更新人账号
	*/
	@ApiModelProperty(value="更新人账号")
	private String updateBy;
	
	/**
	*更新时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="更新时间")
	private Date updateAt;
	
	/**
	*状态 DEL删除，STP停用，NOL正常
	*/
	@ApiModelProperty(value="状态 DEL删除，STP停用，NOL正常")
	private String status;
	
	/**
	*备注
	*/
	@ApiModelProperty(value="备注")
	private String remark;


	/**
	 *备注
	 */
	@ApiModelProperty(value="是否邀请")
	private String applyState;

}