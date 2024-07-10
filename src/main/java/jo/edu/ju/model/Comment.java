package jo.edu.ju.model;

import java.time.LocalDate;

public class Comment {

	private Long id;
	private String comment;
	private LocalDate created;
	private String author;

	public Comment() {

	}

	public Comment(Long id, String comment, String author) {
		this.id = id;
		this.comment = comment;
		this.created = LocalDate.now();
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", created=" + created + ", author=" + author + "]";
	}

}
