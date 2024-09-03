package com.p3.poc.lineage.bean;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Root{
    private int code;
    private Data data;
    private String sessionId;
}
