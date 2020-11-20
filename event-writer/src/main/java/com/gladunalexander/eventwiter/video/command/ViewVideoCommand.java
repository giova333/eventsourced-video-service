package com.gladunalexander.eventwiter.video.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ViewVideoCommand {
    @NotNull private UUID videoId;
    @NotNull private UUID accountId;
}
