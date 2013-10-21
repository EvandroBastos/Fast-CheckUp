package br.com.ejm.examples.helpdesk.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ejm.examples.helpdesk.bean.Attendant;
import br.com.ejm.examples.helpdesk.bean.Chat;
import br.com.ejm.examples.helpdesk.bean.User;

public class HelpDeskDb {

	private static final HelpDeskDb INSTANCE = new HelpDeskDb();

	private List<User> waitingQueue = Collections
			.synchronizedList(new ArrayList<User>());

	private List<Attendant> attendants = Collections
			.synchronizedList(new ArrayList<Attendant>());

	private Map<String, Chat> chats = Collections
			.synchronizedMap(new HashMap<String, Chat>());

	private HelpDeskDb() {
	}

	public static HelpDeskDb instance() {
		return INSTANCE;
	}

	public int countAvailableAttendants() {
		return attendants.size();
	}

	public int countWaitingUsers() {
		return waitingQueue.size();
	}

	public void registerUser(User user) {
		if (!waitingQueue.contains(user)) {
			waitingQueue.add(user);
		}
	}

	public void unregisterUser(User user) {
		System.out.println("User unregistered: " + waitingQueue.remove(user));
	}

	public void registerAttendant(Attendant attendant) {
		if (!attendants.contains(attendant)) {
			attendants.add(attendant);
		}
	}

	public void unregisterAttendant(Attendant attendant) {
		if (attendants.contains(attendant)) {
			attendants.remove(attendant);
		}
	}

	public User removeUser(int index) {
		return waitingQueue.remove(index);
	}

	public Chat getChat(String chatId) {
		return chats.get(chatId);
	}

	public boolean hasChat(String chatId) {
		return chats.containsKey(chatId);
	}

	public void insertChat(String chatId, Chat chat) {
		chats.put(chatId, chat);
	}

	public int getUserIndex(User user) {
		return waitingQueue.indexOf(user);
	}

	public void deleteChat(String chatId) {
		System.out.println("Chat " + chatId + " destroyed: "
				+ chats.remove(chatId));
	}

}
