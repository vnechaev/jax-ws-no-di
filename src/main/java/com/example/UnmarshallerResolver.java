package com.example;

import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;
import javax.xml.bind.*;
import javax.xml.bind.helpers.DefaultValidationEventHandler;

@Provider
//@Consumes({"application/yaml", MediaType.TEXT_PLAIN})
public class UnmarshallerResolver implements ContextResolver<Unmarshaller> {

    @Context Providers providers;

    @Override
    public Unmarshaller getContext(Class<?> type) {
        try {
            ContextResolver<JAXBContext> resolver = providers.getContextResolver(JAXBContext.class, MediaType.APPLICATION_XML_TYPE);
            JAXBContext jaxbContext;
            if(null == resolver || null == (jaxbContext = resolver.getContext(type))) {
                jaxbContext = JAXBContext.newInstance(type);
            }
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setEventHandler(new DefaultValidationEventHandler());
            return unmarshaller;
        } catch(JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}