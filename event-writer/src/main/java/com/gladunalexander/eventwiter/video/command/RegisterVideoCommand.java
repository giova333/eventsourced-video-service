package com.gladunalexander.eventwiter.video.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class RegisterVideoCommand {
    @NotNull  private UUID accountId;
    @NotBlank private String imageUrl;
    @NotBlank private String title;
    @NotBlank private String videoUrl;
}
