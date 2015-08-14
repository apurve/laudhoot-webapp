package laudhoot.config.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private String failurePage = "/login?error";
	
	private String failureResponse = "";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
        if (failurePage == null) {
            logger.debug("No failure URL set, sending 401 Unauthorized error");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
        } else {
            saveException(request, exception);
            if (isUseForward()) {
            	if (request.getHeader(HttpHeaders.ACCEPT).equals(MediaType.APPLICATION_JSON_VALUE)) {
            		logger.debug("Forwarding to " + failureResponse);
                    request.getRequestDispatcher(failureResponse).forward(request, response);
            	} else {
            		logger.debug("Forwarding to " + failurePage);
                    request.getRequestDispatcher(failurePage).forward(request, response);
            	}
            } else {
            	if (request.getHeader(HttpHeaders.ACCEPT).equals(MediaType.APPLICATION_JSON_VALUE)) {
            		logger.debug("Redirecting to " + failureResponse);
            		getRedirectStrategy().sendRedirect(request, response, failureResponse);
            	} else {
            		logger.debug("Redirecting to " + failurePage);
                    getRedirectStrategy().sendRedirect(request, response, failurePage);
            	}
            }
        }
	}
}
