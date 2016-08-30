package com.adanac.module.blog.thread;

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

import com.adanac.module.blog.orm.DaoFactory;
import org.apache.log4j.Logger;

import com.adanac.module.blog.api.HttpApiHelper;
import com.adanac.module.blog.config.Configuration;
import com.adanac.module.blog.dao.HtmlPageDao;

/**
 * @author adanac
 * @since 2015年5月25日 下午9:13:19
 */
public class BaiduPushTask implements Runnable {
	
	private static final Logger logger = Logger.getLogger(BaiduPushTask.class);

	@Override
	public void run() {
		boolean first = true;
		while (true) {
			try {
				if (first) {
					first = false;
					Thread.sleep(1000 * 60 * Integer.valueOf(Configuration.get("baidu.push.thread.wait.minutes")));
				}
				DaoFactory.getDao(HtmlPageDao.class).flush();
				HttpApiHelper.baiduPush(1);
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (Exception e) {
				logger.warn("baidu push failed ...", e);
				break;
			}
		}
	}
	
}
