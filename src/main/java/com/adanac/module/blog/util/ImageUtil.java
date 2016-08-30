package com.adanac.module.blog.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

import com.adanac.module.blog.cache.CacheManager;
import com.adanac.module.blog.config.Configuration;
import com.adanac.module.blog.model.Type;

/**
 * @author adanac
 * @since 5/18/2015 2:33 PM
 */
public abstract class ImageUtil {

	private static final String BASE_PATH = "resources/img/common/";

	public static String generateDir() {
		String date = new SimpleDateFormat("yyyyMM").format(new Date());
		File dir = new File(Configuration.getContextPath("image/" + date));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return date;
	}

	public static String generatePath(String origin) {
		String suffix = origin.substring(origin.lastIndexOf("."), origin.length());
		if (suffix.length() > 4) {
			suffix = ".jpg";
		}
		String path = "image/" + generateDir() + "/" + new SimpleDateFormat("ddHHmmssSSS").format(new Date()) + suffix;
		return path;
	}

	public static void loadArticleImages() {
		CacheManager.getConcurrentHashMapCache().set("articleImages", getAllArticleImages());
	}

	public static String randomArticleImage(String subject) {
		return randomArticleImage(subject, Type.article);
	}

	@SuppressWarnings("unchecked")
	public static String randomArticleImage(String subject, Type articleType) {
		if (articleType == Type.novel) {
			if (subject.startsWith("一个屌丝程序猿的人生")) {
				return Configuration.getSiteUrl(BASE_PATH + "novel_2.jpg");
			} else if (subject.startsWith("［异能程序员］")) {
				return Configuration.getSiteUrl(BASE_PATH + "novel_1.jpg");
			}
		}
		List<String> articleImages = (List<String>) CacheManager.getConcurrentHashMapCache().get("articleImages");
		if (articleImages == null || articleImages.size() == 0) {
			return null;
		}
		return articleImages.get(new Random().nextInt(articleImages.size()));
	}

	static {
		loadArticleImages();
	}

	private static List<String> getAllArticleImages() {
		File imageDir = new File(Configuration.getContextPath(BASE_PATH));
		File[] articleImages = imageDir.listFiles(pathname -> {
			if (pathname.getName().startsWith("article_") && pathname.getName().endsWith(".jpg")) {
				return true;
			}
			return false;
		});
		List<String> nameList = new ArrayList<>();
		if (articleImages != null) {
			for (int i = 0; i < articleImages.length; i++) {
				nameList.add(Configuration.getSiteUrl(BASE_PATH + articleImages[i].getName()));
			}
		}
		return nameList;
	}

}
