package com.adanac.module.blog.generator;

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

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.adanac.module.blog.config.Configuration;
import com.adanac.module.blog.freemarker.FreemarkerHelper;

/**
 * @author adanac
 * @since 5/7/2015 6:06 PM
 */
public class CommonGenerator implements Generator {

	@Override
	public int order() {
		return 0;
	}

	@Override
	public void generate() {
		Writer writer = null;
		try {
			Map<String, Object> data = FreemarkerHelper.buildCommonDataMap("common", VIEW_MODE);
			String htmlPath = Configuration.getContextPath("/html/insert_code.html");
			writer = new FileWriter(htmlPath);
			FreemarkerHelper.generate("WEB-INF/view/common", "insert_code", writer, data);

			htmlPath = Configuration.getContextPath("/html/upload_image.html");
			writer = new FileWriter(htmlPath);
			FreemarkerHelper.generate("WEB-INF/view/common", "upload_image", writer, data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
