package th.ac.ku.kps.eng.cpe.soa.filter;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {
		String authCredentials = containerRequest.getHeaderString(HttpHeaders.AUTHORIZATION);
		AuthService authsvc = new AuthService();
		boolean authStatus = authsvc.authenticate(authCredentials);
		if (!authStatus) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
	}
}