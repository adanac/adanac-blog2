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

import javax.servlet.http.HttpServletRequest;

import com.adanac.module.blog.dao.*;
import com.adanac.module.blog.orm.DaoFactory;
import org.apache.log4j.Logger;

import com.adanac.module.blog.generator.Generators;
import com.adanac.module.blog.util.HttpUtil;

/**
 * @author adanac
 * @since 5/8/2015 4:12 PM
 */
public class CommentRemark extends AbstractServlet {

	private static final Logger logger = Logger.getLogger(CommentRemark.class);
	
    @Override
    protected void service() throws IOException {
    	HttpServletRequest request = getRequest();
		if ("1".equals(request.getParameter("type"))) {
			handleComment(request);
		} else if ("2".equals(request.getParameter("type"))) {
			handleAnswer(request);
		}
    }

	private void handleComment(HttpServletRequest request) {
		Integer articleId = Integer.valueOf(request.getParameter("articleId"));
		Integer commentId = Integer.valueOf(request.getParameter("commentId"));
		String column = request.getParameter("column");
		if (logger.isInfoEnabled()) {
			logger.info("CommentRemark param : commentId = " + commentId + "   , column = " + column);
		}
		String ip = HttpUtil.getVisitorIp(request);
		String username = getUsername();
		if (DaoFactory.getDao(CommentIdVisitorIpDao.class).exists(commentId, ip, username)) {
			writeText("exists");
			if (logger.isInfoEnabled()) {
				logger.info(ip + " has remarked...");
			}
			return ;
		} else {
			DaoFactory.getDao(CommentIdVisitorIpDao.class).save(commentId, ip, username);
		}
		boolean result = DaoFactory.getDao(CommentDao.class).updateCount(commentId, column);
		if (!result) {
			logger.error("updateCount error!");
			return;
		}
		if (result && logger.isInfoEnabled()) {
			logger.info("updateCount success!");
		}
		Generators.generateArticle(articleId);
		writeText("success");
	}

	private void handleAnswer(HttpServletRequest request) {
		Integer questionId = Integer.valueOf(request.getParameter("questionId"));
		Integer commentId = Integer.valueOf(request.getParameter("commentId"));
		String column = request.getParameter("column");
		if (logger.isInfoEnabled()) {
			logger.info("CommentRemark param : commentId = " + commentId + "   , column = " + column);
		}
		String ip = HttpUtil.getVisitorIp(request);
		String username = getUsername();
		if (DaoFactory.getDao(AnswerIdVisitorIpDao.class).exists(commentId, ip, username)) {
			writeText("exists");
			if (logger.isInfoEnabled()) {
				logger.info(ip + " has remarked...");
			}
			return ;
		} else {
			DaoFactory.getDao(AnswerIdVisitorIpDao.class).save(commentId, ip, username);
		}
		boolean result = DaoFactory.getDao(AnswerDao.class).updateCount(commentId, column);
		if (!result) {
			logger.error("updateCount error!");
			return;
		}
		if (result && logger.isInfoEnabled()) {
			logger.info("updateCount success!");
		}
		Generators.generateQuestion(questionId);
		writeText("success");
	}

}

