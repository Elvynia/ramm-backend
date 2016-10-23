package org.arcanic.ramm.document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "memories")
public class Memory extends MongoDocument {
	public String content;
	@DBRef
	public List<Tag> tags;
	
	
}
