package jo.edu.ju.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {

	private long id;
	private String message;
	private LocalDate created;
	private String author;
	private Map<Long, Comment> comments = new HashMap<Long, Comment>();
	private List<Link> links = new ArrayList<Link>();
	
	public Message() {
		
	}

	public Message(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.created = LocalDate.now();
		this.author = author;
		
		// For testing filter by year
		if(id == 3)
			this.created = LocalDate.of(2020, 12, 1);
	}
	
	public void addLink(String url, String rel) {
		links.add(new Link(url, rel));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	
	

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", created=" + created + ", author=" + author + "]";
	}
	
	

}
