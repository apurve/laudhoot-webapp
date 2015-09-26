package laudhoot.core.services.security;

import laudhoot.web.domain.ClientTO;

public interface ClientDetailsService {

	public ClientTO createClient(ClientTO clientTo);
	
}
