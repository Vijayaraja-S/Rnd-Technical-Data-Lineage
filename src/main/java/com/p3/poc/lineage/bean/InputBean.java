package com.p3.poc.lineage.bean;

import lombok.Data;

@Data
public class InputBean {
    private boolean sunburst;
    private String searchName;
    private String searchId;
    private String inputFilePath;
    private String metadataFilePath;
    private boolean doMetadataValidation;
}
