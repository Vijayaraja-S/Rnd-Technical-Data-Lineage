package com.p3.poc.parser.bean.query.query_body;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseQueryBodyInfo{
    private QueryBodyType queryBodyType;
}
