package backend.rest.interceptor;

import java.io.IOException;

import backend.rest.token.ITokenManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Inject
    private ITokenManager tokenManager;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String login = requestContext.getHeaderString("Name");
        String token = requestContext.getHeaderString("Token");

        if (token.equals("") || token == null) { // if user doesnt have token
            throw new NotAuthorizedException("Authorization could not validate token: " + token);
        }
        // user has token
        // check if token is correct
        if (!tokenManager.checkToken(login, token)) { // if invalid token
            throw new NotAuthorizedException("Exception while trying to check token");
        }
    }
}