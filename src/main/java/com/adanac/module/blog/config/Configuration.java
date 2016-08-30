package com.adanac.module.blog.config;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

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

import freemarker.template.Version;

/**
 * @author adanac
 * @since 5/7/2015 10:05 AM
 */
public abstract class Configuration {

	private static final Logger logger = Logger.getLogger(Configuration.class);

	private static final Properties properties = new Properties();

	private static final freemarker.template.Configuration configuration;

	private static ServletContext servletContext;

	private static final String system;

	static {
		system = System.getProperty("os.name").toLowerCase();
		if (logger.isInfoEnabled()) {
			logger.info("os.name = " + system);
		}
		File classpath = getClasspathFile("conf");
		File[] propertyFiles = classpath.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".properties") && !file.getName().startsWith("log4j");
			}
		});
		for (int i = 0; i < propertyFiles.length; i++) {
			try {
				properties.load(new FileInputStream(propertyFiles[i]));
				if (logger.isInfoEnabled()) {
					logger.info("load properties file success :" + propertyFiles[i]);
				}
			} catch (IOException e) {
				logger.warn("load properties file failed , skiped :" + propertyFiles[i], e);
			}
		}
		configuration = new freemarker.template.Configuration(new Version(2, 3, 22));
	}

	public static ClassLoader getClassLoader() {
		return Configuration.class.getClassLoader();
	}

	public static File getClasspathFile(String path) {
		return new File(getClassLoader().getResource(path).getFile());
	}

	public static void init(ServletContext servletContext) {
		Configuration.servletContext = servletContext;
		try {
			configuration.setDirectoryForTemplateLoading(new File(getContextPath()));
			configuration.setDefaultEncoding("UTF-8");
			if (logger.isInfoEnabled()) {
				logger.info("templateBasePath set success ... ");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getContextPath() {
		return getContextPath("");
	}

	public static String getContextPath(String path) {
		String contextFile = servletContext.getRealPath(path);
		if (contextFile == null && !path.startsWith("/")) {
			contextFile = servletContext.getRealPath("/" + path);
		} else if (contextFile == null && path.startsWith("/")) {
			contextFile = servletContext.getRealPath(path.substring(1));
		}
		if (logger.isInfoEnabled()) {
			logger.info("contextFile : " + contextFile);
		}
		return contextFile;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static freemarker.template.Configuration getFreemarkerConfiguration() {
		return configuration;
	}

	public static boolean isProductEnv() {
		return system.contains("linux");
	}

	public static String getSiteUrl() {
		return isProductEnv() ? get("context.path.product") : get("context.path");
	}

	public static String getSiteUrl(String path) {
		return getSiteUrl() + (path.startsWith("/") ? path : ("/" + path));
	}

}
