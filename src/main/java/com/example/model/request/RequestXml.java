package com.example.model.request;

import com.example.model.Extra;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestXml {
    @XmlElement(name = "request-type")
    private String requestType;

    @XmlElement(name = "extra")
    private List<Extra> extraList;

    public RequestXml() {
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public List<Extra> getExtraList() {
        return extraList;
    }

    public void setExtraList(List<Extra> extraList) {
        this.extraList = extraList;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType='" + requestType + '\'' +
                '}';
    }
}
