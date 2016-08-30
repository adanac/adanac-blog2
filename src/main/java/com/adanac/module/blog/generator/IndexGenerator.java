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
import com.adanac.module.blog.freemarker.IndexHelper;

/**
 * @author adanac
 * @since 5/7/2015 3:06 PM
 */
public class IndexGenerator implements Generator {

    @Override
    public int order() {
        return 0;
    }

    public void generate() {
        Writer writer = null;
        try {
            Map<String, Object> data = FreemarkerHelper.buildCommonDataMap(VIEW_MODE);
            IndexHelper.putDataMap(data, VIEW_MODE);
            String htmlPath = Configuration.getContextPath(IndexHelper.generateStaticPath());
            writer = new FileWriter(htmlPath);
            FreemarkerHelper.generate("index", writer, data);
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
