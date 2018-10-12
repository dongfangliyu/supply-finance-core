package cn.fintecher.supply.finance.loan.manager.pm.bff.filter;

import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.pm.bff.common.redis.IRedisService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysMenuService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huanglei
 * @version v2.0
 * @time 2018年4月8日 下午1:19:32
 */
@Component
@ServletComponentScan
//@WebFilter(urlPatterns = "/*",filterName = "ChecckFilter")
@WebFilter(filterName = "/CodeFilter", urlPatterns = "/*")
public class ChecckFilter implements Filter {
    private List<String> excludedPageArray = null;


    @Autowired
    private IRedisService redisService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        excludedPageArray = new ArrayList<>();
        excludedPageArray.add("/auto/login");
        excludedPageArray.add("/register/user");
        excludedPageArray.add("/registerUser/file");
        excludedPageArray.add("/audit/auditFile");
        excludedPageArray.add("/business/file");
        excludedPageArray.add("/enterprise/financial");
        excludedPageArray.add("/base");
        excludedPageArray.add("/login");
        excludedPageArray.add("/verifycode");
        excludedPageArray.add("/overdue/overdueOrder/downLoadOrde");
        excludedPageArray.add("/sysMenu/navigation");
        excludedPageArray.add("/pledge/register");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
            //放开跨域
            
    		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            String uri = httpRequest.getRequestURI();
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(uri.substring(uri.length()-2,uri.length()-1));
            if(isNum.matches()){
                if (uri.lastIndexOf("/") > 0) {
                    uri = uri.substring(0, uri.lastIndexOf("/"));
                }
            }
       
            JSONObject json = new JSONObject();
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Headers",
                    "origin,content-type, accept, authorization,accessToken");
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST,PUT, DELETE, OPTIONS, HEAD");
            if (httpRequest.getMethod().equals("OPTIONS")) {
                return;
            }
            httpResponse.setHeader("Content-Type", "application/json; charset=utf-8");
            boolean isExcludedPage = true;
            for (String page : excludedPageArray) {// 遍历例外url数组
                // 判断当前URL是否与例外页面相同
                String subStringPath = "";
                if (uri.length()>page.length()) {
                    subStringPath = uri.substring(0,page.length());
                }else {
                    subStringPath = uri;
                }
                if(subStringPath.equals(page)){
                    isExcludedPage = true;
                    break;
                }
            }

            if (isExcludedPage) {//在过滤url之外
                chain.doFilter(servletRequest, httpResponse);
                return;
            }
            String tokenValue = httpRequest.getHeader(Constants.AUTHORZATION);
            if (Strings.isNullOrEmpty(httpRequest.getHeader(Constants.AUTHORZATION))) {
                chain.doFilter(servletRequest, httpResponse);
                return;
            }
            if (tokenValue.length() < 40) {
                chain.doFilter(servletRequest, httpResponse);
                return;
            }
            String str = redisService.getString(tokenValue);
            JSONObject  object = JSONObject.parseObject(str);
            if (ChkUtil.isEmpty(object)) {
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setContentType("text/html; charset=UTF-8");
                PrintWriter out = httpResponse.getWriter();
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                json.put("code", MessageType.MSG_ERROR);
                json.put("message", "登陆失效,请重新登陆!");
                out.println(json);
                out.flush();
                out.close();
                return;
            }

           // CompanyUserEntity companyUserEntity = JSONUtil.toBean(string, CompanyUserEntity.class);
            if (object.get("enterpriseId") != null) {
                chain.doFilter(servletRequest, httpResponse);
                return;
            }
           // SysUserAdminEntity sysUserAdminEntity = JSONUtil.toBean(string, SysUserAdminEntity.class);
            String username =(String)object.get("username");
            if (Constants.SYSTEM_ADMIN == (int)object.get("id")) {
            	 chain.doFilter(servletRequest, httpResponse);
                 return;
			}
            CommonResponse<List<SysMenuEntity>> navigation = sysMenuService.getAuthMenus(username);
            List<SysMenuEntity> data = JSONUtil.toList(navigation.getData(), SysMenuEntity.class);
            if (ChkUtil.isEmpty(data)){
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                httpResponse.setContentType("text/html; charset=UTF-8");
                httpResponse.getWriter().append("error url: "+ uri);
                return;
            }
            for (int i = 0; i < data.size(); i++) {
                SysMenuEntity sysMenuEntity = data.get(i);
                String url = sysMenuEntity.getUrl();
                url = url.trim();
                if (uri.equals(url)) {
                    chain.doFilter(servletRequest, httpResponse);
                    return;
                } else {
                    if (i == data.size() - 1) {
                        httpResponse.setCharacterEncoding("UTF-8");
                        httpResponse.setContentType("text/html; charset=UTF-8");
                        httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        httpResponse.getWriter().append("error url: "+ uri);
                        return;
                    }
                }
            }
    }

    @Override
    public void destroy() {
        this.excludedPageArray = null;
    }

}
