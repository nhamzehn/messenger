package jo.edu.ju.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
// import javax.ws.rs.ext.Provider;

import jo.edu.ju.model.ErrorMessage;

// @Provider Comment for disable this class
public class DataNotFoundException implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException exception) {

		ErrorMessage errorMessage = new ErrorMessage(404, exception.getMessage(), "https://nhamzehn.com");

		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();

	}
}
