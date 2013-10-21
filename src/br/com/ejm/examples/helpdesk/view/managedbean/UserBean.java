package br.com.ejm.examples.helpdesk.view.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ejm.examples.helpdesk.bean.User;

@ManagedBean(name = "userMb")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{managerLocatorMb}")
	private HelpDeskManagerLocatorBean managerLocator;

	@ManagedProperty(value = "#{chatMb}")
	private ChatBean chat;

	private User user = new User();

	private boolean authenticated;

	public void setManagerLocator(HelpDeskManagerLocatorBean managerLocator) {
		this.managerLocator = managerLocator;
	}

	public void setChat(ChatBean chat) {
		this.chat = chat;
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
		managerLocator.getManager().unregisterUser(user);
		user = new User();
		setAuthenticated(false);
	}

	public int getUserPosition() {
		return managerLocator.getManager().getUserPosition(user) + 1;
	}

	public void startChat() {
		managerLocator.getManager().registerUser(user);
		chat.reset(user.getEmail(), user.getName());
		System.out.println("Entrando no Chat: " + user.getEmail());
	}
}
