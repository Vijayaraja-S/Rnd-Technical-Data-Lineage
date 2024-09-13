package com.p3.poc.chart;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Data
@Getter
@Setter
public class SunburstNode {
    private String id;
    private String parent;
    private String name;
    private int value;

    public SunburstNode(String id, String parent, String name, int value) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.value = value;
    }

    public JSONObject toJson() {
        JSONObject nodeJson = new JSONObject();
        nodeJson.put("id", this.id);

        if (this.parent != null && !this.parent.isEmpty()) {
            nodeJson.put("parent", this.parent);
        }

        nodeJson.put("name", this.name);

        if (this.value != 0) {
            nodeJson.put("value", this.value);
        }

        return nodeJson;
    }
}
