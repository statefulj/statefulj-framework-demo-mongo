/***
 * 
 * Copyright 2014 Andrew Hall
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.statefulj.webapp.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.userdetails.User;
import org.statefulj.webapp.model.Account;
import org.statefulj.webapp.model.Notification;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(
	value={"org.statefulj.webapp"}
)
@EnableMongoAuditing
public class DBConfig extends AbstractMongoConfiguration {
	
	public final static String DB_NAME = "statefulj-demo";

	@Override
	public Mongo mongo() throws Exception {
	    return new MongoClient("localhost", 37017);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#getDatabaseName()
	 */
	@Override
	protected String getDatabaseName() {
		return DB_NAME;
	}
	
    @PostConstruct
    public void init() throws Exception {
    	
    	// Clean out the collections on startup
    	//
    	this.mongoTemplate().dropCollection(User.class);
    	this.mongoTemplate().dropCollection(Account.class);
    	this.mongoTemplate().dropCollection(Notification.class);
    }


}