package com.example;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URL;

import com.example.model.request.RequestXml;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.*;

@Provider
@Consumes("application/xml")
public class ValidatingReader implements MessageBodyReader<RequestXml> {

    @Context
    protected Providers providers;

    private Schema schema;
    private JAXBContext jaxbContext;

    public ValidatingReader() {
        try {
            JAXBContext jc = JAXBContext.newInstance(RequestXml.class);
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL schemaURL = null; // URL for your XML schema
//            schema = sf.newSchema(schemaURL);
            schema = sf.newSchema();
//            MySchemaOutputResolver sor = new MySchemaOutputResolver();
//            jaxbContext.generateSchema( sor);
//            schema = sor.getSchema();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return arg0 == RequestXml.class;
    }

    public RequestXml readFrom(Class<RequestXml> arg0, Type arg1, Annotation[] arg2, MediaType arg3, MultivaluedMap<String, String> arg4, InputStream arg5)
            throws IOException, WebApplicationException {
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);
            return (RequestXml) unmarshaller.unmarshal(arg5);
        } catch(JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}