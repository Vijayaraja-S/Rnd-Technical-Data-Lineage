package com.p3.poc.parser.bean;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectQueryInfo {
    @Builder.Default private boolean distinct=false;
    private List<SelectColumnInfo> selectColumnInfo;
}
