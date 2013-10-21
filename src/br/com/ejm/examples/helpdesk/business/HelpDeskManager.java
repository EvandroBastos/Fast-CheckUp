package br.com.ejm.examples.helpdesk.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ejm.examples.helpdesk.bean.Attendant;
import br.com.ejm.examples.helpdesk.bean.Chat;
import br.com.ejm.examples.helpdesk.bean.User;

public class HelpDeskManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<User> waitingQueue = Collections
			.synchronizedList(new ArrayList<User>());

	private List<Attendant> attendants = Collections
			.synchronizedList(new ArrayList<Attendant>());

	private Map<String, Chat> chats = Collections
			.synchronizedMap(new HashMap<String, Chat>());

	public HelpDeskManager() {
		System.out.println("MANAGER");
	}

	// public boolean hasAvailableAttendant() {
	// return getAvailableAttendants() > 0;
	// }

	public int getAvailableAttendants() {
		return attendants.size();
	}

	public int getWaitingUsers() {
		return waitingQueue.size();
	}

	// public boolean online() {
	// return true;
	// }

	public void registerUser(User user) {
		if (!waitingQueue.contains(user)) {
			waitingQueue.add(user);
		}
	}

	public void unregisterUser(User user) {
		waitingQueue.remove(user);
	}

	public void registerAttendant(Attendant attendant) {
		if (!attendants.contains(attendant)) {
			attendants.add(attendant);
		}
	}

	public void unregisterAttendant(Attendant attendant) {
		attendants.remove(attendant);
	}

	public User getNextUser() {
		return waitingQueue.remove(0);
	}

	public boolean hasChat(String chatId) {
		return chats.containsKey(chatId);
	}

	public Chat getChat(String chatId) {
		return chats.get(chatId);
	}

	public synchronized void createChat(String chatId) {
		chats.put(chatId, new Chat());
	}

	public int getUserPosition(User user) {
		return waitingQueue.indexOf(user);
	}

	public void closeChat(String chatId) {
		chats.remove(chatId);
	}
}
