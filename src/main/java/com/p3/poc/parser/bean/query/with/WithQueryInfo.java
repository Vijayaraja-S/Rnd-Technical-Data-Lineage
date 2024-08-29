package com.p3.poc.parser.bean.query.with;

import com.p3.poc.parser.bean.query.BaseQueryInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithQueryInfo {
    private UUID beanId;
    private String name;
    private BaseQueryInfo query;
}
