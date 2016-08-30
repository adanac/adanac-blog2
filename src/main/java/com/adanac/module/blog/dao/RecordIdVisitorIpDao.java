package com.adanac.module.blog.dao;

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

import com.adanac.module.blog.model.ViewMode;
import com.adanac.module.blog.orm.BaseDao;

import java.sql.ResultSet;
import java.util.Map;

/**
 * @author adanac
 * @since 2015年5月12日 下午5:47:40
 */
public class RecordIdVisitorIpDao extends BaseDao {

	public boolean save(final int recordId, final String visitorIp, final String username) {
		return saveIpRecord("record_id_visitor_ip", "record_id", recordId, visitorIp, username);
	} 
	
	public boolean exists(final int recordId, final String visitorIp, final String username) {
		return existsIpRecord("record_id_visitor_ip", "record_id", recordId, visitorIp, username);
	}

	@Override
	public Map<String, String> transfer(ResultSet resultSet, ViewMode viewMode) {
		throw new UnsupportedOperationException();
	}
}
