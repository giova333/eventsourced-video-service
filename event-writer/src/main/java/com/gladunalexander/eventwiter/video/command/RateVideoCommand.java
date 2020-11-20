package com.gladunalexander.eventwiter.video.command;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class RateVideoCommand {
    @NotNull private UUID videoId;
    @NotNull private UUID accountId;
    @Min(1) @Max(5) private double rating;
}
