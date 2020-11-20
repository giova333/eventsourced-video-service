package com.gladunalexander.eventwiter.account.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class RegisterAccountCommand {
    @NotNull private UUID accountId;
    @NotBlank private String name;
    @NotBlank private String description;
}
