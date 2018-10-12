package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class BaseAreaEntity implements Serializable {
    
	/**
	*id
	*/
    private Long pid;
	/**
	*地区名称
	*/
    private String name;
	/**
	*父id
	*/
    private Long parentId;

}

