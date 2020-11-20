package com.gladunalexander.accountservice.persistance;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends Neo4jRepository<Account, String> {

    default Optional<Account> findById(UUID aggregateId) {
        return findById(aggregateId.toString());
    }
}
