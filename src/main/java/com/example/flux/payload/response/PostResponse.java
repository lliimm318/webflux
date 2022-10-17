package com.example.flux.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PostResponse {

    private Integer id;
    private String title;
    private String content;
    private LocalDate date;

}
