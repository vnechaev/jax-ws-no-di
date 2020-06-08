package com.example;

import com.example.model.request.Request;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class test {
    public static void main(String[] args) {
        String requestStr= "<?xml version=\"1.0\" encoding=\"utf-8\" ?><request><request-type>CREATE-AGT</request-type><extra name=\"login\">123456</extra><extra name=\"password\">pwd</extra></request>";
        Request request;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader("xml string here");
            request = (Request) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while unmarshaling");
        }
    }
}
