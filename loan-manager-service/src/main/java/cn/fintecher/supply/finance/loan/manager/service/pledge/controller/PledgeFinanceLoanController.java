package cn.fintecher.supply.finance.loan.manager.service.pledge.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeFinanceLoanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:36
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/finance/loan")
public class PledgeFinanceLoanController extends BaseCompanyUserController{

    @Autowired
    private PledgeFinanceLoanService pledgeFinanceLoanService;

    @ResponseBody
    @RequestMapping(value = "/searchFinanceLoanList", method = RequestMethod.POST)
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> searchFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        Message<PagedResponse<List<PledgeFinanceLoanResponse>>> message = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
            CompanyUserEntity user = getCompanyUser(pledgeFinanceLoanForm.getUserName());
            message =pledgeFinanceLoanService.searchFinanceLoanList(pledgeFinanceLoanForm,user);
            message.setCode(message.getCode());
            message.setMessage(message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(MessageType.MSG_ERROR);
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/selectFinanceLoanList", method = RequestMethod.POST)
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        return pledgeFinanceLoanService.selectFinanceLoanList(pledgeFinanceLoanForm);
    }

    @ResponseBody
    @RequestMapping(value = "/selectFinanceLoanDetail", method = RequestMethod.GET)
    public Message selectFinanceLoanDetail(@RequestParam(value = "pid") Long pid){
        return pledgeFinanceLoanService.selectFinanceLoanDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/financeLoanPass", method = RequestMethod.GET)
    public Message financeLoanPass(@RequestParam(value = "pid") Long pid){
        return pledgeFinanceLoanService.financeLoanPass(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/financeLoanDefault", method = RequestMethod.GET)
    public Message financeLoanDefault(@RequestParam(value = "pid") Long pid){
        return pledgeFinanceLoanService.financeLoanDefault(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/selectFinanceReviewLoanList", method = RequestMethod.POST)
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceReviewLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        return pledgeFinanceLoanService.selectFinanceReviewLoanList(pledgeFinanceLoanForm);
    }

    @ResponseBody
    @RequestMapping(value = "/financeReviewLoanPass", method = RequestMethod.GET)
    public Message financeReviewLoanPass(@RequestParam(value = "pid") Long pid,@RequestParam(value = "loanTime") String loanTime){
        return pledgeFinanceLoanService.financeReviewLoanPass(pid,loanTime);
    }

    @ResponseBody
    @RequestMapping(value = "/financeReviewLoanDefault", method = RequestMethod.GET)
    public Message financeReviewLoanDefault(@RequestParam(value = "pid") Long pid){
        return pledgeFinanceLoanService.financeReviewLoanDefault(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/selectLoanByTermAndTime", method = RequestMethod.GET)
    public Message selectLoanByTermAndTime(@RequestParam(value = "contractApplyTerm") Long contractApplyTerm,@RequestParam(value = "LoanTime") String loanTime){
        return pledgeFinanceLoanService.selectLoanByTermAndTime(contractApplyTerm,loanTime);
    }

}
