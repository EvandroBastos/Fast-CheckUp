package br.com.ejm.examples.helpdesk.view.managedbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import br.com.ejm.examples.helpdesk.bean.Message;

@ManagedBean(name = "_chatMb")
@SessionScoped
public class _CopyOfChatBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{chatSessionMb}")
	private _ChatSessionBean chatSession;

	private String message;

	public void setChatSession(_ChatSessionBean chatSession) {
		this.chatSession = chatSession;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isReady() {
		return chatSession.isChatReady();
	}

	public void retrieveLastMessage() {

		try {
			RequestContext context = RequestContext.getCurrentInstance();

			log("Ready?: " + chatSession.isChatReady());

			if (chatSession.isChatReady()) {

				int lastMessage = chatSession.getLastMessage();
				Message message = chatSession.getChat().getMessage(lastMessage);
				if (message != null) {
					context.addCallbackParam("ok", true);
					context.addCallbackParam("message", message);
					chatSession.setLastMessage(lastMessage + 1);
				}
			} else {
				log("FECHANDO!!!!");
				context.addCallbackParam("close", true);
			}

		} catch (Throwable e) {
			log("ERRO: " + e.getMessage());
		}

	}

	public void close() {
		chatSession.close();
		setMessage("");
	}

	public void sendMessage() {
		chatSession.getChat().addMessage(getMessage(),
				chatSession.getUserName());
		setMessage("");
	}

	public List<Message> getMessages() {
		List<Message> result = null;
		if (chatSession.getChat() != null) {
			result = chatSession.getChat().getMessages();
		}
		return result;
	}

	private void log(String message) {
		System.out.println(Calendar.getInstance().getTime().toString() + " - "
				+ hashCode() + " [" + chatSession.getUserName() + ", "
				+ chatSession.getChatId() + "] " + message);
	}

}
