package cn.fintecher.supply.finance.loan.manager.service.utils.pdf.eSign;


import cn.fintecher.supply.finance.loan.manager.common.util.FlagTool;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;

import java.io.File;

/***
 * 电子签章
 * 
 * @author Ching
 *
 */
public class PdfSignUtil {
	
	public static int seal(String filePath, String saveFilePath, String saveFileName,int type) {
		System.out.println("111111111111");
		SignHelper.initProject();
		System.out.println("222222222222");
		
		// 企业印章图片文件路径
		String organizeImgFilePath = FlagTool.SIGN_IMAGE_PATH+ File.separator;
		Integer x = 0;
		Integer y = 0;
		String page = "";
		int flag = -1;
		switch (type) {
		case 1:
			flag = 1;
			x = 170;
			y = 514;
			page = "1";
			organizeImgFilePath +="OrganizeSeal.png";
			break;
		default:
			break;
		}
		if (flag<0) {
			return -1;
		}
		// 创建企业客户账号
		String userOrganizeAccountId = SignHelper.addOrganizeAccount();
		// 通过上传的印章图片获取企业印章数据
		String organizeSealData = SignHelper.getSealDataByImage(organizeImgFilePath);

		// 签章，签署方式：坐标定位,以文件流的方式传递pdf文档
		FileDigestSignResult userOrganizeSignResult = SignHelper
				.userOrganizeSignByStream(FileHelper.getBytes(filePath), userOrganizeAccountId, organizeSealData,page,x,y);

		if (0 == userOrganizeSignResult.getErrCode()) {
			SignHelper.saveSignedByStream(userOrganizeSignResult.getStream(), FlagTool.Proxy_PATH+saveFilePath, saveFileName);
		}
		return 1;
	}


}
