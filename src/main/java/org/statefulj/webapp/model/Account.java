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
package org.statefulj.webapp.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.statefulj.persistence.mongo.model.StatefulDocument;

@Document
public class Account extends StatefulDocument {
	
	public static final String LOAN = "Loan";
	
	// States
	//
	public static final String NON_EXISTENT = "NON_EXISTENT";
	public static final String ACTIVE = "ACTIVE";
	public static final String DELETED = "DELETED";
	public static final String APPROVAL_PENDING = "APPROVAL_PENDING";
	public static final String REJECTED = "REJECTED";
	
	@Id
	String id;

	@DBRef(lazy=true)
	User owner;
	
	BigDecimal amount;
	
	String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public String toString() {
		return getType() + ": state=" + getStateDocument().getState();
	}
}
