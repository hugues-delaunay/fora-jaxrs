package io.robusta.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Topic implements Serializable, HasTag, Comparable<Topic> {

	private static final long serialVersionUID = 8551283529065516442L;

	
	long id;
	String title;
		
	List<Comment> comments = new ArrayList<Comment>();
	List<Tag> tags = new ArrayList<Tag>();
	User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	public void addComments(Comment... comments){
		Collections.addAll(this.comments, comments);		
	}

	// Using id and title
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public boolean isTagged() {

		return this.tags != null && !this.tags.isEmpty();
	}

	@Override
	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private void writeObject(java.io.ObjectOutputStream stream)
			throws IOException {
		System.out.println(">>>> Making serialization");
		stream.writeLong(id);
	
		stream.writeObject(title);

	}

	private void readObject(java.io.ObjectInputStream stream)
			throws IOException, ClassNotFoundException {
		System.out.println(">>>> Unserialization");
		id = stream.readLong();
	
		title = (String) stream.readObject();
	}

	@Override
	public int compareTo(Topic t) {		
		Integer size = this.comments.size();
		return size.compareTo(t.getComments().size());
	}

	
	@Override
	public String toString() {
		return this.title;
	}
}
