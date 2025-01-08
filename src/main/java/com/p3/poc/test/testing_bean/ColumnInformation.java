package com.p3.poc.test.testing_bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ColumnInformation {
    @Builder.Default private String columnName="";
    @Builder.Default private String aliasOrUniqueId = "";
}
