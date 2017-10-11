package com.fzl.oa.web.util;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

public abstract class FilterOnUrl extends OncePerRequestFilter {

	private HashSet<String> notCheckURLList = new HashSet<String>();

	public String[] getIgnoreUrls() {
		return notCheckURLList.toArray(new String[notCheckURLList.size()]);
	}

	public void setIgnoreUrls(String... urls) {
		this.notCheckURLList.clear();
		this.notCheckURLList.addAll(Arrays.asList(urls));
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
		return notCheckURLList.contains(uri);
	}

}
