package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockListResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeFinanceLoanService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:31
 */
@RestController
@RequestMapping("/pledge/financeLoan")
@Api(tags = "仓单质押财务放款")
public class PledgeFinanceLoanController {

    @Autowired
    private PledgeFinanceLoanService pledgeFinanceLoanService;


    @ApiOperation(value="财务放款初审客户端列表", notes="财务放款初审客户端列表")
    @RequestMapping(value ="/searchFinanceLoanList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> searchFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm,Principal principa){
        Message<PagedResponse<List<PledgeFinanceLoanResponse>>> message = new Message<>(MessageType.MSG_SUCCESS,"finance",null);
        try {
            if (Strings.isNullOrEmpty(principa.getName())){
                message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
                return message;
            }
            pledgeFinanceLoanForm.setUserName(principa.getName());
            message = pledgeFinanceLoanService.searchFinanceLoanList(pledgeFinanceLoanForm);
        }catch (Exception e){
            e.printStackTrace();
            message.setCode(MessageType.MSG_ERROR);
        }
        return message;
    }


    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */


    @ApiOperation(value="财务放款初审列表", notes="财务放款初审列表")
    @RequestMapping(value ="/selectFinanceLoanList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        try {
            return pledgeFinanceLoanService.selectFinanceLoanList(pledgeFinanceLoanForm);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }

    @ApiOperation(value="财务放款初审详情", notes="财务放款初审详情")
    @RequestMapping(value ="/selectFinanceLoanDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PledgeFinanceResponse> selectFinanceLoanDetail(@RequestParam(value = "pid") Long pid){
        try {
            return pledgeFinanceLoanService.selectFinanceLoanDetail(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }


    @ApiOperation(value="财务放款初审通过", notes="财务放款初审通过")
    @RequestMapping(value ="/financeLoanPass", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<Object> financeLoanPass(@RequestParam(value = "pid") Long pid){
        try {
            return pledgeFinanceLoanService.financeLoanPass(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }


    @ApiOperation(value="财务放款初审拒绝", notes="财务放款初审拒绝")
    @RequestMapping(value ="/financeLoanDefault", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<Object> financeLoanDefault(@RequestParam(value = "pid") Long pid){
        try {
            return pledgeFinanceLoanService.financeLoanDefault(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }



    /***
     * 线上为初审接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为复审接口代码
     */


    @ApiOperation(value="财务放款复审列表", notes="财务放款复审列表")
    @RequestMapping(value ="/selectFinanceReviewLoanList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceReviewLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        try {
            return pledgeFinanceLoanService.selectFinanceReviewLoanList(pledgeFinanceLoanForm);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }


    @ApiOperation(value="财务放款复审详情", notes="财务放款复审详情")
    @RequestMapping(value ="/selectFinanceReviewLoanDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<Object> selectFinanceReviewLoanDetail(@RequestParam(value = "pid") Long pid){
        try {
            return pledgeFinanceLoanService.selectFinanceLoanDetail(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }



    @ApiOperation(value="财务放款复审通过", notes="财务放款复审通过")
    @RequestMapping(value ="/financeReviewLoanPass", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<Object> financeReviewLoanPass(@RequestParam(value = "pid") Long pid,@RequestParam(value = "loanTime") String loanTime){
        try {
            return pledgeFinanceLoanService.financeReviewLoanPass(pid,loanTime);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }


    @ApiOperation(value="财务放款复审拒绝", notes="财务放款复审拒绝")
    @RequestMapping(value ="/financeReviewLoanDefault", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<Object> financeReviewLoanDefault(@RequestParam(value = "pid") Long pid){
        try {
            return pledgeFinanceLoanService.financeReviewLoanDefault(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }

    @ApiOperation(value="财务放款根据放款期限和放款时间计算放款周期", notes="财务放款根据放款期限和放款时间计算放款周期")
    @RequestMapping(value ="/selectLoanByTermAndTime", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<Object> selectLoanByTermAndTime(@RequestParam(value = "contractApplyTerm") Long contractApplyTerm,@RequestParam(value = "LoanTime") String loanTime){
        try {
            return pledgeFinanceLoanService.selectLoanByTermAndTime(contractApplyTerm,loanTime);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"financeLoan",e.getMessage());
        }
    }



}
