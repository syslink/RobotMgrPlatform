/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */
package com.pirobot.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


 
public class SessionVerifyInterceptor implements  HandlerInterceptor  {

    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {
    	Object manager = request.getSession().getAttribute("manager");
		if (manager != null) {
			return true;
		} else {

			String basePath = request.getScheme()
					+ "://"
					+ ((HttpServletRequest) request).getServerName() + ":"
					+ ((HttpServletRequest) request).getServerPort()
					+ ((HttpServletRequest) request).getContextPath();
			String path = basePath + "/login.jsp";
			response.sendRedirect(path);
			return false;
		}
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}
	 

}
