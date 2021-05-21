package com.gladunalexander.videoservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "account-service", url = "http://localhost:8084/accounts")
public interface AccountClient {

    @GetMapping("/{id}/subscriptions")
    List<AccountResponse> getSubscriptions(@PathVariable UUID id);
}
