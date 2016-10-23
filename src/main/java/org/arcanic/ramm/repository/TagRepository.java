package org.arcanic.ramm.repository;

import org.arcanic.ramm.document.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<Tag, String>{

}
