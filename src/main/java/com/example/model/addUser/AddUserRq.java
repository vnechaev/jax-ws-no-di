package com.example.model.addUser;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddUserRq {
//<request-type>CREATE-AGT</request-type>
//<extra name="login">123456</extra>
//<extra name="password">pwd</extra>
    private String requestType;
    private String login;
    private String password;

    public AddUserRq(String requestType, String login, String password) {
        this.requestType = requestType;
        this.login = login;
        this.password = password;
    }


}
