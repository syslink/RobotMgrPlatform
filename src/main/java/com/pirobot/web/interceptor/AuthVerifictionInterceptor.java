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

 
public class AuthVerifictionInterceptor   implements  HandlerInterceptor {

	
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {
		    String key  = request.getParameter("API_AUTH_KEY");
		    
			if (CustomKeyManager.getInstance().isapproved(key)) {
				return true;
			} else {
				response.getWriter().print("{\"code\":401}");
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