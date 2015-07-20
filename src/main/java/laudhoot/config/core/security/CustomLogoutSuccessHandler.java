package laudhoot.config.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private String targetUrl = "/login";
	
	private String targetResponse = "/logoutSuccessfulResponse";
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        if (request.getHeader(HttpHeaders.ACCEPT).equals(MediaType.APPLICATION_JSON_VALUE)) {
            request.getRequestDispatcher(targetResponse).forward(request, response);
    	} else {
    		redirectStrategy.sendRedirect(request, response, targetUrl);
    	}
        
    }

}
