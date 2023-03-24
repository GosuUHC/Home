package backend.rest.interceptor;

import java.io.IOException;

import backend.rest.token.ITokenManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@TokenRequired
public class TokenInterceptor implements ContainerRequestFilter {

    @Inject
    private ITokenManager tokenManager;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getHeaderString("Token");
        requestContext.setProperty("checkToken", tokenManager.checkToken(token));
    }
}