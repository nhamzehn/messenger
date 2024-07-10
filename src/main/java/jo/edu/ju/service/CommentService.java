package jo.edu.ju.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jo.edu.ju.model.Comment;
import jo.edu.ju.model.Message;


public class CommentService {

	private Map<Long, Message> messages = new HashMap<Long, Message>();
	
	public CommentService() {
		messages.put(1L, new Message(1, "Hello World", "Ali"));
		messages.put(2L, new Message(2, "Hello Jersy", "Sami"));
		messages.put(3L, new Message(3, "Hello Fadi", "Fadi"));
		
		Map<Long, Comment> comments = new HashMap<Long, Comment>();
		comments.put(1L, new Comment(1L, "Hello", "Hamzeh"));
		comments.put(2L, new Comment(2L, "Hi", "Sami"));
		comments.put(3L, new Comment(3L, "Hi Ali", "Fadi"));
		
		
		messages.get(1L).setComments(comments);
	}

	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}

	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId((long) (comments.size() + 1));
		comments.put(comment.getId(), comment);

		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment removeMessage(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
