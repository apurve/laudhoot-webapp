package laudhoot.config.web.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private String errorPage = "/login.jsp?authorization_error=true";
	
	private String errorResponse = "/loginError";
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (!response.isCommitted()) {
			
			// Put exception into request scope (perhaps of use to a view)
			request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException.getMessage());

			// Set the 403 status code.
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);

			if (request.getHeader(HttpHeaders.ACCEPT).equals(MediaType.APPLICATION_JSON_VALUE)) {
				// forward to error controller.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorResponse);
				dispatcher.forward(request, response);
			} else {
				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			}
		}
    }

}
