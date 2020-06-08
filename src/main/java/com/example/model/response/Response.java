package com.example.model.response;

import com.example.model.Extra;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "response")
public class Response {
    @XmlElement(name = "result-code")
    private String resultCode;

    @XmlElement(name = "extra")
    private List<Extra> extraList;

    public Response() {
        extraList = new ArrayList<Extra>();
    }

    public List<Extra> getExtraList() {
        return extraList;
    }


    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
