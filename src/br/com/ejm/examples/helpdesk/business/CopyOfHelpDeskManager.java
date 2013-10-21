package br.com.ejm.examples.helpdesk.business;

import java.io.Serializable;

import br.com.ejm.examples.helpdesk.bean.Attendant;
import br.com.ejm.examples.helpdesk.bean.Chat;
import br.com.ejm.examples.helpdesk.bean.User;
import br.com.ejm.examples.helpdesk.persistence.HelpDeskDb;

public class CopyOfHelpDeskManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private HelpDeskDb db = HelpDeskDb.instance();

	public CopyOfHelpDeskManager() {
		System.out.println("MANAGER");
	}

	public boolean hasAvailableAttendant() {
		return getAvailableAttendants() > 0;
	}

	public int getAvailableAttendants() {
		return db.countAvailableAttendants();
	}

	public int getWaitingUsers() {
		return db.countWaitingUsers();
	}

	public boolean online() {
		return true;
	}

	public void registerUser(User user) {
		db.registerUser(user);
	}

	public void unregisterUser(User user) {
		db.unregisterUser(user);
	}

	public void registerAttendant(Attendant attendant) {
		db.registerAttendant(attendant);
	}

	public void unregisterAttendant(Attendant attendant) {
		db.unregisterAttendant(attendant);
	}

	public User getNextUser() {
		return db.removeUser(0);
	}

	public Chat getChat(String chatId) {
		return db.getChat(chatId);
	}

	public boolean hasChat(String chatId) {
		return db.hasChat(chatId);
	}

	public synchronized void createChat(String chatId) {
		Chat chat = new Chat();
		db.insertChat(chatId, chat);
	}

	public int getUserPosition(User user) {
		return db.getUserIndex(user) + 1;
	}

	public void closeChat(String chatId) {
		db.deleteChat(chatId);
	}
}
