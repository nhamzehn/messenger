package jo.edu.ju.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import jo.edu.ju.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {

		ErrorMessage errorMessage = new ErrorMessage(500, exception.getMessage(), "https://nhamzehn.com");

		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();

	}
}
