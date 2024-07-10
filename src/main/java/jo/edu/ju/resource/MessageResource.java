package jo.edu.ju.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import jo.edu.ju.bean.MessageFilterBean;
import jo.edu.ju.model.Message;
import jo.edu.ju.service.MessageService;

@Path("/messages")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {

		if (filterBean.getYear() > 0)
			return messageService.getAllMessagesForYear(filterBean.getYear());

		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0)
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());

		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		if (message == null) {
			System.out.println("Message not exists");
			throw new RuntimeException("Message not exists");
		}

		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForcomment(uriInfo, message), "comments");

		return message;
	}

//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Message addMesage(Message message) {
//		return messageService.addMessage(message);
//	}

	@POST
	public Response addMesage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());

		System.out.println("Absolute Path: " + uriInfo.getAbsolutePath());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		System.out.println("id: " + id);
		messageService.removeMessage(id);
		System.out.println(messageService.getAllMessages());
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uriString = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId()))
				.build().toString();
		return uriString;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uriString = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build()
				.toString();
		return uriString;
	}

	private String getUriForcomment(UriInfo uriInfo, Message message) {
		String uriString = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource").path(CommentResource.class)
				.resolveTemplate("messageId", message.getId()).build().toString();
		return uriString;
	}

}
