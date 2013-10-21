package br.com.ejm.examples.helpdesk.view.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ejm.examples.helpdesk.bean.User;
import br.com.ejm.examples.helpdesk.business.HelpDeskManager;

@ManagedBean(name = "_userMb")
@SessionScoped
public class _CopyOfUserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{managerLocatorMb}")
	private HelpDeskManagerLocatorBean managerLocator;

	@ManagedProperty(value = "#{chatSessionMb}")
	private _ChatSessionBean chatSession;

	private HelpDeskManager manager;

	private User user = new User();

	private boolean authenticated;

	public void setManagerLocator(HelpDeskManagerLocatorBean managerLocator) {
		this.managerLocator = managerLocator;
	}

	public void setChatSession(_ChatSessionBean chatSession) {
		this.chatSession = chatSession;
	}

	private HelpDeskManager getManager() {

		if (manager == null && managerLocator != null) {
			manager = managerLocator.getManager();
		}
		return manager;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void signIn() {
		setAuthenticated(true);

	}

	public void signOut() {
		getManager().unregisterUser(user);
		user = new User();
		setAuthenticated(false);
	}

	public int getUserPosition() {
		return getManager().getUserPosition(user) + 1;
	}

	public void startChat() {
		if (user != null) {
			getManager().registerUser(user);
			chatSession.reset(user.getEmail(), user.getName());
			System.out.println("Entrando no Chat: " + user.getEmail());
		}
	}
}
