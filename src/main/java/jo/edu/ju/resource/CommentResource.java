package jo.edu.ju.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import jo.edu.ju.model.Comment;
import jo.edu.ju.model.ErrorMessage;
import jo.edu.ju.model.Message;
import jo.edu.ju.service.CommentService;
import jo.edu.ju.service.MessageService;


@Path("/")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	MessageService messageService = new MessageService(); 
	CommentService commentService = new CommentService();

	@GET
	public List<Comment> getComments(@PathParam("messageId") long messageId) {
		System.out.println(commentService.getAllComments(messageId));
		return commentService.getAllComments(messageId);
	}

	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		
		ErrorMessage errorMessage = new ErrorMessage(404, "Not found", "https://nhamzehn.com");

		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();

		
		Message message = messageService.getMessage(messageId);
		if(message == null) {
			throw new WebApplicationException(response);
		}
		
		Comment comment = commentService.getComment(messageId, commentId);
		if(comment == null) {
			throw new NotFoundException(response);			
		}
		
		return comment;
	}

	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId,
			Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}

	@DELETE
	@Path("/{commentId}")
	public Comment deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.removeMessage(messageId, commentId);
	}

}
