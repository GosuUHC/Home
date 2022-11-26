package rest.path;

import builder.Built;
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
import model.interfaces.delete.IOrdersDeleter;
import model.interfaces.get.IItemsGetter;
import model.interfaces.get.IOrdersGetter;
import model.interfaces.post.IOrdersPoster;
import other.implementation.authentication.User;
import other.interfaces.IAuthorizer;
import other.interfaces.IRegistrator;
import rest.interceptor.TokenRequired;
import rest.token.TokenREST;

@Path("/")
public class Service {

    @Inject
    @Built
    private IAuthorizer authorizer;

    @Inject
    @Built
    private IRegistrator registrator;

    @Inject
    @Built
    private IOrdersGetter ordersGetter;

    @Inject
    @Built
    private IOrdersPoster ordersPoster;

    @Inject
    @Built
    private IOrdersDeleter ordersDeleter;

    @Inject
    @Built
    private IItemsGetter itemsGetter;

    // authorization
    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(String loginPasswordJSON, @Context HttpHeaders httpHeaders) {
        Jsonb jsonb = JsonbBuilder.create();
        User user;
        String token;
        String resultJSON;

        try {
            try {
                user = jsonb.fromJson(loginPasswordJSON, User.class);
                token = httpHeaders.getHeaderString("Token");
                if (token.equals("") || token == null) { // if user doesnt have token
                    if (!authorizer.authorize(user)) { // if invalid login/password
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }
                    // authorized
                    token = TokenREST.generateToken(user.getLogin());
                } else {
                    // user has token
                    // check if token is correct
                    if (!TokenREST.checkToken(user.getLogin(), token)) { // if invalid token
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }
                }
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            resultJSON = jsonb.toJson(token);

        } catch (JsonbException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
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
        User user;

        try {
            try {
                user = jsonb.fromJson(loginPasswordJSON, User.class);
            } catch (JsonbException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            if (!registrator.register(user)) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @GET
    @TokenRequired
    @Path("/orders")
    @Produces("application/json")
    public Response getOrdersData(@Context HttpHeaders httpHeaders) {
        Jsonb jsonb = JsonbBuilder.create();
        String login = httpHeaders.getHeaderString("Name");
        String resultJSON;

        try {
            resultJSON = jsonb.toJson(ordersGetter.findAllByUsername(login));
            return Response.ok(resultJSON).build();
        } catch (JsonbException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @TokenRequired
    @Path("/orders")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response createNewOrder(@Context HttpHeaders httpHeaders) {
        String login = httpHeaders.getHeaderString("Name");
        Integer itemid = Integer.valueOf(httpHeaders.getHeaderString("itemid"));
        String itemType = httpHeaders.getHeaderString("itemType");
        String itemCount = httpHeaders.getHeaderString("itemCount");

        try {
            ordersPoster.addNewOrder(login, itemid, itemCount, itemType);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok("Order added").build();
    }

    @DELETE
    @TokenRequired
    @Path("/orders")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response deleteOrder(@Context HttpHeaders httpHeaders) {
        int id = Integer.valueOf(httpHeaders.getHeaderString("id"));

        try {
            ordersDeleter.deleteById(id);
            return Response.ok("Order deleted").build();
        } catch (JsonbException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET // get type=...
    @Path("/items")
    @TokenRequired
    @Consumes("application/json")
    @Produces("application/json")
    public Response getItemByType(@Context HttpHeaders httpHeaders) {
        Jsonb jsonb = JsonbBuilder.create();
        String type = httpHeaders.getHeaderString("type");

        String resultJSON;
        try {
            resultJSON = jsonb.toJson(itemsGetter.getAllByType(type));
            return Response.ok(resultJSON).build();
        } catch (JsonbException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
