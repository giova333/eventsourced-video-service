package com.gladunalexander.videostreamprocessor.serdes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountAndSum {
    private long count;
    private double sum;
}
