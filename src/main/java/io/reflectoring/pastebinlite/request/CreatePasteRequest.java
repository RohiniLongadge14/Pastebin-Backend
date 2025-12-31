package io.reflectoring.pastebinlite.request;

import lombok.Data;

@Data
public class CreatePasteRequest {

    private String content;
    private Integer ttl_seconds;
    private Integer max_views;
}
