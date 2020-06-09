package com.example.model.response;

import com.example.model.Extra;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseXml {
    @XmlElement(name = "result-code")
    private String resultCode;

    @XmlElement(name = "extra")
    private List<Extra> extraList;

    public String getResultCode() {
        return resultCode;
    }

    public ResponseXml() {
        extraList = new ArrayList<Extra>();
    }

    public List<Extra> getExtraList() {
        return extraList;
    }


    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
