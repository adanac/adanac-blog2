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

import com.adanac.module.blog.dao.UserDao;
import com.adanac.module.blog.orm.DaoFactory;

/**
 * @author adanac
 * @since 2015年6月18日 下午7:08:23
 */
public class UpdateImagePath extends AbstractServlet {

	@Override
	protected void service() throws ServletException, IOException {
		if (!isLogin()) {
			throw new RuntimeException();
		}
		if (DaoFactory.getDao(UserDao.class).uploadImage(getUsername(), getRequest().getParameter("imagePath"))) {
			writeText("success");
		}
	}

}
