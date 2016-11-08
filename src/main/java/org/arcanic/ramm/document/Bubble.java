package org.arcanic.ramm.document;

import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bubbles")
public class Bubble extends MongoDocument {
	public String content;
	@Transient
	public String color;
}
