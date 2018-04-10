package com.lsg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lsg.member.constants.Member;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인터셉터");
		String contextPath = request.getContextPath();
		
		if ( request.getSession().getAttribute(Member.USER) == null ) {
			System.out.println("인터셉터 if문");
			response.sendRedirect(contextPath + "/login");
			return false;
		}
		
		return true;
	}

}



