package com.p3.poc.parser.bean;

import lombok.Data;

@Data
public class SelectQueryInfo {
    private boolean distinct;
    private SelectColumnInfo selectColumnInfo;

}
