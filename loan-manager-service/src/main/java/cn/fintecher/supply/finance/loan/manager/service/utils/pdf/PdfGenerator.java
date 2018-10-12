package cn.fintecher.supply.finance.loan.manager.service.utils.pdf;


import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.FlagTool;
import com.lowagie.text.pdf.BaseFont;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;

/**
 * pdf生成工具类
 * @author wangcy
 *
 */
public class PdfGenerator {
	private static final Logger logger = Logger.getLogger(PdfGenerator.class);
	public static void generate(String htmlStr, OutputStream out, String pictureName) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(htmlStr.getBytes("UTF-8")));
			ITextRenderer renderer = new ITextRenderer();
			/*String systemPath = PdfGenerator.class.getResource("/").getFile();
			String pathPrefix = ResourceUtils.getURL("classpath:").getPath();*/
			renderer.getFontResolver().addFont(FlagTool.Proxy_PATH +File.separator +"simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.setDocument(doc, null);
			if(!ChkUtil.isEmpty(pictureName)){
				renderer.getSharedContext().setBaseURL("file:"+pictureName);
			}
			//解决图片的相对路径问题
			renderer.layout();
			renderer.createPDF(out);
			out.close();
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
		}
	}
//	public static void generate(String htmlStr, OutputStream out) {
//		try {
//			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//			Document doc = builder.parse(new ByteArrayInputStream(htmlStr.getBytes("UTF-8")));
//			ITextRenderer renderer = new ITextRenderer();
//			String systemPath = ContractUtil.class.getClassLoader().getResource("/").getFile();
//			renderer.getFontResolver().addFont(systemPath+"SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//			renderer.setDocument(doc, null);
//			renderer.layout();
//			renderer.createPDF(out);
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
