package cn.fintecher.supply.finance.loan.manager.common.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 菜单查询条件
 * @author hujinbao
 *
 */
@JsonIgnoreProperties
public class MenuSearchForm implements Serializable {
	
	@ApiModelProperty(value="菜单名称")
    private String menuName;
	@ApiModelProperty(value="状态")
    private String status;

    private static final long serialVersionUID = 1L;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	

	
}