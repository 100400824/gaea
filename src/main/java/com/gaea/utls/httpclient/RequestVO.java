package com.gaea.utls.httpclient;

import lombok.Data;

import java.util.Map;

@Data
public class RequestVO {

    private String url;
    private Map<String ,Object> headers;
}
