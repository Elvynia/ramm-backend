package org.arcanic.ramm.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
public class Tag extends MongoDocument {
	public String name;
}
