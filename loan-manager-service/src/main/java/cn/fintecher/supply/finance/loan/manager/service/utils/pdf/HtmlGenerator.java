package cn.fintecher.supply.finance.loan.manager.service.utils.pdf;

import cn.fintecher.supply.finance.loan.manager.service.utils.pdf.freemarker.FreemarkerConfiguration;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * 根据模板 生成 html 生成工具类
 * @author wangcy
 *
 */
public class HtmlGenerator {
	private static Logger logger = LoggerFactory.getLogger(HtmlGenerator.class);
	
	public static String htmlGenerate(String template, Map<String,Object> variables) throws IOException, TemplateException {
		Configuration config = FreemarkerConfiguration.getConfiguation();
		config.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
		config.setDefaultEncoding("utf-8");
		Template tp = config.getTemplate(template);
		StringWriter stringWriter = new StringWriter();  
		BufferedWriter writer = new BufferedWriter(stringWriter);  
		tp.setEncoding("UTF-8");  
		tp.process(variables, writer);  
		String htmlStr = stringWriter.toString();
		writer.flush();  
		writer.close();
		return htmlStr;
	}
	
	/*public static void main(String[] args) {
		Configuration config = FreemarkerConfiguration.getConfiguation();        
		config.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
		config.setDefaultEncoding("utf-8");
		try {
			Template tp = config.getTemplate("commitment.ftl");
			tp.getName();
		} catch (TemplateNotFoundException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(),e);
		} catch (MalformedTemplateNameException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(),e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(),e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(),e);
		}
		
	}*/

}
