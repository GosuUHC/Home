package rest.interceptor;

import java.io.IOException;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import rest.token.TokenREST;

@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String login = requestContext.getHeaderString("Name");
        String token = requestContext.getHeaderString("Token");

        if (token.equals("") || token == null) { // if user doesnt have token
            throw new NotAuthorizedException("Authorization could not validate token: " + token);
        }
        // user has token
        // check if token is correct
        if (!TokenREST.checkToken(login, token)) { // if invalid token
            throw new NotAuthorizedException("Exception while trying to check token");
        }
    }
}