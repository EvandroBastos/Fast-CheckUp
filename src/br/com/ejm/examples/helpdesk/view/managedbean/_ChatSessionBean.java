package br.com.ejm.examples.helpdesk.view.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ejm.examples.helpdesk.bean.Chat;

@ManagedBean(name = "chatSessionMb")
@SessionScoped
public class _ChatSessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{managerLocatorMb}")
	private HelpDeskManagerLocatorBean managerLocator;

	private String chatId;

	private String userName;

	private int lastMessage = 0;

	public void setManagerLocator(HelpDeskManagerLocatorBean managerLocator) {
		this.managerLocator = managerLocator;
	}

	public void reset(String chatId, String userName) {
		lastMessage = 0;
		this.chatId = chatId;
		this.userName = userName;
	}

	public String getChatId() {
		return chatId;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isChatReady() {
		return managerLocator.getManager().hasChat(getChatId());
	}

	public int getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(int lastMessage) {
		this.lastMessage = lastMessage;
	}

	public Chat getChat() {
		return managerLocator.getManager().getChat(getChatId());
	}

	public void close() {
		managerLocator.getManager().closeChat(getChatId());
		reset(null, null);
	}

}
