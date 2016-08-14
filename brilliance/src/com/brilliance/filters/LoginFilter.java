package com.brilliance.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.brilliance.po.AdminInfo;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

public class LoginFilter implements Filter {
	
	public Logger log = Logger.getLogger(Filter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        
       
        
        HttpSession session = servletRequest.getSession();

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        
        // 从session里取员工工号信息
        //UserInfo user = (UserInfo) session.getAttribute("userInfo");
        AdminInfo admin = (AdminInfo) session.getAttribute("adminInfo");

        /*创建类Constants.java，里面写的是无需过滤的页面
        for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {

            if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }*/
        
        // 登陆页面无需过滤
		if (path.indexOf("index.html") > -1 || path.indexOf("login") > -1
				|| path.indexOf("forwardToRegister") > -1
				|| path.indexOf("register") > -1
				|| path.indexOf("admin") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		/*异步请求。需要登录权限，返回个前台未登录标识。
		 * 日记的增删改查
		 * 用户的查询
		 */
		//请求方式
		String ajaxRequest = servletRequest.getHeader("x-requested-with");
		if(ajaxRequest!=null&&ajaxRequest.equals("XMLHttpRequest")){
			if (path.indexOf("DiaryInfo") > 0||path.indexOf("order") > 0) {
				if (admin == null) {
					log.warn("***********用户未登录的异步请求。请求路径："+path+"**********");
					PrintWriter out = response.getWriter();
					String back = Tools.getErrorsRetrunsMsg("00001").generateJsonData();
					out.print(back);
					return;
				}
			}
		}

        // 同步请求。判断如果没有取到员工信息,就跳转到登陆页面
        if (admin == null) {
            // 跳转到登陆页面
            servletResponse.sendRedirect("page/admin");
        }
        chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
