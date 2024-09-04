package com.p3.poc.parser.bean.query.with;

import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WithInfo {
    private String id;
    private boolean isrecursive;
}
