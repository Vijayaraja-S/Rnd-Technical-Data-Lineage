package com.p3.poc.parser.bean.parsing_details;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JoinDetailsInfo {
    @Builder.Default private String detailsId="";
    @Builder.Default private String joinId="";
    @Builder.Default private String joinType = "";
    @Builder.Default private String joinEquation="";
    @Builder.Default private String joinCondition="";
}
