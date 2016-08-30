package com.adanac.module.blog.freemarker;

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

import com.adanac.module.blog.dao.AnswerDao;
import com.adanac.module.blog.dao.QuestionDao;
import com.adanac.module.blog.dao.RecordDao;
import com.adanac.module.blog.model.ViewMode;
import com.adanac.module.blog.orm.DaoFactory;
import com.adanac.module.blog.servlet.AbstractServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author adanac
 * @since 2015年5月31日 下午5:07:46
 */
public abstract class RecordHelper {

	public static void putDataMap(Map<String, Object> data, ViewMode viewMode,int recordId) {
		Map<String, String> record = DaoFactory.getDao(RecordDao.class).getRecord(recordId, viewMode);
        data.put("record", record);
	}
	
	public static String generateStaticPath(int recordId) {
		return "/html/record_" + recordId + ".html";
	}
	
	public static String generateDynamicPath(String staticPath) {
		String name = staticPath.substring(staticPath.lastIndexOf("/") + 1 , staticPath.lastIndexOf("."));
		return generateDynamicPath(Integer.valueOf(name.split("_")[1]));
	}
	
	public static String generateDynamicPath(int recordId) {
		return "/record/record.ftl?id=" + recordId;
	}
	
}
