package rest;

import java.sql.SQLException;
import java.util.ArrayList;

import DB.UsersUtil;
import DB.POJO.Item;
import DB.POJO.Order;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import logic.Authentication.Auth;
import logic.Authentication.AuthData;
import logic.Registration.Registration;
import rest.Token.TokenREST;

@Path("/")
public class Service {

    // authorization
    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(String authJSON, @Context HttpHeaders httpHeaders) {
        Jsonb jsonb = JsonbBuilder.create();
        AuthData authData;
        String token;
        String resultJSON;

        try {
            try {
                authData = jsonb.fromJson(authJSON, AuthData.class);
                token = httpHeaders.getHeaderString("Token");
                if (token.equals("") || token == null) { // if user doesnt have token
                    if (!Auth.authorize(authData)) { // if invalid login/password
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }
                    // authorized
                    token = TokenREST.generateToken(authData.getLogin());
                } else {
                    // user has token
                    // check if token is correct
                    if (!TokenREST.checkToken(authData.getLogin(), token)) { // if invalid token
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }
                }
            } catch (Exception e) {
                throw new Exception("AUTH JSON TRANSFORMATTING ERROR");
            }

            resultJSON = jsonb.toJson(token);

        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok(resultJSON).build(); // send token
    }

    // registration
    @POST
    @Path("/registration")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(String loginPasswordJSON) {
        Jsonb jsonb = JsonbBuilder.create();
        AuthData authData;
        try {
            try {
                authData = jsonb.fromJson(loginPasswordJSON, AuthData.class);

            } catch (Exception e) {
                throw new Exception("AUTH JSON TRANSFORMATTING ERROR");
            }

            if (!Registration.register(authData.getLogin(), authData.getPassword())) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();

    }

    @GET
    @Path("/orders")
    @Produces("application/json")
    public Response getOrdersData(@Context HttpHeaders httpHeaders) {
        Jsonb jsonb = JsonbBuilder.create();
        String login = httpHeaders.getHeaderString("Name");
        String token = httpHeaders.getHeaderString("Token");
        ArrayList<Order> ordersList;
        String resultJSON;
        if (!TokenREST.checkToken(login, token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        try {
            ordersList = UsersUtil.getOrdersPojo(login);
            resultJSON = jsonb.toJson(ordersList);
            return Response.ok(resultJSON).build();
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e1) {
            return Response.serverError().build();
        }

    }

    @POST
    @Path("/items")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getItemByType(String typeJSON, @Context HttpHeaders httpHeaders) {
        Jsonb jsonb = JsonbBuilder.create();
        String login = httpHeaders.getHeaderString("Name");
        String token = httpHeaders.getHeaderString("Token");
        String resultJSON;
        if (!TokenREST.checkToken(login, token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        try {
            Type type = jsonb.fromJson(typeJSON, Type.class);
            ArrayList<Item> allItems = UsersUtil.getPartsByType(type.getType());
            resultJSON = jsonb.toJson(allItems);
            return Response.ok(resultJSON).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (JsonbException e) {
            return Response.status(404).build();
        }
    }

}