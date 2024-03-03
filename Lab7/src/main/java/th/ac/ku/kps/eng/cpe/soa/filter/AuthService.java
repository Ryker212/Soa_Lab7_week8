package th.ac.ku.kps.eng.cpe.soa.filter;

import java.io.IOException;
import java.util.StringTokenizer;
import th.ac.ku.kps.eng.cpe.soa.dao.CustomerDAO;
import th.ac.ku.kps.eng.cpe.soa.model.Customer;
import java.util.Base64;

public class AuthService {
	public boolean authenticate(String authCredentials) {
		if (null == authCredentials)
			return false;
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		CustomerDAO c = new CustomerDAO();
		Customer cus = c.findCustomer(username);
		boolean authenticationStatus = cus.getUsername().equals(username) && cus.getPwd().equals(password);
		return authenticationStatus;
	}
}