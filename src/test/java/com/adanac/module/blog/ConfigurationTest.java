package com.adanac.module.blog;

import org.junit.Assert;
import org.junit.Test;

import com.adanac.module.blog.config.Configuration;

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

/**
 *
 * @author adanac
 * @since 5/7/2015 10:19 AM
 */
public class ConfigurationTest {

	@Test
	public void test() {
		Assert.assertEquals("value", Configuration.get("key"));
	}

}
