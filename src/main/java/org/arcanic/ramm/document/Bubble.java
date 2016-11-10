package org.arcanic.ramm.document;

import javax.persistence.Transient;

import org.arcanic.ramm.util.Vector3;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bubbles")
public class Bubble extends MongoDocument {
	@Transient
	public int color;
	public String content;
	@Transient
	public Vector3 position;
}
