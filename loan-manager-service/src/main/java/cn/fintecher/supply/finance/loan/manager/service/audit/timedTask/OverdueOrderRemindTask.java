package cn.fintecher.supply.finance.loan.manager.service.audit.timedTask;

import cn.fintecher.supply.finance.loan.manager.service.audit.service.OverdueRemindTaskService;
import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueOrderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 还款提醒定时任务
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:21
 */
@Component
public class OverdueOrderRemindTask {

    @Autowired
    private OverdueRemindTaskService overdueRemindTaskService;

  	@Scheduled(cron = "0 0 14 * * ?")
  	public void createOverdueOrder(){
		overdueRemindTaskService.submitRemindByDate();
  	}

}
