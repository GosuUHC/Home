package backend.rest.path;

import java.util.HashMap;
import java.util.Map;

import backend.builder.Built;
import backend.model.interfaces.async.INotificationsCounterAsync;
import backend.model.interfaces.async.INotificationsUpdaterAsync;
import backend.model.interfaces.items.IItemsGetter;
import backend.model.interfaces.orders.IOrdersDeleter;
import backend.model.interfaces.orders.IOrdersGetter;
import backend.model.interfaces.orders.IOrdersPoster;
import backend.model.interfaces.orders.IOrdersUpdater;
import backend.model.pojo.Order;
import backend.model.pojo.OrderStatus;
import backend.model.pojo.Order.OrderBuilder;
import backend.other.implementation.authentication.User;
import backend.other.interfaces.IAuthorizer;
import backend.other.interfaces.IRegistrator;
import backend.rest.interceptor.TokenRequired;
import backend.rest.token.ITokenManager;
import backend.websocket.Notification;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
    private IOrdersUpdater ordersUpdater;

    @Inject
    @Built
    private IItemsGetter itemsGetter;

    @Inject
    private ITokenManager tokenManager;

    @Inject
    private INotificationsCounterAsync notificationsCounterAsync;

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
        String userRole = "";

        try {
            try {
                user = jsonb.fromJson(loginPasswordJSON, User.class);
                token = requestContext.getHeaderString("Token");
                if (token == null || token.equals("") || token.equals("undefined")
                        || token.length() != ITokenManager.TOKEN_LENGTH) { // if user doesnt have token
                    if (!authorizer.authorize(user)) { // if invalid login/password
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }

                    userRole = authorizer.getUserRole(user);
                    if (userRole == null || userRole.equals("")) {
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }

                    // authorized
                    token = tokenManager.generateToken(user.getLogin(), userRole);

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

            Map<String, String> tokenAndRole = new HashMap<>();
            tokenAndRole.put("token", token);
            tokenAndRole.put("role", userRole);

            resultJSON = jsonb.toJson(tokenAndRole);

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
                user.setRole("user");
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
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @TokenRequired
    @Path("/orders")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response createOrder(@Context ContainerRequestContext requestContext) {
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
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.ok("Order added!!").build();

    }

    @PATCH
    @TokenRequired
    @Path("/orders/{orderid}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response patchOrderStatus(@PathParam("orderid") Integer orderid, JsonObject orderStatus,
            @Context ContainerRequestContext requestContext) {

        String checkedToken = requestContext.getProperty("checkToken").toString();
        String role = requestContext.getHeaderString("Role").toLowerCase();
        if (checkedToken.equals("false") || !role.equals("admin")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String newStatus = orderStatus.getString("status").toLowerCase();
        Order order = ordersGetter.findById(orderid);
        if (order == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Order changedOrder = new OrderBuilder(order.getUserLogin(), order.getitemType(), order.getItemid(),
                order.getItemCount())
                .setId(order.getId()).setItem(order.getItem()).setPrice()
                .setStatus(OrderStatus.getValue(newStatus)).build();
        ordersUpdater.updateOrder(order, changedOrder);

        INotificationsUpdaterAsync updater;
        String login = changedOrder.getUserLogin();
        updater = (int value) -> {
            Notification.send(login, String.valueOf(value));
        };
        notificationsCounterAsync.nextAndUpdate(updater, login);

        return Response.ok(changedOrder).build();
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
