package com.adanac.module.blog.filter;

/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.adanac.module.blog.config.Configuration;
import com.adanac.module.blog.servlet.AbstractServlet;
import com.adanac.module.blog.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author adanac
 * @since 5/15/2015 5:30 PM
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	String loginPage = "/admin/login.ftl";
    	String loginUrl = "/admin/login.do";
    	String indexUrl = "/admin/admin_index.ftl";
    	String adminDefaultUrl = "/admin";
    	String contextPath = Configuration.getSiteUrl();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
    	String requestUri = StringUtil.replaceSlants(request.getRequestURI().substring(request.getContextPath().length()));
    	if (loginPage.equals(requestUri) || loginUrl.equals(requestUri)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
    	if (adminDefaultUrl.equals(requestUri) || (adminDefaultUrl + "/").equals(requestUri)) {
    		((HttpServletResponse)servletResponse).sendRedirect(contextPath + indexUrl);
    		return;
		}
        if (!AbstractServlet.isAdminLogin((HttpServletRequest)servletRequest)) {
        	((HttpServletResponse)servletResponse).sendRedirect(contextPath + loginPage);
        	return;
		}
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}

}
