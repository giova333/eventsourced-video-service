package com.gladunalexander.videoservice.external;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AccountResponse {
    UUID id;
}
