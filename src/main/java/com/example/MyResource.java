package com.example;

import com.example.db.IUserDb;
import com.example.db.UserDAO;
import com.example.model.Extra;
import com.example.model.Response;
import com.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    public MyResource(DataSource dataSource) {
        this(new JdbcTemplate(dataSource));
    }

    public MyResource(JdbcTemplate jdbcTemplate) {
        this(new UserDAO(jdbcTemplate));
    }

    public MyResource(IUserDb userDb) {
        this.userDAO = userDb;
    }

    private IUserDb userDAO;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    @Produces({ MediaType.TEXT_XML })
//    @Produces(MediaType.APPLICATION_XML)
//    public Response getIt() {
////        boolean result = userDAO.addUser(new User("Bill", "gates"));
////        if (result) {
////            System.out.println("Bill was added");
////        }
////        String response = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
////                "<response>\n" +
////                " <result-code>0</result-code>\n" +
////                "</response>";
////        System.out.println("response = " + response);
////        return response;
//        Response response = new Response();
//        response.setResultCode("234");
//        return response;
//    }
    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    @Produces({ MediaType.TEXT_XML })
    @Produces(MediaType.APPLICATION_XML)
    public Response getsfd() {

        Response response = new Response();
        response.setResultCode("234");
        Extra extra = new Extra();
        extra.setName("balance");
        extra.setValue("100");
        response.getExtraList().add(extra);
//        response.setExtraList(Collections.singletonList(extra));
        return response;
    }


    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_XML)
    public String addUser() {
        boolean result = userDAO.addUser(new User("Bill", "gates"));
        if (result) {
            System.out.println("Bill was added");
        }
        String response = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<response>\n" +
                " <result-code>0</result-code>\n" +
                "</response>";
        System.out.println("response = " + response);
        return response;
    }
}
