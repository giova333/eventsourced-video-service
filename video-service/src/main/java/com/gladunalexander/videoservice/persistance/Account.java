package com.gladunalexander.videoservice.persistance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * One of the important use cases is retrieving videos of the followed users but
 * since wi did not have info about account subscriptions in the Video Service we had to make a synchronous call
 * to the Account Service and fetch missing data from there. Synchronous communication between services reduces
 * availability of a calling service and add coupling to the system. To avoid that we decided to store the replica
 * of the account subscriptions in the Video Service.
 *
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    private UUID id;

    //Don't use in production
    @ElementCollection
    private List<UUID> subscriptions = new ArrayList<>();

    public Account(UUID id) {
        this.id = id;
    }

    public void addSubscription(UUID targetAccountId) {
        subscriptions.add(targetAccountId);
    }
}
