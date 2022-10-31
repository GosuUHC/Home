package rest;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import model.interfaces.delete.IOrderDeleter;
import model.interfaces.get.IItemGetter;
import model.interfaces.get.IOrdersGetter;
import model.interfaces.post.IOrderPoster;
import other.implementation.authentication.AuthData;
import other.interfaces.IAuthorizer;
import other.interfaces.IRegistrator;
import rest.pojo.idTypeAndCount;
import rest.token.TokenREST;

@Path("/")
public class Service {

    @Inject
    IAuthorizer authorizer;
    @Inject
    IRegistrator registrator;

    @Inject
    IOrdersGetter ordersGetter;
    @Inject
    IOrderPoster orderPoster;
    @Inject
    IOrderDeleter orderDeleter;

    @Inject
    IItemGetter itemGetter;

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
                    if (!authorizer.authorize(authData)) { // if invalid login/password // // ///////////////////
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
                throw new Exception("JSON TRANSFORMATTING ERROR");
            }

            if (!registrator.register(authData)) {
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
        String resultJSON;

        if (!TokenREST.checkToken(login, token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try {
            resultJSON = jsonb.toJson(ordersGetter.findAllByUsername(login));
            return Response.ok(resultJSON).build();
        } catch (JsonbException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

    @POST
    @Path("/orders")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createNewOrder(String itemidTypeAndCount, @Context HttpHeaders httpHeaders) {
        Jsonb jsonb = JsonbBuilder.create();
        String login = httpHeaders.getHeaderString("Name");
        String token = httpHeaders.getHeaderString("Token");

        if (!TokenREST.checkToken(login, token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        idTypeAndCount idTypeCount = jsonb.fromJson(itemidTypeAndCount, idTypeAndCount.class);

        try {
            orderPoster.addNewOrder(login, idTypeCount.getId(), idTypeCount.getCount(), idTypeCount.getType());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        return Response.ok("Order added").build();

    }

    @DELETE
    @Path("/orders")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteOrder(@Context HttpHeaders httpHeaders) {
        String login = httpHeaders.getHeaderString("Name");
        String token = httpHeaders.getHeaderString("Token");
        int id = Integer.valueOf(httpHeaders.getHeaderString("id"));

        if (!TokenREST.checkToken(login, token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        try {
            orderDeleter.deleteById(id);
            return Response.ok("ORDER DELETED").build();
        } catch (JsonbException e) {
            return Response.serverError().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET // get type=...
    @Path("/items")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getItemByType(@Context HttpHeaders httpHeaders) {
        Jsonb jsonb = JsonbBuilder.create();
        String login = httpHeaders.getHeaderString("Name");
        String token = httpHeaders.getHeaderString("Token");
        String type = httpHeaders.getHeaderString("type");

        String resultJSON;
        if (!TokenREST.checkToken(login, token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        try {
            resultJSON = jsonb.toJson(itemGetter.getAllByType(type));
            return Response.ok(resultJSON).build();
        } catch (JsonbException e) {
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
