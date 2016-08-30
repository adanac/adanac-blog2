package com.adanac.module.blog.servlet;

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

import java.io.IOException;

import javax.servlet.ServletException;

import com.adanac.module.blog.config.Configuration;
import com.adanac.module.blog.dao.ArticleDao;
import com.adanac.module.blog.mvc.RequestMapping;
import com.adanac.module.blog.orm.DaoFactory;

/**
 * @author adanac
 * @since 2015年6月18日 下午6:50:20
 */
@RequestMapping("/admin/adminArticleDelete.do")
public class AdminArticleDelete extends AbstractServlet {

	@Override
	protected void service() throws ServletException, IOException {
		Integer id = Integer.valueOf(getRequest().getParameter("id"));
		if (DaoFactory.getDao(ArticleDao.class).delete(id)) {
			getResponse().sendRedirect(Configuration.getSiteUrl("/admin/article_manager.ftl"));
		}
	}

}
