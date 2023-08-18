package com.happyineo.addribute.Beans;

import java.util.HashMap;
import java.util.Map;

public class AttributeConfig {
    private Map<String,Attribute> attributes = new HashMap<>();

    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Attribute> attributes) {
        this.attributes = attributes;
    }
}
