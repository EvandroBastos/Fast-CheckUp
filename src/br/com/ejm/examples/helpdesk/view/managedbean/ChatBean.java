package br.com.ejm.examples.helpdesk.view.managedbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import br.com.ejm.examples.helpdesk.bean.Chat;
import br.com.ejm.examples.helpdesk.bean.Message;

@ManagedBean(name = "chatMb")
@SessionScoped
public class ChatBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{managerLocatorMb}")
	private HelpDeskManagerLocatorBean managerLocator;

	private String message;

	private String chatId;

	private String userName;

	/*
	 * =========================================================================
	 */

	private int lastMessage = 0;

	/*
	 * =========================================================================
	 */

	public void setManagerLocator(HelpDeskManagerLocatorBean managerLocator) {
		this.managerLocator = managerLocator;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * =========================================================================
	 */

	public int getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(int lastMessage) {
		this.lastMessage = lastMessage;
	}

	/*
	 * =========================================================================
	 */

	public boolean isReady() {
		return managerLocator.getManager().hasChat(getChatId());
	}

	public Chat getChat() {
		return managerLocator.getManager().getChat(getChatId());
	}

	/*
	 * =========================================================================
	 */

	public void retrieveLastMessage() {

		try {
			RequestContext context = RequestContext.getCurrentInstance();

			log("Ready?: " + isReady());

			if (isReady()) {

				int lastMessage = getLastMessage();
				Message message = getChat().getMessage(lastMessage);
				if (message != null) {
					context.addCallbackParam("newMessage", true);
					context.addCallbackParam("message", message);
					setLastMessage(lastMessage + 1);
				}
			} else {
				log("FECHANDO!!!!");
				context.addCallbackParam("close", true);
			}

		} catch (Throwable e) {
			log("ERRO: " + e.getMessage());
		}

	}

	/*
	 * =========================================================================
	 */

	public void close() {
		managerLocator.getManager().closeChat(getChatId());
		reset(null, null);
	}

	public void sendMessage() {
		getChat().addMessage(getMessage(), getUserName());
		setMessage("");
	}

	public List<Message> getMessages() {
		List<Message> result = null;
		if (isReady()) {
			result = getChat().getMessages();
		}
		return result;
	}

	public void reset(String chatId, String userName) {
		setLastMessage(0);
		setChatId(chatId);
		setUserName(userName);
		setMessage("");
	}

	private void log(String message) {
		System.out.println(Calendar.getInstance().getTime().toString() + " - "
				+ hashCode() + " [" + getUserName() + ", " + getChatId() + "] "
				+ message);
	}

}
