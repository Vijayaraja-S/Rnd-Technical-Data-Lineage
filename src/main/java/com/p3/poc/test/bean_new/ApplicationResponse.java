package com.p3.poc.test.bean_new;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApplicationResponse {
    private int appId;
    private String applicationName;
    private List<SchemaResponse> schemaInfos;
}
