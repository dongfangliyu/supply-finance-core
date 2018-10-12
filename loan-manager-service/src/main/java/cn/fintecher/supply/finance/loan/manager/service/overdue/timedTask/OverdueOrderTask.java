package cn.fintecher.supply.finance.loan.manager.service.overdue.timedTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueOrderTaskService;

/**
 * 逾期定时任务
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:21
 */
@Component
public class OverdueOrderTask {

    @Autowired
    private OverdueOrderTaskService overdueOrderTaskService;
   

    /**
     * 定时插入逾期信息
     */
  	@Scheduled(cron = "0 0 1 * * ?")
  	public void createOverdueOrder(){
  		overdueOrderTaskService.createOverdueOrder();
  		
  	}

}
