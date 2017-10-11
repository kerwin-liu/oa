package com.fzl.oa.web.util;

import com.fzl.oa.web.util.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Title: <br>
 * Description: 登录验证过滤器 <br>
 * 
 * @author jeff
 * @version 1.0
 */
public class LoginFilter extends com.fzl.oa.web.util.FilterOnUrl {

	private String redirectURL = null;
	private String sessionKey = null;


	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (sessionKey != null && session.getAttribute(sessionKey) == null) {
			response.sendRedirect(request.getContextPath() + redirectURL);
		}else{
			filterChain.doFilter(request, response);
		}

	}
	
}
