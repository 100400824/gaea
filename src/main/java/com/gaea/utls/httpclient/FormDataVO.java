package com.gaea.utls.httpclient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class FormDataVO extends RequestVO {

    private Map<String ,Object> content;

}
