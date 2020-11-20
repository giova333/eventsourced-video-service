package com.gladunalexander.eventwiter;

import java.time.Instant;

public interface Event<ID> {

    String getType();

    ID getAggregateId();

    Instant getOccurredAt();
}
