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
import java.util.Map;

import javax.servlet.ServletException;

/**
 * @author adanac
 * @since 5/27/2015 1:51 PM
 */
public class Logout extends AbstractServlet {

	private static final long serialVersionUID = -3057874850069731524L;

	@Override
    protected void service() throws ServletException, IOException {
        Map<String, String> user = getUser();
        if (user != null ) {
            getRequest().getSession(false).removeAttribute("user");
        }
        writeText("success");
    }

}
