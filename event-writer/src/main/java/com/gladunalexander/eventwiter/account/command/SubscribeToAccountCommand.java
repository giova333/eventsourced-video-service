package com.gladunalexander.eventwiter.account.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class SubscribeToAccountCommand {
    @NotNull private UUID sourceAccountId;
    @NotNull private UUID targetAccountId;
}
