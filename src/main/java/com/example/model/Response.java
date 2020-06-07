package com.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "response")
public class Response {
    @XmlElement(name = "result-code")
    private String resultCode;

    public Response(String resultCode) {
        this.resultCode = resultCode;
    }
    //    @XmlElement(name = "extra")
//    private List<Extra> extraList;


    public Response() {
    }

}
