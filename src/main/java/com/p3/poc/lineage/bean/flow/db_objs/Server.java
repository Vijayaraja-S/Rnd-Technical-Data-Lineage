package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.*;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Server {
    private List<Database> databases;
}
