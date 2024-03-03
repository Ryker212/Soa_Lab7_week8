package th.ac.ku.kps.eng.cpe.soa.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;


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