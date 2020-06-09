package com.example;

import com.example.configuration.ConfigDbTables;
import com.example.db.balance.IBalanceDB;
import com.example.db.user.IUserDb;
import com.example.db.user.UserDAO;
import com.example.model.request.ProcessedRequest;
import com.example.model.request.RequestTypes;
import com.example.model.request.RequestXml;
import com.example.model.response.IResponse;
import com.example.model.response.ResponseXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Arrays;

@Path("myresource")
public class MyResource {
    private static final Logger log = LoggerFactory.getLogger(MyResource.class);

    public MyResource(DataSource dataSource, ConfigDbTables configDbTables) {
        this(new JdbcTemplate(dataSource), configDbTables);
    }

    public MyResource(JdbcTemplate jdbcTemplate, ConfigDbTables configDbTables) {
        this(new UserDAO(jdbcTemplate, configDbTables));
    }

    public MyResource(IUserDb userDb) {
        this.userDAO = userDb;
    }

    private IUserDb userDAO;
    private IBalanceDB balanceDAO;

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public ResponseXml addUser(String xmRequest) throws Exception {
        log.debug("Request: " +xmRequest);
        RequestXml requestXml = unMarshall(xmRequest);
        IResponse resultResponse = handleWithDB(requestXml);
        log.debug("ResultCode " + resultResponse.response().getResultCode());
        return resultResponse.response();
    }

    private IResponse handleWithDB(RequestXml requestXml) throws Exception {
        IResponse resultResponse;
        log.debug("Request-type " + requestXml.getRequestType());
        if (requestXml.getRequestType().equals(RequestTypes.ADD_USER.getName())) {
            resultResponse = userDAO.addUser(new ProcessedRequest(requestXml));
        } else if (requestXml.getRequestType().equals(RequestTypes.GET_BALANCE.getName())) {
            resultResponse = balanceDAO.getBalance(new ProcessedRequest(requestXml));
        } else {
            throw new RuntimeException("Unknown request type: " + requestXml.getRequestType() + " must be in " + RequestTypes.listOfNames());
        }
        return resultResponse;
    }

    private RequestXml unMarshall(String xmRequest) {
        RequestXml requestXml;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RequestXml.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xmRequest);
            requestXml = (RequestXml) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while unmarshaling");
        }
        return requestXml;
    }
}
