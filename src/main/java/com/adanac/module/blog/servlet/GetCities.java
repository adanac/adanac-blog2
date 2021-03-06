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

import com.adanac.module.blog.dao.CityDao;
import com.adanac.module.blog.dao.ProvinceDao;
import com.adanac.module.blog.orm.DaoFactory;
import com.adanac.module.blog.util.StringUtil;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author adanac
 * @since 6/16/2015 10:33 AM
 */
public class GetCities extends AbstractServlet {

    @Override
    protected void service() throws ServletException, IOException {
        String province = StringUtil.urlDecode(getRequest().getParameter("province"));
        Integer provinceId = DaoFactory.getDao(ProvinceDao.class).getId(province);
        writeJsonArray(DaoFactory.getDao(CityDao.class).getCities(provinceId));
    }

}
