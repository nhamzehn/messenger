package jo.edu.ju.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jo.edu.ju.model.Message;

public class MessageService {

	private static Map<Long, Message> messages = new HashMap<>();

	public MessageService() {
		messages.put(1L, new Message(1, "Hello World", "Ali"));
		messages.put(2L, new Message(2, "Hello Jersy", "Sami"));
		messages.put(3L, new Message(3, "Hello Fadi", "Fadi"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<Message>();
		for (Message message : messages.values()) {
			if(message.getCreated().getYear() == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> list = new ArrayList<Message>(messages.values());
		return list.subList(start, size);
	}

	public Message getMessage(Long id) {
		return messages.get(id);
	}

	public Message addMessage(Message message) {
		message.setId((long) (messages.size() + 1));
		messages.put(message.getId(), message);

		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(Long id) {
		return messages.remove(id);
	}

}
