package com.p3.poc.test.testing_bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableInformation {
    @Builder.Default private String tableFullName = "";
    @Builder.Default private String aliasNameOrUniqueId = "";
    @Builder.Default private Boolean hasAllColumnsSelected = false;
}
