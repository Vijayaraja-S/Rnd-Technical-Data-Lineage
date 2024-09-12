package com.p3.poc.result_bean;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class BeanBuilder {
    private BeanBuilder() {
    }

    public static InputBean buildInputBean(String configFile) throws IOException {
        log.error("Path : {}", configFile);
        try (FileInputStream input = new FileInputStream(configFile)) {
            String yamlString = new String(input.readAllBytes());
            return new Yaml().loadAs(yamlString, InputBean.class);
        } catch (IOException exception) {
            log.error("Exception : {}", exception.getMessage(), exception);
            throw exception;
        }
    }
}
