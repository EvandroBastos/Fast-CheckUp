package br.com.ejm.examples.helpdesk.view.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ejm.examples.helpdesk.bean.Attendant;
import br.com.ejm.examples.helpdesk.bean.User;
import br.com.ejm.examples.helpdesk.business.HelpDeskManager;

@SessionScoped
@ManagedBean(name = "_attendantMb")
public class _CopyOfAttendantBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Attendant attendant = new Attendant();

	private boolean authenticated;

	@ManagedProperty(value = "#{managerLocatorMb}")
	private HelpDeskManagerLocatorBean managerLocator;

	@ManagedProperty(value = "#{chatSessionMb}")
	private _ChatSessionBean chatSession;

	public void setManagerLocator(HelpDeskManagerLocatorBean managerLocator) {
		this.managerLocator = managerLocator;
	}

	public void setChatSession(_ChatSessionBean chatSession) {
		this.chatSession = chatSession;
	}

	private HelpDeskManager getManager() {
		return managerLocator.getManager();
	}

	public Attendant getAttendant() {
		return attendant;
	}

	public void setAttendant(Attendant attendant) {
		this.attendant = attendant;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void signIn() {
		if (getAttendant().getUsername().equals(getAttendant().getPassword())) {
			getManager().registerAttendant(attendant);
			setAuthenticated(true);
		} else {
			FacesMessage message = new FacesMessage("Login/senha inválidos.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void signOut() {
		getManager().unregisterAttendant(attendant);
		attendant = new Attendant();
		setAuthenticated(false);
	}

	public void startChat() {
		User user = getManager().getNextUser();
		if (user != null) {
			String chatId = user.getEmail();
			getManager().createChat(chatId);
			chatSession.reset(chatId, attendant.getUsername());
			System.out.println("Chat created: " + chatId);
		}
	}
}
