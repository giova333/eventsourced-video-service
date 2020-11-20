package com.gladunalexander.eventwiter.comment.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class AddCommentCommand {
    @NotNull private UUID videoId;
    @NotNull private UUID accountId;
    @NotBlank private String comment;
}
