package cn.fintecher.supply.finance.loan.manager.pm.bff.company.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
public interface CompanyChainService {
    /**
     * 链属名单列表
     * @param companyChainFrom
     * @return
     */
    public Message<PagedResponse<List<CompanyChainEntity>>> searchListChain(CompanyChainFrom companyChainFrom);

    /**
     * 增加链属名单
     * @param companyChainEntity
     * @return
     */
    public Message submitChain(CompanyChainEntity companyChainEntity);
	
    public Message submitUploadChain(MultipartFile file,String userName);
	
    public Message submitUpdateChain(CompanyChainEntity companyChainEntity);
	
    public Message deleteChain(String id,String userName);

	public XSSFWorkbook downLoad(CompanyChainFrom companyChainFrom);

	public Message searchList(String userName);
    /**
     * 邀请经销商
     * @param userName
     * @return
     */
    public Message inviteAgency(Long id,String userName);

    /**
     * 批量邀请经销商
     * @param userName
     * @return
     */
    Message inviteAgencyBatch( List<String> ids, String userName);
}
