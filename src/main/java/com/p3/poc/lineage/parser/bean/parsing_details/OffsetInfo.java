package com.p3.poc.lineage.parser.bean.parsing_details;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OffsetInfo {
    @Builder.Default private String offset = "";
}
