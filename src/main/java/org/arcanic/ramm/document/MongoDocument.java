package org.arcanic.ramm.document;

public abstract class MongoDocument {
	private String _id;

	public String getId() {
		return this._id;
	}

	public void setId(final String id) {
		this._id = id;
	}
}
