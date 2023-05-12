package backend.infrastructure.in.rest.interceptor;

import java.io.IOException;

import backend.infrastructure.in.rest.token.ITokenManager;
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
        String userRole = requestContext.getHeaderString("Role");
        requestContext.setProperty("checkToken", tokenManager.checkToken(token, userRole));
    }
}