package br.com.ejm.examples.helpdesk.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author 04343650413
 * 
 */
public class Chat implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Message> messages = Collections
			.synchronizedList(new ArrayList<Message>());

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(String messageText, String author) {
		Message message = new Message();
		message.setContent(messageText);
		message.setAuthor(author);
		message.setDate(new Date());

		messages.add(message);
	}

	public Message getMessage(int id) {
		Message result = null;

		if (messages.size() > id) {
			result = messages.get(id);
		}

		return result;

	}

}
