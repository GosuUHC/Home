package backend.rest.path;

import backend.builder.Built;
import backend.model.interfaces.delete.IOrdersDeleter;
import backend.model.interfaces.get.IItemsGetter;
import backend.model.interfaces.get.IOrdersGetter;
import backend.model.interfaces.post.IOrdersPoster;
import backend.other.implementation.authentication.User;
import backend.other.interfaces.IAuthorizer;
import backend.other.interfaces.IRegistrator;
import backend.rest.interceptor.TokenRequired;
import backend.rest.token.ITokenManager;
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
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

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

    @Inject
    private ITokenManager tokenManager;

    // authorization
    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(String loginPasswordJSON, @Context ContainerRequestContext requestContext) {
        Jsonb jsonb = JsonbBuilder.create();
        User user;
        String token;
        String resultJSON;

        try {
            try {
                user = jsonb.fromJson(loginPasswordJSON, User.class);
                token = requestContext.getHeaderString("Token");
                if (token.equals("") || token == null || token.equals("undefined")) { // if user doesnt have token
                    if (!authorizer.authorize(user)) { // if invalid login/password
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }
                    // authorized
                    token = tokenManager.generateToken(user.getLogin());

                } else {
                    // user has token
                    // check if token is correct
                    if (!tokenManager.checkToken(token)) { // if invalid token
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
                return Response.status(Response.Status.BAD_REQUEST).build();
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
    public Response getOrdersData(@Context ContainerRequestContext requestContext) {
        String checkedToken = requestContext.getProperty("checkToken").toString();
        if (checkedToken.equals("false")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Jsonb jsonb = JsonbBuilder.create();
        String login = requestContext.getHeaderString("Name");
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
    public Response createNewOrder(@Context ContainerRequestContext requestContext) {
        String checkedToken = requestContext.getProperty("checkToken").toString();
        if (checkedToken.equals("false")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String login = requestContext.getHeaderString("Name");
        Integer itemid = Integer.valueOf(requestContext.getHeaderString("itemid"));
        String itemType = requestContext.getHeaderString("itemType");
        String itemCount = requestContext.getHeaderString("itemCount");

        try {
            ordersPoster.addNewOrder(login, itemid, itemCount, itemType);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok("Order added!!").build();
    }

    @DELETE
    @TokenRequired
    @Path("/orders")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response deleteOrder(@Context ContainerRequestContext requestContext) {
        String checkedToken = requestContext.getProperty("checkToken").toString();
        if (checkedToken.equals("false")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        int id = Integer.valueOf(requestContext.getHeaderString("id"));

        try {
            ordersDeleter.deleteById(id);
            return Response.ok("Order deleted").build();
        } catch (JsonbException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/items")
    @TokenRequired
    @Consumes("application/json")
    @Produces("application/json")
    public Response getItemByType(@Context ContainerRequestContext requestContext) {
        String checkedToken = requestContext.getProperty("checkToken").toString();
        if (checkedToken.equals("false")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Jsonb jsonb = JsonbBuilder.create();
        String type = requestContext.getHeaderString("type");

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
