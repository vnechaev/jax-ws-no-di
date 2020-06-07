package com.example.model;

import javax.xml.bind.annotation.*;

public class Extra {

    @XmlAttribute
    private String name;
    @XmlValue
    private String value;

    public Extra() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
