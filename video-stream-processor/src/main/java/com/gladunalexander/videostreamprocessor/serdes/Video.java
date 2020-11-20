package com.gladunalexander.videostreamprocessor.serdes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {
    private UUID aggregateId;
    private UUID accountId;
    private String title;
}
