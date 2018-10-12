package cn.fintecher.supply.finance.loan.manager.common.util;

import java.io.IOException;
import java.util.Properties;

public class FlagTool {
	
	public static String File_PATH;
	public static String Resource_PATH;
	public static String Download_PATH;
	public static String QRCode_PATH;
	public static String Process_Base_url;
	public static String PLC_ROLE;
	public static String Proxy_PATH;
	public static String PDF_PATH;
	public static String PDF_PATH_LIV;
	public static String SIGN_IMAGE_PATH;
	
	static {
		FlagTool ft = new FlagTool();
			try {
				File_PATH=ft.getValue("File_PATH");
				Resource_PATH=ft.getValue("Resource_PATH"); 
				Download_PATH=ft.getValue("Download_PATH"); 
				QRCode_PATH=ft.getValue("QRCode_PATH");
				Process_Base_url=ft.getValue("Process_Base_url");
				PLC_ROLE=ft.getValue("plcRole");
				PDF_PATH=ft.getValue("PDF_PATH");
				PDF_PATH_LIV=ft.getValue("PDF_PATH_LIV");
				Proxy_PATH=ft.getValue("Proxy_PATH");
				SIGN_IMAGE_PATH=ft.getValue("SIGN_IMAGE_PATH");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public String getValue(String key) throws IOException{
    	Properties prop = new Properties();
    	prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
    	return (String) prop.get(key);
    }
}
