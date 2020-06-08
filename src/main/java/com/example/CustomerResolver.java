package com.example;

import com.example.model.request.Request;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class CustomerResolver {
    @Provider
    @Produces("application/xml")
    public class CustomerResolver123 implements ContextResolver<JAXBContext> {
        private JAXBContext ctx;

        public CustomerResolver123() throws JAXBException {
            this.ctx =  JAXBContext.newInstance(Request.class);; // initialize it the way you want
        }


        public JAXBContext getContext(Class<?> type) {
            if (type.equals(Request.class)) {
                return ctx;
            } else {
                return null;
            }
        }
    }
}
