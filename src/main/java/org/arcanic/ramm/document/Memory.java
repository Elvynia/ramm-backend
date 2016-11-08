package org.arcanic.ramm.document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "memories")
public class Memory extends MongoDocument {
	@DBRef
	public Bubble root;
	@DBRef
	public List<Bubble> children;
	
	
}
