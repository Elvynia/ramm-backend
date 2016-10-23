package org.arcanic.ramm.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
public class Tag {
	private String _id;
	public String name;
	
	public String getId() {
		return this._id;
	}
}
