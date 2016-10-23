package org.arcanic.ramm.repository;

import org.arcanic.ramm.document.Memory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends MongoRepository<Memory, String> {

}
