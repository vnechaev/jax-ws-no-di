package com.example;

import com.example.configuration.ConfigDbTables;
import com.example.db.IUserDb;
import com.example.db.UserDAO;
import com.example.model.Extra;
import com.example.model.addUser.ResultCode;
import com.example.model.request.ProcessedRequest;
import com.example.model.request.RequestXml;
import com.example.model.response.AddUserResponse;
import com.example.model.response.IResponse;
import com.example.model.response.ResponseXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
    private static final Logger logger = LoggerFactory.getLogger(MyResource.class);

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

    @POST
//    @Path("/addUser")
    @Produces(MediaType.APPLICATION_XML)
//    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public ResponseXml addUser(String xmRequest) throws Exception {
        logger.debug(xmRequest);
        RequestXml requestXml = unMarshall(xmRequest);
        logger.debug(requestXml.getRequestType());
        ProcessedRequest addUserRequest = new ProcessedRequest(requestXml);
        ResultCode resultCode = userDAO.addUser(addUserRequest);
        AddUserResponse addUserResponse = new AddUserResponse(resultCode.getCode());
        logger.debug("ResultCode " + addUserResponse.response().getResultCode());
        return addUserResponse.response();
    }

    @POST
    @Path("/addUser")
    @Produces(MediaType.APPLICATION_XML)
//    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public ResponseXml getBalance(String xmRequest) throws Exception {
        logger.debug(xmRequest);
        RequestXml requestXml = unMarshall(xmRequest);

        logger.debug(requestXml.getRequestType());
        ProcessedRequest addUserRequest = new ProcessedRequest(requestXml);
        IResponse response = userDAO.getBalance(addUserRequest);
        logger.debug(response.response().getResultCode());
        return response.response();
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


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ResponseXml heartBeat() {
        ResponseXml responseXml = new ResponseXml();
        responseXml.setResultCode("234");
        Extra extra = new Extra();
        extra.setName("balance");
        extra.setValue("100");
        responseXml.getExtraList().add(extra);
        return responseXml;
    }
}
