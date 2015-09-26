package laudhoot.core.services.security;

import laudhoot.config.core.OAuth2SecutiryConfig;
import laudhoot.core.domain.security.OauthClientDetails;
import laudhoot.core.repository.security.ClientDetailsRepository;
import laudhoot.core.util.ClientDetailsBuilder;
import laudhoot.core.util.validation.LaudhootExceptionUtils;
import laudhoot.core.util.validation.LaudhootValidator;
import laudhoot.web.domain.ClientTO;
import laudhoot.web.util.ServiceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	
	@Autowired
	private LaudhootValidator validator;
	
	@Override
	public ClientTO createClient(ClientTO clientTo) {
		LaudhootExceptionUtils.isNotNull(clientTo,
				"Client details transfer object cannot be null.");
		validator.validate(clientTo, clientTo.validation,
				ServiceRequest.CreateClient.class);
		if (clientTo.validation.hasErrors()) {
			return clientTo;
		}
		
		ClientDetailsBuilder builder = new ClientDetailsBuilder(clientTo.getClientId());
		builder.resourceIds(OAuth2SecutiryConfig.LAUDHOOT_RESOURCE_ID)
			.authorizedGrantTypes(OAuth2SecutiryConfig.GRANT_TYPE_CLIENT)
			.authorities("ROLE_CLIENT")
			.scopes("read", "write")
			.secret("secret") //TODO - password generation strategy
			.autoApprove(false)
			.accessTokenValiditySeconds(OAuth2SecutiryConfig.ACCESS_TOKEN_VALIDITY_SECONDS)
			.refreshTokenValiditySeconds(OAuth2SecutiryConfig.REFRESH_TOKEN_VALIDITY_SECONDS);
		
		OauthClientDetails clientDetails = builder.build();
		clientDetailsRepository.save(clientDetails);
		clientTo.setclientSecret(clientDetails.getClientSecret());
		return clientTo;
	}

}
