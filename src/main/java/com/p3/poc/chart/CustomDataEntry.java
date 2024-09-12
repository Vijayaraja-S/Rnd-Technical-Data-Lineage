package com.p3.poc.chart;

class CustomDataEntry extends DataEntry {
        CustomDataEntry(String name, String id) {
            setValue("name", name);
            setValue("id", id);
        }

        CustomDataEntry(String name, String id, String parent) {
            setValue("name", name);
            setValue("id", id);
            setValue("parent", parent);
        }

        CustomDataEntry(String name, String id, String parent, int value) {
            setValue("name", name);
            setValue("id", id);
            setValue("parent", parent);
            setValue("value", value);
        }
    }
