package com.gladunalexander.accountservice.persistance;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NodeEntity
@NoArgsConstructor
public class Account {

    @Id
    private String id;

    private String name;

    private String description;

    private Instant createdAt;

    @Relationship(type = "SUBSCRIPTION", direction = Relationship.OUTGOING)
    private List<Account> subscriptions = new ArrayList<>();

    public Account(UUID id, String name, String description, Instant createdAt) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public void subscribeTo(Account account) {
        this.subscriptions.add(account);
    }

    public UUID getId() {
        return UUID.fromString(id);
    }
}

